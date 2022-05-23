package com.company.pvzextras;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Pea extends Sprite {

    long expireTime;

    public Pea(Texture t) {
        super(t);
        expireTime = System.currentTimeMillis();
    }
//moves peas forward
    public void moveBullet() {
        translateX(5);
    }

    //checks if the peas are old and deletes to reduce lag
    public boolean isExpired() {
        if (System.currentTimeMillis() - expireTime > 2500) {
            expireTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

}
