package com.web.ModelAttribute;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/ModelAttr")
public class getModelAttribute {
    @RequestMapping("/m1")

    public String m1(){

        return "success";
    }
    @ModelAttribute
    //调用该方法的时候，会执行该前置方法
    public void ma(){
        System.out.println("前置方法ModelAttribute执行！");
    }
}
