package com.example.service.user;

import com.example.domain.user.UserLogin;

import java.util.List;

/**
 * Created by cicada on 2019/9/9.
 */
public interface IUserService {

    public   List<UserLogin>  queryList();

}
