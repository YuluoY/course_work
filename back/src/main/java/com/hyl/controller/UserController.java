package com.hyl.controller;

import com.hyl.pojo.User;
import com.hyl.service.CommonService;
import com.hyl.service.UserService;
import com.hyl.util.IDGenerator;
import com.hyl.util.ToMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private CommonService commonService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public Map<String, Object> userLogin(@RequestBody User user, HttpServletResponse res) {
        User user2 = userService.login(user.getId(), user.getPassword());
        if (user2 == null) {
            res.setStatus(401);
            return null;
        }
        String token = IDGenerator.genID();
        user2.setToken(token);
        System.out.println(user2);
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("password", user2.getPassword());
        userMap.put("identity", user2.getIdentity());
        userMap.put("token", user2.getToken());
        commonService.updateInfo(userMap, 0, user2.getId());
        Map<String, Object> map = userService.findUserInfo(user2.getId(), user2.getIdentity());
        map.put("identity", user2.getIdentity());
        return map;
    }

    @PatchMapping("/update")
    @ResponseBody
    public int userUpdate(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<?> findAllUser() {
        return userService.findAll();
    }

    @GetMapping("/findAllCourse")
    @ResponseBody
    public List<?> findAllCourseInfo() {
        return userService.findAllCourseInfo();
    }

    @GetMapping("/findAllSchool")
    @ResponseBody
    public List<?> findAllSchoolInfo() {
        return userService.findAllSchool(new HashMap<>(), 6);
    }
}
