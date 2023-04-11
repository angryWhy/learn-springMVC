package com.MyspringMVC.servlet;

import com.MyspringMVC.annotation.Controller;
import com.MyspringMVC.annotation.RequsetMapping;
import com.MyspringMVC.contetx.SpringContext;
import com.MyspringMVC.handler.Handler;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DispatchServlet extends HttpServlet {
    //记录映射关系
    private List<Handler> handlerList = new ArrayList<>();
    SpringContext springContext;
    @Override
    public void init() throws ServletException {
         springContext = new SpringContext();
        try {
            //初始化
            springContext.init();
            //完成映射
            initHandleMapping();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("自定义的DispatchServlet---doGet调用");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("自定义的DispatchServlet---doPost调用");
        //调用请求分发
        try {
            executeDispatch(req, resp);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    //编写方法，完成url和终止其方法的映射
    private void initHandleMapping(){
        if(springContext.ioc.isEmpty()){
            return;
        }
        for (Map.Entry<String,Object> entry: springContext.ioc.entrySet()){
            Class<?> aClass = entry.getValue().getClass();
            if(aClass.isAnnotationPresent(Controller.class)){
                Method[] methods = aClass.getMethods();
                for(Method m:methods){
                    if(m.isAnnotationPresent(RequsetMapping.class)){
                        RequsetMapping requsetMapping = m.getAnnotation(RequsetMapping.class);
                        //getServletContext().getContextPath(),可以拿到工程路径，动态获取
                        String url = getServletContext().getContextPath() + requsetMapping.value();
                        Handler handler = new Handler(url, entry.getValue(), m);
                        handlerList.add(handler);
                    }
                }
            }
        }
    }

    //根据request对象，返回handle对象
    public Handler getHandler(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        //先获取用户请求的uri，/springmvc/monster/list
        //遍历handleList，找到与用户请求相同的
        for(Handler handler:handlerList){
            if(requestURI.equals(handler.getUrl())){
                return handler;
            }
        }
        return null;
    }

    //编写方法，完成分发请求
    private  void executeDispatch(HttpServletRequest request,HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException {
        //传入request对象，然后进行查找
        Handler handler = getHandler(request);
        if(handler==null){
            //请求的资源没找到,404
            response.getWriter().print("<h1>404</h1>");
        }else{
            Method method = handler.getMethod();
            //调用方法
            method.invoke(handler.getHandler(),request,response);
        }
    }
}
