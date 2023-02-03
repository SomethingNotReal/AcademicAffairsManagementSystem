package com.chen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.domain.Student;

import java.util.List;

public interface StudentService extends IService<Student> {
    Student login(String studentId, String password);
    Student getStudentById(String studentId);
    Integer updateByStudentId(Student student);
    String getClassByStudentId(String studentId);
    List<Student> getStudentByClass(String classroom);
    Integer removeStudentById(String studentId);

    IPage<Student> getPage(int currentPage, int pageSize);

    IPage<Student> getPage(int currentPage, int pageSize, String studentId);

}
