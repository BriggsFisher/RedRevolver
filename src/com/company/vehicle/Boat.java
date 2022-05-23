package com.company.vehicle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Boat extends Vehicle {

    public Boat(Texture texture) {
        super(texture, 6,Color.YELLOW);
        setSeaworthy(true);
    }
}
