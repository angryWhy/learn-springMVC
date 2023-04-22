package com.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyException {
    //编写方法，模拟异常
    @RequestMapping("/exception")
    public String test01(Integer num){
        int i = 9/num;
        System.out.println(i);
        return "success";
    }


    //加上ExceptionHandler，参数是一个数组，添加上可能会出现的异常类
    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})
    public String localException(Exception exception, HttpServletRequest httpServletRequest){
        System.out.println(exception);
        httpServletRequest.setAttribute("reason",exception.getMessage());
        return "?";
    }
}
