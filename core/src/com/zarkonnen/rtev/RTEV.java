package com.zarkonnen.rtev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import java.util.Random;

public class RTEV extends ApplicationAdapter {
	ArrayList<ShipType> shipTypes;
	Ship ship;
	OrthographicCamera cam;
	ArrayList<Vector3> stars = new ArrayList<Vector3>();
	Random r = new Random();
	long lastTick = 0;
	
	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(800, 800 * (h / w));
		cam.position.set(0, 0, 0);
		cam.update();
		shipTypes = ShipType.load();
		ship = new Ship(shipTypes.get(0));
		for (int i = 0; i < 500; i++) {
			stars.add(new Vector3(r.nextFloat() * 800 - 400, r.nextFloat() * 600 - 300, r.nextFloat() * 0.8f + 0.2f));
		}
		lastTick = System.currentTimeMillis();
	}

	@Override
	public void render () {
		cam.position.set(ship.pos, 0);
		cam.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		ShapeRenderer sr = new ShapeRenderer();
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeRenderer.ShapeType.Point);
		for (Vector3 s : stars) {
			if (s.x - cam.position.x < -400) {
				s.x += 800;
			}
			if (s.x - cam.position.x > 400) {
				s.x -= 800;
			}
			if (s.y - cam.position.y < -300) {
				s.y += 600;
			}
			if (s.y - cam.position.y > 300) {
				s.y -= 600;
			}
			sr.setColor(s.z, s.z, s.z, 1);
			sr.point(s.x, s.y, 0);
		}
		sr.end();
		
		ship.render(cam);
		
		// Input
		/*long t = System.currentTimeMillis();
		float s = Math.min(1, (t - lastTick) * 0.001f);
		lastTick = t;*/
		float s = Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			ship.dir.rotate(ship.type.rot * s);
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			ship.dir.rotate(-ship.type.rot * s);
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			ship.vel.add(new Vector2(ship.dir).scl(ship.type.acc * s));
		}
		
		// Update
		ship.tick(s);
	}
}
