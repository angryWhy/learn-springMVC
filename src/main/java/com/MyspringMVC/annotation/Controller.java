package com.MyspringMVC.annotation;

import java.lang.annotation.*;

//用来表示一个控制器
@Target(ElementType.TYPE)
//反射用得到
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default "";
}
