package com.psillicoder.bg.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.psillicoder.bg.BreakoutGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Breakout Game v0.01";
		config.height = 480;
		config.width = 640;
		new LwjglApplication(new BreakoutGame(), config);
	}
}
