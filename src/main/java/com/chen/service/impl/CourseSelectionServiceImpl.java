package com.chen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.dao.CourseSelectionDao;
import com.chen.domain.CourseSelection;
import com.chen.service.CourseSelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseSelectionServiceImpl extends ServiceImpl<CourseSelectionDao, CourseSelection> implements CourseSelectionService {
    @Autowired
    CourseSelectionDao courseSelectionDao;
    @Override
    public List<CourseSelection> getByStudentId(String studentId) {
        return courseSelectionDao.selectByStudentId(studentId);
    }

    @Override
    public List<CourseSelection> getByCurriculaId(String curriculaId) {
        return courseSelectionDao.selectByCurriculaId(curriculaId);
    }

    @Override
    public Integer updateByCurriculaId(CourseSelection courseSelection) {
        return courseSelectionDao.updateByCurriculaId(courseSelection);
    }

    @Override
    public Integer deleteByCurriculaId(String curriculaId) {
        return courseSelectionDao.deleteByCurriculaId(curriculaId);
    }
}
