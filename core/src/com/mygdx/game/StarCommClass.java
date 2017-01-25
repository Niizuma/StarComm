package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StarCommClass extends ApplicationAdapter {
	SpriteBatch batch; // объявляем область отрисовки
	Background background;
	Hero hero;
	final int ASTEROIDS_COUNT = 50; // кол-во астероидов
	Asteroids[] asteroids;
	final int BULLETS_COUNT = 200; // кол-во пуль
	public static Bullet[] bullets;
	Texture textureBullet;

	@Override
	public void create () {
		batch = new SpriteBatch(); // создаём область отрисовки
		background = new Background(); // создаём задний фон
		hero = new Hero();
		asteroids = new Asteroids[ASTEROIDS_COUNT];
		bullets = new Bullet[BULLETS_COUNT];
		textureBullet = new Texture("bullet20.png");
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i] = new Asteroids();
		}
		for (int i = 0; i < bullets.length; i++) {
			bullets[i] = new Bullet();
		}
	}

	@Override // метод для отрисовки
	public void render () { // блок для графики
		update();
		Gdx.gl.glClearColor(1, 1, 1 , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin(); // начинаем рисовать
		background.render(batch);
		hero.render(batch);
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i].render(batch);
		}
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i].isActive()) {
				batch.draw(textureBullet,bullets[i].getPosition().x, bullets[i].getPosition().y);
			}
		}
		batch.end(); // заканчиваем рисовать
	}

	public void update() { // блок для расчётов
		background.update();
		hero.update();
		for(int i = 0; i < asteroids.length; i++) {
			asteroids[i].update();
			if(asteroids[i].getRect().contains(hero.getPosition())) { // если корабль столкнулся с астероидом
				asteroids[i].reCreate();
				hero.destroy();
			}
		}
		for(int i = 0; i < bullets.length; i++) {
			if (bullets[i].isActive()) {
				bullets[i].update();
				for (int j = 0; j < asteroids.length; j++) {
					// Если прямоугольник астероида содержит координаты пули, то они столкнулись
					if(asteroids[j].getRect().contains(bullets[i].getPosition())) {
						asteroids[j].reCreate(); // пересоздаём астероид
						bullets[i].destroy(); // выключаем пулю
					}
				}
			}
		}
	}

	@Override // метод для очистки
	public void dispose () {
		batch.dispose();
	}
}
