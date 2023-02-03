package com.chen.domain;

import lombok.Data;

@Data
public class Curricula {
    private String curriculaId,curriculaName,teacherId,teacherName,startTime,endTime,examTime;

    public Curricula(){}
    public Curricula(ElectiveCurricula electiveCurricula){
        this.curriculaId=electiveCurricula.getCurriculaId();
        this.curriculaName=electiveCurricula.getCurriculaName();
        this.teacherId=electiveCurricula.getTeacherId();
        this.teacherName=electiveCurricula.getTeacherName();
        this.startTime=electiveCurricula.getStartTime();
        this.endTime=electiveCurricula.getEndTime();
        this.examTime=electiveCurricula.getExamTime();
    }

}
