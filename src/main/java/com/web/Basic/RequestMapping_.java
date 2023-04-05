package com.web.Basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user")
public class RequestMapping_ {
//    如果没有设置request类型，默认是get和post
    @RequestMapping(value = "/buy")
    public String buy(){
        System.out.println("购买成功");
        return "success";
    }
//    请求该目标方法时候必须给定book_id参数
    @RequestMapping(value = "/find",params = "book_id")
    public String search(String book_id){
        System.out.println("搜索成功");
        return "success";
    }
//    value=/message/**,可以匹配/user/message/aa/bb/ccc，可以匹配多层
//    value=/message
//    @RequestMapping(value = "/buy/a")
    @PostMapping(value = "/buy/a")
    public String buy2(){
        System.out.println("购买成功");
        return "success";
    }

    @RequestMapping(value = "/reg/{username}/{userid}")
    public String reg(@PathVariable("username") String name, @PathVariable("userid") String id){
        System.out.println(name+id);
        return "success";
    }
}
