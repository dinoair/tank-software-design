package ru.mipt.bit.platformer.graphics;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public interface GameObjGraphics {

    void draw(Batch batch);

    Rectangle getBounding();


}
