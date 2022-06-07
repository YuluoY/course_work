package com.hyl.service.impl;


import com.hyl.dao.CommonDao;
import com.hyl.dao.StudentDao;
import com.hyl.pojo.Pagination;
import com.hyl.pojo.Student;
import com.hyl.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Resource
    private CommonDao commonDao;


    @Override
    public List<?> findStuCourseById(int id) {
        return studentDao.findStuCourseById(id);
    }

    @Override
    public List<?> findCourseScoreById(int id) {
        return studentDao.findCourseScoreById(id);
    }

    @Override
    public List<?> findCurriculum() {
        return studentDao.findCurriculum();
    }

    @Override
    public List<?> findStuToCourseMappingById(int id) {
        return studentDao.findStuToCourseMappingById(id);
    }

    @Override
    public int addCourse(int sId, int cId) {
        return studentDao.addCourse(sId, cId);
    }

    @Override
    public int cancelSelectedCourse(int sId, int cId) {
        return studentDao.cancelSelectedCourse(sId, cId);
    }

    @Override
    public int updatePersonalInfo(Map<String, Object> map, int identity, int id) {
        return commonDao.updateOperation(map, identity, id);
    }
}
