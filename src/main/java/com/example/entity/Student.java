package com.example.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by cicada on 2019/8/21.
 */
@Data
public class Student implements Serializable{

    private long id;

    private String studentName;
}
