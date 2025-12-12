package com.toadder;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class ToadderGestureListener implements GestureDetector.GestureListener {
    private final Toadder toad;

    public ToadderGestureListener(Toadder toad) {
        super();
        this.toad = toad;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        //Gdx.app.error("Toadder", "Tap!! " + x + ", " + y + ", " + count + ", " + button);
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        //Gdx.app.error("Toadder", "swipe!! " + velocityX + ", " + velocityY);

        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > 0) {
                toad.moveRight();
            } else {
                toad.moveLeft();
            }
        } else {
            if (velocityY > 0) {
                toad.moveDown();
            } else {
                toad.moveUp();
            }
        }
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
