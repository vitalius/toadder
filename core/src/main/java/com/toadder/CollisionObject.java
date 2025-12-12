package com.toadder;

/**
 * Simple square overlap detection
 */
public abstract class CollisionObject {
    public float x;
    public float y;
    public float size;

    public boolean isColliding(CollisionObject a) {
        if ( (x+size) > (a.x) && (y+size) > a.y )
            return true;
        return false;
    }
}
