package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Actor.Marancsics;
import hu.hdani1337.marancsicsDash.Actor.Zsolti;
import hu.hdani1337.marancsicsDash.Stage.TestStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class Home extends MyScreen {

    TestStage test;

    public Home(marancsicsGame game) {
        super(game);
        test = new TestStage(new ExtendViewport(1280,720),spriteBatch,game);
        Gdx.input.setInputProcessor(test);
    }

    @Override
    public void init() {

    }

    public void render(float delta){
        super.render(delta);
        test.act(delta);
        test.draw();
    }

}
