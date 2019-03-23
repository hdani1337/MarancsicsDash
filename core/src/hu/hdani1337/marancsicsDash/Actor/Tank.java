package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.Gdx;
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
        addBaseCollisionRectangleShape();
        switch (OptionsStage.difficulty){
            case 0: tankSpeed = 200;
            case 1: tankSpeed = 350;
            case 2: tankSpeed = 500;
        }
        setFps(24);
        setDebug(false);
        if(Gdx.graphics.getWidth() >= 1920) setSize(getWidth()*1.5f,getHeight()*1.5f);
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
                        setX(1800);
                }

                Marancsics.tankComing = false;


                switch (OptionsStage.difficulty){
                    case 0: tankSpeed += 20;
                    case 1: tankSpeed += 30;
                    case 2: tankSpeed += 40;
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

