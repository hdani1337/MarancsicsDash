package hu.hdani1337.marancsicsDash.Actor;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.hdani1337.marancsicsDash.Stage.OptionsStage;

import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.preferences;

public class Coin extends OneSpriteAnimatedActor {
    public static boolean felvette = false;
    private short minimum = 150;
    private short maximum = 250;
    private boolean mozog;
    public static long coin = preferences.getLong("coin");

    public Coin(boolean act) {
        super(Assets.manager.get(Assets.COIN));
        if(coin % 1 != 0) coin = 0;
        setFps(60);
        setSize(80,80);
        addBaseCollisionRectangleShape();
        setDebug(false);
        mozog = act;
    }

    public void newPosition(){
        int newY = (int)(Math.random() * maximum + minimum);
        int newX = (int)(Math.random() * 7160 + 2560);
        setPosition(newX,newY);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(mozog) {
            if (getX() < 0 - getWidth()) newPosition();

            if (felvette) {
                coin += 1;
                felvette = false;
                newPosition();
            }

            //System.out.println(coin);

            if (OptionsStage.difficulty == 1) setX(getX() - 5);
            if (OptionsStage.difficulty == 2) setX(getX() - 10);
            if (OptionsStage.difficulty == 3) setX(getX() - 15);
        }
    }
}
