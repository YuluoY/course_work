package com.hyl.dao;

import com.hyl.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    int deleteStudent(int id);

    List<?> findStuCourseById(@Param("id") int id);

    List<?> findCourseScoreById(@Param("id") int id);

    List<?> findCurriculum();

    List<?> findStuToCourseMappingById(@Param("id") int id);

    int addCourse(@Param("sId") int sId, @Param("cId") int cId);

    int cancelSelectedCourse(@Param("sId") int sId, @Param("cId") int cId);

}
