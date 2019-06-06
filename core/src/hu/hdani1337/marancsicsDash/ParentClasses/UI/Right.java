package hu.hdani1337.marancsicsDash.ParentClasses.UI;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteStaticActor;

public class Right extends OneSpriteStaticActor {
    public Right() {
        super(Assets.manager.get(Assets.RIGHT));
        setDebug(false);
    }
}
