package com.lagou.service;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService{
    public List<Course> findCourseByCondition(CourseVO courseVO);
    public void saveCourseOrTeacher(CourseVO courseVO);
    public CourseVO findCourseById(Integer id);
    public void updateCourseOrTeacher(CourseVO courseVO);
    public void updateCourseStatus(int id,int status);
}
