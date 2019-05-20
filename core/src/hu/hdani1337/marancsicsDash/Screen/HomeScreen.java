package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Stage.BossStage;
import hu.hdani1337.marancsicsDash.Stage.CrashStage;
import hu.hdani1337.marancsicsDash.Stage.HomeStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;
import static hu.hdani1337.marancsicsDash.Stage.HomeStage.uraim;

public class HomeScreen extends MyScreen {

    HomeStage homeStage;

    public HomeScreen(marancsicsGame game) {
        super(game);
        float keparany = Gdx.graphics.getWidth() / (Gdx.graphics.getHeight()/1.0f);
        if (keparany >= (21/9f)) homeStage = new HomeStage(new FitViewport(1680,720),spriteBatch,game);
        else if (keparany >= (19/9f)) homeStage = new HomeStage(new FitViewport(1520,720),spriteBatch,game);
        else if (keparany >= (18.67/9.0f)) homeStage = new HomeStage(new FitViewport(1493,720),spriteBatch,game);
        else if (keparany >= (18.5f/9.0f)) homeStage = new HomeStage(new FitViewport(1480,720),spriteBatch,game);
        else if (keparany >= (18/9f)) homeStage = new HomeStage(new FitViewport(1440,720),spriteBatch,game);
        else homeStage = new HomeStage(new FitViewport(1280,720),spriteBatch,game);
        Gdx.input.setInputProcessor(homeStage);
    }

    @Override
    public void init() {

    }

    public void render(float delta){
        super.render(delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        {
            if(!muted) {
                uraim.play();
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        HomeStage.music.stop();
                        game.setScreen(new IntroScreen(game));
                    }
                }, 1);
            }
            else{
                game.setScreen(new IntroScreen(game));
            }
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            if(!muted) {
                HomeStage.hee.play();
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        HomeStage.music.stop();
                        Gdx.app.exit();
                    }
                }, 0.65f);
            }
            else{
                Gdx.app.exit();
            }
        }

        homeStage.act(delta);
        if(delta >= 0){
            homeStage.draw();
        }
    }

}
