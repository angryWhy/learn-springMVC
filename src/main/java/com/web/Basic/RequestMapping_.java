package com.web.Basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user")
public class RequestMapping_ {
//    如果没有设置request类型，默认是get和post
    @RequestMapping(value = "/buy",method = RequestMethod.POST)
    public String buy(){
        System.out.println("购买成功");
        return "success";
    }
}
