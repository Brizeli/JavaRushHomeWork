package com.javarush.test.level34.lesson15.big01.model;

/**
 * Created by Next on 17.05.2016.
 */
public enum Direction {
    LEFT, RIGHT, UP, DOWN;

    Direction opposite() {
        switch (this) {
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
        }
        return null;
    }
}
