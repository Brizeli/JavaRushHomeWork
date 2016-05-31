package test.java8new;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by Next on 24.05.2016.
 */
public class Lambda3 {
    public static void main(String[] args) {
        Predicate<String> predicate = s -> s.length() > 0;
        System.out.println(predicate.test("sss"));
        Function<String, Integer> toInteger = Integer::valueOf;
        System.out.println(toInteger.apply("123"));
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        System.out.println(backToString.apply("456"));
        Supplier<Person> supplier = Person::new;
        System.out.println(supplier.get());
        Consumer<Person> greeter = p -> System.out.println("Hello, " + p.firstName + ' ' + p.lastName);
        greeter.accept(new Person("Luke", "Skywalker"));
        greeter.accept(supplier.get());
    }
}