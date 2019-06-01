package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Timer;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyRectangle;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.JumpIcon;

import static hu.hdani1337.marancsicsDash.Stage.GameStage.ground;

public class Zsolti extends OneSpriteAnimatedActor {
    public static boolean jump = false;
    public static boolean forcejump = false;
    public static boolean fall = false;
    public static boolean intro = false;
    boolean jumped;
    public static boolean doThings = true;

    public Zsolti(TextureAtlas texture) {
        super(texture);
        addCollisionShape("zsoltiHitbox",new MyRectangle(110,205,10,3));
        setFps(12);
        setDebug(false);
        jumped = false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(doThings) {
            if (ground >= 270) {
                JumpIcon.jumpHeight = 420;

                if (getY() <= ground && jumped) {
                    jumped = false;
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            jump = true;
                        }
                    }, 3);
                }
            }

            if (fall) {
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
                    setY(getY() - delta * 360);
                    setRotation(getRotation() - delta * 30);
                    if (getY() <= ground) {
                        setY(ground);
                        JumpIcon.doubleJumped = false;
                        setRotation(0);
                        jump = false;
                        fall = false;
                    }
                }
            } else {
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

            if (intro) {
                setX(getX() + delta * 240);
                if (getX() >= 250) {
                    setX(250);
                    intro = false;
                }
            }
        }
    }
}
