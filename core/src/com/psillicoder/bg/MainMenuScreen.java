package com.psillicoder.bg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by hp on 7/4/2016.
 */
public class MainMenuScreen implements Screen {
    final BreakoutGame game;
    OrthographicCamera camera;

    public MainMenuScreen(final BreakoutGame gam) {
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 600);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.getData().setScale(2f);
        game.font.draw(game.batch, "Breakout!!! ", (Gdx.graphics.getWidth() / 2)-50, (Gdx.graphics.getHeight() / 2) + 100);
        game.font.draw(game.batch, "Tap anywhere to begin!",  (Gdx.graphics.getWidth() / 2)-125, (Gdx.graphics.getHeight() / 2) + 50);
        game.batch.end();
        game.font.getData().setScale(1f);

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            System.out.println("TOUCHED");
            dispose();
        }
    }

    public void dispose() {

    }

    public void show() {

    }

    public void hide() {

    }

    public void resize(int width, int height) {

    }

    public void pause() {}
    public void resume() {}

}
