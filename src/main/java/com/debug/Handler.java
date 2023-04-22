package com.debug;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Handler {
    @RequestMapping("/debug")
    public ModelAndView hello(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");//对应到配置的pages文件
        modelAndView.addObject("name","我是名字");
        return modelAndView;
    }
}
