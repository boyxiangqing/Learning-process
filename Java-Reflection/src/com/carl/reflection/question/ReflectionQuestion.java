package com.carl.reflection.question;

import com.carl.Cat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author Carl
 * @version 1.0
 * 反射问题的引入
 */
public class ReflectionQuestion {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //根据配置文件 re.properties 指定信息,创建Cat对象并调用方法hi

        //传统方式 new对象 -> 调用方法
        Cat cat = new Cat();
        cat.hi();

        //使用Properties读取配置类
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/re.properties"));
        String classfullpath = properties.get("classfullpath").toString();
        String methodName = properties.get("method").toString();
        System.out.println("classfulpath="+classfullpath);
        System.out.println("method="+methodName);

        //反射机制解决
        //1.加载类,返回Class类型的对象
        Class cls = Class.forName(classfullpath);
        //2.通过cls得到你加载的类 com.carl.Cat实例
        Object o = cls.newInstance();
        System.out.println("o的运行类型"+o.getClass());
        //3.通过cls得到你加载的类 com.carl.Cat 的methodName"hi" 的方法对象
        //  即:在反射中,可以把方法视为对象(万物皆对象)
        Method method1 = cls.getMethod(methodName);
        //4.通过method1 调用方法:即通过方法对象来实现调用方法
        method1.invoke(o); //传统方法 对象.方法() ,反射机制 方法.invoke(对象)

    }
}
