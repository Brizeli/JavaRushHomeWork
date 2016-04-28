package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        List<Advertisement> listChosen = new ArrayList<>();
        for (Advertisement adv : storage.list())
            if (adv.getHits() > 0) listChosen.add(adv);
        List<Advertisement> finalList = videoSearch(listChosen, timeSeconds);
        if (finalList.isEmpty() || finalList.size() == 0){
            EventDataRow eventData = new NoAvailableVideoEventDataRow(timeSeconds);
            StatisticManager.getInstance().register(eventData);
            throw new NoVideoAvailableException();
        }
        Collections.sort(finalList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int compare = Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
                if (compare != 0) return compare;
                long x1 = 1000*o1.getAmountPerOneDisplaying()/o1.getDuration();
                long x2 = 1000*o2.getAmountPerOneDisplaying()/o2.getDuration();
                return Long.compare(x1, x2);
            }
        });
        int totalDuration=0;
        long amount=0;
        for (Advertisement ad:finalList){
            totalDuration+=ad.getDuration();
            amount+=ad.getAmountPerOneDisplaying();
        }
        EventDataRow eventData = new VideoSelectedEventDataRow(finalList, amount, totalDuration);
        StatisticManager.getInstance().register(eventData);
        for (Advertisement ad : finalList) {
            ConsoleHelper.writeMessage(ad.getName() + " is displaying... " + ad.getAmountPerOneDisplaying() + ", "
                    + (1000*ad.getAmountPerOneDisplaying()/ad.getDuration()));
            ad.revalidate();
        }
    }

    private List<Advertisement> videoSearch(List<Advertisement> list, int timeSeconds) {
        List<Advertisement> testList = new ArrayList<>();
        for (Advertisement a : list)
            if (a.getDuration() <= timeSeconds)
                testList.add(a);
        if (testList.isEmpty())
            return testList;
        Advertisement savedVideo = testList.remove(0);

        List<Advertisement> listK1 = videoSearch(testList, timeSeconds - savedVideo.getDuration());
        List<Advertisement> listK2 = videoSearch(testList, timeSeconds);

        listK1.add(savedVideo);

        long totalPrice1 = 0, totalPrice2 = 0;
        long totalDuration1 = 0, totalDuration2 = 0;

        for (Advertisement adv : listK1) {
            totalPrice1 += adv.getAmountPerOneDisplaying();
            totalDuration1 += adv.getDuration();
        }

        for (Advertisement adv : listK2) {
            totalPrice2 += adv.getAmountPerOneDisplaying();
            totalDuration2 += adv.getDuration();
        }

        if (totalPrice1 > totalPrice2) return listK1;

        if (totalPrice1 == totalPrice2) {
            if (totalDuration1 > totalDuration2)
                return listK1;
            else return listK2;
        }

        if (totalDuration1 == totalDuration2) {
            if (listK1.size() > listK2.size())
                return listK1;
            else return listK2;
        }

        return listK2;
    }
}
