package com.MyspringMVC.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MonsterController {
    //列出所有妖怪的方法
    public void listMonster(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        //获取writer，返回信息
        PrintWriter writer = response.getWriter();
        writer.write("<h1>信息列表</h1>");
    }
}
