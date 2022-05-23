package com.company;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DBullet {

    private World world;
    public Body body;
    public boolean place;

    TextureAtlas atlas;
    Animation animation;
    Sprite sprite;

    public Box2DBullet(World world, Body bulletBody) {
        this.world = world;
        this.body = bulletBody;
        body.setUserData(this);

        atlas = new TextureAtlas(Gdx.files.internal("assets/Splunky.atlas"));
        animation = new Animation(1/15f,atlas.getRegions());
        sprite = new Sprite(animation.getKeyFrame(0));

    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void update(Box2DPlayer player) {

        if(place) {
                body.setTransform(player.body.getPosition().x, player.body.getPosition().y, 0);
                System.out.println("place");
        }
        sprite.setPosition(body.getPosition().x*100-48, body.getPosition().y*100-24);
}
}
