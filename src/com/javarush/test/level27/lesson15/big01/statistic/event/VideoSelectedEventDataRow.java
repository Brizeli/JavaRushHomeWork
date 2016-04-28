package com.javarush.test.level27.lesson15.big01.statistic.event;

import com.javarush.test.level27.lesson15.big01.ad.Advertisement;

import java.util.Date;
import java.util.List;

public class VideoSelectedEventDataRow implements EventDataRow{
    private Date currentDate;
    private List<Advertisement> optimalVideoSet;
    private long amount;
    private int totalDuration;

    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration) {
        currentDate=new Date();
        this.optimalVideoSet = optimalVideoSet;
        this.amount = amount;
        this.totalDuration = totalDuration;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    public long getAmount() {
        return amount;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }

    @Override
    public EventType getType() {
        return EventType.SELECTED_VIDEOS;
    }
}
