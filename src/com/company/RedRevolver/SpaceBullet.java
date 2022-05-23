package com.company.RedRevolver;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpaceBullet extends Sprite {

    private float speed;
    private long expires;
    public float px;
    public float py;

    public SpaceBullet(Texture t){
        super(t);
        speed = 10;
        expires = System.currentTimeMillis() + 2000;
    }

    public boolean isExpired() {
        return expires < System.currentTimeMillis();
    } //makes the bullet disappear after 2s

    public void move(){
        translate(0, speed);
    } //moves the player's bullet up
}
