package com.psillicoder.bg;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by hp on 8/9/2016.
 */
public class Brick {
    private static float WIDTH = 32 / BreakoutGame.PPM;
    private static float HEIGHT = 16 / BreakoutGame.PPM;

    private float x;
    private float y;

    public Body b2dBody;
    private World world;

    private int brickType;



    public Brick (int type, World world, float x, float y) {
        this.brickType = type;
        this.x = x / BreakoutGame.PPM;
        this.y = y / BreakoutGame.PPM;
        this.world = world;
        setupBrick();
    }

    private void setupBrick() {

            BodyDef brickBodyDef = new BodyDef();
            brickBodyDef.position.set(this.x,this.y);
            b2dBody = world.createBody(brickBodyDef);
            PolygonShape brickShape = new PolygonShape();
            brickShape.setAsBox(WIDTH / 2f,HEIGHT / 2f);
            b2dBody.createFixture(brickShape, 0.0f);
            brickShape.dispose();


        }


    public void setPosition(float x, float y) {
        this.x = x / BreakoutGame.PPM;
        this.y = y / BreakoutGame.PPM;
    }

    public Rectangle getRect() {
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }
}
