package ru.mipt.bit.platformer.level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LevelFromFileGenerator implements LevelGenerator {
    @Override
    public void generateLevel(String filePath) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            String string = stringBuilder.toString();
            int index = string.indexOf('T');
            
        }

    }

    public static void main(String[] args) throws IOException {
        LevelFromFileGenerator levelFromFileGenerator = new LevelFromFileGenerator();
        levelFromFileGenerator.generateLevel("src/main/resources/disposition");

    }
}

