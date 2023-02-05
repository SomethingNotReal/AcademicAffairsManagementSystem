package com.chen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.dao.HomeworkSubmitDao;
import com.chen.domain.HomeworkSubmit;
import com.chen.service.HomeworkSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkSubmitServiceImpl extends ServiceImpl<HomeworkSubmitDao, HomeworkSubmit> implements HomeworkSubmitService {
    @Autowired
    HomeworkSubmitDao homeworkSubmitDao;
    @Override
    public List<HomeworkSubmit> getReleaseHomeworkByCurriculaIdAndStudentId(String curriculaId, String studentId) {
        return homeworkSubmitDao.selectReleaseHomeworkByCurriculaIdAndStudentId(curriculaId,studentId);
    }

    @Override
    public List<HomeworkSubmit> getReleaseHomeworkByCurriculaIdAndTeacherId(String curriculaId, String teacherId) {
        return homeworkSubmitDao.selectReleaseHomeworkByCurriculaIdAndTeacherId(curriculaId,teacherId);
    }

    @Override
    public Integer deleteSubmitHomeworkByCurriculaIdAndTeacherIdAndFileName(String curriculaId, String teacherId, String releaseName) {
        return homeworkSubmitDao.deleteSubmitHomeworkByCurriculaIdAndTeacherIdAndFileName(curriculaId,teacherId,releaseName);
    }

    @Override
    public Integer submitHomework(HomeworkSubmit homeworkSubmit) {
        return homeworkSubmitDao.updateStudentIdAndCurriculaIdAndTeacherId(homeworkSubmit);
    }
}
