package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Stage.InfoStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class InfoScreen extends MyScreen {
    InfoStage infoStage;

    public InfoScreen(marancsicsGame game) {
        super(game);
        infoStage = new InfoStage(new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()),spriteBatch,game);
        Gdx.input.setInputProcessor(infoStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        infoStage.act(delta);
        if(delta >= 0){
            infoStage.draw();
        }
    }
}
