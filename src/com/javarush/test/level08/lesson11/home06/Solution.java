package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {

        Human child1=new Human("One",true,5);
        Human child2=new Human("Two",false,10);
        Human child3=new Human("Three",true,15);
        ArrayList<Human> children=new ArrayList<Human>();
        children.add(child1);
        children.add(child2);
        children.add(child3);
        Human father=new Human("Dad",true,35,children);
        ArrayList<Human> fathers=new ArrayList<Human>();
        fathers.add(father);
        Human mother=new Human("Mom",false,29,children);
        ArrayList<Human> mothers=new ArrayList<Human>();
        mothers.add(mother);
        Human granpa1=new Human("Ded1",true,90,fathers);
        Human granpa2=new Human("Ded2",true,85,mothers);
        Human gramma1=new Human("Baba1",false,80,fathers);
        Human gramma2=new Human("Baba2",false,78,mothers);
        System.out.println(granpa1);
        System.out.println(granpa2);
        System.out.println(gramma1);
        System.out.println(gramma2);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(child1);
        System.out.println(child2);
        System.out.println(child3);
    }

    public static class Human
    {
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children=new ArrayList<Human>();
        public Human(String name,boolean sex,int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }
        public Human(String name,boolean sex,int age,ArrayList<Human> children) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children=children;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
