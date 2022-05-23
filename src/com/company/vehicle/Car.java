package com.company.vehicle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Car extends Vehicle {

    public Car(Texture texture, int maxAcceleration, Color color) {
        super(texture, maxAcceleration, color);
    }

    public Car(Texture texture){
        super(texture, 10, Color.BLACK);
    }
}
