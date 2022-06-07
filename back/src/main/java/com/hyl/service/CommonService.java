package com.hyl.service;

import com.hyl.pojo.Pagination;

import java.util.List;
import java.util.Map;

public interface CommonService {
    int addInfo(Map<String, Object> map, int identity);
    int updateInfo(Map<String, Object> map, int identity, int id);
    int deleteById(int id, int identity);
    List<?> paginationQuery(Pagination pagination);
    List<?> findAllInfo(int identity);
}
