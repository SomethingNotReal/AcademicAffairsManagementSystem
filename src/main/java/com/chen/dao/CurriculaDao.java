package com.chen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.domain.Curricula;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CurriculaDao extends BaseMapper<Curricula>{
    @Select("select * from curricula where teacher_id=#{teacherId}")
    List<Curricula> selectCurriculaByTeacherId(String teacherId);

    @Update("update curricula set curricula_id=#{curriculaId},curricula_name=#{curriculaName},teacher_id=#{teacherId},teacher_name=#{teacherName},start_time=#{startTime},end_time=#{endTime},exam_time=#{examTime} where curricula_id=#{curriculaId}")
    Integer updateByCurriculaId(Curricula curricula);

    @Delete("delete from curricula where curricula_id=#{curriculaId}")
    Integer deleteCurriculaById(String curriculaId);

}
