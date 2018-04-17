package com.lify.test;

import com.lify.annotations.Description;
import com.lify.domain.Child;
import sun.security.krb5.internal.crypto.Des;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Test {

    @SuppressWarnings(value = "deprecated")
    public static void main(String[] args) {
//        new Child().run();
        try {
            //1 使用类加载器加载类
            Class c = Class.forName("com.lify.domain.Child");
            //2 找到类上面的注解
            boolean isExist = c.isAnnotationPresent(Description.class);//判读 在这个类上 是否存在 参数注解
            if (isExist) {
                Description description = (Description) c.getAnnotation(Description.class);
                System.out.println(description.value());
            }

            Method[] methods = c.getMethods();
            for (Method method : methods) {
                boolean isMExist = method.isAnnotationPresent(Description.class);
                if (isMExist) {
                    Description description = (Description) method.getAnnotation(Description.class);
                    System.out.println(description.value());
                }
            }
            for (Method method : methods) {
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Description) {
                        Description description = (Description) annotation;
                        System.out.println(description.value());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
