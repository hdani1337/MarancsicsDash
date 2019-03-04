package hu.hdani1337.marancsicsDash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Game.MyGame;
import hu.hdani1337.marancsicsDash.Screen.Home;

public class marancsicsGame extends MyGame {
	@Override
	public void create () {
		Assets.prepare();
		Assets.load();
		System.out.println("Loading");
		while (!Assets.manager.update()){
			System.out.print(".");
		}
		setScreen(new Home(this));//miután betöltött, meghívom a kezdőképernyőt
	}
}
