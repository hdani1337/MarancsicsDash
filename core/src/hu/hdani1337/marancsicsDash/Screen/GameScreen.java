package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Stage.GameStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class GameScreen extends MyScreen {

    GameStage gameStage;

    public GameScreen(marancsicsGame game) {
        super(game);
        gameStage = new GameStage(new ExtendViewport(1280,720),spriteBatch,game);
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        gameStage.act(delta);
        gameStage.draw();
    }
}
