package com.example.controller;

import com.example.domain.student.Student;
import com.example.entity.StdJson;
import com.example.entity.request.StudentRequest;
import com.example.intercepter.LoginRequired;
import com.example.service.transition.TestTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by cicada on 2019/11/8.
 */
@RestController
@RequestMapping("/test")
public class TestTransactionController {

    private final Logger log = LoggerFactory.getLogger(TestTransactionController.class);


    @Autowired
    private TestTransaction testTransaction;

    @RequestMapping("/transation")
    @ResponseBody
    @LoginRequired
    public StdJson testInsert() throws Exception {
        log.info("测试查询，请求数据：{}");
//        int num =12/0;
        /*if (true){
            throw new Exception("我就是想异常");
        }*/
//        List<UserLogin> userLogins = userService.queryList();
//        List<Student> studentList = studentService.queryList();
        boolean b = testTransaction.testInsert();
        return StdJson.ok(b);
    }
}
