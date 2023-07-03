package com.recommend.provider.service.cimpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.recommend.consumer.dao.DocumentBinaryDao;
import com.recommend.consumer.dao.DocumentDataDao;
import com.recommend.consumer.domain.dto.DocumentDataDTO;
import com.recommend.consumer.domain.dto.DocumentSearchDTO;
import com.recommend.consumer.domain.pojo.documentData.DocumentBinary;
import com.recommend.consumer.domain.pojo.documentData.DocumentData;
import com.recommend.consumer.service.DocumentDataService;
import com.recommend.consumer.util.ContextUtil;
import com.recommend.consumer.util.SystemTimeUtil;
import com.recommend.provider.domain.ByteFile;
import com.recommend.provider.util.FileUtil;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.time.Instant;
import java.util.ArrayList;
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

    public static final String HEADER_NAME = "名称";
    public static final String header_AUTHOR = "作者";
    public static final String header_AUTHOR_NATIONAL = "作者国籍";
    public static final String INTRO = "简介";
    public static final String DFORMAT = "文件格式";
    public static final String DCLASS = "文件类型";
    public static final String CREATEBY = "创建者";
    public static final String CREATEDATE = "创建时间";
    public static final String LASTMODIFIEDDATE = "最后修改时间";

    static List<String> titleNameList = new ArrayList<>();

    static {
        titleNameList.add(HEADER_NAME);
        titleNameList.add(header_AUTHOR);
        titleNameList.add(header_AUTHOR_NATIONAL);
        titleNameList.add(INTRO);
        titleNameList.add(DFORMAT);
        titleNameList.add(DCLASS);
        titleNameList.add(CREATEBY);
        titleNameList.add(CREATEDATE);
        titleNameList.add(LASTMODIFIEDDATE);
    }

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
    public Integer getCount() {
        return documentDataDao.getCount();
    }

    @Override
    public List<DocumentDataDTO> searchDataByName(String name) {
        if(StrUtil.isBlank(name)) {
            return CollUtil.newArrayList();
        }
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
    public boolean exportExcel(List<DocumentDataDTO> documentDataDTOs, HttpServletRequest request, HttpServletResponse response) {
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建sheet
        XSSFSheet sheet = wb.createSheet("文档资料");
        //设置全局默认宽高
        sheet.setDefaultColumnWidth(25);
        sheet.setDefaultRowHeight((short) 450);

        //创建单元格字体
        XSSFFont headerFont = wb.createFont();
        //设置字体加粗
        headerFont.setBold(true);

        //==========================Style处理
        //黄色单元格部分
        DefaultIndexedColorMap yellowColorMap = new DefaultIndexedColorMap();
        XSSFCellStyle yellowStyle = wb.createCellStyle();
        //设置颜色
        XSSFColor yellowColor = new XSSFColor(new java.awt.Color(255, 192, 0), yellowColorMap);
        yellowStyle.setFillForegroundColor(yellowColor);
        //填充
        yellowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //字体
        yellowStyle.setFont(headerFont);
        //居中对齐
        yellowStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直对其
        yellowStyle.setVerticalAlignment(VerticalAlignment.TOP);
        //加锁
        yellowStyle.setLocked(true);



        DefaultIndexedColorMap blueColorMap = new DefaultIndexedColorMap();
        XSSFCellStyle blueStyle = wb.createCellStyle();
        XSSFColor blueColor = new XSSFColor(new java.awt.Color(0, 176, 240), blueColorMap);
        blueStyle.setFillForegroundColor(blueColor);
        blueStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        blueStyle.setAlignment(HorizontalAlignment.CENTER);
        blueStyle.setVerticalAlignment(VerticalAlignment.TOP);
        blueStyle.setFont(headerFont);
        blueStyle.setLocked(true);
        //==========================锁定处理
        XSSFCellStyle unLockStyle = wb.createCellStyle();
        unLockStyle.setVerticalAlignment(VerticalAlignment.TOP);
        unLockStyle.setLocked(false);

        //处理首行,先暂时写死在代码里
        XSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < titleNameList.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(titleNameList.get(i));
            cell.setCellStyle(yellowStyle);
        }

        //赋值
        int count = 0;
        for (DocumentDataDTO documentDataDTO : documentDataDTOs) {
            Row valueRow = sheet.createRow(1);
            Cell cellHValue = valueRow.createCell(0);
            cellHValue.setCellValue(String.valueOf(count));
            cellHValue = valueRow.createCell(1);
            cellHValue.setCellValue(documentDataDTO.getName());
            cellHValue = valueRow.createCell(2);
            cellHValue.setCellValue(documentDataDTO.getAuthor());
            cellHValue = valueRow.createCell(3);
            cellHValue.setCellValue(documentDataDTO.getAuthorNational());
            cellHValue = valueRow.createCell(4);
            cellHValue.setCellValue(documentDataDTO.getIntro());
            cellHValue = valueRow.createCell(5);
            cellHValue.setCellValue(documentDataDTO.getDformat());
            cellHValue = valueRow.createCell(6);
            cellHValue.setCellValue(documentDataDTO.getDclass());
            cellHValue = valueRow.createCell(7);
            cellHValue.setCellValue(documentDataDTO.getCreateBy());
            cellHValue = valueRow.createCell(8);
            cellHValue.setCellValue(SystemTimeUtil.getTime(documentDataDTO.getCreateDate()));
            cellHValue = valueRow.createCell(9);
            cellHValue.setCellValue(SystemTimeUtil.getTime(documentDataDTO.getLastModifiedDate()));
            cellHValue.setCellStyle(unLockStyle);
        }

        //密码
        sheet.protectSheet("996");
        try (ServletOutputStream outputStream = response.getOutputStream()){
            response.setContentType("application/x-download");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition","attachment;filename="+ URLDecoder.decode("资料列表","UTF-8") + "." + "xlsx");
            wb.write(outputStream);
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean uploadDocument(MultipartFile file) throws IOException {
        ByteFile byteFile = FileUtil.uploadFile(file, file.getName());
        return false;
    }

    @Override
    public List<DocumentDataDTO> searchAll(DocumentSearchDTO documentDataDTO) {
        List<DocumentDataDTO> result = Lists.newArrayList();
        if(null == documentDataDTO) {
            return result;
        }
        if(StrUtil.isBlank(documentDataDTO.getName())
                && StrUtil.isBlank(documentDataDTO.getDclass())
                && StrUtil.isBlank(documentDataDTO.getDformat())
                && StrUtil.isBlank(documentDataDTO.getAuthorNational())) {
            return getAllData();
        }
        List<DocumentDataDTO> nDocumentDataDTOList = searchDataByName(documentDataDTO.getName());
        List<DocumentDataDTO> dcDocumentDataDTOList = searchDataByDClass(documentDataDTO.getDclass());
        List<DocumentDataDTO> dfDocumentDataDTOList = searchDataByDFormat(documentDataDTO.getDformat());
        List<DocumentDataDTO> naDocumentDataDTOList = searchDataByNational(documentDataDTO.getAuthorNational());
        result.addAll(naDocumentDataDTOList);
        result.addAll(dcDocumentDataDTOList);
        result.addAll(dfDocumentDataDTOList);
        result.addAll(nDocumentDataDTOList);
        if(CollUtil.isEmpty(result)) {
            return result;
        }
        return result.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<DocumentDataDTO> searchDataByDFormat(String dformat) {
        if(StrUtil.isBlank(dformat)) {
            return CollUtil.newArrayList();
        }
        return transitionDocumentDTO(documentDataDao.searchDataByDFormat(dformat));
    }

    @Override
    public List<DocumentDataDTO> searchDataByDClass(String dclass) {
        if(StrUtil.isBlank(dclass)) {
            return CollUtil.newArrayList();
        }
        return transitionDocumentDTO(documentDataDao.searchDataByDClass(dclass));
    }

    @Override
    public List<DocumentDataDTO> searchDataByCreateBy(String createBy) {
        if(StrUtil.isBlank(createBy)) {
            return CollUtil.newArrayList();
        }
        return transitionDocumentDTO(documentDataDao.searchDataByCreateBy(createBy));
    }

    @Override
    public List<DocumentDataDTO> searchDataByNational(String national) {
        if(StrUtil.isBlank(national)) {
            return CollUtil.newArrayList();
        }
        return transitionDocumentDTO(documentDataDao.searchDataByNational(national));
    }

    @Override
    public List<DocumentDataDTO> searchDataByNameAndDClass(String name, String dclass) {
        return transitionDocumentDTO(documentDataDao.searchDataByNameAndDClass(name, dclass));
    }

    @Override
    public List<DocumentDataDTO> searchDataByDClassAndDFormat(String dclass, String dformat) {
        return transitionDocumentDTO(documentDataDao.searchDataByDClassAndDFormat(dclass, dformat));
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
    public List<String> getAllDClass() {
        return documentDataDao.getAllDClass();
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
        if(!StrUtil.isBlank(documentDataDTO.getId())) {
            return updateDocumentData(documentDataDTO);
        }
        documentDataDTO.setId(UUID.randomUUID().toString());
        documentDataDTO.setCreateBy(StrUtil.isBlank(documentDataDTO.getCreateBy()) ? ContextUtil.getName() : documentDataDTO.getCreateBy());
        documentDataDTO.setCreateDate(Instant.now());
        documentDataDTO.setLastModifiedBy(StrUtil.isBlank(documentDataDTO.getLastModifiedBy()) ? ContextUtil.getName() : documentDataDTO.getLastModifiedBy());
        documentDataDTO.setLastModifiedDate(Instant.now());
        DocumentData documentData = transitionDocument(documentDataDTO);
        documentDataDao.saveDocumentData(documentData);
        return documentDataDTO;
    }

    @Override
    public DocumentDataDTO updateDocumentData(DocumentDataDTO documentDataDTO) {
        documentDataDTO.setCreateBy(StrUtil.isBlank(documentDataDTO.getCreateBy()) ? ContextUtil.getName() : documentDataDTO.getCreateBy());
        documentDataDTO.setCreateDate(Instant.now());
        documentDataDTO.setLastModifiedBy(StrUtil.isBlank(documentDataDTO.getLastModifiedBy()) ? ContextUtil.getName() : documentDataDTO.getLastModifiedBy());
        documentDataDTO.setLastModifiedDate(Instant.now());
        DocumentData documentData = transitionDocument(documentDataDTO);
        documentDataDao.updateDocumentData(documentData);
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
