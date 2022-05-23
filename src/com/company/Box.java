package com.company;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

public class Box extends Sprite {

    Body body;

    public Box(Texture t) {
        super(t);
    }
    public void update() {
        setPosition(body.getPosition().x*100-16, body.getPosition().y*100-16);
        setRotation((float)Math.toDegrees(body.getAngle()));
    }
}
