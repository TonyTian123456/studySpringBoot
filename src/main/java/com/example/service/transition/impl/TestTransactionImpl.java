package com.example.service.transition.impl;

import com.example.domain.student.Student;
import com.example.domain.user.UserLogin;
import com.example.mapper.student.StudentMapper;
import com.example.mapper.user.UserLoginMapper;
import com.example.service.transition.TestTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cicada on 2019/11/8.
 */
@Service
public class TestTransactionImpl implements TestTransaction {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    @Transactional
    public boolean testInsert() {
        Student student = new Student();
        student.setStudent_id(1112l);
        student.setStudent_name("ceshiyixiahahahah");
        boolean res1 = studentMapper.insert(student)>0;
//        int i = 12 / 0;
        UserLogin userLogin =new UserLogin();
        userLogin.setDescription("这是测试的");
        userLogin.setIdentity_card("41142119890722");
        userLogin.setUser_id(2221);
        userLogin.setPhone_num("17601028036");
        userLogin.setUser_name("ceshiNamehahahah");
        return res1&&userLoginMapper.insert(userLogin)>0;
    }
}
