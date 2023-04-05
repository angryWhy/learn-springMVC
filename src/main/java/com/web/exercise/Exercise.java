package com.web.exercise;

import org.springframework.web.bind.annotation.RequestMapping;

public class Exercise {
    @RequestMapping(value = "/computer")
    public String use(String name){
        System.out.println("购买电脑！");
        return "success";
    }
}
