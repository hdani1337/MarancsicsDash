package hu.hdani1337.marancsicsDash.Actor;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Lightsaber extends OneSpriteStaticActor {
    public Lightsaber() {
        super(Assets.manager.get(Assets.LIGHTSABER));
    }
}
