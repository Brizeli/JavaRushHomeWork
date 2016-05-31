package com.javarush.test.level34.lesson15.big01.model;

/**
 * Created by Next on 17.05.2016.
 */
public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int x = getX();
        int y = getY();
        switch (direction) {
            case DOWN:
                y += Model.FIELD_SELL_SIZE;
                break;
            case UP:
                y -= Model.FIELD_SELL_SIZE;
                break;
            case RIGHT:
                x += Model.FIELD_SELL_SIZE;
                break;
            case LEFT:
                x -= Model.FIELD_SELL_SIZE;
                break;
        }
        if (x == gameObject.getX() && y == gameObject.getY()) return true;
        return false;
    }
}
