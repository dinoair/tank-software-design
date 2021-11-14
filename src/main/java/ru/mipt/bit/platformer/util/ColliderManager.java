package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Obstacle;
import ru.mipt.bit.platformer.Tank;

import java.util.ArrayList;

public class ColliderManager {
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private final ArrayList<Tank> tanks = new ArrayList<>();

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }
    public void addTank(Tank tank) {
        tanks.add(tank);
    }


    public boolean canMove(GridPoint2 srcCoordinate, GridPoint2 move) {
        GridPoint2 estimatedCoordinates = srcCoordinate.cpy().add(move);
        for (Obstacle obstacle : obstacles) {
            if (obstacle.coordinates.equals(estimatedCoordinates)) {
                return false;
            }
        }
        for (Tank tank : tanks) {
            if (tank.coordinates.equals(estimatedCoordinates)){
                return false;
            }
        }
        return true;
    }
}
