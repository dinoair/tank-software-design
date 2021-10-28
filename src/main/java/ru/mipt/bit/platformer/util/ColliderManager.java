package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Obstacle;

import java.util.ArrayList;

public class ColliderManager {
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public boolean canMove(GridPoint2 srcCoordinate, GridPoint2 move) {
        GridPoint2 estimatedCoordinates = srcCoordinate.cpy().add(move);
        for (var obstacle : obstacles) {
            if (obstacle.coordinates.equals(estimatedCoordinates)) {
                return false;
            }
        }
        return true;
    }
}
