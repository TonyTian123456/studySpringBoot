package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cicada on 2019/9/19.
 */
@Controller
@RequestMapping("/webSocket")
public class HiController {

    @RequestMapping("/hi")
    public String hi(){
        return "index";
    }
}
