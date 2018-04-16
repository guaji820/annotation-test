package com.lify.domain;

public class Child extends Person {

    @Override
    public void say() {
        System.out.println("hahaa");
    }

    @Override
    @Deprecated
    public void run() {

    }
}
