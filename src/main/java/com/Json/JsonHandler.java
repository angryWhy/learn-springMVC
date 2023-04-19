package com.Json;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
//@ResponseBody可以注解到类上，直接对所有方法弄

//使用@RestController，就有了Controller和ResponseBody两个注解
@RestController

public class JsonHandler {
    @RequestMapping(value = "getJson")
    @ResponseBody
    public Dog getDog(){
        Dog dog = new Dog();
        dog.setId(1);
        dog.setName("大黄");
        return dog;
    }
   @RequestMapping(value = "/save2")
    @ResponseBody
    //将前台传过来的数据，以JSON形式返回
    //@RequestBody User user,在形参上加上这个注解，SpringMVC将提交的数据字符串，填充给指定的javabean
    public User save(@RequestBody User user){
        return user;
    }
    @RequestMapping(value = "/save3")
    @ResponseBody
    //将前台传过来的数据，以JSON形式返回
    //@RequestBody User user,在形参上加上这个注解，SpringMVC将提交的数据字符串，填充给指定的javabean
    public List<User> save3(@RequestBody User user){
        List<User> list = new ArrayList<>();
        list.add(new User("123","321"));
        list.add(new User("555","555"));
        list.add(new User("666","666"));
        return list;
    }

    @RequestMapping(value = "/downFile")

    //将前台传过来的数据，以JSON形式返回，文件下载
    public ResponseEntity<byte[]> save4(HttpSession session){
        //得到下载文件的InputStream
        InputStream resourceAsStream = session.getServletContext().getResourceAsStream("/images/Pic.png");
        try {
            //开辟存放byte[]数组，支持存放数据
            byte[] bytes = new byte[resourceAsStream.available()];
            //将要下载文件的数据，要读入到byte数组
            resourceAsStream.read(bytes);
            //创建返回的httpStatus
            HttpStatus httpStatus = HttpStatus.OK;
            //创建headers
            HttpHeaders httpHeaders = new HttpHeaders();
            //attachment附件形式处理
            httpHeaders.add("Content-Disposition","attachment;filename=2.jpg");
            //构建entity对象
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes, httpHeaders, httpStatus);
            //找不到文件，要重启，rebuild项目
            return responseEntity;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //文件上传
    @RequestMapping("/uploadFile")
    public String fileUpload(@RequestParam(value = "file") MultipartFile multipartFile, HttpServletRequest httpServletRequest) throws IOException {
        //接受到文件名字
        String originalFilename = multipartFile.getOriginalFilename();
        System.out.println("文件名字"+originalFilename);
        String fullPath = httpServletRequest.getServletContext().getRealPath("/img" + originalFilename);
        //上传文件
        File file = new File(fullPath);
        //将上传的文件，转存到file
        multipartFile.transferTo(file);
        return "success";
    }
}
