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
}
