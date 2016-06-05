package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Next on 17.05.2016.
 */
public class Wall extends CollisionObject {

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        int leftX = getX() - getWidth() / 2;
        int upperY = getY() - getHeight() / 2;
        int rightX = leftX+getWidth();
        int lowerY = upperY+getHeight();

        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(leftX, upperY, getWidth(), getHeight());
        graphics.setColor(Color.BLACK);
        graphics.drawRect(leftX, upperY, getWidth(), getHeight());
    }
}
