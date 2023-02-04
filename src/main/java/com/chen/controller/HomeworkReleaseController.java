package com.chen.controller;

import com.chen.controller.utils.Resource;
import com.chen.domain.HomeworkRelease;
import com.chen.service.HomeworkReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homeworkReleases")
public class HomeworkReleaseController {
    @Autowired
    HomeworkReleaseService homeworkReleaseService;
    @GetMapping("/getReleaseHomeworkByCurriculaIdAndTeacherId/{curriculaId}/{teacherId}")
    public Resource getReleaseHomeworkByCurriculaIdAndTeacherId(@PathVariable String curriculaId,@PathVariable String teacherId){
        return new Resource(true,homeworkReleaseService.getReleaseHomeworkByCurriculaIdAndTeacherId(curriculaId,teacherId));
    }

    @DeleteMapping("/deleteReleaseHomeworkByCurriculaIdAndTeacherIdAndFileName/{curriculaId}/{teacherId}/{homeworkFileName}")
    public Resource deleteReleaseHomeworkByCurriculaIdAndTeacherIdAndFileName(@PathVariable String curriculaId,@PathVariable String teacherId,@PathVariable String homeworkFileName){
        if (homeworkReleaseService.deleteReleaseHomeworkByCurriculaIdAndTeacherIdAndFileName(curriculaId,teacherId,homeworkFileName)>0){
            return new Resource(true,"删除成功");
        }else {
            return new Resource(true,"删除失败");
        }

    }
}
