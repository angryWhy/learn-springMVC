package com.dataValid.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Monster {
    //可以使用NotNull来显示错误提示信息
    private int id;
    @Range(min = 0,max = 1000)
    @NotNull(message = "年龄必须填写为100")
    private Integer age;
    //不能为空，表示name不能为空
    @NotNull(message = "名字不能为空")
    @NotEmpty
    private String name;
    @NotNull(message = "薪水不能为空")
    @NumberFormat(pattern = "###,##.##")
    private Double salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }



    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Monster(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Monster() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //对属性取消绑定
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        //取消属性绑定了，验证没意义，应该取消验证注解
        webDataBinder.setDisallowedFields("name");
    }
}
