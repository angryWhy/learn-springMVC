package com.web.GetHeader;

import com.web.entity.Master;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/vote")
@Controller
public class GetHeader {
    @RequestMapping("/vote1")
//    required默认为true
//    使用这个注解了，求情的参数名和方法的形参名可以不一致
    public String test(@RequestParam(value = "name", required = false) String username, @RequestHeader("Accept-Encoding") String ae, @RequestHeader("Host") String host) {
        System.out.println(username+ae);
        return "success";
    }
    @RequestMapping("/vote2")
    public String test2(Master master){
        System.out.println(master);
        return"success";
    }
}
