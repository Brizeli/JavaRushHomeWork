package com.javarush.test.level34.lesson15.big01.view;

import com.javarush.test.level34.lesson15.big01.controller.Controller;
import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.GameObjects;

import javax.swing.*;

public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);
    }

    public void init() {
        field = new Field(this);
        add(field);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Сокобан - " + controller.getModel().getCurrentLevel());
        setVisible(true);
    }

    public void update() {
        setTitle("Сокобан - " + controller.getModel().getCurrentLevel());
        field.repaint();
    }

    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }

    public void completed(int level) {
        update();
        JOptionPane.showMessageDialog(null, "Уровень " + level + " пройден", "Level", JOptionPane.INFORMATION_MESSAGE);
        controller.startNextLevel();
    }
}
