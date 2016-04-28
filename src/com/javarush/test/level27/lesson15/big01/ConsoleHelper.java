package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.*;
import java.util.*;

public class ConsoleHelper {
    private final static BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws IOException {
        return reader.readLine();
    }
    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> orderedDishes=new ArrayList<>();
        String allDishes = Dish.allDishesToString();
        writeMessage("Select dishes: "+ allDishes);
        String orderString;
        while(!"exit".equalsIgnoreCase(orderString=readString())){
            try {
                orderedDishes.add(Dish.valueOf(orderString));
            }
            catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage(orderString + " is not detected");
            }
        }
        return orderedDishes;
    }
}
