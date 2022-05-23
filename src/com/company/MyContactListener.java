package com.company;

import com.badlogic.gdx.physics.box2d.*;

public class MyContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
     Fixture a = contact.getFixtureA();
     Fixture b = contact.getFixtureB();
     if(a.isSensor() && a.getBody().getUserData() != null && a.getBody().getUserData() instanceof Box2DPlayer) {
         ((Box2DPlayer) a.getBody().getUserData()).touchingRef++;
     }
        if(b.isSensor() && b.getBody().getUserData() != null && b.getBody().getUserData() instanceof Box2DPlayer) {
            ((Box2DPlayer) b.getBody().getUserData()).touchingRef++;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        if(a.isSensor() && a.getBody().getUserData() != null && a.getBody().getUserData() instanceof Box2DPlayer) {
            ((Box2DPlayer) a.getBody().getUserData()).touchingRef--;
        }
        if(b.isSensor() && b.getBody().getUserData() != null && b.getBody().getUserData() instanceof Box2DPlayer) {
            ((Box2DPlayer) b.getBody().getUserData()).touchingRef--;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
