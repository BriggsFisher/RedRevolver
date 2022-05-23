package com.company.RedRevolver;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class InvaderBullet extends Sprite {

    private float speedb;
    private long expiresb;
    public float px;
    public float py;

    public InvaderBullet(Texture t){
        super(t);
        speedb = -5;
        expiresb = System.currentTimeMillis() + 9000;
    }


    public boolean isExpiredb() {
        return expiresb < System.currentTimeMillis();
    } //after 9s the enemy bullets disappear


    public void moveb(){
            translate(px * 8, py * 8);
    }
}
