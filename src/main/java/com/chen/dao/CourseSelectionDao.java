package com.chen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.domain.CourseSelection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CourseSelectionDao extends BaseMapper<CourseSelection> {
    @Select("select * from course_selection where student_id=#{studentId}")
    List<CourseSelection> selectByStudentId(String studentId);

    @Select("select * from course_selection where curricula_id=#{curriculaId}")
    List<CourseSelection> selectByCurriculaId(String curriculaId);

    @Delete("delete from course_selection where curricula_id=#{curriculaId}")
    Integer deleteByCurriculaId(String curriculaId);
    @Delete("delete from course_selection where student_id=#{studentId}")
    Integer deleteByStudentId(String studentId);

    @Update("update course_selection set curricula_id=#{curriculaId},curricula_name=#{curriculaName},teacher_id=#{teacherId},teacher_name=#{teacherName},start_time=#{startTime},end_time=#{endTime},exam_time=#{examTime},classroom=#{classroom} where curricula_id=#{curriculaId}")
    Integer updateByCurriculaId(CourseSelection courseSelection);
}


