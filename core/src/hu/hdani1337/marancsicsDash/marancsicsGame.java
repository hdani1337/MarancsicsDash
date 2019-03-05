package hu.hdani1337.marancsicsDash;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Game.MyGame;
import hu.hdani1337.marancsicsDash.Screen.HomeScreen;

public class marancsicsGame extends MyGame {

	public Label.LabelStyle getLabelStyle() {
		Label.LabelStyle style;
		style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
		style.font = Assets.manager.get(Assets.FONT);
		style.fontColor = Color.WHITE;
		return style;
	}

	public TextButton.TextButtonStyle getButtonStyle(){
		TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
		buttonStyle.font = Assets.manager.get(Assets.FONT);
		buttonStyle.fontColor = new Color(1, 1, 1, 1);
		buttonStyle.overFontColor = new Color(0, 0, 0, 1);
		buttonStyle.downFontColor = new Color(0, 0, 0, 1);

		return buttonStyle;
	}

	@Override
	public void create () {
		Assets.prepare();
		Assets.load();
		System.out.println("Loading");
		while (!Assets.manager.update()){
			System.out.print(".");
		}
		setScreen(new HomeScreen(this));//miután betöltött, meghívom a kezdőképernyőt
	}
}
