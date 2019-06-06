package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteAnimatedActor;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class LoadingScreen extends MyScreen {
    BitmapFont bitmapFont = new BitmapFont();
    TextureAtlas atlas = new TextureAtlas("atlas/marancsics.atlas");
    OneSpriteAnimatedActor marancsics = new OneSpriteAnimatedActor(atlas);
    {
        marancsics.setPosition(Gdx.graphics.getWidth()/2-marancsics.getWidth()/2,Gdx.graphics.getHeight()/2 - marancsics.getHeight()/3);
        marancsics.setFps(12);
        marancsics.setLooping(true);
    }

    public LoadingScreen(marancsicsGame game) {
        super(game);
    }

    @Override
    public void show() {
        Assets.manager.finishLoading();
        Assets.load();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        marancsics.draw(spriteBatch,1f);
        marancsics.act(delta);
        bitmapFont.draw(spriteBatch,"Betöltés..." + " (" + ((int)(Assets.manager.getProgress()*100f)) + "%)",marancsics.getX()-5,marancsics.getY() - 20);
        spriteBatch.end();
        if (Assets.manager.update()) {
            game.setScreen(new HomeScreen(game));
        }
    }

    public void init() {
        setBackGroundColor(0f, 0f, 0f);
    }
}
