package com.company;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Box2DPlayer {

    private World world;
    public Body body;

    public boolean left, right, up;
    public int touchingRef = 0;

    TextureAtlas atlas;
    Animation animationLeft, animationRight, animationStop;
    Sprite sprite;
    int animationDirection = 0;

    float elapsedTime = 0;

    public Box2DPlayer(World world, Body playerBody) {
        this.world = world;
        this.body = playerBody;
        body.setUserData(this);

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

        animationLeft = new Animation(1 / 2f, left);
        animationRight = new Animation(1 / 2f, right);


        animationStop = new Animation(1 / 5f, stop);
        sprite = new Sprite(animationStop.getKeyFrame(0));

    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void update() {
        if (up) {
            if (touchingRef > 0) {
                body.applyLinearImpulse(new Vector2(0f, body.getMass()),
                        body.getWorldCenter(), true);
            }
        }
        if (left) {
            body.applyForce(new Vector2(-0.25f, 0),
                    body.getWorldCenter(), true);
            System.out.println("left");
            animationDirection = 0;

        }
        if (right) {
            body.applyForce(new Vector2(0.25f, 0),
                    body.getWorldCenter(), true);
            animationDirection = 1;
        }
        if (!right && !left) {
            animationDirection = 2;
        }
        sprite.setPosition(body.getPosition().x * 100 - 48, body.getPosition().y * 100 - 24);
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