package com.zarkonnen.rtev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;

public class ShipType {
	public String name;
	public float rot,acc,spd;
	public int bodyStart, bodyW, bodyL;
	public int wingStart, wingEnd, wingWidth;
	public float wingR, wingG, wingB;
	
	public static ArrayList<ShipType> load() {
		Json json = new Json();
		return json.fromJson(ArrayList.class, ShipType.class, Gdx.files.internal("ShipType.json"));
	}
}
