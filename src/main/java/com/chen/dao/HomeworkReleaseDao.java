package com.chen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.domain.HomeworkRelease;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HomeworkReleaseDao extends BaseMapper<HomeworkRelease> {
    @Select("select * from homework_release where curricula_id=#{curriculaId} and teacher_id=#{teacherId}")
    List<HomeworkRelease> selectReleaseHomeworkByCurriculaIdAndTeacherId(String curriculaId,String teacherId);
    @Delete("delete from homework_release where curricula_id=#{curriculaId} and teacher_id=#{teacherId} and homework_file_name=#{homeworkFileName}")
    Integer deleteReleaseHomeworkByCurriculaIdAndTeacherIdAndFileName(String curriculaId,String teacherId,String homeworkFileName);
}
