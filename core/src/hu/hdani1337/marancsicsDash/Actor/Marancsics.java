package hu.hdani1337.marancsicsDash.Actor;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Marancsics extends OneSpriteAnimatedActor {
    public static boolean tankComing = false;

    public Marancsics() {
        super(Assets.manager.get(Assets.MARANCSICS));
        setFps(8);
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(tankComing)
        {
            if(getY() < 220) {
                setY(getY() + delta * 240);
                setRotation(getRotation() + delta * 30);
            }

            if(getY() >= 220){
                setY(getY() + delta * 30);
                setRotation(getRotation() - delta * 30);
                if(getY() >= 225){
                    tankComing = false;
                }
            }
        }

        else
        {
            if(getY()!=30) {
                setY(getY() - delta * 280);
                setRotation(getRotation() - delta * 30);

                if (getY() <= 30) {
                    setY(30);
                    setRotation(0);
                }
            }
        }
    }
}
