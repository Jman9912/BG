package com.psillicoder.bg;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by hp on 8/10/2016.
 */
public class Ball {

    private static float WIDTH = 16 / BreakoutGame.PPM;
    private static float HEIGHT = 16 / BreakoutGame.PPM;

    private float x, y;

    private World world;
    public Body b2dBody;

        public Ball(World world, float x, float y) {
            this.x = x / BreakoutGame.PPM;
            this.y = y / BreakoutGame.PPM;
            this.world = world;
            setupBall();
        }

    private void setupBall() {
        BodyDef ballBodyDef = new BodyDef();
        ballBodyDef.type = BodyDef.BodyType.DynamicBody;
        ballBodyDef.position.set(this.x, this.y);
        b2dBody = world.createBody(ballBodyDef);

        CircleShape ballShape = new CircleShape();
        ballShape.setRadius(8f / BreakoutGame.PPM);

        FixtureDef ballFixtureDef = new FixtureDef();
        ballFixtureDef.shape = ballShape;
        ballFixtureDef.density = 0.5f;
        ballFixtureDef.friction = 0.4f;
        ballFixtureDef.restitution = 1.01f;

        b2dBody.createFixture(ballFixtureDef);

        ballShape.dispose();

    }




    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
