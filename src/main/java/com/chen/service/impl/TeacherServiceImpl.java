package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.dao.TeacherDao;
import com.chen.domain.Achievement;
import com.chen.domain.Teacher;
import com.chen.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherDao,Teacher> implements TeacherService {
    @Autowired
    TeacherDao teacherDao;
    @Override
    public Teacher login(String teacherId, String password) {
        return teacherDao.selectTeacherByIdAndPassword(teacherId,password);
    }

    @Override
    public Teacher getTeacherById(String teacherId) {
        return teacherDao.selectTeacherById(teacherId);
    }

    @Override
    public Integer updateByTeacherId(Teacher teacher) {
        return teacherDao.updateByTeacherId(teacher);
    }



    @Override
    public List<Achievement> getMyCurriculaStudent(String teacherId, String curriculaId) {
        return teacherDao.selectMyCurriculaStudent(teacherId,curriculaId);
    }

    @Override
    public Integer removeTeacherById(String teacherId) {
        return teacherDao.deleteTeacherById(teacherId);
    }

    @Override
    public IPage<Teacher> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage,pageSize);
        teacherDao.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<Teacher> getPage(int currentPage, int pageSize, String teacherId) {
        QueryWrapper<Teacher> lqw = new QueryWrapper<Teacher>();
        lqw.eq("teacher_id",teacherId);
        IPage page = new Page(currentPage,pageSize);
        teacherDao.selectPage(page,lqw);
        return page;
    }


}
