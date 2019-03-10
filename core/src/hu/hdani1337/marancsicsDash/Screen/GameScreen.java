package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Stage.GameStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class GameScreen extends MyScreen {

    GameStage gameStage;

    public GameScreen(marancsicsGame game, float tankX, float tankY, float zsoltiR, float zsoltiY, boolean backFromPause) {
        super(game);
        gameStage = new GameStage(new FitViewport(1280,720),spriteBatch,game,tankX,tankY,zsoltiR,zsoltiY,backFromPause);
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
        if(delta >= 0){
            gameStage.draw();
        }
    }
}
