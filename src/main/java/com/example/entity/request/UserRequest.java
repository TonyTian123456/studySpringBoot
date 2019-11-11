package com.example.entity.request;

import lombok.Data;

/**
 * Created by cicada on 2019/8/21.
 */
@Data
public class UserRequest {

    private  long id;

    private  String userId;

    private String  phoneNum;

    private String password;
}
