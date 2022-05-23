package com.company;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Arrays;

public class HighscoreScreen implements Screen {

    SpriteBatch batch;
    BitmapFont font;
    long reaction;

    FileHandle highscoreFile;
    String[] scores;

    FileIO game;

    public HighscoreScreen(long reaction, FileIO game) {
        this.game = game;
        System.out.println("HERE");
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.reaction = reaction;

        try {
            highscoreFile = Gdx.files.local("highscores.txt");
            if (!highscoreFile.exists()) {
                highscoreFile.file().createNewFile();
            }
            String contents = highscoreFile.readString();
            scores = contents.split("\n");
            Arrays.sort(scores);

            highscoreFile.writeString(reaction+"\n", true);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        int i = 1;
        batch.begin();
        for(String s:scores) {
            font.draw(batch, s + "", 100, i * 50 );
            i++;
        }
        batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game));
        }
    }


    @Override
    public void show() {
        System.out.println("HER2E");

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
