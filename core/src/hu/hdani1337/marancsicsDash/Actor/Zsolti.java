package hu.hdani1337.marancsicsDash.Actor;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Zsolti extends OneSpriteAnimatedActor {
    public static boolean jump = false;
    private boolean fall = false;
    public Zsolti() {
        super(Assets.manager.get(Assets.ZSOLTI));
        setFps(12);
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        addBaseCollisionRectangleShape();

        if(fall){
            setY(getY() - delta * 280);
            setRotation(getRotation() - delta * 30);
            if (getY() <= 30) {
                setY(30);
                setRotation(0);
                jump = false;
                fall = false;
            }
        }

        else {
            if (jump) {
                if (getY() < 240) {
                    setY(getY() + delta * 240);
                    setRotation(getRotation() + delta * 30);
                }
                if (getY() >= 240) {
                    setY(getY() + delta * 15);
                    setRotation(getRotation() - delta * 40);
                    if (getY() >= 250) fall = true;
                }
            }
        }
    }
}
