package hu.hdani1337.marancsicsDash.Actor;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class MarancsicsBoss extends OneSpriteAnimatedActor {
    public MarancsicsBoss() {
        super(Assets.manager.get(Assets.MARANCSICS_BOSS));
        setFps(18);
        setSize(getWidth()*1.5f,getHeight()*1.5f);
    }
}
