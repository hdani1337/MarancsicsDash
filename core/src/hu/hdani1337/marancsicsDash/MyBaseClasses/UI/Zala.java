package hu.hdani1337.marancsicsDash.MyBaseClasses.UI;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Zala extends OneSpriteStaticActor {
    public Zala() {
        super(Assets.manager.get(Assets.GAME_BG3));
        setDebug(false);
        setSize(400,225);
    }
}
