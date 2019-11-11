package com.example.controller;

import com.example.baiduTest.FaceAdd;
import com.example.entity.StdJson;
import com.example.intercepter.LoginRequired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by cicada on 2019/11/7.
 */
@RestController
@RequestMapping("/face")
public class FaceController {

    private final Logger log = LoggerFactory.getLogger(StudentController.class);

    private String  accessToken = "24.e7a8174ba7c2e30462c4d8ff912bf43e.2592000.1575702726.282335-17663176";

    String  filePath="/Users/cicada/Desktop";

    @Autowired
    private FaceAdd faceAdd;

    @RequestMapping("/add")
    @ResponseBody
//    @LoginRequired
    public StdJson add(@RequestParam MultipartFile file ) throws Exception {
        log.info("新增人脸照片，请求数据：{}");
//        int num =12/0;
        /*if (true){
            throw new Exception("我就是想异常");
        }*/
//        List<UserLogin> userLogins = userService.queryList();
        InputStream inputStream = file.getInputStream();
        String fileName =file.getOriginalFilename();
        String result = faceAdd.add(filePath+"/"+fileName, accessToken);

        return StdJson.ok(result);
    }

    @RequestMapping("/list")
    @ResponseBody
//    @LoginRequired
    public StdJson list() throws Exception {
        log.info("查询人脸list，请求数据：{}");
//        int num =12/0;
        /*if (true){
            throw new Exception("我就是想异常");
        }*/
//        List<UserLogin> userLogins = userService.queryList();
        String result = faceAdd.getList(accessToken);

        return StdJson.ok(result);
    }

    @RequestMapping("/search")
    @ResponseBody
//    @LoginRequired
    public StdJson search(@RequestParam MultipartFile file ) throws Exception {
        log.info("查询人脸list，请求数据：{}");
//        int num =12/0;
        /*if (true){
            throw new Exception("我就是想异常");
        }*/
//        List<UserLogin> userLogins = userService.queryList();
//        String result = faceAdd.search(file.,accessToken);
        String fileName =file.getOriginalFilename();
        String result = faceAdd.search(filePath+"/"+fileName, accessToken);

        return StdJson.ok(result);
    }
}
