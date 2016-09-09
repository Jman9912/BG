package com.psillicoder.bg;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Created by hp on 7/27/2016.
 */
public class MapHandler {

    BreakoutGame game;
    OrthographicCamera camera;
    public static final int MAP_WIDTH = 10;
    public static final int MAP_HEIGHT = 10;

    World world;

    //TiledMapRenderer
    TiledMapRenderer mRenderer;
    TiledMap level1Map;

    Array<Brick> brickList = new Array<Brick>();

    public MapHandler(World world, BreakoutGame game, OrthographicCamera cam) {
        this.game = game;
        this.camera = cam;
        this.world = world;

        level1Map = new TmxMapLoader().load("level1.tmx");
        mRenderer = new OrthoCachedTiledMapRenderer(level1Map,1 / BreakoutGame.PPM);

        setupCollisionTiles((TiledMapTileLayer)level1Map.getLayers().get(getWallLayer(level1Map)));
        setupCollisionTiles((TiledMapTileLayer)level1Map.getLayers().get(getBrickLayer(level1Map)));



    }

    public void render(SpriteBatch batch) {

        mRenderer.setView(camera);
        mRenderer.render();
    }


    private int getWallLayer(TiledMap map) {
        for (int i=0; i < map.getLayers().getCount(); i++) {
            if (map.getLayers().get(i).getName().equalsIgnoreCase("walls")) {
                return i;
            }
        }
        return -1;
    }

    private int getBrickLayer(TiledMap map) {
        for (int i=0; i < map.getLayers().getCount(); i++) {
            if (map.getLayers().get(i).getName().equalsIgnoreCase("bricks")) {
                return i;
            }

        }
        return -1;
    }


    private void setupCollisionTiles(TiledMapTileLayer layer) {
        for (int x = 0; x < layer.getWidth(); x++ ) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell == null) continue;
                if (cell.getTile() != null) {
                    Brick tBrick = new Brick(cell.getTile().getId(),world, (x * 32f) + 16f, (y * 16f) + 8f);
                    tBrick.setPosition(x * tBrick.getRect().width, y * tBrick.getRect().height);
                    brickList.add(tBrick);
                    System.out.println("Cell ID: " + cell.getTile().getId());
                    System.out.println("X: " + tBrick.getRect().x  / 32 + " Y: " + tBrick.getRect().y / 16);
                }else {continue;}
            }
        }

    }






}
