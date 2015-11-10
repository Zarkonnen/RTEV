package com.zarkonnen.rtev;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Ship {
	public ShipType type;
	public Vector2 dir = new Vector2(0, 1);
	public Vector2 pos = new Vector2(0, 0), vel = new Vector2(0, 0);
	
	public void tick(float s) {
		vel.clamp(0, type.spd);
		pos = pos.add(new Vector2(vel).scl(s));
	}
	
	public void render(OrthographicCamera cam) {
		ShapeRenderer sr = new ShapeRenderer();
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.translate(pos.x, pos.y, 0);
		sr.rotate(0, 0, 1, dir.angle());
		sr.setColor(type.wingR, type.wingG, type.wingB, 1);
		sr.triangle(-type.wingStart, 0, -type.wingEnd, type.wingWidth, -type.wingEnd, -type.wingWidth);
		sr.setColor(0.7f, 0.7f, 0.7f, 1);
		sr.rect(-type.bodyStart - type.bodyL, -type.bodyW / 2, type.bodyL, type.bodyW);
		sr.end();
	}

	public Ship(ShipType type) {
		this.type = type;
	}
}
