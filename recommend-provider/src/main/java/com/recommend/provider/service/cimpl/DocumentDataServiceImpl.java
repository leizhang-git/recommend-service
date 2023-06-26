package com.recommend.provider.service.cimpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.recommend.consumer.dao.DocumentBinaryDao;
import com.recommend.consumer.dao.DocumentDataDao;
import com.recommend.consumer.domain.dto.DocumentDataDTO;
import com.recommend.consumer.domain.pojo.documentData.DocumentBinary;
import com.recommend.consumer.domain.pojo.documentData.DocumentData;
import com.recommend.consumer.service.DocumentDataService;
import com.recommend.consumer.util.ContextUtil;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/25 17:45
 */
@Service
public class DocumentDataServiceImpl implements DocumentDataService {

    @Autowired
    private DocumentDataDao documentDataDao;

    @Autowired
    private DocumentBinaryDao documentBinaryDao;

    @Override
    public List<DocumentDataDTO> getAllData() {
        return transitionDocumentDTO(documentDataDao.findAllDocuments());
    }

    @Override
    public List<DocumentDataDTO> getAllData(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return transitionDocumentDTO(documentDataDao.findAllDocuments());
    }

    @Override
    public List<DocumentDataDTO> searchDataByName(String name) {
        return transitionDocumentDTO(documentDataDao.searchDataByName(name));
    }

    @Override
    public List<DocumentDataDTO> searchAll(String input) {
        List<DocumentDataDTO> result = Lists.newArrayList();
        if(StrUtil.isBlank(input)) {
            return result;
        }
        List<DocumentDataDTO> nDocumentDataDTOList = searchDataByName(input);
        List<DocumentDataDTO> cyDocumentDataDTOList = searchDataByCreateBy(input);
        List<DocumentDataDTO> dcDocumentDataDTOList = searchDataByDClass(input);
        List<DocumentDataDTO> dfDocumentDataDTOList = searchDataByDFormat(input);
        List<DocumentDataDTO> naDocumentDataDTOList = searchDataByNational(input);
        result.addAll(naDocumentDataDTOList);
        result.addAll(cyDocumentDataDTOList);
        result.addAll(dcDocumentDataDTOList);
        result.addAll(dfDocumentDataDTOList);
        result.addAll(nDocumentDataDTOList);
        return result.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<DocumentDataDTO> searchDataByDFormat(String dFormat) {
        return transitionDocumentDTO(documentDataDao.searchDataByDFormat(dFormat));
    }

    @Override
    public List<DocumentDataDTO> searchDataByDClass(String dClass) {
        return transitionDocumentDTO(documentDataDao.searchDataByDClass(dClass));
    }

    @Override
    public List<DocumentDataDTO> searchDataByCreateBy(String createBy) {
        return transitionDocumentDTO(documentDataDao.searchDataByCreateBy(createBy));
    }

    @Override
    public List<DocumentDataDTO> searchDataByNational(String national) {
        return transitionDocumentDTO(documentDataDao.searchDataByNational(national));
    }

    @Override
    public List<DocumentDataDTO> searchDataByNameAndDClass(String name, String dClass) {
        return transitionDocumentDTO(documentDataDao.searchDataByNameAndDClass(name, dClass));
    }

    @Override
    public List<DocumentDataDTO> searchDataByDClassAndDFormat(String dClass, String dFormat) {
        return transitionDocumentDTO(documentDataDao.searchDataByDClassAndDFormat(dClass, dFormat));
    }

    @Override
    public List<DocumentDataDTO> searchDataByContent(String content) {
        //这个比较困难，先不搞~
        return null;
    }

    @Override
    public List<DocumentDataDTO> searchDataByIntro(String intro) {
        //搞一个算法
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        return documentDataDao.deleteById(id);
    }

    @Override
    public boolean saveDocumentDataAndContext(DocumentDataDTO documentDataDTO, DocumentBinary documentBinary) {
        boolean b = documentDataDao.saveDocumentData(transitionDocument(documentDataDTO));
        boolean b1 = documentBinaryDao.saveDocumentBinary(documentBinary);
        return b && b1;
    }

    @Transactional
    @Override
    public DocumentDataDTO saveDocumentData(DocumentDataDTO documentDataDTO) {
        documentDataDTO.setId(UUID.randomUUID().toString());
        documentDataDTO.setCreateBy(StrUtil.isBlank(documentDataDTO.getCreateBy()) ? ContextUtil.getName() : documentDataDTO.getCreateBy());
        documentDataDTO.setCreateDate(Instant.now());
        documentDataDTO.setLastModifiedBy(StrUtil.isBlank(documentDataDTO.getLastModifiedBy()) ? ContextUtil.getName() : documentDataDTO.getLastModifiedBy());
        documentDataDTO.setLastModifiedDate(Instant.now());
        DocumentData documentData = transitionDocument(documentDataDTO);
        documentDataDao.saveDocumentData(documentData);
        return documentDataDTO;
    }

    public DocumentDataDTO transitionDocumentDTO(DocumentData data) {
        if(null == data) {
            return null;
        }
        DocumentDataDTO documentDataDTO = new DocumentDataDTO();
        BeanUtil.copyProperties(data, documentDataDTO);
        return documentDataDTO;
    }

    public List<DocumentDataDTO> transitionDocumentDTO(List<DocumentData> datas) {
        List<DocumentDataDTO> result = Lists.newArrayList();
        if(CollUtil.isNotEmpty(datas)) {
            List<DocumentDataDTO> documentDataDTOS = BeanUtil.copyToList(datas, DocumentDataDTO.class);
            result.addAll(documentDataDTOS);
        }
        return result;
    }

    public DocumentData transitionDocument(DocumentDataDTO dataDTO) {
        if(null == dataDTO) {
            return null;
        }
        DocumentData documentData = new DocumentData();
        BeanUtil.copyProperties(dataDTO, documentData);
        return documentData;
    }
}
