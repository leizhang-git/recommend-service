package com.recommend.provider.service.cimpl;

import com.recommend.consumer.dao.DocumentDataDao;
import com.recommend.consumer.domain.dto.DocumentDataDTO;
import com.recommend.consumer.domain.pojo.documentData.DocumentBinary;
import com.recommend.consumer.service.DocumentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/25 17:45
 */
@Service
public class DocumentDataServiceImpl implements DocumentDataService {

    @Autowired
    private DocumentDataDao documentDataDao;


    @Override
    public List<DocumentDataDTO> getAllData() {
        return null;
    }

    @Override
    public List<DocumentDataDTO> getAllData(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<DocumentDataDTO> searchDataByName(String name) {
        return null;
    }

    @Override
    public List<DocumentDataDTO> searchDataByDFormat(String dFormat) {
        return null;
    }

    @Override
    public List<DocumentDataDTO> searchDataByDClass(String dClass) {
        return null;
    }

    @Override
    public List<DocumentDataDTO> searchDataByCreateBy(String createBy) {
        return null;
    }

    @Override
    public List<DocumentDataDTO> searchDataByNational(String national) {
        return null;
    }

    @Override
    public List<DocumentDataDTO> searchDataByNameAndDClass(String name, String dClass) {
        return null;
    }

    @Override
    public List<DocumentDataDTO> searchDataByDClassAndDFormat(String dClass, String dFormat) {
        return null;
    }

    @Override
    public List<DocumentDataDTO> searchDataByContent(String content) {
        return null;
    }

    @Override
    public List<DocumentDataDTO> searchDataByIntro(String intro) {
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public boolean saveDocumentData(DocumentDataDTO documentDataDTO, DocumentBinary documentData) {
        return false;
    }
}
