package com.psillicoder.bg;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by hp on 8/10/2016.
 */
public class Paddle {

    private static final float WIDTH = 64 / BreakoutGame.PPM;
    private static final float HEIGHT = 16 / BreakoutGame.PPM;

    private float x, y;

    public Body b2dBody;
    private World world;

    public Paddle(World world, float x, float y) {
        this.x = x / BreakoutGame.PPM;
        this.y = y / BreakoutGame.PPM;
        this.world = world;

        setupPaddle();
    }

    private void setupPaddle() {
        BodyDef paddleBodyDef = new BodyDef();

        paddleBodyDef.type = BodyDef.BodyType.StaticBody;
        paddleBodyDef.gravityScale = 0.0f;

        paddleBodyDef.position.set(this.x + WIDTH,this.y + HEIGHT);
        b2dBody = world.createBody(paddleBodyDef);

        PolygonShape paddleShape = new PolygonShape();
        paddleShape.setAsBox((WIDTH / 2), (HEIGHT / 2));

        b2dBody.createFixture(paddleShape,0f);
        paddleShape.dispose();


    }

}
