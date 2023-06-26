package com.recommend.consumer.service;

import com.recommend.consumer.domain.dto.DocumentDataDTO;
import com.recommend.consumer.domain.pojo.documentData.DocumentBinary;

import java.util.List;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/25 17:09
 */
public interface DocumentDataService {

    /**
     * 获取全部资源
     * @return
     */
    List<DocumentDataDTO> getAllData();

    /**
     * 获取全部资源(分页)
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<DocumentDataDTO> getAllData(Integer pageNum, Integer pageSize);

    /**
     * 根据名称搜索资源
     * @param name
     * @return
     */
    List<DocumentDataDTO> searchDataByName(String name);

    /**
     * 搜索
     * @param input
     * @return
     */
    List<DocumentDataDTO> searchAll(String input);

    /**
     * 根据格式搜索资源
     * @param dFormat
     * @return
     */
    List<DocumentDataDTO> searchDataByDFormat(String dFormat);

    /**
     * 根据类别搜索资源
     * @param dClass
     * @return
     */
    List<DocumentDataDTO> searchDataByDClass(String dClass);

    /**
     * 根据创建者搜索资源
     * @param createBy
     * @return
     */
    List<DocumentDataDTO> searchDataByCreateBy(String createBy);

    /**
     * 根据作者国籍搜索资源
     * @param national
     * @return
     */
    List<DocumentDataDTO> searchDataByNational(String national);


    /**
     * 根据名称+类别搜索资源
     * @param name
     * @Param dClass
     * @return
     */
    List<DocumentDataDTO> searchDataByNameAndDClass(String name, String dClass);

    /**
     * 根据类别+格式搜索资源
     * @param dClass
     * @param dFormat
     * @return
     */
    List<DocumentDataDTO> searchDataByDClassAndDFormat(String dClass, String dFormat);

    /**
     * 根据资源内容搜索（有些困难）
     * @param content
     * @return
     */
    List<DocumentDataDTO> searchDataByContent(String content);

    /**
     * 根据资源简介搜索（有些困难）
     * @param intro
     * @return
     */
    List<DocumentDataDTO> searchDataByIntro(String intro);

    /**
     * 根据id删除资源
     * @param id
     * @return
     */
    boolean deleteById(String id);

    /**
     * 保存资源
     * @param documentDataDTO
     * @param documentData
     * @return
     */
    boolean saveDocumentDataAndContext(DocumentDataDTO documentDataDTO, DocumentBinary documentData);

    /**
     * 保存资源
     * @param documentDataDTO
     * @return
     */
    DocumentDataDTO saveDocumentData(DocumentDataDTO documentDataDTO);
}
