package com.web.HomeWork;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "loginUser")
@Controller
public class loginHandler {
    @RequestMapping(value = "l1")
    public String login(User user){
        if(user.getName().equals("wzx")&&user.getPwd().equals("123")){
            return "success2";
        }else{
            return "login_err";
        }
    }
}
