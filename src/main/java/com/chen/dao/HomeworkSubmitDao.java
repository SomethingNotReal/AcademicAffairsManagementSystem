package com.chen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.domain.HomeworkSubmit;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface HomeworkSubmitDao extends BaseMapper<HomeworkSubmit> {
    @Select("select * from homework_submit where curricula_id=#{curriculaId} and student_id=#{studentId}")
    List<HomeworkSubmit> selectReleaseHomeworkByCurriculaIdAndStudentId(String curriculaId, String studentId);

    @Select("select * from homework_submit where curricula_id=#{curriculaId} and teacher_id=#{teacherId}")
    List<HomeworkSubmit> selectReleaseHomeworkByCurriculaIdAndTeacherId(String curriculaId, String teacherId);


    @Delete("delete from homework_submit where curricula_id=#{curriculaId} and teacher_id=#{teacherId} and release_name=#{releaseName}")
    Integer deleteSubmitHomeworkByCurriculaIdAndTeacherIdAndFileName(String curriculaId,String teacherId,String releaseName);
    @Update("update homework_submit set name=#{name}, state=#{state} where student_id=#{studentId} and curricula_id=#{curriculaId} and teacher_id=#{teacherId} and release_name=#{releaseName}")
    Integer updateStudentIdAndCurriculaIdAndTeacherId(HomeworkSubmit homeworkSubmit);
}
