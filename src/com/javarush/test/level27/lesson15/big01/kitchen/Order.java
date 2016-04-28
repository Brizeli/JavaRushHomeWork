package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private List<Dish> dishes;
    private Tablet tablet;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public int getTotalCookingTime(){
        int res=0;
        for (Dish dish:dishes)
            res+=dish.getDuration();
        return res;
    }
    public boolean isEmpty(){
        return dishes.isEmpty()||dishes==null;
    }
    @Override
    public String toString() {
        if (isEmpty()) return "";
        return "Your order: " + dishes + " of " + tablet;
    }
}
