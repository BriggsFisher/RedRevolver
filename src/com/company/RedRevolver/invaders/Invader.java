package com.company.RedRevolver.invaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Invader extends Sprite {
    private int health;
    long invaderbullettime = System.currentTimeMillis();

    public Invader(Texture texture, int health){
        super(texture);
        this.health = health;
    }

    //remove 1 health from enemy
    public void damage(){
        health = health - 1;
    }

    //checks if the enemy has no health left
    public boolean isDead() {
        if (health < 1) {
            return true;
        }
        return false;
    }
    //if the invader goes offscreen it is deleted
    public boolean isPassed() {
        if (getY() < -20) {
            return true;
        }
        return false;
    }

    public boolean shouldShoot() {
        if(System.currentTimeMillis() - invaderbullettime > 1000){
            invaderbullettime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public void InvaderMoveDown() {
        translateY((float)(-1.3)); //when forwards is false the enemy moves left
    } //-1.3


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
