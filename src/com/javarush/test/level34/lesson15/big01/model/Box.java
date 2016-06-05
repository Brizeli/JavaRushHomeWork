package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Next on 17.05.2016.
 */
public class Box extends CollisionObject implements Movable {

    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        int leftX = getX() - getWidth()/2;
        int upperY = getY() - getHeight()/2;
        int rightX = leftX + getWidth();
        int lowerY = upperY + getHeight();
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(leftX, upperY, getWidth(), getHeight());
        graphics.setColor(Color.BLACK);
        graphics.drawRect(leftX, upperY, getWidth(), getHeight());
        graphics.drawLine(leftX,upperY,rightX,lowerY);
        graphics.drawLine(rightX,upperY,leftX,lowerY);
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
}
