package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyRectangle;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.hdani1337.marancsicsDash.Stage.OptionsStage;

import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;

public class Tank extends OneSpriteAnimatedActor {
    public int tankSpeed = 320;
    public static int pontszam = 0;
    Sound crash = Assets.manager.get(Assets.CRASH);

    public Tank() {
        super(Assets.manager.get(Assets.TANK));
        addCollisionShape("tankHitbox", new MyRectangle(200,75,76,110));
        switch (OptionsStage.difficulty){
            case 0: tankSpeed = 200;
            case 1: tankSpeed = 350;
            case 2: tankSpeed = 500;
        }
        setFps(24);
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(Marancsics.tankComing == true){
            setX(getX() + delta * 1080);
            setRotation(getRotation() - delta * 180);
            setY(getY() + delta * 85);
            if(getX() > Gdx.graphics.getWidth()){
                if(!muted) {
                    crash.play();
                }
                pontszam += 1;

                int random = (int) (Math.random() * 5 + 1);

                if (random == 2) setX(2100);
                else if (random == 3) setX(2400);
                else if (random == 4) setX(2700);
                else if (random == 5) setX(3000);
                else setX(1800);

                Marancsics.tankComing = false;

                if (OptionsStage.difficulty == 0) tankSpeed += 15;
                else if (OptionsStage.difficulty == 1) tankSpeed += 25;
                else if (OptionsStage.difficulty == 2) tankSpeed += 35;
                else tankSpeed += 30;
            }
        }
        else {
            setY(-40);
            setRotation(0);
            setX(getX() - delta * tankSpeed);
            }
        }
    }

