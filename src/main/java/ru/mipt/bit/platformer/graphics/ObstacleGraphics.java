package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.graphics.GameObjGraphics;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class ObstacleGraphics implements GameObjGraphics {
    private final TextureRegion region;
    private final Rectangle bounding;
    public Texture texture;
    public final float rotation = 0f;

    public ObstacleGraphics(Texture texture, TiledMapTileLayer groundLayer, GridPoint2 coordinates) {
        region = new TextureRegion(texture);
        bounding = createBoundingRectangle(region);
        this.texture = texture;
        moveRectangleAtTileCenter(groundLayer, bounding, coordinates);
    }

    @Override
    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, region, bounding, rotation);
    }

    @Override
    public Rectangle getBounding() {
        return bounding;
    }
}

