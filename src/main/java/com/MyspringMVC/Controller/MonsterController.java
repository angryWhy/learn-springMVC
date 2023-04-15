package com.MyspringMVC.Controller;

import com.MyspringMVC.Service.MonsterService;
import com.MyspringMVC.annotation.AutoWired;
import com.MyspringMVC.annotation.Controller;
import com.MyspringMVC.annotation.RequsetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
}
