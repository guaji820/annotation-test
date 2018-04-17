package com.lify.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Description {
//    String desc();
//    String author();
    String value();
//    int age() default 18;
}
