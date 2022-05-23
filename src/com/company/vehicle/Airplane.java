package com.company.vehicle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Airplane extends Vehicle{
    public Airplane(Texture texture, int maxAcceleration, Color color) {
        super(texture, maxAcceleration, color);
        setAirbourne(true);
    }

}
