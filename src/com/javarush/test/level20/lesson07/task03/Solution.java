package com.javarush.test.level20.lesson07.task03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Externalizable Person
Класс Person должен сериализоваться с помощью интерфейса Externalizable.
Подумайте, какие поля не нужно сериализовать.
Исправьте ошибку сериализации.
Сигнатуры методов менять нельзя.
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        Person mom = new Person("Lena", "Ivanova", 35);
        Person pop = new Person("Dima", "Ivanov", 35);
        Person grl = new Person("Katya", "Ivanova", 5);
        Person boy = new Person("Vasya", "Ivanov", 3);
        grl.setFather(pop);
        grl.setMother(mom);
        boy.setFather(pop);
        boy.setMother(mom);
        ArrayList<Person> children = new ArrayList<>();
        children.add(boy);
        children.add(grl);
        mom.setChildren(children);
        pop.setChildren(children);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(baos);
        os.writeObject(mom);
        os.writeObject(pop);
        os.writeObject(grl);
        os.writeObject(boy);
        ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        Person mom1 = (Person) is.readObject();
        Person pop1 = (Person) is.readObject();
        Person grl1 = (Person) is.readObject();
        Person boy1 = (Person) is.readObject();
        System.out.println(mom1);
        System.out.println(pop1);
        System.out.println(grl1);
        System.out.println(boy1);
    }

    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public Person() {
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    ", mother=" + mother +
                    ", father=" + father +
//                    ", children=" + children +
                    '}';
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeInt(age);
            out.writeObject(mother);
            out.writeObject(father);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName = (String) in.readObject();
            lastName = (String) in.readObject();
            age = in.readInt();
            mother = (Person) in.readObject();
            father = (Person) in.readObject();
            children = (List) in.readObject();
        }
    }
}
