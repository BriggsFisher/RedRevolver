package com.company;

import com.badlogic.gdx.physics.box2d.*;
import com.company.LevelsExample.PlatformDef;

public class BodyGenerator {
    World world;

    public BodyGenerator(World world) {
        this.world = world;
    }

    public Body createRectangle(float x, float y, float width, float height, BodyDef.BodyType type, float angle) {

        // create a body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x,y);
        bodyDef.type = type;
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2, height/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        body.setTransform(x,y,angle);

        return body;
    }

    public Body createBullet(float x, float y, float width, float height, float angle) {

        // create a body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x,y);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2, height/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        body.setTransform(x,y,angle);

        return body;
    }

    public Body createCircle(float x, float y) {

        FixtureDef fixtureDef = new FixtureDef();

        // create a body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);


        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(0.16f);

        fixtureDef = new FixtureDef();
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);

        return body;

    }


    public Body createFloor(float x, float y, float width, float height) {

        // create a body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x,y);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2, height/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

        return body;

    }

    public Body createPlayer(float x, float y) {
        // create a body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);

        // create upper body
        PolygonShape shape = new PolygonShape();
        shape.set(new float[] {-0.08f, 0.0f, -0.08f, 0.32f, 0.08f, 0.32f, 0.08f, 0.0f, -0.08f, 0.0f});

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(0.08f);

        fixtureDef = new FixtureDef();
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.isSensor = true;
        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);

        return body;
    }

    public void createFloor(PlatformDef p) {
        createFloor(p.x,p.y,p.w,p.h);
    }
}