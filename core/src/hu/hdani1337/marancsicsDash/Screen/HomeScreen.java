package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Stage.HomeStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class HomeScreen extends MyScreen {

    HomeStage homeStage;

    public HomeScreen(marancsicsGame game) {
        super(game);
        homeStage = new HomeStage(new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()),spriteBatch,game);
        Gdx.input.setInputProcessor(homeStage);
    }

    @Override
    public void init() {

    }

    public void render(float delta){
        super.render(delta);
        homeStage.act(delta);
        if(delta >= 0){
            homeStage.draw();
        }
    }

}
