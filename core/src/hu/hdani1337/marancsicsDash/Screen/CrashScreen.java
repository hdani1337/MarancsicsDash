package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Stage.BossStage;
import hu.hdani1337.marancsicsDash.Stage.CrashStage;
import hu.hdani1337.marancsicsDash.Stage.GameStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class CrashScreen extends MyScreen {
    CrashStage crashStage;

    public CrashScreen(marancsicsGame game) {
        super(game);
        float keparany = Gdx.graphics.getWidth() / (Gdx.graphics.getHeight()/1.0f);
        if (keparany >= (21/9f)) crashStage = new CrashStage(new FitViewport(1680,720),spriteBatch,game);
        else if (keparany >= (19/9f)) crashStage = new CrashStage(new FitViewport(1520,720),spriteBatch,game);
        else if (keparany >= (18.67/9.0f)) crashStage = new CrashStage(new FitViewport(1493,720),spriteBatch,game);
        else if (keparany >= (18.5f/9.0f)) crashStage = new CrashStage(new FitViewport(1480,720),spriteBatch,game);
        else if (keparany >= (18/9f)) crashStage = new CrashStage(new FitViewport(1440,720),spriteBatch,game);
        else crashStage = new CrashStage(new FitViewport(1280,720),spriteBatch,game);
        Gdx.input.setInputProcessor(crashStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) game.setScreen(new GameScreen(game,0,0,0,0,false));

        else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    game.setScreen(new HomeScreen(game));
                }
            }, 0.1f);
        }

        crashStage.act(delta);
        if(delta >= 0){
            crashStage.draw();
        }
    }
}
