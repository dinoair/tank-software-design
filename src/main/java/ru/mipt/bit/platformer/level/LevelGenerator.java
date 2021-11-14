package ru.mipt.bit.platformer.level;

import java.io.IOException;

public interface LevelGenerator {

    void generateLevel() throws IOException;
}
