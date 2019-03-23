package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.Gdx;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Zsolti extends OneSpriteAnimatedActor {
    public static boolean jump = false;
    public static boolean fall = false;
    public static boolean intro = false;

    public Zsolti() {
        super(Assets.manager.get(Assets.ZSOLTI));
        addBaseCollisionRectangleShape();
        setFps(12);
        setDebug(false);
        if(Gdx.graphics.getWidth() >= 1920) setSize(getWidth()*1.5f,getHeight()*1.5f);
        System.out.println(getWidth());
        System.out.println(getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(fall){
            setY(getY() - delta * 360);
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
                if (getY() < 275) {
                    setY(getY() + delta * 280);
                    setRotation(getRotation() + delta * 30);
                }
                if (getY() >= 275) {
                    setY(getY() + delta * 90);
                    setRotation(getRotation() - delta * 25);
                    if (getY() >= 290) fall = true;
                }
            }
        }

        if(intro){
            setX(getX() + delta * 240);
            if(Gdx.graphics.getWidth() >= 1920){
                if(getX() >= 350){
                    setX(350);
                    intro = false;
                }
            }
            else {
                if (getX() >= 250) {
                    setX(250);
                    intro = false;
                }
            }
        }
    }
}
