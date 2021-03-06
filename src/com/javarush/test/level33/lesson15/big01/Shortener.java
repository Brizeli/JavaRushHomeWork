package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;

/**
 * Created by Next on 15.05.2016.
 */
public class Shortener {
    private Long lastId = 0l;
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(String string) {
        if (storageStrategy.containsValue(string))
            return storageStrategy.getKey(string);
        storageStrategy.put(++lastId, string);
        return lastId;
    }

    public synchronized String getString(Long id) {
        if (storageStrategy.containsKey(id))
            return storageStrategy.getValue(id);
        return null;
    }
}
