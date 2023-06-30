package com.recommend.consumer.dao;

import com.recommend.consumer.domain.pojo.documentData.DocumentData;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/25 17:47
 */
@Mapper
public interface DocumentDataDao {

    @Select("select * from `document_data`")
    List<DocumentData> findAllDocuments();

    @Select("select * from `document_data` where `name` like CONCAT('%', #{name}, '%')")
    List<DocumentData> searchDataByName(@Param("name") String name);

    @Select("select * from `document_data` where `dformat` like CONCAT('%', #{dFormat}, '%')")
    List<DocumentData> searchDataByDFormat(@Param("dFormat") String dFormat);

    @Select("select * from `document_data` where `dclass` like CONCAT('%', #{dClass}, '%')")
    List<DocumentData> searchDataByDClass(@Param("dClass") String dClass);

    @Select("select * from `document_data` where `create_by` like CONCAT('%', #{createBy}, '%')")
    List<DocumentData> searchDataByCreateBy(@Param("createBy") String createBy);

    @Select("select * from `document_data` where `author_national` like CONCAT('%', #{national}, '%')")
    List<DocumentData> searchDataByNational(@Param("national") String national);

    @Select("select * from `document_data` where `name` like CONCAT('%', #{name}, '%') and `dclass` = #{dClass}")
    List<DocumentData> searchDataByNameAndDClass(@Param("name") String name, @Param("dClass") String dClass);

    @Select("select * from `document_data` where `dclass` = #{dClass} and `dformat` = #{dFormat}")
    List<DocumentData> searchDataByDClassAndDFormat(@Param("dClass") String dClass, @Param("dFormat") String dFormat);

    @Select("select distinct `dclass` from `document_data`")
    List<String> getAllDClass();

    @Delete("delete from `document_data` where `id` = #{id}")
    boolean deleteById(@Param("id") String id);

    boolean saveDocumentData(DocumentData documentData);

    @Select("select count(*) from `document_data`")
    Integer getCount();
}
