package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyRectangle;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.hdani1337.marancsicsDash.Stage.OptionsStage;

import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.difficulty;
import static hu.hdani1337.marancsicsDash.marancsicsGame.keparany;

public class Tank extends OneSpriteAnimatedActor {
    public float tankSpeed = 6.5f;
    public static int pontszam = 0;
    Sound crash = Assets.manager.get(Assets.CRASH);

    public Tank() {
        super(Assets.manager.get(Assets.TANK));
        addCollisionShape("tankHitbox", new MyRectangle(200,75,76,110));
        switch (difficulty){
            case 0: tankSpeed = 6;
            case 1: tankSpeed = 6.5f;
            case 2: tankSpeed = 7;
        }
        setFps(24);
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(Marancsics.tankComing == true){
            setX(getX() + delta * tankSpeed * 200);
            setRotation(getRotation() - delta * tankSpeed * 20);
            setY(getY() + delta * tankSpeed * 12);
            if(getX() > keparany()){
                if(!muted) {
                    crash.play();
                }
                pontszam += 1;

                int random = (int) (Math.random() * 5 + 3);

                setX(random * 700);

                Marancsics.tankComing = false;

                if (difficulty == 0) tankSpeed += 0.1f;
                else if (difficulty == 1) tankSpeed += 0.2f;
                else if (difficulty == 2) tankSpeed += 0.35f;
                else tankSpeed += 0.25f;
            }
        }
        else {
            setY(-40);
            setRotation(0);
            setX(getX() - difficulty*tankSpeed);
            }
        }
    }

