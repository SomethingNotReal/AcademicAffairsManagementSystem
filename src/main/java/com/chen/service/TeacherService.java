package com.chen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.domain.Achievement;
import com.chen.domain.Teacher;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface TeacherService extends IService<Teacher> {
    Teacher login(String teacherId, String password);
    Teacher getTeacherById(String teacherId);
    Integer updateByTeacherId(Teacher teacher);

    List<Achievement> getMyCurriculaStudent(String teacherId, String curriculaId);

    @Delete("delete from teacher where teacher_id=#{teacherId}")
    Integer removeTeacherById(String teacherId);

    IPage<Teacher> getPage(int currentPage, int pageSize);

    IPage<Teacher> getPage(int currentPage, int pageSize, String teacherId);


}
