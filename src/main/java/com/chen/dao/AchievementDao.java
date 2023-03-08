package com.chen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.domain.Achievement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AchievementDao extends BaseMapper<Achievement> {
    @Update("update achievement set score=#{score} where student_id=#{studentId} and curricula_id=#{curriculaId}")
    Integer updateByStudentIdAndCurriculaId(Achievement achievement);

    @Update("update achievement set student_id=#{studentId},student_name=#{studentName},curricula_id=#{curriculaId},curricula_name=#{curriculaName},teacher_id=#{teacherId},teacher_name=#{teacherName},score=#{score} where curricula_id=#{curriculaId}")
    Integer updateAchievement(Achievement achievement);
    @Select("select * from achievement where student_id=#{studentId}")
    List<Achievement> selectByStudentId(String studentId);

    @Select("select * from achievement where curricula_id=#{curriculaId} and student_id=#{studentId}")
    Achievement selectByCurriculaIdAndStudentId(String curriculaId,String studentId);

    @Select("select * from achievement where curricula_id=#{curriculaId}")
    List<Achievement> selectByCurriculaId(String curriculaId);

    @Select("select * from achievement where curricula_id=#{curriculaId} and studentId=#{studentId}")
    List<Achievement> selectByStudentIdAndCurriculaId(String curriculaId,String studentId);

    @Delete("delete from achievement where curricula_id=#{curriculaId}")
    Integer deleteByCurriculaId(String curriculaId);
    @Delete("delete from achievement where student_id=#{studentId}")
    Integer deleteByStudentId(String studentId);

}
