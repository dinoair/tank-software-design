package ru.mipt.bit.platformer.level;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LevelGeneratorFromFileTest {
    LevelGeneratorFromFile levelGenerator = new LevelGeneratorFromFile("src/main/resources/disposition");


    @ParameterizedTest
    @ValueSource(ints = 4)
    void testEstimationXCoordinate(int x) throws IOException {
        levelGenerator.generateLevel();
        int estimatedTankXCoordinate = levelGenerator.tanks.get(0).
                coordinates.x;
        assertEquals(estimatedTankXCoordinate, x);
    }

    @ParameterizedTest
    @ValueSource(ints = 3)
    void testEstimationYCoordinate(int y) throws IOException {
        levelGenerator.generateLevel();
        int estimatedTankYCoordinate = levelGenerator.tanks.get(0).
                coordinates.y;
        assertEquals(estimatedTankYCoordinate, y);
    }

}