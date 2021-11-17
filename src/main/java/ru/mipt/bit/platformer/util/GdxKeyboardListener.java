package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.Input.Keys.*;

public class GdxKeyboardListener implements KeyboardListener {
    @Override
    public boolean isLeft() {
        return Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A);
    }

    @Override
    public boolean isUp() {
        return Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W);

    }

    @Override
    public boolean isRight() {
        return Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D);

    }

    @Override
    public boolean isDown() {
        return Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S);

    }
}
