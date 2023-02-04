package com.chen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.dao.HomeworkReleaseDao;
import com.chen.domain.HomeworkRelease;
import com.chen.service.HomeworkReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkReleaseServiceImpl extends ServiceImpl<HomeworkReleaseDao, HomeworkRelease> implements HomeworkReleaseService {
    @Autowired
    HomeworkReleaseDao homeworkReleaseDao;
    @Override
    public List<HomeworkRelease> getReleaseHomeworkByCurriculaIdAndTeacherId(String curriculaId,String teacherId) {
        return homeworkReleaseDao.selectReleaseHomeworkByCurriculaIdAndTeacherId(curriculaId,teacherId);
    }

    @Override
    public Integer deleteReleaseHomeworkByCurriculaIdAndTeacherIdAndFileName(String curriculaId,String teacherId,String homeworkFileName) {
        return homeworkReleaseDao.deleteReleaseHomeworkByCurriculaIdAndTeacherIdAndFileName(curriculaId,teacherId,homeworkFileName);
    }
}
