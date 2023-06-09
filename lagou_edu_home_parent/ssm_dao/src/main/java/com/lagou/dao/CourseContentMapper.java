package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    /*根据课程ID查询关联的章节信息及章节信息关联的课时信息*/
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);
    /*回显章节对应课程信息*/
    public Course findCourseByCourseId(Integer id);
    /*新增章节信息*/
    public void saveSection(CourseSection courseSection);
    /*更新章节信息*/
    public void updateSection(CourseSection courseSection);
    /*修改章节状态*/
    public void updateSectionstatus(CourseSection courseSection);
}
