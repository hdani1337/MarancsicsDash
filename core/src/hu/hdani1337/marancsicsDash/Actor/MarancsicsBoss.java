package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyRectangle;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.hdani1337.marancsicsDash.Stage.OptionsStage;

import static hu.hdani1337.marancsicsDash.Stage.GameStage.ground;

public class MarancsicsBoss extends OneSpriteAnimatedActor {

    public static float marancsicsHealth = 99.9f;
    private int speed;
    public Viewport tempView;

    public MarancsicsBoss(Viewport viewport) {
        super(Assets.manager.get(Assets.MARANCSICS_BOSS));
        addCollisionShape("bossHitbox",new MyRectangle(325,90,18,10));
        addCollisionShape("bossHitbox2",new MyRectangle(200,120,18+125,10));
        addCollisionShape("bossHitbox3",new MyRectangle(80,30,100,85,10,10,0,25));
        setDebug(false);
        tempView = viewport;
        setFps(18);
        setY(ground - 15);
        setSize(getWidth()*1.5f,getHeight()*1.5f);
        speed = OptionsStage.difficulty * 3;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (marancsicsHealth < 0) marancsicsHealth = 0;
        else if (marancsicsHealth > 99.9) marancsicsHealth = 99.9f;
        setX(getX() - speed);
        if (getX() + getWidth() < 0)
        {
            setX(tempView.getWorldWidth());
            if(OptionsStage.difficulty == 1) speed = (int)(Math.random() * 6 + 8);
            else if(OptionsStage.difficulty == 3) speed = (int)(Math.random() * 6 + 12);
            else speed = (int)(Math.random() * 6 + 10);
        }
    }
}
