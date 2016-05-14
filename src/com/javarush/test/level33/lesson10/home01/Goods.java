package com.javarush.test.level33.lesson10.home01;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.stream.events.Namespace;
import java.util.List;

@XmlType
@XmlRootElement
public class Goods {
    public List<String> names;

    @Override
    public String toString() {
        return "Goods{" +
                       "names=" + names +
                       '}';
    }
}
