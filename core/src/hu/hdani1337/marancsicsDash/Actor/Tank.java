package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.audio.Sound;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.hdani1337.marancsicsDash.Stage.OptionsStage;

import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;

public class Tank extends OneSpriteAnimatedActor {
    public int tankSpeed = 320;
    public static int pontszam = 0;
    Sound crash = Assets.manager.get(Assets.CRASH);

    public Tank() {
        super(Assets.manager.get(Assets.TANK));
        switch (OptionsStage.difficulty){
            case 0: tankSpeed = 250;
            case 1: tankSpeed = 320;
            case 2: tankSpeed = 400;
        }
        setFps(24);
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        addBaseCollisionRectangleShape();
        if(Marancsics.tankComing == true){
            setX(getX() + delta * 720);
            setRotation(getRotation() - delta * 150);
            setY(getY() + delta * 120);
            if(getX() > 1280){
                if(!muted) {
                    crash.play();
                }
                pontszam += 1;

                int random = (int) (Math.random() * 5 + 1);

                switch (random) {
                    case 1:
                        setX(1800);
                    case 2:
                        setX(2100);
                    case 3:
                        setX(2400);
                    case 4:
                        setX(2700);
                    case 5:
                        setX(3000);
                    default:
                        setX(1500);
                }

                Marancsics.tankComing = false;


                switch (OptionsStage.difficulty){
                    case 0: tankSpeed += 15;
                    case 1: tankSpeed += 25;
                    case 2: tankSpeed += 35;
                }
            }
        }
        else {
            setY(-40);
            setRotation(0);
            setX(getX() - delta * tankSpeed);
            }
        }
    }

