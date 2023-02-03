package com.chen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.domain.ElectiveCurricula;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ElectiveCurriculaDao extends BaseMapper<ElectiveCurricula> {
    @Update("update elective_curricula set curricula_id=#{curriculaId},curricula_name=#{curriculaName},teacher_id=#{teacherId},teacher_name=#{teacherName},start_time=#{startTime},end_time=#{endTime},exam_time=#{examTime} where curricula_id=#{curriculaId}")
    Integer updateByCurriculaId(ElectiveCurricula electiveCurricula);

    @Delete("delete from elective_curricula where curricula_id=#{curriculaId}")
    Integer deleteCurriculaById(String curriculaId);

    @Select("select * from elective_curricula where teacher_id=#{teacherId}")
    List<ElectiveCurricula> selectCurriculaByTeacherId(String teacherId);
}
