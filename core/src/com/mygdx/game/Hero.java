package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by Alex on 25.01.2017.
 */
public class Hero {
    private Texture texture;
    private Vector2 position;
    private float speed;
    private int fireCounter; // счётчик пуль
    private int fireRate; // частота стрельбы
    private Rectangle rect;

    public Rectangle getRect() {
        return rect;
    }


    public Vector2 getPosition() {
        return position;
    }

    public Hero () {
        texture = new Texture("ship80x60.tga");
        speed = 10.0f;
        position = new Vector2(100, 100);
        rect = new Rectangle(position.x, position.y, 80, 60);
        fireRate = 5;
        fireCounter = 0;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void update () {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) { // нажата ли кнопка ВВЕРХ
            position.y += speed; // двигаем корабль вверх
            if(position.y > 730) {
                position.y = -40;
            }
            rect.y = position.y;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= speed;
            if(position.y < - 40) { // если корабль улетел вниз
                position.y = 730; // перекидываем его наверх
            }
            rect.y = position.y;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= speed;
            if(position.x < 0) { // блокируем вылет за границы по оси Ох
                position.x = 0;
            }
            rect.x = position.x;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += speed;
            if(position.x > 1210) {
                position.x = 1210;
            }
            rect.x = position.x;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fireCounter++;
            if (fireCounter > fireRate) {
                fireCounter = 0;
                fire();
            }
        }

    }

    public void destroy() {
        position.x = 100;
        position.y = 100;
    }

    public void fire() {
        for (int i = 0; i < StarCommClass.bullets.length; i++) {
            if(!StarCommClass.bullets[i].isActive()) {
                StarCommClass.bullets[i].setup(position.x, position.y);
                break;
            }
        }
    }
}
