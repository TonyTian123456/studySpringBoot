package com.example.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cicada on 2019/8/20.
 */
@RestController
@RequestMapping
public class TestBootController {

    @RequestMapping("/test")
    @ResponseBody
    public String home() {
        System.out.println();
        return "hello world";
    }
}
