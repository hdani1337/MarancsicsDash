package hu.hdani1337.marancsicsDash.Actor;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;

import static hu.hdani1337.marancsicsDash.Actor.Zsolti.nowSuper;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.difficulty;

public class Mushroom extends OneSpriteStaticActor {

    public Mushroom() {
        super(Assets.manager.get(Assets.GOMBA));
        setDebug(false);
        addBaseCollisionCircleShape();
        setSize(80,80);
    }

    void newPosition()
    {
        setPosition((int)(Math.random() * 8240 + 3840),(int)(Math.random() * 250 + 150));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(nowSuper || getX() + getWidth() < 0) newPosition();
        else setX(getX() - difficulty * 6);
    }
}
