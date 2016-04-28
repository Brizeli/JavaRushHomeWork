package com.javarush.test.level17.lesson10.bonus01;

import java.text.*;
import java.util.*;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("too few arguments");
            return;
        }
        if (args[0].equals("-c")) createPerson(args[1], args[2], args[3]);
        else if (args[0].equals("-u")) updatePerson(args[1], args[2], args[3], args[4]);
        else if (args[0].equals("-d")) removePerson(args[1]);
        else if (args[0].equals("-i")) displayPerson(args[1]);
        else {
            System.out.println("wrong argument: " + args[0]);
            return;
        }
    }

    private static void displayPerson(String id) {
        Person person = getPerson(id);
        DateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        String sex = person.getSex() == null?null:person.getSex()==Sex.MALE ? "м" : "ж";
        String bd = person.getBirthDay()==null?null:df1.format(person.getBirthDay());
        System.out.println(person.getName() + " " + sex + " " + bd);
    }

    private static Person getPerson(String id) {
        int ID = Integer.parseInt(id);
        Person person = allPeople.get(ID);
        return person;
    }

    private static void removePerson(String id) {
        Person person = getPerson(id);
        person.setBirthDay(null);
        person.setSex(null);
        person.setName(null);
    }

    private static void updatePerson(String id, String name, String sex, String bd) throws ParseException {
        Date db = df.parse(bd);
        Person person = getPerson(id);
        person.setName(name);
        person.setBirthDay(db);
        if (sex.equals("м")) person.setSex(Sex.MALE);
        else person.setSex(Sex.FEMALE);
    }

    private static void createPerson(String name, String sex, String bd) throws ParseException {
        Date db = df.parse(bd);
        if (sex.equals("м")) allPeople.add(Person.createMale(name, db));
        else allPeople.add(Person.createFemale(name, db));
        System.out.println(allPeople.size()-1);
    }
}
