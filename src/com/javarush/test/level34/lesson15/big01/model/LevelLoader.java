package com.javarush.test.level34.lesson15.big01.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Next on 17.05.2016.
 */
public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        if (level > 60) level -= 60;
        int fSize = Model.FIELD_SELL_SIZE;
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toString()))) {
            while (!reader.readLine().equals("Maze: " + level)) ;
            reader.readLine();
            int width = Integer.parseInt(reader.readLine().split(" ")[2]);
            int height = Integer.parseInt(reader.readLine().split(" ")[2]);
            reader.readLine();
            reader.readLine();
            reader.readLine();
            for (int y = 0; y < height; y++) {
                char[] charObjects = reader.readLine().toCharArray();
                int y0 = y*fSize + fSize/2;
                for (int x = 0; x < width; x++) {
                    int x0 = x*fSize + fSize/2;
                    switch (charObjects[x]) {
                        case '@':
                            player = new Player(x0, y0);
                            break;
                        case 'X':
                            walls.add(new Wall(x0, y0));
                            break;
                        case '*':
                            boxes.add(new Box(x0, y0));
                            break;
                        case '.':
                            homes.add(new Home(x0, y0));
                            break;
                        case '&':
                            homes.add(new Home(x0, y0));
                            boxes.add(new Box(x0, y0));
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
