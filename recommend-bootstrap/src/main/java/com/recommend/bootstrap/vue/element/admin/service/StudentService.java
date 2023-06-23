package com.recommend.bootstrap.vue.element.admin.service;

import com.recommend.bootstrap.vue.element.admin.dao.StudentMapper;
import com.recommend.bootstrap.vue.element.admin.entity.Student;
import com.recommend.bootstrap.vue.element.admin.entity.StudentQueryDto;
import com.recommend.bootstrap.vue.element.admin.entity.StudentSaveDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: zhanglei
 * @create: 2023-06-23 23:19
 **/
@Service
public class StudentService {

    public List<Student> findAll() {
        studentMapper.findAll();
        return studentMapper.findAll();
    }

    public Student findById(int id) {
        return studentMapper.findById(id);
    }

    public List<Student> findBy(StudentQueryDto q) {
        return studentMapper.findBy(q.getName(), q.getSex(), q.getAge(), q.offset(), q.limit());
    }

    public long findCount(StudentQueryDto q) {
        return studentMapper.findCount(q.getName(), q.getSex(), q.getAge());
    }

    public void insert(StudentSaveDto dto) {
        Student stu = new Student();
        BeanUtils.copyProperties(dto, stu);
        studentMapper.insert(stu);
    }

    public void update(int id, StudentSaveDto dto) {
        Student stu = studentMapper.findById(id);
        BeanUtils.copyProperties(dto, stu);
        studentMapper.update(stu);
    }

    public void deleteById(int id) {
        studentMapper.deleteById(id);
    }

    @Transactional
    public void deleteByIds(int[] ids) {
        for (int id : ids) {
            studentMapper.deleteById(id);
        }
    }

    @Autowired
    private StudentMapper studentMapper;
}
