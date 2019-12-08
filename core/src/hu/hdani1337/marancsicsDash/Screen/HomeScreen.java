package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Stage.HomeStage;
import hu.hdani1337.marancsicsDash.Stage.InfoStage;
import hu.hdani1337.marancsicsDash.Stage.IntroStage;
import hu.hdani1337.marancsicsDash.Stage.OptionsStage;
import hu.hdani1337.marancsicsDash.Stage.ShopStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;
import static hu.hdani1337.marancsicsDash.Stage.HomeStage.uraim;
import static hu.hdani1337.marancsicsDash.marancsicsGame.desktop;
import static hu.hdani1337.marancsicsDash.marancsicsGame.keparany;

public class HomeScreen extends MyScreen {

    HomeStage homeStage;
    OptionsStage optionsStage;
    InfoStage infoStage;
    ShopStage shopStage;
    IntroStage introStage;

    FitViewport fitViewport;

    private static String whatToDraw;

    public HomeScreen(marancsicsGame game) {
        super(game);
        if(desktop) fitViewport = new FitViewport(keparany(),900);
        else fitViewport = new FitViewport(keparany(),720);
        whatToDraw = "home";
        homeStage = new HomeStage(fitViewport,spriteBatch,game);
        optionsStage = new OptionsStage(fitViewport,spriteBatch,game);
        infoStage = new InfoStage(fitViewport,spriteBatch,game);
        shopStage = new ShopStage(fitViewport,spriteBatch,game);
        introStage = new IntroStage(fitViewport,spriteBatch,game);
    }

    @Override
    public void init() {

    }

    InputMultiplexer inputMultiplexer = new InputMultiplexer();

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public void render(float delta){
        super.render(delta);
        if(whatToDraw == "home") {
            if(!inputMultiplexer.getProcessors().contains(homeStage,true)) {
                inputMultiplexer.clear();
                inputMultiplexer.addProcessor(homeStage);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                if (!muted) {
                    uraim.play();
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            HomeStage.music.stop();
                            whatToDraw = "game";
                        }
                    }, 1);
                } else {
                    whatToDraw = "game";
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                if (!muted) {
                    HomeStage.hee.play();
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            HomeStage.music.stop();
                            Gdx.app.exit();
                        }
                    }, 0.65f);
                } else {
                    Gdx.app.exit();
                }
            }
            homeStage.act(delta);
            if (delta >= 0) {
                homeStage.draw();
            }
        }
        else if (whatToDraw == "shop")
        {
            if(!inputMultiplexer.getProcessors().contains(shopStage,true)) {
                inputMultiplexer.clear();
                inputMultiplexer.addProcessor(shopStage);
            }
            shopStage.act(delta);
            if (delta >= 0) {
                shopStage.draw();
            }
        }
        else if (whatToDraw == "options")
        {
            if(!inputMultiplexer.getProcessors().contains(optionsStage,true)) {
                inputMultiplexer.clear();
                inputMultiplexer.addProcessor(optionsStage);
            }
            optionsStage.act(delta);
            if (delta >= 0) {
                optionsStage.draw();
            }
        }
        else if (whatToDraw == "info")
        {
            if(!inputMultiplexer.getProcessors().contains(infoStage,true)) {
                inputMultiplexer.clear();
                inputMultiplexer.addProcessor(infoStage);
            }
            infoStage.act(delta);
            if (delta >= 0) {
                infoStage.draw();
            }
        }
        else if (whatToDraw == "game")
        {
            inputMultiplexer.clear();
            introStage.act(delta);
            if(delta >= 0){
                introStage.draw();
            }
        }
    }

    public static void setWhatToDraw(String s) {
        whatToDraw = s;
    }
}
