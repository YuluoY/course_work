package com.hyl.service.impl;

import com.hyl.dao.CommonDao;
import com.hyl.pojo.Pagination;
import com.hyl.service.CommonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    @Resource
    private CommonDao commonDao;

    @Override
    public int addInfo(Map<String, Object> map, int identity) {
        return commonDao.addOperation(map, identity);
    }


    @Override
    public int updateInfo(Map<String, Object> map, int identity, int id) {
        return commonDao.updateOperation(map, identity, id);
    }

    @Override
    public int deleteById(int id, int identity) {
        return commonDao.deleteById(id, identity);
    }

    @Override
    public List<?> paginationQuery(Pagination pagination) {
        return commonDao.paginationQueryOperation(pagination);
    }

    @Override
    public List<?> findAllInfo(int identity) {
        return commonDao.findAll(identity);
    }
}
