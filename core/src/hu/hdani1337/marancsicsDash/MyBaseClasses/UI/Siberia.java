package hu.hdani1337.marancsicsDash.MyBaseClasses.UI;

import com.badlogic.gdx.graphics.Texture;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Siberia extends OneSpriteStaticActor {
    public Siberia() {
        super(Assets.manager.get(Assets.GAME_BG2));
        setDebug(false);
        setSize(400,225);
    }
}
