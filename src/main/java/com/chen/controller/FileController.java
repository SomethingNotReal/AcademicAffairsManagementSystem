package com.chen.controller;

import cn.hutool.core.io.FileUtil;
import com.chen.controller.utils.Resource;
import com.chen.domain.Achievement;
import com.chen.domain.HomeworkRelease;
import com.chen.domain.HomeworkSubmit;
import com.chen.service.HomeworkReleaseService;
import com.chen.service.HomeworkSubmitService;
import com.chen.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    HomeworkReleaseService homeworkReleaseService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    HomeworkSubmitService homeworkSubmitService;
    String homeworkFileName =null;
    String submitFileName=null;
    private String homeWorkReleasePath=null;
    private String homeWorkSubmitPath =null;

    @ResponseBody
    @PostMapping("/addHomework")
    public Resource addHomework(@RequestParam("file") MultipartFile file)  {

        if (file.isEmpty()) {

            return new Resource("空文件！");
        }
        // 文件名
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Mac OS")) {
            // 苹果
        } else if (osName.startsWith("Windows")) {
            homeWorkReleasePath ="C:"+File.separator+"Users"+File.separator+"23605"+File.separator+"Documents"+File.separator+"springboot_upload"+File.separator+"AcademicAffairsManagementSystem"+File.separator+"HomeworkRelease";
        } else {
            homeWorkReleasePath =File.separator+"home"+File.separator+"imagine"+File.separator+"文档"+File.separator+"SpringBootUpload"+File.separator+"AcademicAffairsManagementSystem"+File.separator+"HomeworkRelease";
        }
        File fileHomeworkRelease = new File(homeWorkReleasePath + "/" + fileName);
        homeworkFileName =fileName;
        // 检测目录是否存在
        if (!fileHomeworkRelease.getParentFile().exists()) {
            fileHomeworkRelease.getParentFile().mkdirs();
        }
        // 使用文件名称检测文件是否已经存在
        if (fileHomeworkRelease.exists()) {
            return new Resource("文件已存在");
        }

        try {
            // 写入文件:方式1
            // file.transferTo(fileTempObj);
            // 写入文件:方式2
            FileUtil.writeBytes(file.getBytes(), fileHomeworkRelease);
        } catch (Exception e) {

            return new Resource("error");
        }
     //   homeworkReleaseService.save(homeworkRelease);
        return new Resource(true,"作业发布成功");
    }

    @ResponseBody
    @PostMapping("/submitHomework")
    public Resource submitHomework(@RequestParam("file") MultipartFile file)  {

        if (file.isEmpty()) {

            return new Resource("空文件！");
        }
        // 文件名
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Mac OS")) {
            // 苹果
        } else if (osName.startsWith("Windows")) {
            homeWorkSubmitPath ="C:"+File.separator+"Users"+File.separator+"23605"+File.separator+"Documents"+File.separator+"springboot_upload"+File.separator+"AcademicAffairsManagementSystem"+File.separator+"HomeworkSubmit";
        } else {
            homeWorkSubmitPath=File.separator+"home"+File.separator+"imagine"+File.separator+"文档"+File.separator+"SpringBootUpload"+File.separator+"AcademicAffairsManagementSystem"+File.separator+"HomeworkSubmit";
        }
        File fileHomeworkRelease = new File(homeWorkSubmitPath + "/" + fileName);
        submitFileName =fileName;
        // 检测目录是否存在
        if (!fileHomeworkRelease.getParentFile().exists()) {
            fileHomeworkRelease.getParentFile().mkdirs();
        }
        // 使用文件名称检测文件是否已经存在
        if (fileHomeworkRelease.exists()) {
            return new Resource("文件已存在");
        }

        try {
            // 写入文件:方式1
            // file.transferTo(fileTempObj);
            // 写入文件:方式2
            FileUtil.writeBytes(file.getBytes(), fileHomeworkRelease);
        } catch (Exception e) {

            return new Resource("error");
        }
        //   homeworkReleaseService.save(homeworkRelease);
        return new Resource(true,"作业提交成功");
    }

    @PostMapping("/submitHomeworkInDataBase")
    public Resource submitHomeworkInDataBase(@RequestBody HomeworkSubmit homeworkSubmit) throws InterruptedException {
        if (submitFileName ==null){
            Thread.sleep(200);
        }
        homeworkSubmit.setName(submitFileName);
        if (submitFileName!=null){
            homeworkSubmit.setState("已提交");
        }
        homeworkSubmitService.submitHomework(homeworkSubmit);
        return new Resource(true,"作业提交成功");
    }

    @PostMapping("/addHomeworkInDataBase")
    public Resource addHomeworkInDataBase(@RequestBody HomeworkRelease homeworkRelease) throws InterruptedException {
        if (homeworkFileName ==null){
            Thread.sleep(200);
        }
        homeworkRelease.setHomeworkFileName(homeworkFileName);
        homeworkReleaseService.save(homeworkRelease);
        homeworkFileName =null;
        List<Achievement> achievementList=teacherService.getMyCurriculaStudent(homeworkRelease.getTeacherId(),homeworkRelease.getCurriculaId());
        for (int i=0;i<achievementList.size();++i){
            homeworkSubmitService.save(new HomeworkSubmit(homeworkRelease.getHomeworkFileName(),homeworkRelease.getEndTime(),achievementList.get(i)));
        }
        return new Resource(true,"作业发布成功");
    }
    @ResponseBody
    @GetMapping("/downloadHomeworkRelease/{fileName}")
    public Resource downloadHomeworkRelease(HttpServletResponse response, @PathVariable String fileName) throws IOException {

        File file = new File(homeWorkReleasePath+File.separator+fileName);
        if (!file.exists()) {
            return new Resource("文件不存在");
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] readBytes = FileUtil.readBytes(file);
        OutputStream os = response.getOutputStream();
        os.write(readBytes);
        return new Resource("下载成功");
    }

    @ResponseBody
    @GetMapping("/downloadHomeworkSubmit/{name}")
    public Resource downloadHomeworkSubmit(HttpServletResponse response, @PathVariable String name) throws IOException {

        File file = new File(homeWorkSubmitPath+File.separator+name);
        if (!file.exists()) {
            return new Resource("文件不存在");
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + name);
        byte[] readBytes = FileUtil.readBytes(file);
        OutputStream os = response.getOutputStream();
        os.write(readBytes);
        return new Resource("下载成功");
    }


    @ResponseBody
    @DeleteMapping("/deleteHomeworkRelease/{fileName}")
    public Resource deleteHomeworkRelease(HttpServletResponse response, @PathVariable String fileName)  {
        File file = new File(homeWorkReleasePath + File.separator + fileName);
        // 判断文件不为null或文件目录存在
        if (file == null || !file.exists()) {
            return new Resource("文件不存在");
        }
        try {
            if (file.isFile()) file.delete();
            else {
                // 文件夹, 需要先删除文件夹下面所有的文件, 然后删除
                for (File temp : file.listFiles()) {
                    temp.delete();
                }
                file.delete();
            }
        } catch (Exception e) {
            return new Resource("删除失败");
        }
        return new Resource("删除成功");
    }
    @ResponseBody
    @DeleteMapping("/deleteHomeworkSubmit/{fileName}")
    public Resource deleteHomeworkSubmit(HttpServletResponse response, @PathVariable String fileName)  {
        File file = new File(homeWorkSubmitPath + File.separator + fileName);
        // 判断文件不为null或文件目录存在
        if (file == null || !file.exists()) {
            return new Resource("文件不存在");
        }
        try {
            if (file.isFile()) file.delete();
            else {
                // 文件夹, 需要先删除文件夹下面所有的文件, 然后删除
                for (File temp : file.listFiles()) {
                    temp.delete();
                }
                file.delete();
            }
        } catch (Exception e) {
            return new Resource("删除失败");
        }
        return new Resource("删除成功");
    }

}
