package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameObject {
    private final TextureRegion region;
    private final Rectangle bounding;
    public GridPoint2 coordinates;
    public float rotation;

    public GameObject(TiledMapTileLayer groundLayer, Texture texture, GridPoint2 coordinates, float rotation) {
        this.coordinates = coordinates;
        this.region = new TextureRegion(texture);
        bounding = createBoundingRectangle(region);
        this.rotation = rotation;
        moveRectangleAtTileCenter(groundLayer, bounding, coordinates);
    }

    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, region, bounding, rotation);
    }

    public Rectangle getBounding() {
        return bounding;
    }

}
