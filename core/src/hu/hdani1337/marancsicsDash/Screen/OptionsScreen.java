package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Stage.OptionsStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class OptionsScreen extends MyScreen {
    OptionsStage optionsStage;

    public OptionsScreen(marancsicsGame game) {
        super(game);
        optionsStage = new OptionsStage(new FitViewport(1280,720),spriteBatch,game);
        Gdx.input.setInputProcessor(optionsStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        optionsStage.act(delta);
        if(delta >= 0){
            optionsStage.draw();
        }
    }
}
