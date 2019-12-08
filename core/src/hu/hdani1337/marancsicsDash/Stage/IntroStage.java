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
import static hu.hdani1337.marancsicsDash.marancsicsGame.desktop;

public class IntroStage extends MyStage {
    Background bg1;
    Background bg2;
    Marancsics marancsics = new Marancsics();
    Zsolti zsolti = new Zsolti();
    Background menuBackground;

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

        menuBackground = new Background(Assets.manager.get(Assets.MENU_BG),viewport);

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
            if(!desktop) ground = 30;
            else ground = 30+30;
        }

        else if(selectedBackground == 1)
        {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG2), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG2), viewport);
            if(!desktop) ground = 30;
            else ground = 30+20;
        }

        else if(selectedBackground == 2)
        {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG3), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG3), viewport);
            if(!desktop) ground = 90;
            else ground = 90+30;
        }

        else if(selectedBackground == 3)
        {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG4), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG4), viewport);
            if(!desktop) ground = 130;
            else ground = 130+30;
        }

        else if(selectedBackground == 4)
        {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG5), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG5), viewport);
            if(!desktop) ground = 145;
            else ground = 145+30;
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
        addActor(menuBackground);
        addActor(bg1);
        addActor(bg2);
        addActor(marancsics);
        addActor(zsolti);
    }

    @Override
    public void init() {

    }

    float alpha = 0;

    @Override
    public void act(float delta) {
        super.act(delta);
        if(alpha < 1)
        {
            bg1.setColor(1,1,1,alpha);
            alpha += 0.02;
        }else alpha = 1;
        if(!Marancsics.intro) game.setScreen(new GameScreen(game));
    }
}
