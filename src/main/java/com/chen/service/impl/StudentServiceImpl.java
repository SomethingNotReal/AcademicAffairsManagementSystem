package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.dao.StudentDao;
import com.chen.domain.Student;
import com.chen.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao,Student> implements StudentService {
    @Autowired
    private StudentDao studentDao;


    @Override
    public Student login(String studentId, String password) {
        return studentDao.selectStudentByIdAndPassword(studentId,password);
    }

    @Override
    public Student getStudentById(String studentId) {
      return   studentDao.selectStudentById(studentId);
    }

    @Override
    public Integer updateByStudentId(Student student) {
        return studentDao.updateByStudentId(student);
    }

    @Override
    public String getClassByStudentId(String studentId) {
        return studentDao.selectClassByStudentId(studentId);
    }

    @Override
    public List<Student> getStudentByClass(String classroom) {
        return studentDao.selectStudentByClass(classroom);
    }

    @Override
    public Integer removeStudentById(String studentId) {
        return studentDao.deleteStudentById(studentId);
    }

    @Override
    public IPage<Student> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage,pageSize);
        studentDao.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<Student> getPage(int currentPage, int pageSize, String studentId) {
        QueryWrapper<Student> lqw = new QueryWrapper<Student>();
        lqw.eq("student_id",studentId);
        IPage page = new Page(currentPage,pageSize);
        studentDao.selectPage(page,lqw);
        return page;
    }

}
