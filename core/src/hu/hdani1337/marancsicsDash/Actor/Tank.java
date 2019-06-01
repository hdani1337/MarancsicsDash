package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.audio.Sound;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyRectangle;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

import static hu.hdani1337.marancsicsDash.Stage.GameStage.ground;
import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.difficulty;
import static hu.hdani1337.marancsicsDash.marancsicsGame.keparany;

public class Tank extends OneSpriteAnimatedActor {
    public float tankSpeed = 5;
    public static int pontszam = 0;
    Sound crash = Assets.manager.get(Assets.CRASH);

    public Tank() {
        super(Assets.manager.get(Assets.TANK));
        addCollisionShape("tankHitbox", new MyRectangle(200,75,76,110));
        setY(ground - 70);
        setFps(24);
        tankSpeed = 5;
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(Marancsics.tankComing == true){
            setX(getX() + delta * tankSpeed * 140);
            setRotation(getRotation() - delta * tankSpeed * 20);
            setY(getY() + delta * tankSpeed * 13);
            if(getX() > keparany()){
                if(!muted) {
                    crash.play();
                }
                pontszam += 1;

                int random = (int) (Math.random() * 5 + 3);

                setX(random * 700);
                setY(ground - 70);
                setRotation(0);

                Marancsics.tankComing = false;

                tankSpeed += 0.1f;
            }
        }

        else setX(getX() - difficulty*tankSpeed);
    }
}

