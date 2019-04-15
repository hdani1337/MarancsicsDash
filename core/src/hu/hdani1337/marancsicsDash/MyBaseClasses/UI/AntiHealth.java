package hu.hdani1337.marancsicsDash.MyBaseClasses.UI;

import hu.hdani1337.marancsicsDash.Actor.Marancsics;
import hu.hdani1337.marancsicsDash.Actor.MarancsicsBoss;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class AntiHealth extends OneSpriteStaticActor {
    private static float healthWT = 0;
    private static float healthYT = 0;
    private static float healthXT = 0;

    public AntiHealth(float healthW, float healthY, float healthX) {
        super(Assets.manager.get(Assets.RED));
        healthWT = healthW;
        healthYT = healthY;
        healthXT = healthX;
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setSize(healthWT * (100-MarancsicsBoss.marancsicsHealth)/100,60);
        setPosition(healthXT + healthWT - getWidth(),healthYT);
    }
}
