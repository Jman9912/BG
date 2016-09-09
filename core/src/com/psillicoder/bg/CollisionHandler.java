package com.psillicoder.bg;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by hp on 9/7/2016.
 */
public class CollisionHandler implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
    if (contact.getFixtureA() != null && contact.getFixtureB() != null) {

        System.out.println("Collision Between: " + contact.getFixtureA().toString() + " and " + contact.getFixtureB().getUserData());
    }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
