package com.dataValid.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

//用于handler处理器，响应用户提交数据
@Controller
@Scope(value = "prototype")
public class MonsterHandler {
    @RequestMapping("/addMonsterUi")
    public String addMonsterUI(Map<String,Object> map){
        map.put("monster",new Monster());
        return "dataValid";
    }
    @RequestMapping("/save")
    //@Valid表示对monster进行校验
    //校验错误在errors
    //map保存错误信息，保存monster对象
    public String save(@Valid Monster monster, Errors errors,Map<String,Object> map){
        for(Map.Entry<String,Object> entry : map.entrySet()){
            System.out.println(entry.getKey()+entry.getValue());
        }
        List<ObjectError> allErrors = errors.getAllErrors();
        if(errors.hasErrors()){
            for (ObjectError objectError:allErrors) {
                System.out.println(objectError);
            }
            //就回显，不跳进成功页
        }
        return "success2";
    }
}
