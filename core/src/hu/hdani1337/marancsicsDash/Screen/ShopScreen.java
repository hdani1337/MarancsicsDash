package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.Stage.ShopStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.marancsicsGame.keparany;

public class ShopScreen extends MyScreen {
    ShopStage shopStage;

    public ShopScreen(marancsicsGame game) {
        super(game);
        shopStage = new ShopStage(new FitViewport(keparany(),720),spriteBatch,game);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(shopStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if(delta >= 0){
            shopStage.act(delta);
            shopStage.draw();
        }
    }
}
