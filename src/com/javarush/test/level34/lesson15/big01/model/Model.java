package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;
import java.util.Stack;

/**
 * Created by Next on 17.05.2016.
 */
public class Model {
    public static final int FIELD_SELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private Stack<Turn> moves;
    private int currentLevel = 10;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("src/com/javarush/test/level34/lesson15/big01/res/levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        moves = new Stack<>();
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restartLevel(currentLevel);
    }

    public void moveBack() {
        if (moves.empty())return;
        Turn backMove = moves.pop();
        Direction direction = backMove.getDirection().opposite();
        Player player = gameObjects.getPlayer();
        objectMove(direction,player);
        Box box = backMove.getBox();
        if (box !=null)
            objectMove(direction, box);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)) return;
        moves.add(new Turn(direction,null));
        if (checkBoxCollision(direction)) {
            moves.pop();
            return;
        }
        objectMove(direction, player);
        checkCompletion();
    }

    private void objectMove(Direction direction, Movable movable) {
        switch (direction) {
            case LEFT:
                movable.move(-FIELD_SELL_SIZE, 0);
                break;
            case RIGHT:
                movable.move(FIELD_SELL_SIZE, 0);
                break;
            case UP:
                movable.move(0, -FIELD_SELL_SIZE);
                break;
            case DOWN:
                movable.move(0, FIELD_SELL_SIZE);
                break;
        }
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction))
                return true;
        }
        return false;
    }

    public boolean checkBoxCollision(Direction direction) {
        Player player = gameObjects.getPlayer();
        Box collision = null;
        for (Box box : gameObjects.getBoxes()) {
            if (player.isCollision(box, direction)) {
                collision = box;
                break;
            }
        }
        if (collision == null) return false;
        if (checkWallCollision(collision, direction))
            return true;
        moves.peek().setBox(collision);
        for (Box box : gameObjects.getBoxes())
            if (collision.isCollision(box, direction))
                return true;
        objectMove(direction,collision);
        return false;
    }

    public void checkCompletion() {
        boolean res = true;
        for (Home home : gameObjects.getHomes()) {
            boolean currentHome = false;
            for (Box box : gameObjects.getBoxes())
                if (box.getX() == home.getX() && box.getY() == home.getY())
                    currentHome = true;
            if (!currentHome) res = false;
        }
        if (res) eventListener.levelCompleted(currentLevel);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public LevelLoader getLevelLoader() {
        return levelLoader;
    }
}
