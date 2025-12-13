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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    Music music;

    private Toadder toad;
    private Toadder badtoad;
    private Sprite background;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("toadder_sprites.png");
        background = new Sprite(image, 384, 0, 416, 448);
        toad = new Toadder();

        badtoad = new Toadder();
        badtoad.dy(128);

        music = Gdx.audio.newMusic(Gdx.files.internal("src_resources_bg_music.ogg"));
        music.setLooping(true);
        music.setVolume(.5f);
        music.play();

        ToadderGestureListener finger_listener = new ToadderGestureListener(toad);
        Gdx.input.setInputProcessor(new GestureDetector(finger_listener));

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
        badtoad.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    private void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            toad.moveRight();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            toad.moveLeft();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            toad.moveUp();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            toad.moveDown();
        }
    }
}
