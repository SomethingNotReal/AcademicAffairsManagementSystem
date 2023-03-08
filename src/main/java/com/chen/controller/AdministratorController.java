package com.chen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.controller.utils.Resource;
import com.chen.domain.*;
import com.chen.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {
    @Autowired
    AdministratorService administratorService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CurriculaService curriculaService;
    @Autowired
    NewsService newsService;
    @Autowired
    ElectiveCurriculaService electiveCurriculaService;
    @Autowired
    SetCurriculaService setCurriculaService;
    @Autowired
    AchievementService achievementService;
    @Autowired
    CourseSelectionService courseSelectionService;
    @GetMapping("/login/{name}/{password}")
    public Resource login(@PathVariable String name, @PathVariable String password){
        Administrator administrator=administratorService.login(name,password);
        if (administrator!=null){
            return new Resource(true, administrator,"登录成功");
        }else {
            return new Resource(true, "无此管理员");
        }
    }
    @PostMapping("/addTeacher")
    public Resource addTeacher(@RequestBody Teacher teacher){
        return new Resource(teacherService.save(teacher),"添加成功");
    }

    @PostMapping("/addStudent")
    public Resource addStudent(@RequestBody Student student){
        studentService.save(student);
        List<SetCurricula> setCurriculaList=setCurriculaService.getByClassroom(student.getStudentClass());
        Achievement achievement=null;
        for (int i=0;i<setCurriculaList.size();++i){
            achievement=achievementService.getByCurriculaIdAndStudentId(setCurriculaList.get(i).getCurriculaId(),student.getStudentId());
            if (achievement==null){
                achievementService.save(new Achievement(student.getStudentId(),student.getName(),setCurriculaList.get(i)));
            }
        }

        return new Resource("添加成功");
    }
    @PostMapping("/verify")
    public Resource verify(@RequestBody Administrator administrator){

        if (administratorService.verify(administrator.getName(),administrator.getPassword())!=null){
            return new Resource(true,"验证成功");
        }else {
            return new Resource(true,"验证失败");
        }
    }

    @PostMapping("/addCurricula")
    public Resource addCurricula(@RequestBody Curricula curricula){
        if(curriculaService.save(curricula)){
            return new Resource(true,"添加成功");
        }else {
            return new Resource(true,"课程已存在");
        }

    }

    @PostMapping("/addElectiveCurricula")
    public Resource addElectiveCurricula(@RequestBody ElectiveCurricula electiveCurricula){
        if(electiveCurriculaService.save(electiveCurricula)){
            return new Resource(true,"添加成功");
        }else {
            return new Resource(true,"课程已存在");
        }
    }

    @PostMapping("/addNews")
    public Resource addNews(@RequestBody News news){
        return new Resource(newsService.save(news),"添加成功");
    }


    @PostMapping("/setCurriculaForClass")
    public Resource setCurriculaForClass(@RequestBody SetCurricula setCurricula){
        List<Student> studentList=studentService.getStudentByClass(setCurricula.getClassroom());
        Achievement achievement=null;
        for (int i=0;i<studentList.size();++i){
            achievement=new Achievement(studentList.get(i).getStudentId(),studentList.get(i).getName(),setCurricula);
            achievementService.save(achievement);
        }
        return new Resource(setCurriculaService.save(setCurricula),"设置成功");
    }
    @PutMapping("/updateStudent")
    public Resource updateStudent(@RequestBody Student student){
        return new Resource(studentService.updateByStudentId(student)>0,studentService.getStudentById(student.getStudentId()),"修改成功");

    }

    @PutMapping("/updateCurricula")
    public Resource updateCurricula(@RequestBody Curricula curricula){
        List<Achievement> achievementList=achievementService.getByCurriculaId(curricula.getCurriculaId());
        curriculaService.updateByCurriculaId(curricula);
        if(achievementList!=null){
            for (int i=0;i<achievementList.size();++i) {
                achievementList.get(i).setCurriculaId(curricula.getCurriculaId());
                achievementList.get(i).setCurriculaName(curricula.getCurriculaName());
                achievementList.get(i).setTeacherId(curricula.getTeacherId());
                achievementList.get(i).setScore(null);
                achievementService.updateAchievement(achievementList.get(i));
            }
                List<SetCurricula> setCurriculaList=setCurriculaService.getByCurriculaId(curricula.getCurriculaId());
                for (int j=0;j<setCurriculaList.size();++j){
                    setCurriculaList.get(j).setCurriculaId(curricula.getCurriculaId());
                    setCurriculaList.get(j).setCurriculaName(curricula.getCurriculaName());
                    setCurriculaList.get(j).setTeacherId(curricula.getTeacherId());
                    setCurriculaList.get(j).setTeacherName(curricula.getTeacherName());
                    setCurriculaList.get(j).setStartTime(curricula.getStartTime());
                    setCurriculaList.get(j).setEndTime(curricula.getEndTime());
                    setCurriculaList.get(j).setExamTime(curricula.getExamTime());
                    setCurriculaService.updateByCurriculaId(setCurriculaList.get(j));

            }
            return new Resource(true,"修改成功");
        }else {
            return new Resource(true,"修改成功（关联选项）");
        }



    }

    @PutMapping("/updateElectiveCurricula")
    public Resource updateElectiveCurricula(@RequestBody ElectiveCurricula electiveCurricula){
        electiveCurriculaService.updateByCurriculaId(electiveCurricula);
        List<Achievement> achievementList=achievementService.getByCurriculaId(electiveCurricula.getCurriculaId());
        if(achievementList!=null){
            for (int i=0;i<achievementList.size();++i) {
                achievementList.get(i).setCurriculaId(electiveCurricula.getCurriculaId());
                achievementList.get(i).setCurriculaName(electiveCurricula.getCurriculaName());
                achievementList.get(i).setTeacherId(electiveCurricula.getTeacherId());
                achievementList.get(i).setScore(null);
                achievementService.updateAchievement(achievementList.get(i));
            }
            List<CourseSelection> courseSelectionList=courseSelectionService.getByCurriculaId(electiveCurricula.getCurriculaId());
            for (int j=0;j<courseSelectionList.size();++j){
                courseSelectionList.get(j).setCurriculaId(electiveCurricula.getCurriculaId());
                courseSelectionList.get(j).setCurriculaName(electiveCurricula.getCurriculaName());
                courseSelectionList.get(j).setTeacherId(electiveCurricula.getTeacherId());
                courseSelectionList.get(j).setTeacherName(electiveCurricula.getTeacherName());
                courseSelectionList.get(j).setStartTime(electiveCurricula.getStartTime());
                courseSelectionList.get(j).setEndTime(electiveCurricula.getEndTime());
                courseSelectionList.get(j).setExamTime(electiveCurricula.getExamTime());
                courseSelectionService.updateByCurriculaId(courseSelectionList.get(j));

            }
            return new Resource(true,"修改成功");
        }else {
            return new Resource(true,"修改成功（关联选项）");
        }



    }

    @PutMapping("/updateTeacher")
    public Resource updateTeacher(@RequestBody Teacher teacher){
        return new Resource(teacherService.updateByTeacherId(teacher)>0,teacherService.getTeacherById(teacher.getTeacherId()),"修改成功");

    }

    @PutMapping("/updateNews")
    public Resource updateNews(@RequestBody News news){
        return new Resource(newsService.updateNewsById(news)>0,"修改成功");

    }
    @GetMapping("/getAllStudent/{currentPage}/{pageSize}")
    public Resource getAllStudent(@PathVariable int currentPage,@PathVariable int pageSize){
        IPage<Student> page=studentService.getPage(currentPage,pageSize);
          if( currentPage > page.getPages()){
            page = studentService.getPage((int)page.getPages(), pageSize);
        }
        return new Resource(true, page);

    }
    @GetMapping("/getStudentById/{currentPage}/{pageSize}/{studentId}")
    public Resource getStudentById(@PathVariable int currentPage,@PathVariable int pageSize,@PathVariable String studentId){
        IPage<Student> page=studentService.getPage(currentPage,pageSize,studentId);
        if( currentPage > page.getPages()){
            page = studentService.getPage((int)page.getPages(), pageSize,studentId);
        }
        return new Resource(true,page);
    }

    @GetMapping("/getTeacherById/{currentPage}/{pageSize}/{teacherId}")
    public Resource getTeacherById(@PathVariable int currentPage,@PathVariable int pageSize,@PathVariable String teacherId){
        IPage<Teacher> page=teacherService.getPage(currentPage,pageSize,teacherId);
        if( currentPage > page.getPages()){
            page = teacherService.getPage((int)page.getPages(), pageSize,teacherId);
        }
        return new Resource(true,page);
    }

    @GetMapping("/getAllTeacher/{currentPage}/{pageSize}")
    public Resource getAllTeacher(@PathVariable int currentPage,@PathVariable int pageSize){
        IPage<Teacher> page=teacherService.getPage(currentPage,pageSize);
        if( currentPage > page.getPages()){
            page = teacherService.getPage((int)page.getPages(), pageSize);
        }
        return new Resource(true, page);

    }

    @GetMapping("/getAllCurricula/{currentPage}/{pageSize}")
    public Resource getAllCurricula(@PathVariable int currentPage,@PathVariable int pageSize){
        IPage<Curricula> page=curriculaService.getPage(currentPage,pageSize);
        if( currentPage > page.getPages()){
            page = curriculaService.getPage((int)page.getPages(), pageSize);
        }
        return new Resource(true, page);

    }

    @GetMapping("/getCurriculaByLikeName/{currentPage}/{pageSize}/{message}")
    public Resource getCurriculaByLikeName(@PathVariable int currentPage,@PathVariable int pageSize,@PathVariable String message){
        IPage<Curricula> page=curriculaService.getPage(currentPage,pageSize,message);
        if( currentPage > page.getPages()){
            page = curriculaService.getPage((int)page.getPages(), pageSize,message);
        }
        return new Resource(true,page);

    }

    @GetMapping("/getAllElectiveCurricula/{currentPage}/{pageSize}")
    public Resource getAllElectiveCurricula(@PathVariable int currentPage,@PathVariable int pageSize){
        IPage<ElectiveCurricula> page=electiveCurriculaService.getPage(currentPage,pageSize);
        if( currentPage > page.getPages()){
            page = electiveCurriculaService.getPage((int)page.getPages(), pageSize);
        }
        return new Resource(true, page);

    }
    @GetMapping("/getElectiveCurriculaByLikeName/{currentPage}/{pageSize}/{message}")
    public Resource getElectiveCurriculaByLikeName(@PathVariable int currentPage,@PathVariable int pageSize,@PathVariable String message){
        IPage<ElectiveCurricula> page=electiveCurriculaService.getPage(currentPage,pageSize,message);
        if( currentPage > page.getPages()){
            page = electiveCurriculaService.getPage((int)page.getPages(), pageSize,message);
        }
        return new Resource(true,page);

    }
    @DeleteMapping("/deleteTeacher/{teacherId}")
    public Resource deleteTeacher(@PathVariable String teacherId){
        teacherService.removeTeacherById(teacherId);
        return new Resource(true,"删除成功");

    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public Resource deleteStudent(@PathVariable String studentId){
        studentService.removeStudentById(studentId);
        achievementService.removeByStudentId(studentId);
        courseSelectionService.removeByStudentId(studentId);
        return new Resource(true,"删除成功");

    }

    @DeleteMapping("/deleteCurricula/{curriculaId}")
    public Resource deleteCurricula(@PathVariable String curriculaId){
        curriculaService.deleteCurriculaById(curriculaId);
        if(achievementService.getByCurriculaId(curriculaId)!=null){
            achievementService.removeByCurriculaId(curriculaId);
        }
        if (setCurriculaService.getByCurriculaId(curriculaId)!=null){
            setCurriculaService.deleteByCurriculaId(curriculaId);
        }
        return new Resource(true,"删除成功");
    }

    @DeleteMapping("/deleteElectiveCurricula/{curriculaId}")
    public Resource deleteElectiveCurricula(@PathVariable String curriculaId){
        electiveCurriculaService.deleteCurriculaById(curriculaId);
        if(achievementService.getByCurriculaId(curriculaId)!=null){
            achievementService.removeByCurriculaId(curriculaId);
        }
        if(courseSelectionService.getByCurriculaId(curriculaId)!=null){
            courseSelectionService.removeByCurriculaId(curriculaId);
        }
        return new Resource(true,"删除成功");
    }

    @DeleteMapping("/deleteNews/{id}")
    public Resource deleteNews(@PathVariable String id){
        newsService.deleteNewsById(id);
        return new Resource(true,"删除成功");
    }

}
