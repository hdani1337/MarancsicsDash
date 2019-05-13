package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.Gdx;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyRectangle;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.JumpIcon;

public class Zsolti extends OneSpriteAnimatedActor {
    public static boolean jump = false;
    public static boolean forcejump = false;
    public static boolean fall = false;
    public static boolean intro = false;

    public Zsolti() {
        super(Assets.manager.get(Assets.ZSOLTI));
        addCollisionShape("zsoltiHitbox",new MyRectangle(110,205,10,3));
        setFps(12);
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(fall){
            if(forcejump)
            {
                fall = false;
                jump = true;
                forcejump = false;
            }
            else {
                setY(getY() - delta * 360);
                setRotation(getRotation() - delta * 30);
                if (getY() <= 30) {
                    setY(30);
                    setRotation(0);
                    jump = false;
                    fall = false;
                }
            }
        }

        else {
            if (jump) {
                if (getY() < JumpIcon.jumpHeight) {
                    setY(getY() + delta * 280);
                    setRotation(getRotation() + delta * 30);
                }
                if (getY() >= JumpIcon.jumpHeight) {
                    setY(getY() + delta * 90);
                    setRotation(getRotation() - delta * 25);
                    if (getY() >= (JumpIcon.jumpHeight + 10)) fall = true;
                }
            }
        }

        if(intro){
            setX(getX() + delta * 240);
            if (getX() >= 250) {
                setX(250);
                intro = false;
            }
        }
    }
}
