package com.MyspringMVC.servlet;

import com.MyspringMVC.annotation.Controller;
import com.MyspringMVC.annotation.RequestParams;
import com.MyspringMVC.annotation.RequsetMapping;
import com.MyspringMVC.contetx.SpringContext;
import com.MyspringMVC.handler.Handler;
import org.dom4j.DocumentException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DispatchServlet extends HttpServlet {
    //记录映射关系
    private List<Handler> handlerList = new ArrayList<>();
    SpringContext springContext;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //获取web.xml的contextConfigLocation,也就是<param-name>contextConfigLocation</param-name>
        String contextConfigLocation = servletConfig.getInitParameter("contextConfigLocation");
        springContext = new SpringContext(contextConfigLocation);
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
            //目标：将HttpServletRequest request,HttpServletResponse response，封装到参数数组
            //拿到目标方法参数信息，是个数组
            Class<?>[] parameterTypes = handler.getMethod().getParameterTypes();

            //创建实参参数数组，后面调用反射目标方法时候，会使用
            Object[] params = new Object[parameterTypes.length];

            for(int i = 0;i<parameterTypes.length;i++){
                Class<?> parameterType = parameterTypes[i];
                if("HttpServletRequest".equals(parameterType.getSimpleName())){
                    params[i] = request;
                }
                if("HttpServletResponse".equals(parameterType.getSimpleName())){
                    params[i] = response;
                }
            }
            //将请求参数和按照顺序填充到实参数组
            Map<String, String[]> parameterMap = request.getParameterMap();
            //参数一：请求参数名，参数二：参数值（例如多选）
            for(Map.Entry<String,String[]> entry:parameterMap.entrySet()){
                String name = entry.getKey();
                String value = entry.getValue()[0];
                int indexParams = getIndexParams(method,name);
                if(indexParams!=-1){
                    params[indexParams] = value;
                }else{
                    //如果没有requestParams这个注解，按照默认的进行匹配
                    //得到目标方法所有形参名字，遍历，如果匹配，就把当前请求的参数值，填充到实参数组中
                    List<String> paramsName = getParams(handler.getMethod());
                    for (int i = 0; i < paramsName.size(); i++) {
                        if(name.equals(paramsName.get(i))){
                            params[i] = value;
                            break;
                        }
                    }
                }
            }
            //填充实参
            //调用方法
            method.invoke(handler.getHandler(),params);
        }
    }

    //返回请求参数是目标方法的第几个形参
    public int getIndexParams(Method method,String name){
        Parameter[] parameters = method.getParameters();
        for(int i = 0;i<parameters.length;i++){
            Parameter parameter = parameters[i];
            if(parameter.isAnnotationPresent(RequestParams.class)){
                RequestParams annotation = parameter.getAnnotation(RequestParams.class);
                String value = annotation.value();
                if(name.equals(value)){
                    //找到请求参数，对应目标方法的形参位置
                    return i;
                }
            }
        }
        return -1;
    }

    //得到目标方法所有形参名称，并放入到集合中来
    public List<String> getParams(Method method){
        List<String> list = new ArrayList<>();
        //在默认情况下，
        Parameter[] parameters = method.getParameters();
        for(Parameter parameter:parameters){
            list.add(parameter.getName());
        }
        return list;
    }
}
