package com.toadder;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Toadder {

    private Texture texture;
    private Sprite frame_up, frame_down, frame_left, frame_right;
    private Sprite frame_jump_up, frame_jump_down, frame_jump_left, frame_jump_right;

    private Sprite current_frame;

    private float x_position = 5*32;
    private float y_position = 0;

    public Toadder() {
        texture = new Texture("toadder_sprites.png");
        frame_up = new Sprite(texture, 0,0, 32,32);
        frame_down = new Sprite(texture, 32,0, 32,32);
        frame_left = new Sprite(texture, 64,0, 32,32);
        frame_right = new Sprite(texture, 96,0, 32,32);
        current_frame = frame_up;
    }

    public void dx(float delta_x) {
        float new_x = this.x_position + delta_x;
        if (new_x > -1 && new_x < 416) {
            this.x_position = new_x;
        }
    }

    public void dy(float delta_y) {
        float new_y = this.y_position + delta_y;
        if (new_y > -1 && new_y < 448) {
            this.y_position = new_y;
        }
    }

    public void render(SpriteBatch sb) {
        if (current_frame.getX() - x_position > 0)
            current_frame = frame_right;
        else if (current_frame.getX() - x_position < 0)
            current_frame = frame_left;
        else if (current_frame.getY() - y_position > 0)
            current_frame = frame_down;
        else if (current_frame.getY() - y_position < 0)
            current_frame = frame_up;

        current_frame.setX(x_position);
        current_frame.setY(y_position);

        current_frame.draw(sb);
    }
}
