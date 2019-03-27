package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.Gdx;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Marancsics extends OneSpriteAnimatedActor {
    public static boolean tankComing = false;
    public static boolean intro = false;

    public Marancsics() {
        super(Assets.manager.get(Assets.MARANCSICS));
        setFps(8);
        setDebug(false);
        //if(Gdx.graphics.getWidth() >= 1920) setSize(getWidth()*1.5f,getHeight()*1.5f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(intro){
            setX(getX() + delta * 280);
            if(Gdx.graphics.getWidth() >= 1920) {
                if (getX() >= 90) {
                    setX(90);
                    intro = false;
                }
            }
            else {
                if (getX() >= 60) {
                    setX(60);
                    intro = false;
                }
            }
        }
    }
}
