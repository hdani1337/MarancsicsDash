package hu.hdani1337.marancsicsDash.ParentClasses.UI;

import com.badlogic.gdx.graphics.Texture;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteStaticActor;

public class Desert extends OneSpriteStaticActor {
    public Desert() {
        super(Assets.manager.get(Assets.GAME_BG4));
        setDebug(false);
        setSize(400,225);
    }
}
