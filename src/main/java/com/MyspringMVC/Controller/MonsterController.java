package com.MyspringMVC.Controller;

import com.MyspringMVC.Service.MonsterService;
import com.MyspringMVC.annotation.*;
import com.formatData.Monster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller()
public class MonsterController {
    @AutoWired
    private MonsterService monsterService;
    //列出所有妖怪的方法
    @RequsetMapping(value = "/monster")
    public void listMonster(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        //获取writer，返回信息
        PrintWriter writer = response.getWriter();
        writer.write("<h1>信息列表</h1>");
    }
    @RequsetMapping(value = "/monster/login")
    public String login(@RequestParams(value = "mName") String name){
        boolean login = monsterService.login(name);
        if(login){
            return"forward:/success.jsp";
        }else{
            return"forward:/index.jsp";
        }
    }
    @RequsetMapping("/monster/list")
    @ResponseBody()
    public List<Monster> listMonsterByJson(HttpServletRequest request,HttpServletResponse response){
        List<Monster> monsters = monsterService.listMonsters();
        return monsters;
    }
}
