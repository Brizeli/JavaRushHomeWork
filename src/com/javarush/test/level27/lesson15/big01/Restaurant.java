package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet5 = new Tablet(5);
        Cook vasya = new Cook("Vasya");
        Waitor waitor = new Waitor();
        vasya.addObserver(waitor);
        tablet5.addObserver(vasya);
        tablet5.createOrder();
        Tablet tablet1 = new Tablet(5);
        Cook petya = new Cook("Petya");
        petya.addObserver(waitor);
        tablet1.addObserver(petya);
        tablet1.createOrder();
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printActiveVideoSet();
        ConsoleHelper.writeMessage("");
        directorTablet.printAdvertisementProfit();
        ConsoleHelper.writeMessage("");
        directorTablet.printArchivedVideoSet();
        ConsoleHelper.writeMessage("");
        directorTablet.printCookWorkloading();
    }
}
