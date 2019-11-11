package com.example.service.student.impl;

import com.example.domain.student.Student;
import com.example.domain.student.StudentExample;

import com.example.mapper.student.StudentMapper;
import com.example.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cicada on 2019/9/10.
 */
@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> queryList() {
        StudentExample example =new StudentExample();
        example.createCriteria().andIdIsNotNull();
        return studentMapper.selectByExample(example);
    }
}
