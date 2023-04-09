package com.MyspringMVC.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class XmlParser {
    public static String getBasePackage(String xmlFile) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        //通过类的加载路径，获取到spring的配置文件，转换为流
        InputStream inputStream = XmlParser.class.getClassLoader().getResourceAsStream(xmlFile);
        Document document = saxReader.read(inputStream);
        Element rootElement = document.getRootElement();
        //找到component-scan的包文本
        Element componentScanElement = rootElement.element("component-scan");
        Attribute attribute = componentScanElement.attribute("base-package");
        String basePackage = attribute.getText();
        return basePackage;
    }

//    public static void main(String[] args) throws DocumentException {
//        String basePackage = XmlParser.getBasePackage("springMvc.xml");
//        System.out.println(basePackage);
//    }
}
