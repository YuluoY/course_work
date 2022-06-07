package com.hyl.service.impl;

import com.hyl.dao.CommonDao;
import com.hyl.dao.CourseDao;
import com.hyl.dao.StudentDao;
import com.hyl.pojo.Pagination;
import com.hyl.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;

    @Resource
    private CommonDao commonDao;


}
