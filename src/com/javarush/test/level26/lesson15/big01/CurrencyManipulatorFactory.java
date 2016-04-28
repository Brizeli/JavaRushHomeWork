package com.javarush.test.level26.lesson15.big01;

import java.util.*;

public final class CurrencyManipulatorFactory {
    private static Map<String,CurrencyManipulator> manipulators=new HashMap<>();

    private CurrencyManipulatorFactory() {}

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        CurrencyManipulator res=manipulators.get(currencyCode);
        if (res==null) {
            res = new CurrencyManipulator(currencyCode);
            manipulators.put(currencyCode, res);
        }
        return res;
    }
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return manipulators.values();
    }
}
