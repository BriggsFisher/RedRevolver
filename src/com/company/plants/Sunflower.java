package com.company.plants;

import com.badlogic.gdx.graphics.Texture;

public class Sunflower extends Plant {

    long spawnsunTime;

    public Sunflower(Texture texture){
        super(texture, 50, 4);

        spawnsunTime = System.currentTimeMillis();
    }
    //checks if sunflower should spawn a sun
    public boolean shouldspawnSun() {
        if (System.currentTimeMillis() - spawnsunTime > 15000) {
            spawnsunTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
