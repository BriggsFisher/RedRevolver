package com.company.RedRevolver.invaders;

import com.badlogic.gdx.graphics.Texture;

public class Laser extends Invader {
    long invaderbullettimeleft = System.currentTimeMillis();
    long invaderbullettimeright = System.currentTimeMillis();

    public Laser(Texture texture){
        super(texture, 6);
    }

    //shoots a bullet left
    public boolean shouldshootLeft() {
        if(System.currentTimeMillis() - invaderbullettimeleft > 1000){
            invaderbullettimeleft = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    //shoots a bullet right
    public boolean shouldshootRight() {
        if(System.currentTimeMillis() - invaderbullettimeright > 1000){
            invaderbullettimeright = System.currentTimeMillis();
            return true;
        }
        return false;
    }


}
