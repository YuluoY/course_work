package com.hyl.service;

import com.hyl.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User login(int id, String password);

    Map<String, Object> findUserInfo(int id, int identity);

    int addUser(Map<String, Object> map, int identity);

    int delUser(int id);

    int updateUser(User user);

    List<?> findAll();

    List<?> findAllCourseInfo();

    List<?> findAllSchool(Map<String, Object> map, int identity);
}
