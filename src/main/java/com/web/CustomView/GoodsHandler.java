package com.web.CustomView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/goodsView")
@Controller
public class GoodsHandler {
    @RequestMapping("/buy")
    public String buy(){
        System.out.println("执行自定义视图！");
        return "myView";
    }
    //演示重定向
    @RequestMapping("/order")
    public String order(){
        System.out.println("执行重定向！");
        //请求转发到myView.jsp页面,请求转发
        return "forward:/WEB-INF/pages/myView.jsp";
        //重定向，不可以重定向到WEB-INF目录下
        //return "redirect:/WEB-INF/pages/myView.jsp";
    }
}
