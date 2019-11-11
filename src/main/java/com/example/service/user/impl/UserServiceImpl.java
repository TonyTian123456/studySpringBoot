package com.example.service.user.impl;

import com.example.domain.user.UserLogin;
import com.example.domain.user.UserLoginExample;
import com.example.mapper.user.UserLoginMapper;
import com.example.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cicada on 2019/9/9.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    public List<UserLogin> queryList() {
        UserLoginExample example = new UserLoginExample();
        example.createCriteria().andDescriptionIsNotNull();
        return userLoginMapper.selectByExample(example);
    }
}
