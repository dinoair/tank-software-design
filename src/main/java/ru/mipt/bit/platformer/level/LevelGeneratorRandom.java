package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Obstacle;
import ru.mipt.bit.platformer.Tank;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LevelGeneratorRandom implements LevelGenerator {
    public ArrayList<Obstacle> trees = new ArrayList<>();
    public ArrayList<Tank> tanks = new ArrayList<>();
    public final int MAPWIDTH = 10;
    public final int MAPHEIGHT = 8;
    final int NUMOFTANKS = (int) (Math.random() * (MAPWIDTH * MAPHEIGHT / 10));
    final int NUMOFTREES = (int) (Math.random() * (MAPWIDTH * MAPHEIGHT / 5));

    public GridPoint2 getGameObjectRandomCoordinates() {
        int x = (int) ((Math.random() * MAPWIDTH));
        int y = (int) ((Math.random() * MAPHEIGHT));
        return new GridPoint2(x, y);
    }


    @Override
    public void generateLevel() {
        // set is used to except case of creating 2 game objects on the same tile
        Set<GridPoint2> gameObjectsCoordinates = new HashSet<>();
        int currentNumOfTanks = 0;
        int currentNumOfTrees = 0;
        while (currentNumOfTanks < NUMOFTANKS || currentNumOfTanks < NUMOFTREES) {
            GridPoint2 nextCoordinates = getGameObjectRandomCoordinates();
            if (!gameObjectsCoordinates.contains(nextCoordinates) && currentNumOfTanks < NUMOFTANKS) {
                tanks.add(new Tank(nextCoordinates, 0f));
                gameObjectsCoordinates.add(nextCoordinates);
                currentNumOfTanks++;
            }
            GridPoint2 nextTreeCoordinates = getGameObjectRandomCoordinates();
            if (!gameObjectsCoordinates.contains(nextTreeCoordinates) && currentNumOfTrees < NUMOFTREES) {
                trees.add(new Obstacle(nextTreeCoordinates, 0f));
                gameObjectsCoordinates.add(nextTreeCoordinates);
                currentNumOfTrees++;
            }
        }

    }
}

