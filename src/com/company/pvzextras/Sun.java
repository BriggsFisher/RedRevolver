package com.company.pvzextras;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Sun extends Sprite {

    boolean moveable = false;

    public Sun(Texture t) {
        super(t);
    }

    //makes only sky sun moveable
    public void setMovable(boolean m) {
        moveable = m;
    }

    //moves sky sun down
    public void moveDown() {
        if (moveable) {
            translateY(-1);
        }
    }

}
