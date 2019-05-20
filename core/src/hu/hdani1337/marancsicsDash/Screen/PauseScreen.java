package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.Actor.Tank;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.PauseButton;
import hu.hdani1337.marancsicsDash.Stage.CrashStage;
import hu.hdani1337.marancsicsDash.Stage.HomeStage;
import hu.hdani1337.marancsicsDash.Stage.OptionsStage;
import hu.hdani1337.marancsicsDash.Stage.PauseStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class PauseScreen extends MyScreen {
    PauseStage pauseStage;

    public static float tankXT;
    public static float tankYT;
    public static float zsoltiRT;
    public static float zsoltiYT;

    public PauseScreen(marancsicsGame game, float tankX, float tankY, float zsoltiR, float zsoltiY) {
        super(game);
        float keparany = Gdx.graphics.getWidth() / (Gdx.graphics.getHeight()/1.0f);
        if (keparany >= (21/9f)) pauseStage = new PauseStage(new FitViewport(1680,720),spriteBatch,game,tankX,tankY,zsoltiR,zsoltiY);
        else if (keparany >= (19/9f)) pauseStage = new PauseStage(new FitViewport(1520,720),spriteBatch,game,tankX,tankY,zsoltiR,zsoltiY);
        else if (keparany >= (18.67/9.0f)) pauseStage = new PauseStage(new FitViewport(1493,720),spriteBatch,game,tankX,tankY,zsoltiR,zsoltiY);
        else if (keparany >= (18.5f/9.0f)) pauseStage = new PauseStage(new FitViewport(1480,720),spriteBatch,game,tankX,tankY,zsoltiR,zsoltiY);
        else if (keparany >= (18/9f)) pauseStage = new PauseStage(new FitViewport(1440,720),spriteBatch,game,tankX,tankY,zsoltiR,zsoltiY);
        else pauseStage = new PauseStage(new FitViewport(1280,720),spriteBatch,game,tankX,tankY,zsoltiR,zsoltiY);
        Gdx.input.setInputProcessor(pauseStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        {
            PauseButton.paused = false;
            game.setScreen(new GameScreen(game, tankXT, tankYT,zsoltiRT,zsoltiYT, true));
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            PauseButton.paused = false;
            Tank.pontszam = 0;
            game.setScreen(new HomeScreen(game));
        }

        pauseStage.act(delta);
        pauseStage.draw();
    }
}
