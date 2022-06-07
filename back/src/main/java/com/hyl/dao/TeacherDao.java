package com.hyl.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherDao {
    List<?> findTeacherToSchool();
    List<?> findCourseList(@Param("id") int id);
    List<?> findSelectedCourseStudents(@Param("tId") int tId,
                                       @Param("cId") int cId);
    int reviewCourse(@Param("cId") int cId,
                     @Param("sId") int sId,
                     @Param("score") int score);
    int insertRequiredCourse(@Param("cId") int cId,
                             @Param("sId") int sId);
}
