package ru.mipt.bit.platformer.util;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public final class MovementProgress {
    private float progress;
    private final float speed;

    public MovementProgress(float speed) {
        progress = 0f;
        this.speed = speed;
    }

    public void reset() {
        progress = 0f;
    }

    public boolean finishedMoving() {
        return isEqual(progress, 1f);
    }

    public void update(float deltaTime) {
        progress = continueProgress(progress, deltaTime, speed);
    }

    public float getProgress() {
        return progress;
    }
}
