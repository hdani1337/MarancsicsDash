package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.AntiHealth;
import hu.hdani1337.marancsicsDash.Stage.BossStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class BossScreen extends MyScreen {
    BossStage bossStage;

    public BossScreen(marancsicsGame game) {
        super(game);
        bossStage = new BossStage(new  ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()), spriteBatch, game);
        Gdx.input.setInputProcessor(bossStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if(delta >= 0)
        {
            bossStage.act(delta);
            bossStage.draw();
        }
    }
}
