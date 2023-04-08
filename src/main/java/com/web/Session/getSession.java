package com.web.Session;

import com.web.entity.Master;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/session")
@Controller
public class getSession {
    @RequestMapping("/s1")
    public String s1(Master master, HttpSession httpSession){
        //master默认放在request中，现在要放入到session中
        httpSession.setAttribute("master",master);
        return "success";
    }
}
