package hu.hdani1337.marancsicsDash.ParentClasses.UI;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteStaticActor;

public class InstantBoss extends OneSpriteStaticActor {
    public InstantBoss() {
        super(Assets.manager.get(Assets.INSTANTBOSS));
        setDebug(false);
    }
}
