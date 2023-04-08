package com.web.CustomView;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
//MyView继承了类，作为视图类
//@Component(value = "myView")，该视图放入到容器中，id是myView
@Component(value = "myView")
public class MyView extends AbstractView {
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        //完成视图渲染
        //跳转到哪个页面
        System.out.println("进入到自定义的类！");
        httpServletRequest.getRequestDispatcher("/WEB-INF/pages/myView.jsp").forward(httpServletRequest,httpServletResponse);
    }
}
