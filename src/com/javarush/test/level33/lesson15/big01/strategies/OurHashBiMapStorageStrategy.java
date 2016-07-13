package com.javarush.test.level33.lesson15.big01.strategies;

import java.util.HashMap;

/**
 * Created by Next on 24.05.2016.
 */
public class OurHashBiMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> k2v;
    private HashMap<String, Long> v2k;
    @Override
    public boolean containsKey(Long key) {
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        return false;
    }

    @Override
    public void put(Long key, String value) {
    }

    @Override
    public Long getKey(String value) {
        return null;
    }

    @Override
    public String getValue(Long key) {
        return null;
    }
}
