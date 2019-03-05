package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Actor.Marancsics;
import hu.hdani1337.marancsicsDash.Actor.Zsolti;
import hu.hdani1337.marancsicsDash.Stage.HomeStage;
import hu.hdani1337.marancsicsDash.Stage.TestStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class HomeScreen extends MyScreen {

    HomeStage home;

    public HomeScreen(marancsicsGame game) {
        super(game);
        home = new HomeStage(new ExtendViewport(1280,720),spriteBatch,game);
        Gdx.input.setInputProcessor(home);
    }

    @Override
    public void init() {

    }

    public void render(float delta){
        super.render(delta);
        home.act(delta);
        home.draw();
    }

}
