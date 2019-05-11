package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Stage.IntroStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class IntroScreen extends MyScreen {
   IntroStage introStage;

    public IntroScreen(marancsicsGame game) {
        super(game);
        introStage = new IntroStage(new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()),spriteBatch,game);
        Gdx.input.setInputProcessor(introStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        introStage.act(delta);
        if(delta >= 0){
            introStage.draw();
        }
    }
}
