package com.javarush.test.level27.lesson15.big01.statistic.event;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.util.Date;
import java.util.List;

public class CookedOrderEventDataRow implements EventDataRow{
    private Date currentDate;
    private String tabletName;
    private String cookName;
    private int cookingTimeSeconds;
    private List<Dish> cookingDishs;

    public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishs) {
        currentDate=new Date();
        this.tabletName = tabletName;
        this.cookName = cookName;
        this.cookingTimeSeconds = cookingTimeSeconds;
        this.cookingDishs = cookingDishs;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    public String getCookName() {
        return cookName;
    }

    @Override
    public int getTime() {
        return cookingTimeSeconds;
    }

    @Override
    public EventType getType() {
        return EventType.COOKED_ORDER;
    }
}
