package com.javarush.test.level17.lesson10.bonus02;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) return;
        if (args[0].equals("-c")) createPersons(args);
        else if (args[0].equals("-u")) updatePersons(args);
        else if (args[0].equals("-d")) removePersons(args);
        else if (args[0].equals("-i")) displayPersons(args);
        else return;
    }

    private synchronized static void createPersons(String[] args) throws Exception {
        for (int i = 1; i < args.length; )
            createPerson(args[i++], args[i++], args[i++]);
    }

    private synchronized static void updatePersons(String[] args) throws Exception {
        for (int i = 1; i < args.length; )
            updatePerson(args[i++], args[i++], args[i++], args[i++]);
    }

    private synchronized static void removePersons(String[] args) {
        for (int i = 1; i < args.length; i++)
            removePerson(args[i]);
    }

    private synchronized static void displayPersons(String[] args) {
        for (int i = 1; i < args.length; i++)
            displayPerson(args[i]);
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
        return allPeople.get(ID);
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

