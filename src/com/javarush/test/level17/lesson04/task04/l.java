package com.javarush.test.level17.lesson04.task04;

public class l {
    private static l ourInstance = new l();

    public static l getInstance() {
        return ourInstance;
    }

    private l() {
    }
}
