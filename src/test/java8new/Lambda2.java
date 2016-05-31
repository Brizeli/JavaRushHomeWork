package test.java8new;

import java.util.Random;

/**
 * Created by Next on 24.05.2016.
 */
public class Lambda2 {
    public static void main(String[] args) {
        PersonFactory<Person> pFactory = Person::new;
        Person nxt = pFactory.create("Next", "Brizeli");
        System.out.println(nxt);
        System.out.println(pFactory.create());
    }
}

@FunctionalInterface
interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);

    default Person create() {
        return new Person();
    }
}

class Person {
    String firstName;
    String lastName;

    public Person() {
        firstName = randomName();
        lastName = randomName();
    }
    
    private String randomName() {
        Random gen = new Random();
        int length = 3 + gen.nextInt(10);
        char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'};
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
        String name = "";
        int consCount=0;
        name += consonants[gen.nextInt(consonants.length)];
        consCount++;
        for (int i = 0; i <= length/2; i++) {
            if (consCount==2  || gen.nextInt(3) > 1) {
                name += vowels[gen.nextInt(vowels.length)];
                consCount=0;
            } else {
                name += consonants[gen.nextInt(consonants.length)];
                consCount++;
            }
        }
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "firstName='" + firstName + "\', lastName='" + lastName + '\'';
    }
}