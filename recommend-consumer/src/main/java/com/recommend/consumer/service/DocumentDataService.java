package com.recommend.consumer.service;

import com.recommend.consumer.domain.dto.DocumentDataDTO;
import com.recommend.consumer.domain.dto.DocumentSearchDTO;
import com.recommend.consumer.domain.dto.RouterDTO;
import com.recommend.consumer.domain.pojo.documentData.DocumentBinary;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
     * 获取总条数
     * @return
     */
    Integer getCount();

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
     * 导出Excel
     * @param documentDataDTOs
     * @return
     */
    boolean exportExcel(List<DocumentDataDTO> documentDataDTOs, HttpServletRequest request, HttpServletResponse response);

    /**
     * 上传资源
     * @param file
     * @return
     */
    boolean uploadDocument(MultipartFile file) throws IOException;

    /**
     * 搜索
     * @param documentDataDTO
     * @return
     */
    List<DocumentDataDTO> searchAll(DocumentSearchDTO documentDataDTO);

    /**
     * 根据格式搜索资源
     * @param dformat
     * @return
     */
    List<DocumentDataDTO> searchDataByDFormat(String dformat);

    /**
     * 根据类别搜索资源
     * @param dclass
     * @return
     */
    List<DocumentDataDTO> searchDataByDClass(String dclass);

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
     * @Param dclass
     * @return
     */
    List<DocumentDataDTO> searchDataByNameAndDClass(String name, String dclass);

    /**
     * 根据类别+格式搜索资源
     * @param dclass
     * @param dformat
     * @return
     */
    List<DocumentDataDTO> searchDataByDClassAndDFormat(String dclass, String dformat);

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
     * 获取所有类型
     * @return
     */
    List<String> getAllDClass();

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

    /**
     * 修改资源
     * @param documentDataDTO
     * @return
     */
    DocumentDataDTO updateDocumentData(DocumentDataDTO documentDataDTO);

    /**
     * 动态获取路由
     * @return
     */
    List<RouterDTO> getRouters();
}
