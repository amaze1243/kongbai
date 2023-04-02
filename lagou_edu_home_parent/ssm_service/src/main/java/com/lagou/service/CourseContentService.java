package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    /*查询章节及章节相关课时信息*/
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);
    /*回显章节对应课程信息*/
    public Course findCourseByCourseId(Integer id);
    /*保存章节*/
    public void saveSection(CourseSection courseSection);
    /*更新章节信息*/
    public void updateSection(CourseSection courseSection);
    /*修改章节状态*/
    public void updateSectionStatus(Integer id,Integer status);
}
