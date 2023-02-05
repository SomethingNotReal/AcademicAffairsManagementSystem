package com.chen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.domain.HomeworkSubmit;

import java.util.List;

public interface HomeworkSubmitService extends IService<HomeworkSubmit> {

    List<HomeworkSubmit> getReleaseHomeworkByCurriculaIdAndStudentId(String curriculaId, String studentId);
    List<HomeworkSubmit> getReleaseHomeworkByCurriculaIdAndTeacherId(String curriculaId, String teacherId);

    Integer deleteSubmitHomeworkByCurriculaIdAndTeacherIdAndFileName(String curriculaId,String teacherId,String releaseName);
    Integer submitHomework(HomeworkSubmit homeworkSubmit);
}
