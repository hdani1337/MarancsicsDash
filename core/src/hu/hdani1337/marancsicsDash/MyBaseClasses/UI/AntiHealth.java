package hu.hdani1337.marancsicsDash.MyBaseClasses.UI;

import hu.hdani1337.marancsicsDash.Actor.MarancsicsBoss;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class AntiHealth extends OneSpriteStaticActor {
    public AntiHealth(float healthW, float healthY, float healthX) {
        super(Assets.manager.get(Assets.RED));
        setDebug(false);
        if (MarancsicsBoss.marancsicsHealth != 100) setSize(healthW*10 / MarancsicsBoss.marancsicsHealth,60);
        else setSize(0,60);
        setPosition(healthX + healthW - getWidth(),healthY);
    }
}
