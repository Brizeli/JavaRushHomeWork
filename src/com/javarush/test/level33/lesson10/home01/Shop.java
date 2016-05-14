package com.javarush.test.level33.lesson10.home01;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by Next on 14.05.2016.
 */
@XmlType
@XmlRootElement
public class Shop {
    public Goods goods;
    public int count;
    public double profit;
    public List<String> secretData;

    @Override
    public String toString() {
        return "Shop{" +
                       "goods=" + goods +
                       ", count=" + count +
                       ", profit=" + profit +
                       ", secretData='" + secretData + '\'' +
                       '}';
    }
}

