package com.hyl.util;

import com.hyl.pojo.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ToMap {
    public static Map<String, Object> toClassMap(Map<String, Object> map, Class<?> c) {
        Map<String, Object> map1 = new HashMap<>();
        Field[] declaredFields = c.getDeclaredFields();
        for (Field field : declaredFields) {
            map1.put(camelCaseToUnderscore(field.getName()), map.get(field.getName()));
        }
        return map1;
    }

    public static String camelCaseToUnderscore(String str){
        switch (str){
            case "teacherId": str = "teacher_id";break;
            case "startTime": str = "start_time"; break;
            case "endTime": str = "end_time"; break;
            case "courseId": str = "course_id";break;
            case "studentId": str = "student_id";break;
        }
        return str;
    }

    public static Map<String, Object> getClassMap(Map<String, Object> map, int identity) {
        Map<String, Object> commonMap = null;
        switch (identity) {
            case 0:
                commonMap = ToMap.toClassMap(map, User.class);
                break;
            case 1:
                commonMap = ToMap.toClassMap(map, Admin.class);
                break;
            case 2:
                commonMap = ToMap.toClassMap(map, Teacher.class);
                break;
            case 3:
                commonMap = ToMap.toClassMap(map, Student.class);
                break;
            case 4:
                commonMap = ToMap.toClassMap(map, Course.class);
                break;
        }
        return commonMap;
    }
}
