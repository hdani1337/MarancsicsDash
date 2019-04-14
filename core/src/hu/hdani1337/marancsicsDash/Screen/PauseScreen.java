package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.Actor.Tank;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.JumpIcon;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.PauseButton;
import hu.hdani1337.marancsicsDash.Stage.PauseStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Actor.Zsolti.jump;

public class PauseScreen extends MyScreen {
    PauseStage pauseStage;

    public static float tankXT;
    public static float tankYT;
    public static float zsoltiRT;
    public static float zsoltiYT;

    public PauseScreen(marancsicsGame game, float tankX, float tankY, float zsoltiR, float zsoltiY) {
        super(game);
        pauseStage = new PauseStage(new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()),spriteBatch,game,tankX,tankY,zsoltiR,zsoltiY);
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
