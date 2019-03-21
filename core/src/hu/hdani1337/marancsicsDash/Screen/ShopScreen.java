package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.Stage.ShopStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class ShopScreen extends MyScreen {
    ShopStage shopStage;

    public ShopScreen(marancsicsGame game) {
        super(game);
        shopStage = new ShopStage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()),spriteBatch,game);
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
