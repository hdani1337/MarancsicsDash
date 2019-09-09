package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Stage.VictoryStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.marancsicsGame.desktop;
import static hu.hdani1337.marancsicsDash.marancsicsGame.keparany;

public class VictoryScreen extends MyScreen {

    VictoryStage victoryStage;

    public VictoryScreen(marancsicsGame game) {
        super(game);
        if(desktop) victoryStage = new VictoryStage(new FitViewport(keparany(),900),spriteBatch,game);
        else victoryStage = new VictoryStage(new FitViewport(keparany(),720),spriteBatch,game);
        Gdx.input.setInputProcessor(victoryStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if(delta >= 0)
        {
            victoryStage.act(delta);
            victoryStage.draw();
        }
    }
}
