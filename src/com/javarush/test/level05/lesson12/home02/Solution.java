package com.javarush.test.level05.lesson12.home02;

/* Man and Woman
1. Внутри класса Solution создай public static классы Man и Woman.
2. У классов должны быть поля: name(String), age(int), address(String).
3. Создай конструкторы, в которые передаются все возможные параметры.
4. Создай по два объекта каждого класса со всеми данными используя конструктор.
5. Объекты выведи на экран в таком формате [name + " " + age + " " + address].
*/

public class Solution
{
    public static void main(String[] args)
    {
        Man man1=new Man("Man1",30,"Man2Address");
        Man man2=new Man("Man2",35,"Man2Address");
        Woman woman1=new Woman("Woman1",20,"Woman1Address");
        Woman woman2=new Woman("Woman2",25,"Woman2Address");

        System.out.println(man1);
        System.out.println(man2);
        System.out.println(woman1);
        System.out.println(woman2);
    }

    public static class Man{
        public String name,address;
        public int age;
        public Man(String n,int a,String adr){
            this.name=n;
            this.age=a;
            this.address=adr;
        }
        public String toString(){
            return name + " " + age + " " + address;
        }
}
    public static class Woman{
        public String name,address;
        public int age;
        public Woman(String n,int a,String adr){
            this.name=n;
            this.age=a;
            this.address=adr;
        }
        public String toString(){
            return name + " " + age + " " + address;
        }
}
}
