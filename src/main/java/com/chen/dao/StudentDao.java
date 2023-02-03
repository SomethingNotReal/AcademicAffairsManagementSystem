package com.chen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentDao extends BaseMapper<Student> {
    @Select("select * from student where student_id=#{studentId} and password=#{password}")
    Student selectStudentByIdAndPassword(String studentId,String password);
    @Select("select * from student where student_id=#{studentId}")
    Student selectStudentById(String studentId);
    @Update("update student set student_id=#{studentId},name=#{name},sex=#{sex},major=#{major},student_class=#{studentClass},phone=#{phone},password=#{password} where student_id=#{studentId}")
    Integer updateByStudentId(Student student);

    @Select("select student_class from student where student_id=#{studentId}")
    String selectClassByStudentId(String studentId);

    @Select("select * from student where student_class=#{classroom}")
    List<Student> selectStudentByClass(String classroom);

    @Delete("delete from student where student_id=#{studentId}")
    Integer deleteStudentById(String studentId);

}
