package com.company.TicTacToe;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class TicTacToe implements ApplicationListener {

    Sprite background;
    SpriteBatch batch;
    ArrayList<Ex> exs;
    ArrayList<Oh> ohs;
    Texture exTexture, ohTexture;
    boolean toggle = false;
    boolean player1 = true;

    @Override
    public void create() {
        batch = new SpriteBatch();
        exs = new ArrayList();
        ohs = new ArrayList();
        exTexture = new Texture("assets/PlayerX.png");
        ohTexture = new Texture("assets/PlayerO.png");
        background = new Sprite(new Texture("Assets/Grid.png"));
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        float mx = Gdx.input.getX();
        float my = Gdx.graphics.getHeight() - Gdx.input.getY();

        //Placing X
        if (Gdx.input.isButtonPressed(0) && mx > 0 && mx < 300 && my > 0 && my < 300 && !toggle && player1 == true) { //&& overlapping2 == false) {
            toggle = true;
            player1 = false;
            Ex z = new Ex(exTexture);
            exs.add(z);
            z.setPosition(((int) (mx / 100) * 100), (int) (my / 100) * 100);
            System.out.println(exs.size());
        }

//if the button isn't pressed the toggle is turned off so it doesnt spawn a ton of xs
        if (!Gdx.input.isButtonPressed(0)) {
            toggle = false;
        }

        //Placing O
        if (player1 == false) { //&& overlapping2 == false) {
            for (int q = exs.size() - 1; q >= 0; q--) {
                player1 = true;
                Oh z = new Oh(ohTexture);
                ohs.add(z);
                if (z.getX() == 0 && z.getY() < 100 || z.getY() > 200) {
                    z.setPosition(100, 100);
                    System.out.println("hello");
                }
            }
        }

        batch.begin();
        background.draw(batch);
        //draws X
        for (int k = exs.size() - 1; k >= 0; k--) {
            Ex j = exs.get(k);
            j.draw(batch);
        }
        //draws O
        for (int k = ohs.size() - 1; k >= 0; k--) {
            Oh j = ohs.get(k);
            j.draw(batch);
        }
        batch.end();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
