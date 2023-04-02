package com.lagou.service.Impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        List<Course> courseByCondition = courseMapper.findCourseByCondition(courseVO);
        return courseByCondition;
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) {
        //封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(courseVO,course);
        //补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        //保存课程
        courseMapper.saveCourse(course);
        int id = course.getId();
        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVO,teacher);
        //补全讲师信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);
        //保存讲师信息
        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseVO findCourseById(Integer id) {
        CourseVO courseById = courseMapper.findCourseById(id);
        return courseById;
    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) {
        //封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(courseVO,course);
        //补全信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        //保存课程信息
        courseMapper.updateCourse(course);
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVO,teacher);
        teacher.setCourseId(course.getId());
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
    }

    @Override
    public void updateCourseStatus(int id, int status) {
        Course course = new Course();
        Date date = new Date();
        course.setUpdateTime(date);
        course.setStatus(status);
        course.setId(id);
        courseMapper.updateCourseStatus(course);
    }
}
