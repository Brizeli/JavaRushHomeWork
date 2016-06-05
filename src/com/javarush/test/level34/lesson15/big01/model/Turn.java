package com.javarush.test.level34.lesson15.big01.model;

/**
 * Created by Next on 30.05.2016.
 */
public class Turn {
    private Direction direction;
    private Box box=null;

    public Turn(Direction direction, Box box) {
        this.direction = direction;
        this.box = box;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public Box getBox() {
        return box;
    }
}
