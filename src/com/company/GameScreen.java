package com.company;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

    FileIO game;
    long startTime = 0;
    long reaction = 0;

    BitmapFont font;

    SpriteBatch batch;

    public GameScreen(FileIO fileIO) {
        game = fileIO;
        batch = new SpriteBatch();
        font = new BitmapFont();
        startTime = System.currentTimeMillis() + 3000;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        long countdown = startTime - System.currentTimeMillis();
        batch.begin();
        font.draw(batch, "" + countdown, 400, 300);
        batch.end();
        if(countdown < 0) {
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                if(reaction == 0) {
                    reaction = countdown * -1;
                    System.out.println(reaction);
                    game.setScreen(new HighscoreScreen(reaction, game));
                }
            }
        } else {
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                Gdx.app.exit();
            }
        }

    }

    @Override
    public void resize(int i, int i1) {

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
