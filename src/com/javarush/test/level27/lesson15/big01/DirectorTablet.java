package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    private SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    public void printAdvertisementProfit(){
        double totalAmount=0;
        for (Map.Entry<Date,Long> entry:StatisticManager.getInstance().getAdProfitsByDays().entrySet()) {
            String date= dateFormat.format(entry.getKey());
            Double amount = new Double(entry.getValue()/100);
            totalAmount+=amount;
            ConsoleHelper.writeMessage(date + " - " + amount);
        }
        ConsoleHelper.writeMessage("Total - "+totalAmount);
    }
    public void printCookWorkloading(){
        for (Map.Entry<Date,Map<String,Integer>> entry:StatisticManager.getInstance().getCooksInfo().entrySet()){
            ConsoleHelper.writeMessage(dateFormat.format(entry.getKey()));
            for (Map.Entry<String,Integer> entry1:entry.getValue().entrySet()){
                ConsoleHelper.writeMessage(entry1.getKey()+" - "+entry1.getValue()+" min");
            }
            ConsoleHelper.writeMessage("");
        }
    }
    public void printActiveVideoSet(){
        StatisticManager.getInstance().printActiveVideoSet();
    }
    public void printArchivedVideoSet(){
        StatisticManager.getInstance().printArchivedVideoSet();
    }
}
