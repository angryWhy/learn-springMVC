package com.MyspringMVC.handler;

import java.lang.reflect.Method;

public class Handler {
    private String url;
    private Object handler;
    private Method method;

    public Handler(String url, Object handler, Method method) {
        this.url = url;
        this.handler = handler;
        this.method = method;
    }

    public Handler() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getHandler() {
        return handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
