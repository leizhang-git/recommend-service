package com.recommend.bootstrap.vue.element.admin.dao;

import com.recommend.bootstrap.vue.element.admin.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description:
 * @author: zhanglei
 * @create: 2023-06-23 23:20
 **/
@Mapper
public interface StudentMapper {

    @Select("select * from student")
    List<Student> findAll();

    List<Student> findBy(
            @Param("name") String name,
            @Param("sex") String sex,
            @Param("age") Integer[] age,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    long findCount(
            @Param("name") String name,
            @Param("sex") String sex,
            @Param("age") Integer[] age
    );

    @Insert("insert into student(name,sex,age) values(#{name},#{sex},#{age})")
    void insert(Student student);

    @Update("update student set name=#{name}, sex=#{sex}, age=#{age} where id=#{id}")
    void update(Student student);

    @Select("select * from student where id=#{id}")
    Student findById(int id);

    @Delete("delete from student where id=#{id}")
    void deleteById(int id);
}
