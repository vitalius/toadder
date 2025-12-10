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
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    Music music;

    private Toadder toad;
    private Sprite background;
    private OrthographicCamera camera;

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

        camera = new OrthographicCamera();
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
        float step = 32;
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
