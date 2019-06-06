package hu.hdani1337.marancsicsDash.ParentClasses.UI;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteStaticActor;

public class Left extends OneSpriteStaticActor {
    public Left() {
        super(Assets.manager.get(Assets.LEFT));
        setDebug(false);
    }
}
