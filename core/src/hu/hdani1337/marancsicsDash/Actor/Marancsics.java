package hu.hdani1337.marancsicsDash.Actor;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Marancsics extends OneSpriteAnimatedActor {
    public static boolean tankComing = false;
    public static boolean intro = false;

    public Marancsics() {
        super(Assets.manager.get(Assets.MARANCSICS));
        setFps(8);
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(intro){
            setX(getX() + delta * 180);
            if(getX() >= 60){
                setX(60);
                intro = false;
            }
        }
    }
}
