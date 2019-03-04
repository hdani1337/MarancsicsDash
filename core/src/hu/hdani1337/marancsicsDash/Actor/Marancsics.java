package hu.hdani1337.marancsicsDash.Actor;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Marancsics extends OneSpriteAnimatedActor {
    public Marancsics() {
        super(Assets.manager.get(Assets.MARANCSICS));
        setFps(12);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX() + delta * 120);
    }
}
