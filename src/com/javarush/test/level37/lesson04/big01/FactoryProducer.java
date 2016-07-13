package com.javarush.test.level37.lesson04.big01;

import com.javarush.test.level37.lesson04.big01.female.FemaleFactory;
import com.javarush.test.level37.lesson04.big01.male.MaleFactory;

/**
 * Created by Next on 16.06.2016.
 */
public class FactoryProducer {
    
    public static AbstractFactory getFactory(HumanFactoryType type) {
        return type == HumanFactoryType.MALE ? new MaleFactory() : new FemaleFactory();
    }
    
    public enum HumanFactoryType {
        MALE, FEMALE
    }
}
