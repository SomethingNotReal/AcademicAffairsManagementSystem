package com.chen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.controller.utils.Resource;
import com.chen.domain.*;
import com.chen.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    CourseSelectionService courseSelectionService;
    @Autowired
    AchievementService achievementService;
    @Autowired
    ElectiveCurriculaService electiveCurriculaService;
    @Autowired
    SetCurriculaService setCurriculaService;
    @GetMapping("/login/{studentId}/{password}")
    public Resource login(@PathVariable String studentId,@PathVariable String password){
        Student student=studentService.login(studentId,password);
        if (student!=null){
            return new Resource(true, student,"登录成功");
        }else {
            return new Resource(true, "无此学生或密码错误");
        }

    }
    @PutMapping("/{studentId}/{password}/{password_new}")
    public Resource updateStudentPassword(@PathVariable String studentId,@PathVariable String password,@PathVariable String password_new){
       Student student= studentService.getStudentById(studentId);
        if (student.getPassword().equals(password)){
            student.setPassword(password_new);
            return new Resource(studentService.updateByStudentId(student)>0,"修改成功");
        }else {
            return new Resource(true,"原密码错误");
        }

    }

    @PostMapping("/selectCourse")
    public Resource selectCourse(@RequestBody CourseSelection courseSelection){
        Achievement achievement=achievementService.getByCurriculaIdAndStudentId(courseSelection.getCurriculaId(),courseSelection.getStudentId());
        if(achievement==null){
            achievement=new Achievement(courseSelection);
            return new Resource(courseSelectionService.save(courseSelection)&&achievementService.save(achievement));
        }else {
            return new Resource(true,"已有此课程");
        }

    }

    @GetMapping("/get/MyCurricula/{studentId}/{classroom}")
    public Resource getMyCurricula(@PathVariable String studentId,@PathVariable String classroom){
        List<CourseSelection> courseSelectionList=courseSelectionService.getByStudentId(studentId);
        List<SetCurricula> setCurriculaList=setCurriculaService.getByClassroom(classroom);
        Achievement achievement=null;
        for (int i=0;i<setCurriculaList.size();++i){
            courseSelectionList.add(new CourseSelection(studentId,studentService.getStudentById(studentId).getName(),setCurriculaList.get(i)));
        }
        for(int j=0;j<setCurriculaList.size();++j){
           achievement= achievementService.getByCurriculaIdAndStudentId(setCurriculaList.get(j).getCurriculaId(),studentId);
           if (achievement==null){
               achievement=new Achievement(studentId,studentService.getStudentById(studentId).getName(),setCurriculaList.get(j));
               achievementService.save(achievement);
           }

        }
        return new Resource(true,courseSelectionList,courseSelectionList.size());
    }

    @GetMapping("/getElectiveCurricula/{currentPage}/{pageSize}")
    public Resource getElectiveCurricula(@PathVariable int currentPage,@PathVariable int pageSize){
        IPage<ElectiveCurricula> page=electiveCurriculaService.getPage(currentPage,pageSize);
        if( currentPage > page.getPages()){
            page = electiveCurriculaService.getPage((int)page.getPages(), pageSize);
        }
        return new Resource(true, page);
    }

    @GetMapping("/getElectiveCurriculaByLikeMessage/{currentPage}/{pageSize}/{message}")
    public Resource getElectiveCurriculaByLikeMessage(@PathVariable int currentPage,@PathVariable int pageSize,@PathVariable String message){
        IPage<ElectiveCurricula> page=electiveCurriculaService.getPage(currentPage,pageSize,message);
        if( currentPage > page.getPages()){
            page = electiveCurriculaService.getPage((int)page.getPages(), pageSize,message);
        }
        return new Resource(true,page);
    }

    @GetMapping("/getMyAchievement/{studentId}")
    public Resource getMyAchievement(@PathVariable String studentId){

        return new Resource(true,achievementService.getAchievementByStudentId(studentId));

    }
    @GetMapping("/{studentId}")
    public Resource getMyClassroom(@PathVariable String studentId){

        return new Resource(true,studentService.getClassByStudentId(studentId),"");

    }

}
