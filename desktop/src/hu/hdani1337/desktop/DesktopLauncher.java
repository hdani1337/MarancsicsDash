package hu.hdani1337.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.awt.DisplayMode;

import hu.hdani1337.marancsicsDash.Actor.Zsolti;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new marancsicsGame(), config);
		Gdx.graphics.setTitle("Marancsics Dash");
		Gdx.graphics.setVSync(true);
		config.width = Gdx.graphics.getWidth();
		config.height = Gdx.graphics.getHeight();
		config.fullscreen = true;
		Zsolti.multitasking = true;
		marancsicsGame.desktop = true;
	}
}
