package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alex on 25.01.2017.
 */
public class Asteroids {
    private static Texture texture;
    private Vector2 position;
    private float speed;
    private Rectangle rect;

    public Rectangle getRect() {
        return rect;
    }

    public Asteroids() {
        if (texture == null) {
            texture = new Texture("asteroid60.tga");
        }
        position = new Vector2((float) Math.random() * 1280 + 1280, (float) Math.random() * 720);
        speed = 3.0f + (float) Math.random() * 5.0f;
        rect = new Rectangle(position.x, position.y, 60, 60);
    }

    public void reCreate() {
        position.x = (float) Math.random() * 1280 + 1280;
        position.y = (float) Math.random() * 720;
        speed = 3.0f + (float) Math.random() * 5.0f;
    }

    public void render(SpriteBatch batch) {
            batch.draw(texture, position.x, position.y);
    }

    public void update() { // блок для расчётов
        position.x -= speed;
        if (position.x < -60) {
            reCreate();
        }
        rect.x = position.x;
        rect.y = position.y;
    }
}
