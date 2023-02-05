package com.chen.domain;

import lombok.Data;

@Data
public class HomeworkSubmit {
    String name,releaseName,curriculaId,curriculaName,teacherId,teacherName,studentId,studentName,state,endTime;
   public HomeworkSubmit(){}
    public HomeworkSubmit(String releaseName,String endTime, Achievement achievement){
        this.releaseName=releaseName;
        this.curriculaId=achievement.getCurriculaId();
        this.curriculaName=achievement.getCurriculaName();
        this.teacherId=achievement.getTeacherId();
        this.teacherName=achievement.getTeacherName();
        this.studentId=achievement.getStudentId();
        this.studentName=achievement.getStudentName();
        this.endTime=endTime;
        this.state="未提交";
    }
}
