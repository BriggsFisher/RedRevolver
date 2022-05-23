package com.company;

import com.badlogic.gdx.Game;

public class FileIO extends Game {


    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        super.render();
     }
}
