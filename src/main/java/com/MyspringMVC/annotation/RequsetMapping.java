package com.MyspringMVC.annotation;

import java.lang.annotation.*;

//可以作用于方法上，也可以作用到类上
@Target(ElementType.METHOD)
//反射用得到
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequsetMapping {
    String value() default "";
}
