package com.company;

public abstract class Enemy {
    private int health;
    private int damage;
    public Enemy(int health, int damage) {
this.damage = damage;
this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}