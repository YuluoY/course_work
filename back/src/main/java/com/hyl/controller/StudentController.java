package com.hyl.controller;

import com.hyl.dao.CommonDao;
import com.hyl.pojo.Pagination;
import com.hyl.pojo.Student;
import com.hyl.pojo.User;
import com.hyl.service.CommonService;
import com.hyl.service.StudentService;
import com.hyl.service.UserService;
import com.hyl.util.ToMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;
    @Resource
    private UserService userService;
    @Resource
    private CommonService commonService;


    /*
     * @Desc：通过id删除一个学生的所有信息，直接调用删除一条user信息，
     *      数据库通过外键关系级联删除。
     * */
    @DeleteMapping("/deleteById/{id}")
    @ResponseBody
    public int deleteStudentById(@PathVariable int id) {
        return userService.delUser(id);
    }

    @GetMapping("/findStuCourseById")
    @ResponseBody
    public List<?> findStuCourseById(@RequestParam int id) {
        return studentService.findStuCourseById(id);
    }

    @GetMapping("/findCurriculum")
    @ResponseBody
    public List<?> findCurriculum() {
        return studentService.findCurriculum();
    }

    @GetMapping("/findStuToCourseMappingById")
    @ResponseBody
    public List<?> findStuToCourseMappingById(@RequestParam int id) {
        return studentService.findStuToCourseMappingById(id);
    }

    @PostMapping("/addCourse")
    @ResponseBody
    public int addCourse(@RequestBody Map<String, Integer> map) {
        return studentService.addCourse(map.get("sId"), map.get("cId"));
    }

    @DeleteMapping("/cancelSelectedCourse")
    @ResponseBody
    public int cancelSelectedCourse(@RequestParam int sId, @RequestParam int cId) {
        return studentService.cancelSelectedCourse(sId, cId);
    }

    @PatchMapping("/updatePersonalInfo")
    @ResponseBody
    public int updatePersonalInfo(@RequestBody Map<String, Object> map) {
        String password = (String) map.get("password");
        int identity = (int) map.get("identity");
        int id = (int) map.get("id");
        if (!("".equals(password))) {
            Map<String, Object> userMap = ToMap.toClassMap(map, User.class);
            commonService.updateInfo(userMap, 0, id);
        }
        Map<String, Object> studentMap = ToMap.toClassMap(map, Student.class);
        return commonService.updateInfo(studentMap, identity, id);
    }
}
