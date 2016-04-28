package com.javarush.test.level14.lesson06.home01;

public class RussianHen extends Hen {
    @Override
    int getCountOfEggsPerMonth() {
        return 100;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+" Моя страна - "+RUSSIA+". Я несу "+getCountOfEggsPerMonth()+" яиц в месяц.";
    }
}
