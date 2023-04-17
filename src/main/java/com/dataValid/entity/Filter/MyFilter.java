package com.dataValid.entity.Filter;

import javax.servlet.*;
import java.io.IOException;

//编写过滤器，处理中文乱码
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
    //这里加入对中文编码的处理
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        //放行请求
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
