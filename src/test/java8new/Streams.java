package test.java8new;

import test.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Next on 24.05.2016.
 */
public class Streams {
    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        stringCollection
                .stream()
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);
        System.out.println("-------------------");
        stringCollection
                .stream()
                .sorted((o1, o2) -> o2.compareTo(o1))
                .filter(s -> s.length()>3)
                .forEach(System.out::println);
        boolean anyMatch = stringCollection.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println(anyMatch);
        boolean allMatch = stringCollection.stream().allMatch(s -> s.startsWith("z"));
        System.out.println(allMatch);
        System.out.println(stringCollection.stream().noneMatch(s -> s.equals("qqq")));
        Optional<String> optional = Optional.of("bam");
        optional.ifPresent(System.out::println);
    }
}
