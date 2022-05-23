package com.company.plants;

import com.badlogic.gdx.graphics.Texture;

public class Peashooter extends Plant {

    long spawnbulletTime;

    public Peashooter(Texture texture) {
        super(texture,100, 6);

        spawnbulletTime = System.currentTimeMillis();
    }

    //checks if it should spawn a pea
    public boolean shouldspawnBullet() {
        if (System.currentTimeMillis() - spawnbulletTime > 2000) {
            spawnbulletTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

}
