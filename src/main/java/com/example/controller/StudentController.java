package com.example.controller;

import com.example.entity.StdJson;
import com.example.entity.request.StudentRequest;
import com.example.intercepter.LoginRequired;
import com.example.service.student.IStudentService;
import com.example.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicada on 2019/8/21.
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    private final Logger log = LoggerFactory.getLogger(StudentController.class);

    /*@Autowired
    private StudentService studentService;*/
    @Autowired
    IUserService userService;

    @Autowired
    IStudentService studentService;


    @RequestMapping("/queryAllStudents")
    @ResponseBody
    @LoginRequired
    public StdJson queryStudentInfo(StudentRequest studentRequest) throws Exception {
        log.info("查询学生信息，请求数据：{}",studentRequest);
//        int num =12/0;
        /*if (true){
            throw new Exception("我就是想异常");
        }*/
//        List<UserLogin> userLogins = userService.queryList();
        List<com.example.domain.student.Student> studentList = studentService.queryList();
        return StdJson.ok(studentList);
    }
}
