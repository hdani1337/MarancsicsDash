package hu.hdani1337.marancsicsDash.Actor;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyCircle;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyRectangle;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteAnimatedActor;

public class Marancsics extends OneSpriteAnimatedActor {
    public static boolean tankComing = false;
    public static boolean intro = false;

    public Marancsics() {
        super(Assets.manager.get(Assets.MARANCSICS));
        setFps(8);
        setDebug(false);
        addCollisionShape("marancsicsHitbox", new MyRectangle(80,70,0,0));
        addCollisionShape("marancsicsHitbox2",new MyCircle(55,9,105));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(intro){
            setX(getX() + delta * 280);

            if (getX() >= 60) {
                setX(60);
                intro = false;
            }
        }
    }
}
