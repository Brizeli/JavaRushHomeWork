package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public static void main(String[] args) throws Exception{
        Solution solution = new Solution();
        solution.fillInPropertiesMap();
        solution.save(System.out);
    }
    public void fillInPropertiesMap()throws Exception {
        BufferedReader r=new BufferedReader(new InputStreamReader(System.in));
        Properties props = new Properties();
        props.load(new FileReader(r.readLine()));
        for(Map.Entry<Object, Object>entry :props.entrySet()){
            properties.put(entry.getKey().toString(),entry.getValue().toString());
        }
    }

    public void save(OutputStream outputStream) throws Exception {
        PrintStream p=new PrintStream(outputStream);
        for (Map.Entry<String,String> entry:properties.entrySet()){
            p.println(entry.getKey()+"="+entry.getValue());
        }
        p.close();
    }

    public void load(InputStream inputStream) throws Exception {
        BufferedReader r=new BufferedReader(new InputStreamReader(inputStream));
        while (r.ready()){
            String[] props=r.readLine().split("=");
            properties.put(props[0],props[1]);
        }
        r.close();
    }
}
