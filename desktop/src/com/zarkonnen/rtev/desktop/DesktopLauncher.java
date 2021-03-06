package com.zarkonnen.rtev.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zarkonnen.rtev.RTEV;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1366;
		config.height = 768;
		config.fullscreen = true;
		config.title = "RTEV";
		config.foregroundFPS = 120;
		new LwjglApplication(new RTEV(), config);
	}
}
