package com.lify.domain;

import com.lify.annotations.Description;

@Description("the class annotation")
public class Child extends Person {

    @Override
    @Description("the method annotation")
    public void say() {
        System.out.println("hahaa");
    }

    @Override
    @Deprecated
    public void run() {

    }
}
