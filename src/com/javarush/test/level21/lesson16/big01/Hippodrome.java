package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

public class Hippodrome {
    private ArrayList<Horse> horses=new ArrayList<>();
    public static Hippodrome game;

    public static void main(String[] args) {
        game=new Hippodrome();
        Horse horse1=new Horse("horse1",3,0);
        Horse horse2=new Horse("horse2",3,0);
        Horse horse3=new Horse("horse3",3,0);
        game.getHorses().add(horse1);
        game.getHorses().add(horse2);
        game.getHorses().add(horse3);
        game.run();
        game.printWinner();
    }
    public ArrayList<Horse> getHorses() {
        return horses;
    }
    public Horse getWinner(){
        Horse winner=horses.get(0);
        for (int i = 1 ; i < horses.size(); i++) {
            if (horses.get(i).getDistance()>winner.getDistance())
                winner=horses.get(i);
        }
        return winner;
    }
    public void printWinner(){
        System.out.println("Winner is "+getWinner().getName()+"!");
    }
    public void run(){
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void move(){
        for (Horse horse:horses){
            horse.move();
        }
    }
    public void print(){
        for (Horse horse:horses) {
            horse.print();
        }
        System.out.println();
        System.out.println();
    }
}