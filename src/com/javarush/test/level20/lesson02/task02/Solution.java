package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream("1");
            InputStream inputStream = new FileInputStream("1");

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user=new User();
            user.setFirstName("Vasya");
            user.setLastName("Ivanov");
            user.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("29/09/1982"));
            user.setMale(true);
            user.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user);
            user=new User();
            user.setFirstName("Masha");
            user.setLastName("Sidorova");
            user.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("11/12/1938"));
            user.setMale(false);
            user.setCountry(User.Country.UKRAINE);
            javaRush.users.add(user);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintStream p=new PrintStream(outputStream);
            for (User user:users){
                p.println(user.getFirstName());
                p.println(user.getLastName());
                p.println(new SimpleDateFormat("dd/MM/yyyy").format(user.getBirthDate()));
                p.println(user.isMale()?"male":"female");
                p.println(user.getCountry());
            }
            p.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader r=new BufferedReader(new InputStreamReader(inputStream));
            while(r.ready()) {
                User user = new User();
                user.setFirstName(r.readLine());
                user.setLastName(r.readLine());
                user.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse(r.readLine()));
                user.setMale(r.readLine().equals("male") ? true : false);
                user.setCountry(User.Country.valueOf(r.readLine()));
                users.add(user);
            }
            r.close();
        }
    }
}
