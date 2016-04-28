package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {
//        System.out.println(new Human("Next",35,true,174));
    }

    public static class Human
    {
        public String name;
        public String lastname;
        public int age;
        public int height;
        public String hair;
        public boolean sex;

        public Human(String name,String lastname,int age){
            this.name=name;
            this.lastname=lastname;
            this.age=age;
        }public Human(String name,String lastname,int age,int height){
            this.name=name;
            this.lastname=lastname;
            this.age=age;
            this.height=height;
        }public Human(String name,String lastname,int age,boolean sex){
            this.name=name;
            this.lastname=lastname;
            this.age=age;
            this.sex=sex;
        }public Human(String name,String lastname,boolean sex,int height){
            this.name=name;
            this.lastname=lastname;
            this.height=height;
            this.sex=sex;
        }public Human(String name,String lastname,boolean sex,String hair){
            this.name=name;
            this.lastname=lastname;
            this.hair=hair;
            this.sex=sex;
        }public Human(String name,String lastname,int age,boolean sex,String hair){
            this.name=name;
            this.lastname=lastname;
            this.hair=hair;
            this.sex=sex;
            this.age=age;
        }public Human(String name,String lastname,int age,boolean sex,String hair,int height){
            this.name=name;
            this.lastname=lastname;
            this.hair=hair;
            this.sex=sex;
            this.age=age;
            this.height=height;
        }public Human(String name,int age,boolean sex,String hair){
            this.name=name;
            this.hair=hair;
            this.sex=sex;
            this.age=age;
        }public Human(String lastname,int age,boolean sex,int height){
            this.lastname=lastname;
            this.sex=sex;
            this.height=height;
            this.age=age;
        }public Human(String name,String lastname,boolean sex){
            this.name=name;
            this.lastname=lastname;
            this.sex=sex;
        }
    }
}
