package com.hyl.service;

import com.hyl.pojo.Pagination;

import java.util.List;

public interface TeacherService {
    List<?> findTeachers();
    List<?> findTeacherToSchool();
    List<?> findCourseList(int id);
    List<?> findSelectedCourseStudents(int tId, int cId);
    int reviewCourse(int cId, int sId, int score);
    int insertRequiredCourse(int cId, int sId);
}
