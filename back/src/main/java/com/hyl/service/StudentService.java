package com.hyl.service;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<?> findStuCourseById(int id);
    List<?> findCourseScoreById(int id);
    List<?> findCurriculum();
    List<?> findStuToCourseMappingById(int id);
    int addCourse(int sId, int cId);
    int cancelSelectedCourse(int sId, int cId);
    int updatePersonalInfo(Map<String, Object> map,
                           int identity,
                           int id);
}
