package com.chen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.domain.Achievement;
import com.chen.domain.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TeacherDao extends BaseMapper<Teacher> {
    @Select("select * from teacher where teacher_id=#{teacherId} and password=#{password}")
    Teacher selectTeacherByIdAndPassword(String teacherId, String password);
    @Select("select * from teacher where teacher_id=#{teacherId}")
    Teacher selectTeacherById(String teacherId);
    @Update("update teacher set teacher_id=#{teacherId},name=#{name},sex=#{sex},password=#{password} where teacher_id=#{teacherId}")
    Integer updateByTeacherId(Teacher teacher);

    @Select("select * from achievement where teacher_id=#{teacherId} and curricula_id=#{curriculaId}")
    List<Achievement> selectMyCurriculaStudent(String teacherId, String curriculaId);
    @Delete("delete from teacher where teacher_id=#{teacherId}")
    Integer deleteTeacherById(String teacherId);


}
