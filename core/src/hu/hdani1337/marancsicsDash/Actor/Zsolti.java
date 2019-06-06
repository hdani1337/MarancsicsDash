package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Timer;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyRectangle;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteAnimatedActor;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.JumpIcon;

import static hu.hdani1337.marancsicsDash.Stage.GameStage.ground;

public class Zsolti extends OneSpriteAnimatedActor {
    public static boolean jump = false;
    public static boolean forcejump = false;
    public static boolean fall = false;
    public static boolean intro = false;
    boolean jumped;
    public static float superTime = 0;
    public static boolean nowSuper = false;
    public static boolean doThings = true;
    public static boolean multitasking = false;//ez azért kell, mert gépen laggol a threading, én meg balfasz vagyok még hozzá

    TextureAtlas normal = Assets.manager.get(Assets.ZSOLTI);
    TextureAtlas superZS = Assets.manager.get(Assets.SUPERZSOLTI);

    public Zsolti() {
        super(Assets.manager.get(Assets.ZSOLTI));
        addCollisionShape("zsoltiHitbox",new MyRectangle(110,80,10,13));
        addCollisionShape("zsoltiHitbox2",new MyRectangle(90,115,20,93));
        setFps(12);
        setDebug(false);
        jumped = false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(superTime > 0)
        {
            if(superTime != 1337) superTime -= delta;
            textureAtlas = superZS;
            if(superTime <= 0) superTime = 0;
        }
        else
        {
            nowSuper = false;
            textureAtlas = normal;
        }

        if (doThings) {
            if (ground >= 270) reJumpThread();

            if (fall) fallThread(delta);
            else if (jump) jumpThread(delta);

            if (intro) introThread(delta);
        }
    }

    private void fall(float delta)
    {
        if (ground >= 270) {
            if (getY() <= 340 && !jumped) {
                jump = true;
                forcejump = false;
                jumped = true;
                fall = false;
            }
        }

        if (forcejump) {
            fall = false;
            jump = true;
            forcejump = false;
        } else {
            setY(getY() - delta * 450);
            setRotation(getRotation() - delta * 20);
            if (getY() <= ground) {
                setY(ground);
                JumpIcon.doubleJumped = false;
                setRotation(0);
                this.setFps(12);
                jump = false;
                fall = false;
            }
        }
    }
    private void jump(float delta)
    {
        if (getY() < JumpIcon.jumpHeight) {
            setY(getY() + delta * 500);
            if(!nowSuper) this.setFps(0);
            setRotation(getRotation() + delta * 20);
        }
        if (getY() >= JumpIcon.jumpHeight) {
            setY(getY() + delta * 90);
            setRotation(getRotation() - delta * 15);
            if (getY() >= (JumpIcon.jumpHeight + 10)) fall = true;
        }
    }

    private void reJump()
    {
        JumpIcon.jumpHeight = 420;

        if (getY() <= ground && jumped) {
            jumped = false;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    jump = true;
                }
            }, 2);
        }
    }

    private void intro(float delta)
    {
        setX(getX() + delta * 240);
        if (getX() >= 250) {
            setX(250);
            intro = false;
        }
    }

    private void fallThread(final float delta) {
        if(!multitasking) {
            new Thread(new Runnable() {
                public void run() {
                   fall(delta);
                }
            }).start();
        }

        else fall(delta);
    }

    private void jumpThread(final float delta) {
        if(!multitasking) {
            new Thread(new Runnable() {
                public void run() {
                    jump(delta);
                }
            }).start();
        }
        else jump(delta);
    }

    private void reJumpThread() {
        if(!multitasking) {
            new Thread(new Runnable() {
                public void run() {
                   reJump();
                }
            }).start();
        }
        else reJump();
    }

    private void introThread(final float delta) {
        if(!multitasking) {
            new Thread(new Runnable() {
                public void run() {
                   intro(delta);
                }
            }).start();
        }
        else intro(delta);
    }
}
