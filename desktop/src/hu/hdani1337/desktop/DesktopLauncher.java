package hu.hdani1337.desktop;

import com.badlogic.gdx.Files;
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
		config.fullscreen = true;
		config.width = Gdx.graphics.getWidth();
		config.height = Gdx.graphics.getHeight();
		config.title = "Marancsics Dash";
		config.addIcon("ic_launcher.png", Files.FileType.Local);
		Gdx.graphics.setVSync(true);
		Zsolti.multitasking = true;
		marancsicsGame.desktop = true;
	}
}
