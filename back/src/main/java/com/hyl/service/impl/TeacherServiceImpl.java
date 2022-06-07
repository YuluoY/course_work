package com.hyl.service.impl;

import com.hyl.dao.CommonDao;
import com.hyl.dao.TeacherDao;
import com.hyl.pojo.Pagination;
import com.hyl.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private CommonDao commonDao;


    @Override
    public List<?> findTeachers() {
        return commonDao.findAll(2);
    }

    @Override
    public List<?> findTeacherToSchool() {
        return teacherDao.findTeacherToSchool();
    }

    @Override
    public List<?> findCourseList(int id) {
        return teacherDao.findCourseList(id);
    }

    @Override
    public List<?> findSelectedCourseStudents(int tId, int cId) {
        return teacherDao.findSelectedCourseStudents(tId, cId);
    }

    @Override
    public int reviewCourse(int cId, int sId, int score) {
        return teacherDao.reviewCourse(cId, sId, score);
    }

    @Override
    public int insertRequiredCourse(int cId, int sId) {
        return teacherDao.insertRequiredCourse(cId, sId);
    }


}
