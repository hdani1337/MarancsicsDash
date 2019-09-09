package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Stage.InfoStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.marancsicsGame.desktop;
import static hu.hdani1337.marancsicsDash.marancsicsGame.keparany;

public class InfoScreen extends MyScreen {
    InfoStage infoStage;

    public InfoScreen(marancsicsGame game) {
        super(game);
        if(desktop) infoStage = new InfoStage(new FitViewport(keparany(),900),spriteBatch,game);
        else infoStage = new InfoStage(new FitViewport(keparany(),720),spriteBatch,game);
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
