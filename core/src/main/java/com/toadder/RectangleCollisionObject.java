package com.toadder;

/**
 * Simple rectangle overlap detection
 */
public abstract class RectangleCollisionObject {
    public abstract float getX();
    public abstract float getY();
    public abstract float getWidth();
    public abstract float getHeight();

    public boolean isColliding(RectangleCollisionObject a) {
        float x = getX();
        float w = getWidth();
        float y = getY();
        float h = getHeight();

        float ax = a.getX();
        float aw = a.getWidth();
        float ay = a.getY();
        float ah = a.getHeight();

        /*
         *   x,y+h|........|x+w,y+h
         *        |........|
         *     x,y|........|x+w,y
         *
         *      ax,ay+ah|..........|ax+aw,ay+ah
         *              |..........|
         *         ax,ay|..........|ax+aw,ay
         *
         *                 x,y+h|........|x+w,y+h
         *                      |........|
         *                   x,y|........|x+w,y
         */
        boolean collisionX = x+w > ax && ax+aw > x;
        boolean collisionY = y+h > ay && ay+ah > y;

        return collisionX && collisionY;
    }
}
