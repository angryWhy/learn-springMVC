package com.web.GetHeader;

import com.web.entity.Master;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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

    //request域
    @RequestMapping("/vote2")
    public String test2(Master master,HttpServletRequest request){
        //request可以放入自己的东西
        request.setAttribute("address","beijing");
        //对象是引用类型可以进行修改
        master.setName("我是新修改的！");
        //默放进request域的时候，是首字名小写，放进去的
        System.out.println(master);
        return"success";
    }
    //使用Servletapi获取提交的数据
    @RequestMapping("/vote4")
    public String test3(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("id");
        String password = request.getParameter("name");
        System.out.println(username+"-"+password);
        return"success";
    }
    @RequestMapping("/vote6")
    public String test6(Master master, Map<String,Object> map){
        //springMVC会遍历map，然后放到request域中
        map.put("address","伤害");
        //如果这样做的话，就会娶不到，给替换了
        map.put("master",null);
        //默放进request域的时候，是首字名小写，放进去的
        System.out.println(master);
        return"success";
    }
}
