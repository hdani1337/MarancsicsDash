package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Tank extends OneSpriteAnimatedActor {
    public int tankSpeed = 320;

    public Tank() {
        super(Assets.manager.get(Assets.TANK));
        setFps(24);
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX() - delta * tankSpeed);
        if(getX() + getWidth() < 0){
            int random = (int)(Math.random() * 5 + 1);

            switch (random){
                case 1: setX(1800);
                case 2: setX(2100);
                case 3: setX(2400);
                case 4: setX(2700);
                case 5: setX(3000);
                default: setX(1500);
            }

            tankSpeed += 25;
        }
    }
}
