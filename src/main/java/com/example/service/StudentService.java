package com.example.service;

import com.example.entity.Student;
import com.example.exception.BizException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicada on 2019/8/21.
 */
@Component
public class StudentService {



    public List<Student> getStudentInfo(){

        List<Student> studentList = new ArrayList<>();

        Student dw = new Student();
        dw.setId(1);
        dw.setStudentName("王致和");
        studentList.add(dw);
        Student xs = new Student();
        xs.setId(1);
        xs.setStudentName("杨小五");
        studentList.add(xs);
        /*if (true){
            throw new BizException(1011,"不好思，服务挂了");
        }*/
        return studentList;
    }
}
