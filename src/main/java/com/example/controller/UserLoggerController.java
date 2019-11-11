package com.example.controller;

import com.example.domain.user.UserLogin;
import com.example.entity.StdJson;
import com.example.entity.request.StudentRequest;
import com.example.intercepter.LoginRequired;
import com.example.service.student.IStudentService;
import com.example.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by cicada on 2019/9/10.
 */
@RestController
@RequestMapping("/userLogger")
public class UserLoggerController {

    private final Logger log = LoggerFactory.getLogger(UserLoggerController.class);


    @Autowired
    private IUserService userService;


    @RequestMapping("/queryAllUserLoggers")
    @ResponseBody
    @LoginRequired
    public StdJson queryStudentInfo(StudentRequest studentRequest) throws Exception {
        log.info("查询登陆人信息，请求数据：{}",studentRequest);

        List<UserLogin> userLogins = userService.queryList();
        return StdJson.ok(userLogins);
    }
}
