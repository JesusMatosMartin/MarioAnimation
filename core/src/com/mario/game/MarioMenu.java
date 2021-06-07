package com.mario.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MarioMenu implements Screen {

    final Mario game;

    OrthographicCamera camera;

    public MarioMenu(final Mario game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Mario.WIDTH, Mario.HEIGHT);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.setScreen(new MarioScreen(game));
        dispose();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}