package com.mario.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.Rectangle;

public class MarioScreen implements Screen {
	public BitmapFont font;

	private static final int FRAME_COLS = 4, FRAME_ROWS = 4;
	Mario game;
	Animation<TextureRegion> upAnimation;
	Animation<TextureRegion> downAnimation;
	Animation<TextureRegion> leftAnimation;
	Animation<TextureRegion> rightAnimation;
	OrthographicCamera camera;
	Rectangle mario;
	Texture walkSheet;
	float stateTime;

	public MarioScreen(Mario game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Mario.WIDTH, Mario.HEIGHT);
		walkSheet = new Texture(Gdx.files.internal("mario.png"));
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);
		TextureRegion[] downFrames = new TextureRegion[FRAME_COLS];
		TextureRegion[] leftFrames = new TextureRegion[FRAME_COLS];
		TextureRegion[] upFrames = new TextureRegion[FRAME_COLS];
		TextureRegion[] rightFrames = new TextureRegion[FRAME_COLS];

		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			index = 0;
			for (int j = 0; j < FRAME_COLS; j++) {
				if (i == 0)
					downFrames[index++] = tmp[i][j];
				if (i == 1)
					rightFrames[index++] = tmp[i][j];
				if (i == 2)
					upFrames[index++] = tmp[i][j];
				if (i == 3)
					leftFrames[index++] = tmp[i][j];
			}
		}

		leftAnimation = new Animation<TextureRegion>(0.100f, upFrames);
		downAnimation = new Animation<TextureRegion>(0.100f, downFrames);
		rightAnimation = new Animation<TextureRegion>(0.100f, rightFrames);
		upAnimation = new Animation<TextureRegion>(0.100f, leftFrames);
		mario = new Rectangle();
		mario.x = 100;
		mario.y = 100;
		mario.height = 100;
		mario.width = 100;
		stateTime = 0f;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.batch.draw(new Texture(Gdx.files.internal("background.png")), 0, 0);

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			game.batch.draw(leftAnimation.getKeyFrame(stateTime, true), mario.x, mario.y);
			mario.x -= 100 * Gdx.graphics.getDeltaTime();
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			game.batch.draw(rightAnimation.getKeyFrame(stateTime, true), mario.x, mario.y);
			mario.x += 100 * Gdx.graphics.getDeltaTime();
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			game.batch.draw(upAnimation.getKeyFrame(stateTime, true), mario.x, mario.y);
			mario.y += 100 * Gdx.graphics.getDeltaTime();
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			game.batch.draw(downAnimation.getKeyFrame(stateTime, true), mario.x, mario.y);
			mario.y -= 100 * Gdx.graphics.getDeltaTime();
		} else {
			game.batch.draw(downAnimation.getKeyFrames()[1], mario.x, mario.y, mario.width, mario.height);
		}
		game.batch.end();
	}

	@Override
	public void show() {

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
		game.batch.dispose();
	}
}
