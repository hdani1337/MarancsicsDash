package hu.hdani1337.marancsicsDash.Actor;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteAnimatedActor;

import static hu.hdani1337.marancsicsDash.Stage.GameStage.ground;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.difficulty;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.preferences;

public class Coin extends OneSpriteAnimatedActor {
    private boolean mozog;
    public static long coin = preferences.getLong("coin");

    public Coin(boolean act) {
        super(Assets.manager.get(Assets.COIN));
        if(coin % 1 != 0) coin = 0;
        setFps(60);
        setSize(80,80);
        newPosition();
        addBaseCollisionRectangleShape();
        setDebug(false);
        mozog = act;
    }

    public void newPosition(){
        int newY = (int)(Math.random() * 300 + ground+50);
        int newX = (int)(Math.random() * 7160 + 2560);
        setPosition(newX,newY);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(mozog) {
            if (getX() < 0 - getWidth()) newPosition();
            setX(getX() - difficulty*6);
        }
    }

    public void setAct(boolean b) {
        mozog = b;
    }
}
