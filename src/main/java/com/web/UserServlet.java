package com.web;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class UserServlet {
    //编写方法，响应用户请求
    //用户在浏览器输入http://localhost：8080/web路径/login，就能访问到这个
    //相当于配置映射
    //return "login_ok";,表示将结果返回给视图解析器
    //根据配置，决定跳转到那个页面
    @RequestMapping(value = "/login")
    public String login(){
        System.out.println("login ok...");
        return "login_ok";
    }
}
