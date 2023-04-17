package com.Json;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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
        System.out.println(1111111111);
        return user;
    }
}
