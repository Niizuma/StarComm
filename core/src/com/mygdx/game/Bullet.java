package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alex on 25.01.2017.
 */
public class Bullet {
    private Vector2 position; // position.x position.y
    private float speed; // скорость полёта пули
    private boolean active;

    public Vector2 getPosition() { // возвращает позицию
        return position;
    }

    public boolean isActive() { // активана ли
        return active;
    }

    public Bullet() {
        position = new Vector2(0, 0);
        speed = 10.0f;
        active = false;
    }

    public void destroy() { // разрушает пулю
        active = false;
    }

    public void setup(float x, float y) { // выпускает пулю
        position.x = x;
        position.y = y;
        active = true;
    }

    public void update() { // блок для расчётов
        position.x += speed;
        if (position.x > 1280) {
            destroy();
        }
    }
}
