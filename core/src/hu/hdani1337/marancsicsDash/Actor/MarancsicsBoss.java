package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyRectangle;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class MarancsicsBoss extends OneSpriteAnimatedActor {

    public static float marancsicsHealth = 99.9f;
    public Viewport tempView;

    public MarancsicsBoss(Viewport viewport) {
        super(Assets.manager.get(Assets.MARANCSICS_BOSS));
        addCollisionShape("bossHitbox",new MyRectangle(325,90,18,10));
        addCollisionShape("bossHitbox2",new MyRectangle(200,120,18+125,10));
        addCollisionShape("bossHitbox3",new MyRectangle(80,30,100,85,10,10,0,25));
        setDebug(false);
        tempView = viewport;
        setFps(18);
        setSize(getWidth()*1.5f,getHeight()*1.5f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(marancsicsHealth < 0) marancsicsHealth = 0;
        else if(marancsicsHealth > 99.9) marancsicsHealth = 99.9f;
        setX(getX() - 8);
        if(getX() + getWidth() < 0) setX(tempView.getWorldWidth());
    }
}
