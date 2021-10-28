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
import ru.mipt.bit.platformer.util.ColliderManager;
import ru.mipt.bit.platformer.util.GdxKeyboardListener;
import ru.mipt.bit.platformer.util.TileMovement;

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
    private Obstacle tree;
    private ObstacleGraphics treeGraphics;
    private final ArrayList<GameObjGraphics> gameObjectsGraphics = new ArrayList<>();
    private final GdxKeyboardListener keyboardListener = new GdxKeyboardListener();
    public ColliderManager colliderManager = new ColliderManager();

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

    public void initCollider() {
        colliderManager.addObstacle(tree);
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

        loadGameObjectsAndGraphics();

        initCollider();

    }

    private void loadGameObjectsAndGraphics() {
        player = new Tank(new GridPoint2(1, 1), 0f);
        playerGraphics = new TankGraphics(new Texture("images/tank_blue.png"), groundLayer, player.coordinates, player.rotation);
        gameObjectsGraphics.add(playerGraphics);

        tree = new Obstacle(new GridPoint2(1, 3), 0f);
        treeGraphics = new ObstacleGraphics(new Texture("images/greenTree.png"), groundLayer, tree.coordinates);
        gameObjectsGraphics.add(treeGraphics);
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
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        playerGraphics.texture.dispose();
        treeGraphics.texture.dispose();
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

