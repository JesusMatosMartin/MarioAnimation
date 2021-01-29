package com.mario.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private Animation animation;
	private float timePassed = 0;
	private Texture textureBack;
	OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		textureBack = new Texture("background.png");
		atlas = new TextureAtlas(Gdx.files.internal("mario.atlas"));
		animation = new Animation(1/10f,atlas.getRegions());
	}

	@Override
	public void dispose(){
		batch.dispose();
		atlas.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(textureBack,0,0,3000,1500);
		timePassed += Gdx.graphics.getDeltaTime();
		batch.draw((TextureRegion) animation.getKeyFrame(timePassed,true),100,100);
		batch.end();
	}
}
