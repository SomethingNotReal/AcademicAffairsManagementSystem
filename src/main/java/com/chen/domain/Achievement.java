package com.chen.domain;

import lombok.Data;

@Data
public class Achievement {
    private String studentId,studentName,curriculaId,curriculaName,teacherId,teacherName,score;

    public Achievement(){

    }
    public Achievement(CourseSelection courseSelection){
        this.studentId=courseSelection.getStudentId();
        this.studentName=courseSelection.getStudentName();
        this.curriculaId=courseSelection.getCurriculaId();
        this.curriculaName=courseSelection.getCurriculaName();
        this.teacherId=courseSelection.getTeacherId();
        this.teacherName=courseSelection.getTeacherName();
        this.score=null;

    }



    public Achievement(String studentId,String studentName,SetCurricula setCurricula){
        this.studentId=studentId;
        this.studentName=studentName;
        this.curriculaId=setCurricula.getCurriculaId();
        this.curriculaName=setCurricula.getCurriculaName();
        this.teacherId=setCurricula.getTeacherId();
        this.teacherName=setCurricula.getTeacherName();
        this.score=null;

    }

}
