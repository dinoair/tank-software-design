package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class Obstacle implements GameObject{
    public GridPoint2 coordinates;
    public float rotation;

    public Obstacle( GridPoint2 coordinates, float rotation) {
        this.coordinates = coordinates;
        this.rotation = rotation;
    }

}
