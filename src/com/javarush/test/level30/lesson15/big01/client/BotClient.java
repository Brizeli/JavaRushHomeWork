package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BotClient extends Client {
    public static void main(String[] args) {
        new BotClient().run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + Math.random()*100;
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String[] user_question = message.split(": ");
            if (user_question.length != 2) return;
            String user = user_question[0];
            String question = user_question[1];
            String datePattern = "";
            if ("дата".equals(question)) datePattern = "d.MM.YYYY";
            else if ("день".equals(question)) datePattern = "d";
            else if ("месяц".equals(question)) datePattern = "MMMM";
            else if ("год".equals(question)) datePattern = "YYYY";
            else if ("время".equals(question)) datePattern = "H:mm:ss";
            else if ("час".equals(question)) datePattern = "H";
            else if ("минуты".equals(question)) datePattern = "m";
            else if ("секунды".equals(question)) datePattern = "s";
            else return;
            String information = new SimpleDateFormat(datePattern).format(Calendar.getInstance().getTime());
            sendTextMessage("Информация для " + user + ": " + information);

        }
    }
}
