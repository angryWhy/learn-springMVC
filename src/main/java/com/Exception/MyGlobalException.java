package com.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

//全局异常类
@ControllerAdvice
public class MyGlobalException  {
    //不管那个Handler抛出的异常，都可以捕获
    @ExceptionHandler({NumberFormatException.class,NullPointerException.class})
    public String globalException(Exception exception, HttpServletRequest httpServletRequest){
        System.out.println("全局处理异常---"+exception.getMessage());
        return "success";
    }
}
