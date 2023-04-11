package com.MyspringMVC.contetx;

import com.MyspringMVC.annotation.Controller;
import com.MyspringMVC.xml.XmlParser;
import org.dom4j.DocumentException;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SpringContext {
    private String mvcXml;
    public SpringContext(){}
    public SpringContext(String mvcXml){
        this.mvcXml = mvcXml;
    }

    public String getMvcXml() {
        return mvcXml;
    }

    public void setMvcXml(String mvcXml) {
        this.mvcXml = mvcXml;
    }

    //保存类的全路径
    private List<String> classFullPath = new ArrayList<>();
    //定义属性ioc，存放反射生成的bean对象
    public ConcurrentHashMap<String,Object> ioc = new ConcurrentHashMap<>();
    //要对扫描的包进行解析
    public void init() throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //这里是写固定的spring配置文件,现在在dispatchServlet里穿进去了
        String basePackage = XmlParser.getBasePackage(this.mvcXml.split(":")[1]);

        //如果有多个包需要扫描，则需要分类
        String[] packages = basePackage.split(",");
        if(packages.length>0){
            for(String pack:packages){
                scanPackage(pack);
            }
        }else{
            scanPackage(basePackage);
        }
    }
    public void scanPackage(String pack) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //得到当前资源的路径，绝对路径，target最终生成的
        //用类的加载器，获取到指定的包对应的工作路径的绝对路径
        URL url = this.getClass().getClassLoader().getResource("/" + pack.replaceAll("\\.", "/"));

        //得到工作路径，对其进行一个扫描，把类的全路径，加载到classFullPath
        String path = url.getFile();
        File dir = new File(path);
        for(File f:dir.listFiles()){
            if(f.isDirectory()){
                scanPackage(f+"."+f.getName());
            }else{
                //扫描到的文件，class可能是其他文件，要进行判断
               String classFullPath =  pack+"."+ f.getName().replaceAll(".class","");
                this.classFullPath.add(classFullPath);
            }
        }

        executeInstance();
    }
    //编写方法，将符合条件的加入到容器中
    public void executeInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if(classFullPath.size()==0){
            return;
        }
        //遍历classFullPath
        for(String classFullPath:classFullPath){
            Class<?> aClass = Class.forName(classFullPath);
            if(aClass.isAnnotationPresent(Controller.class)){
                Object bean = aClass.newInstance();
               String beanName =  aClass.getSimpleName().substring(0,1).toLowerCase() + aClass.getSimpleName().substring(1);
               ioc.put(beanName,bean);
            }
        }
    }
}
