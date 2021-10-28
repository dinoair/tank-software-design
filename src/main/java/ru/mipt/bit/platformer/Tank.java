package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.*;

public class Tank {
    private static final float MOVEMENT_SPEED = 0.4f;
    public GridPoint2 coordinates;
    public float rotation;
    public final MovementProgress movementProgress;
    public final GridPoint2 previousCoordinates;

    public Tank(GridPoint2 coordinates, float rotation) {
        this.coordinates = coordinates;
        this.rotation = rotation;
        movementProgress = new MovementProgress(MOVEMENT_SPEED);
        previousCoordinates = new GridPoint2(coordinates);

    }

    public void checkAndSetupMove(Direction direction, ColliderManager colliderManager) {
        if (movementProgress.finishedMoving()) {
            GridPoint2 estimatedCoordinates = new GridPoint2(coordinates);
            estimatedCoordinates.add(direction.getCoordinate());
            // check potential player destination for collision with obstacles
            if (!colliderManager.canMove(coordinates, direction.getCoordinate())) {
                return;
            }
            rotation = direction.getAngle();
            coordinates = estimatedCoordinates;
            movementProgress.reset();
        }
    }

    public void move(float deltaTime, ColliderManager colliderManager, GdxKeyboardListener keyboardListener) {
        if (keyboardListener.isUp()) {
            checkAndSetupMove(new Direction(0, 1), colliderManager);
        }
        if (keyboardListener.isLeft()) {
            checkAndSetupMove(new Direction(-1, 0), colliderManager);
        }
        if (keyboardListener.isDown()) {
            checkAndSetupMove(new Direction(0, -1), colliderManager);
        }
        if (keyboardListener.isRight()) {
            checkAndSetupMove(new Direction(1, 0), colliderManager);
        }


        movementProgress.update(deltaTime);
        if (movementProgress.finishedMoving()) {
            // record that the player has reached the destination
            previousCoordinates.set(coordinates);
        }
    }
}
