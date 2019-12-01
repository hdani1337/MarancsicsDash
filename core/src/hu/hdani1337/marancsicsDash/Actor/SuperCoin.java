package hu.hdani1337.marancsicsDash.Actor;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteAnimatedActor;

import static hu.hdani1337.marancsicsDash.Stage.GameStage.ground;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.difficulty;

public class SuperCoin extends OneSpriteAnimatedActor {
    public SuperCoin() {
        super(Assets.manager.get(Assets.SUPERCOIN));
        setDebug(false);
        setFps(75);
        setSize(100,100);
        newPosition();
        addBaseCollisionRectangleShape();
    }

    public void newPosition(){
        int newY = (int)(Math.random() * 300 + ground+50);
        int newX = (int)(Math.random() * 60000 + 35000);
        setPosition(newX,newY);
    }

    @Override
    public synchronized void act(float delta) {
        super.act(delta);
        if (getX() < 0 - getWidth()) newPosition();
        setX(getX() - difficulty*8);
    }
}
