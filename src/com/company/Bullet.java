package com.company;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet extends Sprite {

    private float vx, vy;
    private float speed;
    private long expires;

    public Bullet(Texture t, float vx, float vy){
        super(t);
        this.vx = vx;
        this.vy = vy;
        speed = (float)Math.random() * 5 + 3;
        expires = System.currentTimeMillis() + 700;

    }

    public boolean isExpired() {
        return expires < System.currentTimeMillis();
    }

    public void move(){
        translate(vx*speed, vy*speed);
    }



}
