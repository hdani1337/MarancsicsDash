package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.Marancsics;
import hu.hdani1337.marancsicsDash.Actor.Zsolti;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.Screen.GameScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class IntroStage extends MyStage {
    Background background;
    Background background2;
    Marancsics marancsics;
    Zsolti zsolti;

    public IntroStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        background = new Background(Assets.manager.get(Assets.GAME_BG));
        background2 = new Background(Assets.manager.get(Assets.GAME_BG));
        background.setPosition(0,0);
        background2.setPosition(background.getWidth(),0);

        marancsics = new Marancsics();
        zsolti = new Zsolti();

        marancsics.setPosition(-420,30);
        zsolti.setPosition(-160,30);

        Marancsics.intro = true;
        Zsolti.intro = true;

        addActor(background);
        addActor(background2);
        addActor(marancsics);
        addActor(zsolti);
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(!Marancsics.intro) game.setScreen(new GameScreen(game,0,0,0,0,false));
    }
}
