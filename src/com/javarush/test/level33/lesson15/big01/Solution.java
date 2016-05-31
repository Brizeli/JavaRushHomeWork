package com.javarush.test.level33.lesson15.big01;


import com.javarush.test.level33.lesson15.big01.strategies.FileStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.OurHashMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Next on 15.05.2016.
 */
public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(),100);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<>();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for (Long key : keys) {
            strings.add(shortener.getString(key));
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> strings = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        long start = new Date().getTime();
        Set<Long> ids = getIds(shortener, strings);
        long stop = new Date().getTime();
        Helper.printMessage("Get Ids: " + String.valueOf(stop - start));
        start = new Date().getTime();
        Set<String> gotStrings = getStrings(shortener, ids);
        stop = new Date().getTime();
        Helper.printMessage("Get strings: " + String.valueOf(stop - start));
        Helper.printMessage(strings.equals(gotStrings) ? "Тест пройден." : "Тест не пройден.");
    }
}
