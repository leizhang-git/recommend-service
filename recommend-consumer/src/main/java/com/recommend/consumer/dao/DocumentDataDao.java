package com.recommend.consumer.dao;

import com.recommend.consumer.domain.dto.DocumentDataDTO;
import com.recommend.consumer.domain.pojo.documentData.DocumentData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/25 17:47
 */
@Mapper
public interface DocumentDataDao {

    @Select("select * from `document_data`")
    public List<DocumentData> findAllDocuments();

    @Select("select * from `document_data`")
    public List<DocumentData> searchDataByName();

    @Select("select * from `document_data` where `name` = #{name}")
    List<DocumentDataDTO> searchDataByName(String name);

    @Select("select * from `document_data` where `dformat` = #{dFormat}")
    List<DocumentDataDTO> searchDataByDFormat(String dFormat);

    @Select("select * from `document_data` where `dclass` = #{dClass}")
    List<DocumentDataDTO> searchDataByDClass(String dClass);

    @Select("select * from `document_data` where `createBy` = #{createBy}")
    List<DocumentDataDTO> searchDataByCreateBy(String createBy);

    @Select("select * from `document_data` where `author_national` = #{national}")
    List<DocumentDataDTO> searchDataByNational(String national);

    @Select("select * from `document_data` where `name` = #{name} and `dclass` = #{dClass}")
    List<DocumentDataDTO> searchDataByNameAndDClass(String name, String dClass);

    @Select("select * from `document_data` where `dclass` = #{dClass} and `dformat` = #{dFormat}")
    List<DocumentDataDTO> searchDataByDClassAndDFormat(String dClass, String dFormat);

    @Delete("delete from `document_data` where `id` = #{id}")
    boolean deleteById(String id);

    @Insert("insert into `document_data` values " +
            "(#{documentData.id}, #{documentData.name}, #{documentData.remoteAddr}, #{documentData.localAddr}, " +
            "#{documentData.author}, #{documentData.authorNational}, #{documentData.intro}, #{documentData.dFormat}," +
            "#{documentDara.dClass}, #{documentData.createBy}, #{documentData.createDate}, #{documentData.lastModifiedBy}), #{documentData.lastModifiedDate}")
    boolean saveDocumentData(DocumentData documentData);
}
