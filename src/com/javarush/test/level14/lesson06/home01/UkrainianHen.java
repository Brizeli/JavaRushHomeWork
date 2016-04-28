package com.javarush.test.level14.lesson06.home01;

public class UkrainianHen extends Hen {
    @Override
    int getCountOfEggsPerMonth() {
        return 300;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+" Моя страна - "+UKRAINE+". Я несу "+getCountOfEggsPerMonth()+" яиц в месяц.";
    }
}
