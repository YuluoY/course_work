package com.hyl.dao;

import com.hyl.pojo.User;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public interface UserDao {
    User userLogin(@Param("id") int id,
                   @Param("password") String password);

    Map userInfo(@Param("id") int id,
                 @Param("identity") int identity);

    int updateUser(User user);

    List<?> findAll();

    List<?> findAllCourseInfo();

    List<?> findAllSchool();
}
