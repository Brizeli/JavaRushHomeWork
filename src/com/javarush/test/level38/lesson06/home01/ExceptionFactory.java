package com.javarush.test.level38.lesson06.home01;

/**
 * Created by Next on 13.07.2016.
 */
public class ExceptionFactory {
    public static Throwable getException(Enum anEnum) {
        if (anEnum!=null) {
            String msg = anEnum.name().charAt(0) + anEnum.name().substring(1).toLowerCase().replace('_', ' ');
            if (anEnum instanceof ExceptionApplicationMessage) return new Exception(msg);
            else if (anEnum instanceof ExceptionDBMessage) return new RuntimeException(msg);
            else if (anEnum instanceof ExceptionUserMessage) return new Error(msg);
        }
        return new IllegalArgumentException();
    }
}

