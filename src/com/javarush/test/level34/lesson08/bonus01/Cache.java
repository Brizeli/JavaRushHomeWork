package com.javarush.test.level34.lesson08.bonus01;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {
        V v = cache.get(key);
        if (v == null) {
            Constructor<V> constructor = clazz.getConstructor(key.getClass());
            v = constructor.newInstance(key);
            cache.put(key, v);
        }
        return v;
    }

    public boolean put(V obj) {
        Class<?> objClass = obj.getClass();
        try {
            Method getKey = objClass.getDeclaredMethod("getKey");
            getKey.setAccessible(true);
            K key = (K) getKey.invoke(obj);
            cache.put(key, obj);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public int size() {
        return cache.size();
    }
}
