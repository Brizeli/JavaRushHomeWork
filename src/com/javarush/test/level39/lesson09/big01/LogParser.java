package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.DateQuery;
import com.javarush.test.level39.lesson09.big01.query.EventQuery;
import com.javarush.test.level39.lesson09.big01.query.IPQuery;
import com.javarush.test.level39.lesson09.big01.query.UserQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery {
    private Path logDir;
    private List<Log> logs = new ArrayList<>();

    private class Log {
        String ip;
        String userName;
        Date date;
        Event event;
        int task = -1;
        Status status;

        public Log(String log) {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
            String[] ar = log.split("\t");
            ip = ar[0];
            userName = ar[1];
            try {
                date = df.parse(ar[2]);
            } catch (ParseException e) {
                date = null;
            }
            String[] eventVal = ar[3].split(" ");
            event = Event.valueOf(eventVal[0]);
            if (eventVal.length == 2) task = Integer.parseInt(eventVal[1]);
            status = Status.valueOf(ar[4]);
        }
    }

    public LogParser(Path logDir) {
        this.logDir = logDir;
        for (File logFile : this.logDir.toFile().listFiles())
            if (logFile.isFile() && logFile.getName().endsWith(".log"))
                try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
                    while (reader.ready()) logs.add(new Log(reader.readLine()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

    private List<Log> getLogs(Date after, Date before) {
        List<Log> res = new ArrayList<>();
        if (after == null) after = new Date(0);
        if (before == null) before = new Date(Long.MAX_VALUE);
        for (Log log : logs)
            if (log.date.getTime() >= after.getTime() && log.date.getTime() <= before.getTime())
                res.add(log);
        return res;
    }

    private List<Log> getLogs() {return logs;}

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> ips = new HashSet<>();
        for (Log log : getLogs(after, before)) ips.add(log.ip);
        return ips;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> ips = new HashSet<>();
        for (Log log : getLogs(after, before))
            if (user != null && log.userName.equalsIgnoreCase(user))
                ips.add(log.ip);
        return ips;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> ips = new HashSet<>();
        for (Log log : getLogs(after, before))
            if (event != null && log.event == event)
                ips.add(log.ip);
        return ips;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> ips = new HashSet<>();
        for (Log log : getLogs(after, before))
            if (status != null && log.status == status)
                ips.add(log.ip);
        return ips;
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> users = new HashSet<>();
        for (Log log : getLogs()) users.add(log.userName);
        return users;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (Log log : getLogs(after, before)) users.add(log.userName);
        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        int res = 0;
        for (Log log : getLogs(after, before))
            if (user != null && log.userName.equalsIgnoreCase(user))
                res++;
        return res;
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (Log log : getLogs(after, before))
            if (ip != null && log.ip.equals(ip))
                users.add(log.userName);
        return users;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getUsersByEvent(Event.LOGIN, after, before);
    }

    private Set<String> getUsersByEvent(Event event, Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (Log log : getLogs(after, before))
            if (log.event == event)
                users.add(log.userName);
        return users;
    }

    private Set<String> getUsersByEvent(Event event, Date after, Date before, int task) {
        Set<String> users = new HashSet<>();
        for (Log log : getLogs(after, before))
            if (log.event == event && log.task == task)
                users.add(log.userName);
        return users;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getUsersByEvent(Event.DOWNLOAD_PLUGIN, after, before);
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getUsersByEvent(Event.WRITE_MESSAGE, after, before);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getUsersByEvent(Event.SOLVE_TASK, after, before);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getUsersByEvent(Event.SOLVE_TASK, after, before, task);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getUsersByEvent(Event.DONE_TASK, after, before);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getUsersByEvent(Event.DONE_TASK, after, before, task);
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (Log log : getLogs(after, before))
            if (user != null && log.userName.equalsIgnoreCase(user))
                if (log.event == event)
                    dates.add(log.date);
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (Log log : getLogs(after, before)) if (log.status == Status.FAILED) dates.add(log.date);
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (Log log : getLogs(after, before)) if (log.status == Status.ERROR) dates.add(log.date);
        return dates;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Date date = new Date(Long.MAX_VALUE);
        for (Log log : getLogs(after, before))
            if (user != null && log.userName.equalsIgnoreCase(user))
                if (log.event == Event.LOGIN && log.date.before(date)) date = log.date;
        return date.getTime() == Long.MAX_VALUE ? null : date;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Date date = new Date(Long.MAX_VALUE);
        for (Log log : getLogs(after, before))
            if (user != null && log.userName.equalsIgnoreCase(user))
                if (log.event == Event.SOLVE_TASK && log.task == task && log.date.before(date))
                    date = log.date;
        return date.getTime() == Long.MAX_VALUE ? null : date;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Date date = new Date(Long.MAX_VALUE);
        for (Log log : getLogs(after, before))
            if (user != null && log.userName.equalsIgnoreCase(user))
                if (log.event == Event.DONE_TASK && log.task == task && log.date.before(date))
                    date = log.date;
        return date.getTime() == Long.MAX_VALUE ? null : date;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (Log log : getLogs(after, before))
            if (user != null && log.userName.equalsIgnoreCase(user))
                if (log.event == Event.WRITE_MESSAGE) dates.add(log.date);
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (Log log : getLogs(after, before))
            if (user != null && log.userName.equalsIgnoreCase(user))
                if (log.event == Event.DOWNLOAD_PLUGIN) dates.add(log.date);
        return dates;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (Log log : getLogs(after, before)) events.add(log.event);
        return events;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (Log log : getLogs(after, before)) if (log.ip.equals(ip)) events.add(log.event);
        return events;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (Log log : getLogs(after, before)) if (log.userName.equals(user)) events.add(log.event);
        return events;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (Log log : getLogs(after, before)) if (log.status == Status.FAILED) events.add(log.event);
        return events;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (Log log : getLogs(after, before)) if (log.status == Status.ERROR) events.add(log.event);
        return events;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int res = 0;
        for (Log log : getLogs(after, before))
            if (log.event == Event.SOLVE_TASK && log.task == task) res++;
        return res;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int res = 0;
        for (Log log : getLogs(after, before))
            if (log.event == Event.SOLVE_TASK && log.task == task && log.status == Status.OK) res++;
        return res;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Log log : getLogs(after, before)) {
            if (log.event == Event.SOLVE_TASK) {
                Integer count = map.get(log.task);
                if (count == null) count = 0;
                map.put(log.task, count + 1);
            }
        }
        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Log log : getLogs(after, before)) {
            if (log.event == Event.DONE_TASK) {
                Integer count = map.get(log.task);
                if (count == null) count = 0;
                map.put(log.task, count + 1);
            }
        }
        return map;
    }
}

