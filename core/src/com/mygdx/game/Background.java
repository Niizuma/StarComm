package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by Alex on 23.01.2017.
 */
public class Background {

    class Star {
        private Vector2 position; // position.x position.y
        private float speed; // скорость полёта звезды

        public Star() { // рандомно создаём звёзды
            position = new Vector2((float) Math.random() * 1280, (float) Math.random() * 720);
            speed = 1.0f + (float) Math.random() * 7.0f;
        }

        public void update() { // блок для расчётов
            position.x -= speed;
            if (position.x < -20) {
                position.x = 1280;
                position.y = (float) Math.random() * 720; // задаём случайную координату
                speed = 1.0f + (float) Math.random() * 7.0f; // задаём случайную скорость
            }
        }
    }

    private Texture texture;
    private Texture textureStar;
    private Star[] stars; // массив звёзд
    private final int STARS_COUNT = 300; // константа количества звёзд

    public Background() {
        texture = new Texture("bg.png"); // создаём картинку заднего фона
        textureStar = new Texture("star12.tga"); // добавляем звёзды
        stars = new Star[STARS_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0); // рисуем созданную картинку в заданной точке
        for (int i = 0; i < stars.length; i++) { // рисуем звёзды
            batch.draw(textureStar, stars[i].position.x, stars[i].position.y);
        }
    }

    public void update() {
        for (int i = 0; i < stars.length; i++) { // рисуем звёзды
           stars[i].update();
        }
    }
}
