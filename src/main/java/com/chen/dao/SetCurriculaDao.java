package com.chen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.domain.SetCurricula;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SetCurriculaDao extends BaseMapper<SetCurricula> {
    @Select("select * from set_curricula where classroom=#{classroom}")
    List<SetCurricula> selectByClassroom(String classRoom);

    @Update("update set_curricula set curricula_id=#{curriculaId},curricula_name=#{curriculaName},teacher_id=#{teacherId},teacher_name=#{teacherName},start_time=#{startTime},end_time=#{endTime},exam_time=#{examTime},classroom=#{classroom} where curricula_id=#{curriculaId}")
    Integer updateByCurriculaId(SetCurricula setCurricula);

    @Select("select * from set_curricula where curricula_id=#{curriculaId}")
    List<SetCurricula> selectByCurriculaId(String curriculaId);

    @Delete("delete from set_curricula where curricula_id=#{curriculaId}")
    Integer deleteByCurriculaId(String curriculaId);
}
