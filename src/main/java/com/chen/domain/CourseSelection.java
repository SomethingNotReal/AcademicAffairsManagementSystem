package com.chen.domain;

import lombok.Data;

@Data
public class CourseSelection {
    String studentId,studentName,curriculaId,curriculaName,teacherId,teacherName,startTime,endTime,examTime;
    public CourseSelection(String studentId,String studentName,SetCurricula setCurricula){
        this.studentId=studentId;
        this.studentName=studentName;
        this.curriculaId=setCurricula.getCurriculaId();
        this.curriculaName=setCurricula.getCurriculaName();
        this.teacherId=setCurricula.getTeacherId();
        this.teacherName=setCurricula.getTeacherName();
        this.startTime=setCurricula.getStartTime();
        this.endTime=setCurricula.getEndTime();
        this.examTime=setCurricula.getExamTime();
    }
    public CourseSelection(){}
}
