package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
        StatisticManager.getInstance().register(this);
    }

    @Override
    public void update(Observable tablet, Object order) {
        int totalCookingTime = ((Order) order).getTotalCookingTime();
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + totalCookingTime + "min");
        EventDataRow eventData = new CookedOrderEventDataRow(tablet.toString(), name, totalCookingTime*60, ((Order) order).getDishes());
        StatisticManager.getInstance().register(eventData);
        setChanged();
        notifyObservers(order);
    }

    @Override
    public String toString() {
        return name;
    }
}
