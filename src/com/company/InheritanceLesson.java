package com.company;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.company.vehicle.Airplane;
import com.company.vehicle.Boat;
import com.company.vehicle.Car;
import com.company.vehicle.Vehicle;

import java.util.ArrayList;

public class InheritanceLesson implements ApplicationListener {

    SpriteBatch batch;
    Texture carTexture, boatTexture, airTexture;
    ArrayList<Vehicle> vehicles;

    @Override
    public void create() {
        batch = new SpriteBatch();
        vehicles = new ArrayList<>();
        carTexture = new Texture("assets/Bullet.png");
        boatTexture = new Texture("assets/Spaceship (6).png");
        airTexture = new Texture("assets/Thin Enemy.png");
        // we can add cars, boats, and planes to the vehicles array
        vehicles.add(new Car(carTexture, 15, Color.RED));
        vehicles.add(new Boat(boatTexture));
        vehicles.add(new Airplane(airTexture, 100, Color.WHITE));

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for(Vehicle v: vehicles){
            if(v instanceof Boat) {
                v.translateX(10);
            }
            v.draw(batch);
        }
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
