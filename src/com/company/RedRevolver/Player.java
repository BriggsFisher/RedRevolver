package com.company.RedRevolver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player {
    public int health;

    public boolean left, right;
    public int touchingRef = 0;
    TextureAtlas atlas;
    Animation animationLeft, animationRight, animationStop;
    Sprite sprite;
    int animationDirection = 0;
    float elapsedTime = 0;


    //constructor
    public Player(Texture t, int health) {
        // this will get run when the player object is initialized
        this.health = health;
        atlas = new TextureAtlas(Gdx.files.internal("assets/SpaceShipping.atlas"));
        Array<TextureAtlas.AtlasRegion> left = new Array<>();
        left.add(atlas.findRegion("Left"));
        left.add(atlas.findRegion("Farleft"));

        Array<TextureAtlas.AtlasRegion> right = new Array<>();
        right.add(atlas.findRegion("Right"));
        right.add(atlas.findRegion("Farright"));

        Array<TextureAtlas.AtlasRegion> stop = new Array<>();
        stop.add(atlas.findRegion("Stop1"));
        stop.add(atlas.findRegion("Stop2"));
        stop.add(atlas.findRegion("Stop3"));

        animationLeft = new Animation(1 / 2f, left); //plays the moving left animations
        animationRight = new Animation(1 / 2f, right); //plays the moving right animations


        animationStop = new Animation(1 / 5f, stop); //plays the standing still animations
        sprite = new Sprite(animationStop.getKeyFrame(0));
    }




    //removes 1 health from the player
    public void playerDamage(){
        health = health - 1;
    }

    //checks if the player has no health left
    public boolean playerisDead() {
        if (health < 1) {
            return true;
        }
        return false;
    }

    public void moveLeft() {
        //as long as you aren't using the special then you will go medium speed
        if(Gdx.input.isKeyPressed(Input.Keys.Z) && !Gdx.input.isKeyPressed(Input.Keys.X)) {
                sprite.translateX(-3);
        }
        //it doesn't matter what keys are being pressed as long as X is pressed you move at minimum speed and use the special
            if(Gdx.input.isKeyPressed(Input.Keys.X)) {
                sprite.translateX((float)(-1.2));
        }
        //if only C is pressed move at max speed
        if(Gdx.input.isKeyPressed(Input.Keys.C) && !Gdx.input.isKeyPressed(Input.Keys.X) && !Gdx.input.isKeyPressed(Input.Keys.Z)){
            sprite.translateX(-8);
        }
        //If nothing is pressed move at max speed
        if(!Gdx.input.isKeyPressed(Input.Keys.C) && !Gdx.input.isKeyPressed(Input.Keys.X) && !Gdx.input.isKeyPressed(Input.Keys.Z)){
            sprite.translateX(-8);
        }
    }

    public void moveRight() {
        if(Gdx.input.isKeyPressed(Input.Keys.Z) && !Gdx.input.isKeyPressed(Input.Keys.X)) {
            sprite.translateX(3);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.X)) {
            sprite.translateX((float)1.2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.C) && !Gdx.input.isKeyPressed(Input.Keys.X) && !Gdx.input.isKeyPressed(Input.Keys.Z)){
            sprite.translateX(8);
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.C) && !Gdx.input.isKeyPressed(Input.Keys.X) && !Gdx.input.isKeyPressed(Input.Keys.Z)){
            sprite.translateX(8);
        }
    }

    public void moveUp() {
        if(Gdx.input.isKeyPressed(Input.Keys.Z) && !Gdx.input.isKeyPressed(Input.Keys.X)) {
            sprite.translateY(3);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.X)) {
            sprite.translateY((float)1.2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.C) && !Gdx.input.isKeyPressed(Input.Keys.X) && !Gdx.input.isKeyPressed(Input.Keys.Z)){
            sprite.translateY(8);
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.C) && !Gdx.input.isKeyPressed(Input.Keys.X) && !Gdx.input.isKeyPressed(Input.Keys.Z)){
            sprite.translateY(8);
        }
    }
    public void moveDown() {
        if(Gdx.input.isKeyPressed(Input.Keys.Z) && !Gdx.input.isKeyPressed(Input.Keys.X)) {
            sprite.translateY(-3);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.X)) {
            sprite.translateY((float)-1.2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.C) && !Gdx.input.isKeyPressed(Input.Keys.X) && !Gdx.input.isKeyPressed(Input.Keys.Z)){
            sprite.translateY(-8);
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.C) && !Gdx.input.isKeyPressed(Input.Keys.X) && !Gdx.input.isKeyPressed(Input.Keys.Z)){
            sprite.translateY(-8);
        }
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void update() {
        //This section is for the animation of the player
        if (left) {
            animationDirection = 0;
        }
        if (right) {
            animationDirection = 1;
        }
        if (!right && !left) {
            animationDirection = 2;
        }
        if (animationDirection == 0) {
            elapsedTime += Gdx.graphics.getDeltaTime();
            if (elapsedTime > animationLeft.getAnimationDuration()) {
                elapsedTime -= animationLeft.getAnimationDuration();
            }
            sprite.setRegion(animationLeft.getKeyFrame(elapsedTime));
        }
        if (animationDirection == 1) {
            elapsedTime += Gdx.graphics.getDeltaTime();
            if (elapsedTime > animationRight.getAnimationDuration()) {
                elapsedTime -= animationRight.getAnimationDuration();
            }
            sprite.setRegion(animationRight.getKeyFrame(elapsedTime));
        }
        if (animationDirection == 2) {
            elapsedTime += Gdx.graphics.getDeltaTime();
            if (elapsedTime > animationStop.getAnimationDuration()) {
                elapsedTime -= animationStop.getAnimationDuration();
            }
            sprite.setRegion(animationStop.getKeyFrame(elapsedTime));
        }
    }
}