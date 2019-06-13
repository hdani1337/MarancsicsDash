package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.Marancsics;
import hu.hdani1337.marancsicsDash.Actor.Zsolti;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.Screen.GameScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Actor.Zsolti.superTime;
import static hu.hdani1337.marancsicsDash.Stage.GameStage.ground;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.selectedBackground;

public class IntroStage extends MyStage {
    Background bg1;
    Background bg2;
    Marancsics marancsics = new Marancsics();
    Zsolti zsolti = new Zsolti();

    public IntroStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);

        Marancsics.intro = true;
        Zsolti.intro = true;

        Zsolti.jump = false;
        Zsolti.fall = false;
        Zsolti.forcejump = false;
        Zsolti.doThings = true;

        superTime = 0;
        zsolti.setFps(12);

        setBackground(viewport);
        setPositions();
        addActors();
    }

    void setBackground(Viewport viewport)
    {
        if(selectedBackground == 0)
        {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG), viewport);
            ground = 30;
        }

        else if(selectedBackground == 1)
        {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG2), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG2), viewport);
            ground = 30;
        }

        else if(selectedBackground == 2)
        {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG3), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG3), viewport);
            ground = 90;
        }

        else if(selectedBackground == 3)
        {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG4), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG4), viewport);
            ground = 130;
        }

        else if(selectedBackground == 4)
        {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG5), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG5), viewport);
            ground = 145;
        }
    }

    void setPositions()
    {
        bg1.setPosition(0,0);
        bg2.setPosition(bg1.getWidth(),0);

        marancsics.setPosition(-420,ground);
        zsolti.setPosition(-160,ground);
    }

    void addActors()
    {
        addActor(bg1);
        addActor(bg2);
        addActor(marancsics);
        addActor(zsolti);
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(!Marancsics.intro) game.setScreen(new GameScreen(game));
    }
}
