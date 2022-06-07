package com.hyl.controller;

import com.hyl.pojo.Pagination;
import com.hyl.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private TeacherService teacherService;

    @GetMapping("/findTeachers")
    @ResponseBody
    public List<?> findTeachers() {
        return teacherService.findTeachers();
    }

    @GetMapping("/findTeacherToSchool")
    @ResponseBody
    public List<?> findTeacherToSchool() {
        return teacherService.findTeacherToSchool();
    }

    @GetMapping("/findCourseList")
    @ResponseBody
    public List<?> findCourseList(@RequestParam int id) {
        return teacherService.findCourseList(id);
    }

    @GetMapping("/findSelectedCourseStudents")
    @ResponseBody
    public List<?> findSelectedCourseStudents(@RequestParam int tId,
                                              @RequestParam int cId) {
        return teacherService.findSelectedCourseStudents(tId, cId);
    }

    @PatchMapping("/reviewCourse")
    @ResponseBody
    public int reviewCourse(@RequestBody Map<String, Integer> map) {
        int cId = map.get("cId");
        int sId = map.get("sId");
        int score = map.get("score");
        return teacherService.reviewCourse(cId, sId, score);
    }

    @PatchMapping("/batchInsertRequiredCourse")
    @ResponseBody
    public int batchInsertRequiredCourse(@RequestBody Map<String, ?> map) {
        int res = 0;
        int cId = (Integer) map.get("cId");
        ArrayList<?> sIds = (ArrayList<?>) map.get("sIds");
        for (int i = 0; i < sIds.size(); i++) {
            res = teacherService.insertRequiredCourse(cId, (Integer) sIds.get(i));
        }
        return res;
    }
}
