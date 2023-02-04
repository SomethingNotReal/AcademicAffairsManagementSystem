package com.chen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.domain.HomeworkRelease;

import java.util.List;

public interface HomeworkReleaseService extends IService<HomeworkRelease> {
    List<HomeworkRelease> getReleaseHomeworkByCurriculaIdAndTeacherId(String curriculaId,String teacherId);
    Integer deleteReleaseHomeworkByCurriculaIdAndTeacherIdAndFileName(String curriculaId,String teacherId,String homeworkFileName);
}
