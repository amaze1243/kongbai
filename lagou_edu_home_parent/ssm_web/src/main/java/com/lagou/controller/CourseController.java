package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        List<Course> courseByCondition = courseService.findCourseByCondition(courseVO);
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",courseByCondition);
        return responseResult;
    }
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request){
        //1.判断文件是否为空
        if(multipartFile.isEmpty()){
            throw new RuntimeException();
        }
        //2.获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));
        //3.获取原始文件名
        String originalFilename = multipartFile.getOriginalFilename();
        //4.生成新文件名
        String newFilename = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //5.文件上传
        String uploadpath = substring + "/upload/";
        File filePath = new File(uploadpath, newFilename);
        //如果目录不存在就创建目录
        if(!filePath.exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录"+filePath);
        }
        //图片真正上传
        try {
            multipartFile.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //6.将文件名与路径返回，进行响应
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName",newFilename);
        map.put("filePath","http://localhost:8080/upload/"+newFilename);
        ResponseResult re = new ResponseResult(true, 200, "图片上传成功", map);
        return re;
    }
    /*
        新增课程信息以及讲师信息
        新增课程信息以及修改课程信息写在同一个方法中
    */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO){
        ResponseResult responseResult;
        if (courseVO.getId()==null){
            courseService.saveCourseOrTeacher(courseVO);
            responseResult = new ResponseResult(true, 200, "添加成功", null);
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            responseResult = new ResponseResult(true, 200, "更新成功", null);
        }
            return responseResult;
    }
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVO courseById = courseService.findCourseById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "根据Id查询成功", courseById);
        return responseResult;
    }
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id,int status){
        courseService.updateCourseStatus(id, status);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", hashMap);
        return responseResult;
    }
}
