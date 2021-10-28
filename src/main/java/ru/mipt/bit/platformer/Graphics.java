package ru.mipt.bit.platformer;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public interface Graphics {

    void draw(Batch batch);

    Rectangle getBounding();


}
