package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;

public class Obstacle extends GameObject {

    public Obstacle(TiledMapTileLayer groundLayer, Texture texture, GridPoint2 coordinates, float rotation) {
        super(groundLayer, texture, coordinates, rotation);
    }
}
