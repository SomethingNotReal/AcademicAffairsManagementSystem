package com.chen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.domain.CourseSelection;

import java.util.List;

public interface CourseSelectionService extends IService<CourseSelection> {
    List<CourseSelection> getByStudentId(String studentId);
    List<CourseSelection> getByCurriculaId(String curriculaId);
    Integer updateByCurriculaId(CourseSelection courseSelection);
    Integer deleteByCurriculaId(String curriculaId);
}
