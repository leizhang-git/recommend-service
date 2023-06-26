package com.recommend.consumer.dao;

import com.recommend.consumer.domain.pojo.documentData.DocumentBinary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/26 9:19
 */
@Repository
public interface DocumentBinaryDao {

    @Select("select * from `document_binary_data`")
    List<DocumentBinary> findAllDocumentBinaries();

    @Select("select * from `document_binary_data` where id = #{id}")
    List<DocumentBinary> findAllDocumentBinaryById(@Param("id") String id);

    @Insert("insert into `document_binary_data` values " +
            "(#{documentBinary.id}, #{documentBinary.dBinary}, #{documentData.createBy}, #{documentData.createDate}, " +
            "#{documentData.lastModifiedBy}, #{documentData.lastModifiedDate}")
    boolean saveDocumentBinary(@Param("documentBinary") DocumentBinary documentBinary);
}
