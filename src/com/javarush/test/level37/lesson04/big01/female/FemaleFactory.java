package com.javarush.test.level37.lesson04.big01.female;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by Next on 16.06.2016.
 */
public class FemaleFactory implements AbstractFactory {
    public Human getPerson(int age){
        if (age<=KidGirl.MAX_AGE) return new KidGirl();
        if (age<=TeenGirl.MAX_AGE) return new TeenGirl();
        return new Woman();
    }
}
