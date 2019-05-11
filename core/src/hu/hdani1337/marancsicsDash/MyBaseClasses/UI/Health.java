package hu.hdani1337.marancsicsDash.MyBaseClasses.UI;

import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Health extends OneSpriteStaticActor {
    public Health(Viewport viewport) {
        super(Assets.manager.get(Assets.GREEN));
        setDebug(false);
        setSize(viewport.getWorldWidth() * 0.80f,60);
        setPosition(40, viewport.getWorldHeight() - 125);
    }
}
