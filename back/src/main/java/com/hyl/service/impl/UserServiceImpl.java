package com.hyl.service.impl;

import com.hyl.dao.CommonDao;
import com.hyl.dao.UserDao;
import com.hyl.pojo.User;
import com.hyl.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private CommonDao commonDao;

    @Override
    public User login(int id, String password) {
        return userDao.userLogin(id, password);
    }

    @Override
    public Map findUserInfo(int id, int identity) {
        return userDao.userInfo(id, identity);
    }

    @Override
    public int addUser(Map<String, Object> map, int identity) {
        return commonDao.addOperation(map, identity);
    }

    @Override
    public int delUser(int id) {
        return commonDao.deleteById(id, 0);
    }

    @Override
    public int updateUser(User user) {
        System.out.println("user===>" + user);
        return userDao.updateUser(user);
    }

    @Override
    public List<?> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<?> findAllCourseInfo() {
        return userDao.findAllCourseInfo();
    }

    @Override
    public List<?> findAllSchool(Map<String, Object> map, int identity) {
        return commonDao.selectOperation(map, identity);
    }
}
