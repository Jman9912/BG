package com.psillicoder.bg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by hp on 7/4/2016.
 */
public class GameScreen implements Screen {
    final BreakoutGame game;

    final static float WIDTH = 20;
    final static float HEIGHT = 30;

    OrthographicCamera camera;
    Viewport viewport;
    private MapHandler mapHandler;
    CollisionHandler cHandler;

    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    World world = new World(new Vector2(0, -10), true);

    private Ball playerBall;
    private Paddle playerPaddle;

    public GameScreen(final BreakoutGame gam) {
        this.game = gam;
        cHandler = new CollisionHandler();
        world.setContactListener(cHandler);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, BreakoutGame.V_WIDTH / BreakoutGame.PPM, BreakoutGame.V_HEIGHT / BreakoutGame.PPM);
        viewport = new FitViewport(BreakoutGame.V_WIDTH / BreakoutGame.PPM, BreakoutGame.V_HEIGHT / BreakoutGame.PPM, camera);
        camera.position.set(viewport.getWorldWidth() / 2 , viewport.getWorldHeight() / 2 , 0);


        viewport.apply();
        mapHandler = new MapHandler(world, game, camera);



        game.font.getData().setScale(.03f);

        playerBall = new Ball(world, (BreakoutGame.V_WIDTH / 2), 100);
        playerPaddle = new Paddle(world, BreakoutGame.V_WIDTH / 2 - 64, 0);
    }


    public void update(float dt) {
        ProcessInput();
        game.batch.setProjectionMatrix(camera.combined);
        camera.update();


    }


    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapHandler.render(game.batch);

        //Game Render

        //HUD Render
        game.batch.getProjectionMatrix().set(camera.combined);
        game.batch.begin();
        game.font.draw(game.batch, "Body Count: " + world.getBodyCount(), 10 / BreakoutGame.PPM, Gdx.graphics.getHeight() / BreakoutGame.PPM - 10 / BreakoutGame.PPM);
        game.batch.end();

        debugRenderer.render(world, camera.combined);
        world.step(1/60f, 6, 2);
        update(delta);
    }






    private void ProcessInput() {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && playerPaddle.b2dBody.getLinearVelocity().x >= -1.0f) {
            playerPaddle.b2dBody.setType(BodyDef.BodyType.DynamicBody);
            playerPaddle.b2dBody.applyLinearImpulse(new Vector2(-.1f, 0), playerPaddle.b2dBody.getWorldCenter(), true);
            playerPaddle.b2dBody.setType(BodyDef.BodyType.KinematicBody);
    }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && playerPaddle.b2dBody.getLinearVelocity().x <= 1.0f) {
            playerPaddle.b2dBody.setType(BodyDef.BodyType.DynamicBody);
            playerPaddle.b2dBody.applyLinearImpulse(new Vector2(.1f, 0), playerPaddle.b2dBody.getWorldCenter(), true);
            playerPaddle.b2dBody.setType(BodyDef.BodyType.KinematicBody);
        }
           // System.out.println("Paddle Position: " + playerPaddle.b2dBody.getPosition() + " Paddle Velocity: " + playerPaddle.b2dBody.getLinearVelocity().x);

        if (Gdx.input.isKeyPressed(Input.Keys.J)) {
            Ball playerBall = new Ball(world,(BreakoutGame.V_WIDTH / 2), 100);
        }

    }

    public void dispose() {
        world.dispose();
        game.dispose();
    }

    public void show() {

    }

    public void hide() {

    }

    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.update();
    }

    public void pause() {
    }

    public void resume() {
    }
}