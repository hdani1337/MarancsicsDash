package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Stage.CrashStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class CrashScreen extends MyScreen {
    CrashStage crashStage;

    public CrashScreen(marancsicsGame game) {
        super(game);
        crashStage = new CrashStage(new ExtendViewport(1280,720),spriteBatch,game);
        Gdx.input.setInputProcessor(crashStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        crashStage.act(delta);
        crashStage.draw();
    }
}
