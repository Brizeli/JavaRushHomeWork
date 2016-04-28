package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cookSet = new HashSet<>();

    public static StatisticManager getInstance() {
        return ourInstance;
    }
    
    private StatisticManager() {
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> eventMap;

        public StatisticStorage() {
            eventMap = new HashMap<>();
            for (EventType type : EventType.values())
                eventMap.put(type, new ArrayList<EventDataRow>());
        }

        private void put(EventDataRow data) {
            eventMap.get(data.getType()).add(data);
        }

        private List<EventDataRow> get(EventType eventType) {
            return eventMap.get(eventType);
        }
    }

    public void register(Cook cook) {
        cookSet.add(cook);
    }

    public void printAdvertisementProfit() {

    }

    public Map<Date, Long> getAdProfitsByDays() {
        Map<Date, Long> res = new TreeMap<>(Collections.reverseOrder());
        for (EventDataRow row : statisticStorage.get(EventType.SELECTED_VIDEOS)) {
            Date date = dateWithoutTime(row.getDate());
            Long dayProfit = res.get(date);
            if (dayProfit == null) dayProfit = 0l;
            res.put(date, dayProfit + ((VideoSelectedEventDataRow) row).getAmount());
        }
        return res;
    }

    public Map<Date, Map<String, Integer>> getCooksInfo() {
        Map<Date, Map<String, Integer>> res = new TreeMap<>(Collections.reverseOrder());
        for (EventDataRow row : statisticStorage.get(EventType.COOKED_ORDER)) {
            Date date = dateWithoutTime(row.getDate());
            CookedOrderEventDataRow cook = (CookedOrderEventDataRow) row;
            String cookName = cook.getCookName();
            int cookTime = (int)Math.ceil(cook.getTime()/60);
            Map<String, Integer> cooksTimes = res.get(date);
            if (cooksTimes == null) cooksTimes=new TreeMap<>();
            Integer prevTime = cooksTimes.get(cookName);
            if (prevTime == null) prevTime = 0;
            cooksTimes.put(cookName, prevTime + cookTime);
            res.put(date,cooksTimes);
        }
        return res;
    }
    private Date dateWithoutTime(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    public void printActiveVideoSet() {
    }

    public void printArchivedVideoSet() {
    }
}
