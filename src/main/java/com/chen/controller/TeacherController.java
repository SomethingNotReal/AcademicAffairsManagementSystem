package com.chen.controller;

import com.chen.controller.utils.Resource;
import com.chen.domain.*;
import com.chen.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;
    @Autowired
    ElectiveCurriculaService electiveCurriculaService;
    @Autowired
    CurriculaService curriculaService;
    @Autowired
    AchievementService achievementService;
    @Autowired
    HomeworkReleaseService homeworkReleaseService;
    @GetMapping("/login/{teacherId}/{password}")
    public Resource getByIdAndPassword(@PathVariable String teacherId, @PathVariable String password){
        Teacher teacher=teacherService.login(teacherId,password);
        if (teacher!=null){
            return new Resource(true, teacher,"登录成功");
        }else {
            return new Resource(true, "无此学生或密码错误");
        }

    }
    @PutMapping("/{teacherId}/{password}/{password_new}")
    public Resource updateTeacherPassword(@PathVariable String teacherId, @PathVariable String password, @PathVariable String password_new){
        Teacher teacher= teacherService.getTeacherById(teacherId);
        if (teacher.getPassword().equals(password)){
            teacher.setPassword(password_new);
            return new Resource(teacherService.updateByTeacherId(teacher)>0,"修改成功");
        }else {
            return new Resource(true,"原密码错误");
        }

    }


    @GetMapping("{teacherId}")
    public Resource getMyCurricula(@PathVariable String teacherId){
        List<Curricula> curriculaList =curriculaService.getMyCurricula(teacherId);
        List<ElectiveCurricula>electiveCurriculaList=electiveCurriculaService.getCurriculaByTeacherId(teacherId);
        for (int i=0;i<electiveCurriculaList.size();++i){
            curriculaList.add(new Curricula(electiveCurriculaList.get(i)));
        }
       return new Resource(true,curriculaList);

    }

    @GetMapping("/getStudent/{teacherId}/{curriculaId}")
    public Resource getMyCurriculaStudent(@PathVariable String teacherId, @PathVariable String curriculaId){
            List<Achievement> achievementList=teacherService.getMyCurriculaStudent(teacherId,curriculaId);
            return new Resource(true,achievementList,achievementList.size());

    }

    @PutMapping("/up/date/Achieve/ment")
    public Resource updateAchievement(@RequestBody Achievement achievement){

            return new Resource(achievementService.updateScore(achievement)>0,"修改成功");

    }
    @PostMapping("/addHomeWork")
    public Resource addHomeWork(@RequestBody HomeworkRelease homeworkRelease){
        homeworkReleaseService.save(homeworkRelease);
        return new Resource(true,"作业发布成功");
    }

}
