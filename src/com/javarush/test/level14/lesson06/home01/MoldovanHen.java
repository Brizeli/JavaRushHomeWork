package com.javarush.test.level14.lesson06.home01;

public class MoldovanHen extends Hen {
    @Override
    int getCountOfEggsPerMonth() {
        return 150;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+" Моя страна - "+MOLDOVA+". Я несу "+getCountOfEggsPerMonth()+" яиц в месяц.";
    }
}
