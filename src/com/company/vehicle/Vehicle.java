package com.company.vehicle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Vehicle extends Sprite {
    private float speed;
    private float maxAcceleration;
    private Color color;
    private boolean offRoad;
    private boolean airbourne;
    private boolean seaworthy;

    public Vehicle(Texture texture, int maxAcceleration, Color color) {
        super(texture);
        this.maxAcceleration = maxAcceleration;
        this.color = color;
    }

    public void accelerate(float acceleration) {
        if(acceleration > maxAcceleration){
            acceleration = maxAcceleration;
        }
        speed *= acceleration;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getMaxAcceleration() {
        return maxAcceleration;
    }

    public void setMaxAcceleration(float maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isOffRoad() {
        return offRoad;
    }

    public void setOffRoad(boolean offRoad) {
        this.offRoad = offRoad;
    }

    public boolean isAirbourne() {
        return airbourne;
    }

    public void setAirbourne(boolean airbourne) {
        this.airbourne = airbourne;
    }

    public boolean isSeaworthy() {
        return seaworthy;
    }

    public void setSeaworthy(boolean seaworthy) {
        this.seaworthy = seaworthy;
    }
}
