package hu.hdani1337.marancsicsDash.Actor;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class MarancsicsBoss extends OneSpriteAnimatedActor {

    public static float marancsicsHealth = 99.9f;

    public MarancsicsBoss() {
        super(Assets.manager.get(Assets.MARANCSICS_BOSS));
        setFps(18);
        setSize(getWidth()*1.5f,getHeight()*1.5f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(marancsicsHealth < 0) marancsicsHealth = 0;
        else if(marancsicsHealth > 99.9) marancsicsHealth = 99.9f;
    }
}
