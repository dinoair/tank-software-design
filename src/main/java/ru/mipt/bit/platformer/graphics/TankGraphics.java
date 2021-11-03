package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.graphics.GameObjGraphics;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class TankGraphics implements GameObjGraphics {
    private final TextureRegion region;
    private final Rectangle bounding;
    public Texture texture;
    public float rotation;


    public TankGraphics(Texture texture, TiledMapTileLayer groundLayer, GridPoint2 coordinates, float rotation) {
        region = new TextureRegion(texture);
        bounding = createBoundingRectangle(region);
        this.texture = texture;
        this.rotation = rotation;
        moveRectangleAtTileCenter(groundLayer, bounding, coordinates);
    }

    @Override
    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, region, bounding, rotation);
    }

    public void updateRotation(float rotation){
        this.rotation = rotation;
    }

    @Override
    public Rectangle getBounding() {
        return bounding;
    }
}
