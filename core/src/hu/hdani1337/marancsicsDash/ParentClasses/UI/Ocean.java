package hu.hdani1337.marancsicsDash.ParentClasses.UI;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteStaticActor;

public class Ocean extends OneSpriteStaticActor {
    public Ocean() {
        super(Assets.manager.get(Assets.GAME_BG5));
        setDebug(false);
        setSize(400,225);
    }
}
