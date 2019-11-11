package com.example.controller;

import com.example.constants.CurrentUserConstants;
import com.example.entity.StdJson;
import com.example.entity.Student;
import com.example.entity.data.User;
import com.example.entity.request.StudentRequest;
import com.example.entity.request.UserRequest;
import com.example.exception.BizException;
import com.example.exception.ErrCode;
import com.example.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


/**
 * Created by cicada on 2019/8/21.
 */
@RestController
@RequestMapping("/loginController")
public class LoginController {

    @Autowired
    private RedisTemplate redisTemplate;

    private final Logger log = LoggerFactory.getLogger(TestBootController.class);

    @RequestMapping("/login")
    @ResponseBody
    public StdJson login(@RequestBody UserRequest userRequest) throws Exception {
        log.info("登陆请求，请求数据：{}",userRequest);
        if (StringUtils.isBlank(userRequest.getPassword()) || StringUtils.isBlank(userRequest.getUserId())){
            throw new BizException(ErrCode.USER_INFO_IS_EMPTY);
        }
        User user = new User();
        user.setPhoneNum("17601028036");
        user.setUserId(3765l);
        user.setUserName("我是个测试数据");
        user.setIdentityCard("41142120000521x072");
        if (null != user){
            String token= TokenUtils.createJwtToken(user);
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("token",token);
            map.put("user",user);
            redisTemplate.opsForValue().set(CurrentUserConstants.CHECK_TOKEN_INFO,user.getUserId()+user.getPhoneNum()+user.getIdentityCard());
            return  StdJson.ok(map);
        }else {
            throw  new Exception("登陆失败，用户名或密码不正确");
        }
    }
}
