package com.toadder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Toadder {

    private Texture texture;
    private Sprite frame_up, frame_down, frame_left, frame_right;
    private Sprite frame_jump_up, frame_jump_down, frame_jump_left, frame_jump_right;

    Music frog_jump;

    private Sprite current_frame, next_frame;

    private float x_position = 5*32;
    private float y_position = 0;

    private float animation_speed_x = 0;
    private float animation_speed_y = 0;
    private float animation_final_x = x_position;
    private float animation_final_y = y_position;

    private float animation_timestamp = 0;
    private boolean animating = false;

    private float animation_speed = 150;
    private float animation_delay = 0.15f;

    public Toadder() {
        frog_jump = Gdx.audio.newMusic(Gdx.files.internal("jump.wav"));
        texture = new Texture("toadder_sprites.png");
        frame_up = new Sprite(texture, 0,0, 32,32);
        frame_down = new Sprite(texture, 32,0, 32,32);
        frame_left = new Sprite(texture, 64,0, 32,32);
        frame_right = new Sprite(texture, 96,0, 32,32);

        frame_jump_up =  new Sprite(texture, 160,0, 32,32);
        frame_jump_down =  new Sprite(texture, 192,0, 32,32);
        frame_jump_left = new Sprite(texture, 224,0, 32,32);
        frame_jump_right = new Sprite(texture, 256,0, 32,32);;

        current_frame = frame_up;
        next_frame = frame_up;
        current_frame.setX(x_position);
        current_frame.setY(y_position);
        animation_speed = 32/animation_delay;
    }

    public void dx(float delta_x) {
        if (animating)
            return;

        float new_x = this.x_position + delta_x;
        if (new_x > -1 && new_x < 416) {
            this.animation_final_x = new_x;
            frog_jump.play();

            if (delta_x > 0) {
                current_frame = frame_jump_left;
                next_frame = frame_left;
                animation_speed_x = animation_speed;
                animation_speed_y = 0;
            }
            if (delta_x < 0) {
                current_frame = frame_jump_right;
                next_frame = frame_right;
                animation_speed_x = -animation_speed;
                animation_speed_y = 0;
            }

            animation_timestamp = Gdx.graphics.getDeltaTime();
            animating = true;
        }
    }

    public void dy(float delta_y) {
        if (animating)
            return;

        float new_y = this.y_position + delta_y;
        if (new_y > -1 && new_y < 448) {
            this.animation_final_y = new_y;
            frog_jump.play();

            if (delta_y > 0) {
                current_frame = frame_jump_up;
                next_frame = frame_up;
                animation_speed_y = animation_speed;
                animation_speed_x = 0;
            }
            if (delta_y < 0) {
                current_frame = frame_jump_down;
                next_frame = frame_down;
                animation_speed_y = -animation_speed;
                animation_speed_x = 0;
            }

            animating = true;
            animation_timestamp = Gdx.graphics.getDeltaTime();
        }

    }

    public void render(SpriteBatch sb) {
        if (animating) {
            animation_timestamp += Gdx.graphics.getDeltaTime();
            if (animation_timestamp > animation_delay) {
                current_frame = next_frame;
                animating = false;
                y_position = animation_final_y;
                x_position = animation_final_x;
            }
            else {
                x_position += animation_speed_x * Gdx.graphics.getDeltaTime();
                y_position += animation_speed_y * Gdx.graphics.getDeltaTime();
            }
        }

        //System.out.println(animation_timestamp);
        current_frame.setX(x_position);
        current_frame.setY(y_position);
        current_frame.draw(sb);
    }
}
