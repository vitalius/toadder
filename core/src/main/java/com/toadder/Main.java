package com.toadder;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    Music music;

    private Toadder toad;
    private Sprite background;

    private final float step = 32;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("toadder_sprites.png");
        background = new Sprite(image, 384, 0, 416, 448);
        toad = new Toadder();

        music = Gdx.audio.newMusic(Gdx.files.internal("src_resources_bg_music.ogg"));
        music.setLooping(true);
        music.setVolume(.5f);
        music.play();

        Gdx.input.setInputProcessor(new GestureDetector(new GestureDetector.GestureListener() {
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
                        toad.dx(step);
                    } else {
                        toad.dx(-step);
                    }
                } else {
                    if (velocityY > 0) {
                        toad.dy(-step);
                    } else {
                        toad.dy(step);
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
        }));

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 416, 448);

        Graphics graphics = Gdx.graphics;
        graphics.setWindowedMode(416, 448);
        Gdx.graphics.setTitle("Toadder");
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render() {
        input();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(background, 0,0);
        toad.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    private void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            toad.dx(step);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            toad.dx(-step);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            toad.dy(step);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            toad.dy(-step);
        }
    }
}
