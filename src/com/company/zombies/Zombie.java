package com.company.zombies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Zombie extends Sprite {
    private int speed, health;

    public Zombie(Texture texture, int speed, int health) {
        super(texture);
        this.speed = speed;
        this.health = health;
    }
//moves the zombies to the left
    public void moveZombie() {
        translateX((float)-0.5);
    }




    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}