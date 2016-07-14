package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("src/com/javarush/test/level39/lesson09/big01/logs/"));
//        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
//        System.out.println(logParser.getIPsForEvent(Event.DONE_TASK, null, null));
//        System.out.println(logParser.getIPsForStatus(Status.OK, null, new Date()));
//        System.out.println(logParser.getIPsForUser("Vasya Pupkin",new Date(),null));
//        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko",null,null));
        System.out.println(logParser.execute("get status"));
    }

}
