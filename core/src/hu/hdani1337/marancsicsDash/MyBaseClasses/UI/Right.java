package hu.hdani1337.marancsicsDash.MyBaseClasses.UI;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Right extends OneSpriteStaticActor {
    public Right() {
        super(Assets.manager.get(Assets.RIGHT));
        setDebug(false);
    }
}
