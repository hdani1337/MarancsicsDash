package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Stage.PauseStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class PauseScreen extends MyScreen {
    PauseStage pauseStage;

    public PauseScreen(marancsicsGame game, float tankX, float tankY) {
        super(game);
        pauseStage = new PauseStage(new FitViewport(1280,720),spriteBatch,game,tankX,tankY);
        Gdx.input.setInputProcessor(pauseStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        pauseStage.act(delta);
        pauseStage.draw();
    }
}
