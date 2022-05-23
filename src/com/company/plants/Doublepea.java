package com.company.plants;

import com.badlogic.gdx.graphics.Texture;

public class Doublepea extends Plant {

    long spawndoublepeabulletTime;

    public Doublepea(Texture texture) {
        super(texture, 200, 10);

        spawndoublepeabulletTime = System.currentTimeMillis();
    }

//checks if it should spawn doublepea bullet
    public boolean shouldspawndoublepeaBullet() {
        if (System.currentTimeMillis() - spawndoublepeabulletTime > 1000) {
            spawndoublepeabulletTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

}
