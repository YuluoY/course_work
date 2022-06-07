package com.hyl.controller;

import com.hyl.pojo.*;
import com.hyl.service.*;
import com.hyl.util.ToMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rest")
public class CommonController {
    @Resource
    private CommonService commonService;
    @Resource
    private UserService userService;

    /*
     * @Method：post。
     * @Params：map：它是一个对象，包含着你要传入的信息。根据类的字段规定信息。
     * @Desc：如果你要添加的是管理员、学生或教师信息，那么你需要多提供两个字段，
     *       即：password与identity，password将会存入tb_user表中。而identity
     *       用于判断，你想要添加的是哪个表信息，它会针对表进行类的字段Map化，通过ToMap类中的
     *       静态方法toClassMap，我们可以将前端传入的对象值抽取出来进行根据类字段赋值，若前端传入对象中没有字段值
     *       那么将默认为null。
     * @Special：需要注意的是，数据库中表与表之间设置的外键，只有添加了一条user信息才能进行添加学生、教师或管理员信息。
     *           故通过这个接口，我们可以跳过那一步，直接进行学生、老师或管理员的信息添加，而user信息的添加已被设置，无需再手动。
     * */
    @PostMapping("/add")
    @ResponseBody
    public int addInfo(@RequestBody Map<String, Object> map,
                       HttpServletResponse res) {
        try {
            int identity = (int) map.get("identity");
            Map<String, Object> commonMap = null;
            Map<String, Object> userMap = null;
            if (identity == 1 || identity == 2 || identity == 3) {
                userMap = new HashMap<>();
                userMap.put("id", (int) map.get("id"));
                userMap.put("password", (String) map.get("password"));
                userMap.put("identity", identity);
            }
            switch (identity) {
                // 添加管理员信息
                case 1:
                    commonMap = ToMap.toClassMap(map, Admin.class);
                    userService.addUser(userMap, 0); // 0表示添加user信息
                    break;
                // 添加教师信息
                case 2:
                    commonMap = ToMap.toClassMap(map, Teacher.class);
                    userService.addUser(userMap, 0);
                    break;
                // 添加学生信息
                case 3:
                    commonMap = ToMap.toClassMap(map, Student.class);
                    userService.addUser(userMap, 0);
                    break;
                // 添加课程信息
                case 4:
                    commonMap = ToMap.toClassMap(map, Course.class);
                    break;
                // 添加班级信息
                case 5:
                    break;
            }
            return commonService.addInfo(commonMap, identity);
        } catch (Exception e) {
            res.setStatus(402);
            return 0;
        }
    }

    @GetMapping("/all/{identity}")
    @ResponseBody
    public List<?> findAllInfo(@PathVariable int identity) {
        return commonService.findAllInfo(identity);
    }

    @PatchMapping("/update")
    @ResponseBody
    public int modifyInfo(@RequestBody Map<String, Object> map,
                          HttpServletResponse res) {
        int identity = (int) map.get("identity");
        Map<String, Object> commonMap = ToMap.getClassMap(map, identity);
        if(!("".equals(map.get("password")))){
            Map<String, Object> userMap = ToMap.getClassMap(map, 0);
            commonService.updateInfo(userMap, 0, (int) userMap.get("id"));
        }
        return commonService.updateInfo(commonMap, identity, (int) commonMap.get("id"));
    }

    @DeleteMapping("/deleteById/{id}/{identity}")
    @ResponseBody
    public int deleteById(@PathVariable int id, @PathVariable int identity,
                          HttpServletResponse res) {
        int result = 0;
        if (identity == 0 || identity == 1 || identity == 2 || identity == 3) {
            result = commonService.deleteById(id, 0);
        } else {
            result = commonService.deleteById(id, identity);
        }
        if(result == 0){
            res.setStatus(405);
        }
        return result;
    }

    @GetMapping("/pagination")
    @ResponseBody
    public List<?> paginationQueryInfo(@RequestBody Pagination pagination){
        return commonService.paginationQuery(pagination);
    }


 }
