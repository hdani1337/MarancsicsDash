package hu.hdani1337.marancsicsDash.MyBaseClasses.UI;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Left extends OneSpriteStaticActor {
    public Left() {
        super(Assets.manager.get(Assets.LEFT));
        setDebug(false);
    }
}
