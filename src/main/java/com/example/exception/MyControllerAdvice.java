package com.example.exception;

import com.example.controller.TestBootController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

/**

 * @Description: 全局捕获异常和自定义全局捕获异常
 */
@ControllerAdvice  //不指定包默认加了@Controller和@RestController都能控制
//@ControllerAdvice(basePackages ="com.example.demo.controller")
public class MyControllerAdvice {
/*
    private final Logger log = LoggerFactory.getLogger(TestBootController.class);
    *//**
     * 全局异常处理，反正异常返回统一格式的map
     * @param ex
     * @return
     *//*
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String,Object> exceptionHandler(Exception ex){
        Map<String,Object> map  = new HashMap<String,Object>();
        map.put("code",-1);
        map.put("mag","服务被狗吃了");
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        log.info("统一日志处理：{}",ex.getMessage());
        ex.printStackTrace();
        return map;
     }*/
    }