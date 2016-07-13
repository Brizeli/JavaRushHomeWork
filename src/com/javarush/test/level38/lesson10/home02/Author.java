package com.javarush.test.level38.lesson10.home02;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface Author {
    String value() default "";
    Position position() default Position.OTHER;
    //напиши свой код
}
