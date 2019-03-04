package hu.hdani1337.marancsicsDash.Actor;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Zsolti extends OneSpriteAnimatedActor {
    public Zsolti() {
        super(Assets.manager.get(Assets.ZSOLTI));
        setFps(18);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX() + delta * 120);
    }
}
