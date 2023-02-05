package com.chen.controller;

import com.chen.controller.utils.Resource;
import com.chen.domain.HomeworkSubmit;
import com.chen.service.HomeworkSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/homeworkSubmits")
public class HomeworkSubmitController {
    @Autowired
    HomeworkSubmitService homeworkSubmitService;
    @GetMapping("/getReleaseHomeworkByCurriculaIdAndStudentId/{curriculaId}/{studentId}")
    public Resource getReleaseHomeworkByCurriculaIdAndStudentId(@PathVariable String curriculaId, @PathVariable String studentId){
        return new Resource(true,homeworkSubmitService.getReleaseHomeworkByCurriculaIdAndStudentId(curriculaId,studentId));
    }

    @GetMapping("/getSubmitHomeworkByCurriculaIdAndTeacherId/{curriculaId}/{teacherId}")
    public Resource getSubmitHomeworkByCurriculaIdAndTeacherId(@PathVariable String curriculaId, @PathVariable String teacherId){
        List<HomeworkSubmit> homeworkSubmitList=homeworkSubmitService.getReleaseHomeworkByCurriculaIdAndTeacherId(curriculaId,teacherId);
        return new Resource(true,homeworkSubmitList,homeworkSubmitList.size());
    }

    @DeleteMapping("/deleteSubmitHomeworkByCurriculaIdAndTeacherIdAndFileName/{curriculaId}/{teacherId}/{releaseName}")
    public Resource deleteSubmitHomeworkByCurriculaIdAndTeacherIdAndFileName(@PathVariable String curriculaId,@PathVariable String teacherId,@PathVariable String releaseName){
        if (homeworkSubmitService.deleteSubmitHomeworkByCurriculaIdAndTeacherIdAndFileName(curriculaId,teacherId,releaseName)>0){
            return new Resource(true,"删除成功");
        }else {
            return new Resource(true,"删除失败");
        }

    }
}
