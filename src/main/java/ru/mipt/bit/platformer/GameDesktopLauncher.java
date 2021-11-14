package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.graphics.GameObjGraphics;
import ru.mipt.bit.platformer.graphics.ObstacleGraphics;
import ru.mipt.bit.platformer.graphics.TankGraphics;
import ru.mipt.bit.platformer.level.LevelGeneratorFromFile;
import ru.mipt.bit.platformer.level.LevelGeneratorRandom;
import ru.mipt.bit.platformer.util.ColliderManager;
import ru.mipt.bit.platformer.util.GdxKeyboardListener;
import ru.mipt.bit.platformer.util.TileMovement;

import java.io.IOException;
import java.util.ArrayList;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class GameDesktopLauncher implements ApplicationListener {
    private Batch batch;
    private TiledMap level;
    private TiledMapTileLayer groundLayer;
    private MapRenderer levelRenderer;
    private Tank player;
    private TankGraphics playerGraphics;
    private final ArrayList<GameObjGraphics> gameObjectsGraphics = new ArrayList<>();
    private final GdxKeyboardListener keyboardListener = new GdxKeyboardListener();
    public ColliderManager colliderManager = new ColliderManager();
    public LevelGeneratorFromFile levelGenerator = new LevelGeneratorFromFile("src/main/resources/disposition");
//    public LevelGeneratorRandom levelGenerator = new LevelGeneratorRandom();

    @Override
    public void render() {
        clearScreen();

        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();

        player.move(deltaTime, colliderManager, keyboardListener);
        playerGraphics.updateRotation(player.rotation);

        TileMovement tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        tileMovement.moveRectangleBetweenTileCenters(playerGraphics.getBounding(), player.previousCoordinates, player.coordinates, player.movementProgress.getProgress());

        // render each tile of the level
        levelRenderer.render();

        // start recording all drawing commands
        batch.begin();
        for (GameObjGraphics graphics : gameObjectsGraphics) {
            graphics.draw(batch);
        }

        // submit all drawing requests
        batch.end();
    }

    public void initColliderManager() {
        for (Obstacle obstacle : levelGenerator.trees) {
            colliderManager.addObstacle(obstacle);
        }
        for (Tank tank : levelGenerator.tanks) {
            colliderManager.addTank(tank);
        }
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void create() {
        initLevelRenderer();

        // load level tiles
        level = new TmxMapLoader().load("level.tmx");
        groundLayer = getSingleLayer(level);

        levelRenderer = createSingleLayerMapRenderer(level, batch);

        try {
            loadGameObjectsAndGraphics();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initColliderManager();

    }

    private void loadGameObjectsAndGraphics() throws IOException {
        levelGenerator.generateLevel();

        player = new Tank(new GridPoint2(1, 1), 0f);
////        uncomment the line below to initialize player using class LevelGeneratorRandom (and comment line above)
//        player = new Tank(levelGenerator.getGameObjectRandomCoordinates(), 0f);


        for (Obstacle obstacle : levelGenerator.trees) {
            gameObjectsGraphics.add(new ObstacleGraphics(new Texture("images/greenTree.png"), groundLayer, obstacle.coordinates));
        }
        for (Tank tank : levelGenerator.tanks) {
            gameObjectsGraphics.add(new TankGraphics(new Texture("images/tank_blue.png"), groundLayer, tank.coordinates, 0f));
        }
        playerGraphics = new TankGraphics(new Texture("images/tank_blue.png"), groundLayer, player.coordinates, player.rotation);
        gameObjectsGraphics.add(playerGraphics);

    }

    private void initLevelRenderer() {
        batch = new SpriteBatch();
    }


    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        for (GameObjGraphics gameObjGraphics : gameObjectsGraphics) {
            if (gameObjGraphics instanceof TankGraphics) {
                ((TankGraphics) gameObjGraphics).texture.dispose();
            } else {
                ((ObstacleGraphics) gameObjGraphics).texture.dispose();
            }
        }
        level.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}

