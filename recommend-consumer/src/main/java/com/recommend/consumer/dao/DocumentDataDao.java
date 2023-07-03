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

    @Select("select * from `document_data` where `dformat` like CONCAT('%', #{dformat}, '%')")
    List<DocumentData> searchDataByDFormat(@Param("dformat") String dformat);

    @Select("select * from `document_data` where `dclass` like CONCAT('%', #{dclass}, '%')")
    List<DocumentData> searchDataByDClass(@Param("dclass") String dclass);

    @Select("select * from `document_data` where `create_by` like CONCAT('%', #{createBy}, '%')")
    List<DocumentData> searchDataByCreateBy(@Param("createBy") String createBy);

    @Select("select * from `document_data` where `author_national` like CONCAT('%', #{national}, '%')")
    List<DocumentData> searchDataByNational(@Param("national") String national);

    @Select("select * from `document_data` where `name` like CONCAT('%', #{name}, '%') and `dclass` = #{dclass}")
    List<DocumentData> searchDataByNameAndDClass(@Param("name") String name, @Param("dclass") String dclass);

    @Select("select * from `document_data` where `dclass` = #{dclass} and `dformat` = #{dformat}")
    List<DocumentData> searchDataByDClassAndDFormat(@Param("dclass") String dclass, @Param("dformat") String dformat);

    @Select("select distinct `dclass` from `document_data`")
    List<String> getAllDClass();

    @Delete("delete from `document_data` where `id` = #{id}")
    boolean deleteById(@Param("id") String id);

    boolean saveDocumentData(DocumentData documentData);

    boolean updateDocumentData(DocumentData documentData);

    @Select("select count(*) from `document_data`")
    Integer getCount();

    @Select("select count(*) from `document_data` where `id` = #{id}")
    Integer getCountById(@Param("id") String id);
}
