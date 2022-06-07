package com.hyl.dao;

import com.hyl.pojo.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommonDao {
    // 通用CRUD
    /*
     * @param: identity
     *   0：管理员
     *   1：用户
     *   2：教师
     *   3：学生
     *   4: 课程
     * */
    int addOperation(@Param("addMap") Map<String, Object> map,
                     @Param("identity") int identity);

    int deleteOperation(@Param("delMap") Map<String, Object> map,
                        @Param("identity") int identity);

    int updateOperation(@Param("updateMap") Map<String, Object> map,
                        @Param("identity") int identity,
                        @Param("uniqueVal") int id);

    List<?> selectOperation(@Param("selectMap") Map<String, Object> map,
                            @Param("identity") int identity);


    // 通用分页查询
    List<?> paginationQueryOperation(Pagination pagination);

    // 通过id删除行信息
    int deleteById(@Param("id") int id,
                   @Param("identity") int identity);

    // 获取表中的所有信息
    List<?> findAll(@Param("identity") int identity);
}
