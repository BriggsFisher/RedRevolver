package com.company.plants;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Plant extends Sprite {
    private int cost, health;

    public Plant(Texture texture, int cost, int health) {
        super(texture);
        this.cost = cost;
        this.health = health;
    }

    //removes 1 health from the plant
    public void eat(){
        health = health - 1;
    }

    //checks if the plant has no health left
    public boolean isDead() {
        if (health < 1) {
            return true;
        }
        return false;
    }



    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}