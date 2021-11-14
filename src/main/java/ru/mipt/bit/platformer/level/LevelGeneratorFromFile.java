package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Obstacle;
import ru.mipt.bit.platformer.Tank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LevelGeneratorFromFile implements LevelGenerator {
    public ArrayList<Obstacle> trees = new ArrayList<>();
    public ArrayList<Tank> tanks = new ArrayList<>();
    public String filePath;
    public final int MAPWIDTH = 10;
    public final int MAPHEIGHT = 8;

    public LevelGeneratorFromFile(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public void generateLevel() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
            String string = stringBuilder.toString();
            ArrayList<GridPoint2> treesCoordinates = getObjectsCoordinates(string, 'T');
            ArrayList<GridPoint2> tanksCoordinates = getObjectsCoordinates(string, 'X');
            for (GridPoint2 treeCoordinate : treesCoordinates) {
                trees.add(new Obstacle(treeCoordinate, 0f));

            }
            for (GridPoint2 tankCoordinate : tanksCoordinates) {
                tanks.add(new Tank(tankCoordinate, 0f));
            }
        }


    }

    private ArrayList<GridPoint2> getObjectsCoordinates(String string, char obj) {
        int index = string.indexOf(obj);
        ArrayList<GridPoint2> coordinates = new ArrayList<>();
        while (index >= 0) {
            coordinates.add(new GridPoint2(getX(index), getY(index)));
            index = string.indexOf(obj, index + 1);
        }
        return coordinates;
    }

    private int getY(int index) {
        return MAPHEIGHT - 1 - index / MAPWIDTH;
    }

    private int getX(int index) {
        return index % MAPWIDTH;
    }

}

