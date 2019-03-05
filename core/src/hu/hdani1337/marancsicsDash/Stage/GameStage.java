package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.Marancsics;
import hu.hdani1337.marancsicsDash.Actor.Tank;
import hu.hdani1337.marancsicsDash.Actor.Zsolti;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;
import sun.nio.cs.ext.MacArabic;

public class GameStage extends MyStage {
    Background bg1;
    Background bg2;
    Zsolti zsolti;
    Marancsics marancsics;
    Tank tank;

    public GameStage(Viewport viewport, Batch batch, marancsicsGame game) {
        super(viewport, batch, game);
        bg1 = new Background(Assets.manager.get(Assets.GAME_BG)){
            @Override
            public void act(float delta) {
                super.act(delta);
                bg1.setX(bg1.getX()-5);
                if(bg1.getX() < -bg1.getWidth()){
                    bg1.setX(bg2.getX()+bg2.getWidth()-5);
                }

                if(tank.getX() - 30 <= marancsics.getX() + marancsics.getWidth()){
                    marancsics.tankComing = true;
                    System.out.println("overlaps");
                }
            }
        };

        bg2 = new Background(Assets.manager.get(Assets.GAME_BG)){
            @Override
            public void act(float delta) {
                super.act(delta);
                bg2.setX(bg2.getX()-5);
                if(bg2.getX() < -bg2.getWidth()){
                    bg2.setX(bg1.getX()+bg1.getWidth()-5);
                }
            }
        };

        bg2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Zsolti.jump = true;
            }
        });

        bg1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Zsolti.jump = true;
            }
        });

        zsolti = new Zsolti();
        zsolti.setPosition(250,30);

        tank = new Tank();
        tank.setSize(240,240);
        tank.setY(-40);
        tank.setX(2400);

        marancsics = new Marancsics();
        marancsics.setPosition(60,30);

        bg1.setX(0);
        bg2.setX(bg1.getWidth());

        addActor(bg1);
        addActor(bg2);
        addActor(zsolti);
        addActor(marancsics);
        addActor(tank);
    }

    @Override
    public void init() {

    }
}
