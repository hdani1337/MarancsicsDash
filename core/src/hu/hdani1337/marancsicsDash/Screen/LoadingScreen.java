package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteAnimatedActor;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.marancsicsGame.keparany;

public class LoadingScreen extends MyScreen {
    BitmapFont bitmapFont = new BitmapFont();
    TextureAtlas atlas = new TextureAtlas("atlas/marancsics.atlas");
    Texture loading = new Texture("pic/loading.png");
    Texture loading_bar = new Texture("pic/bar.jpg");
    Texture loading_bg = new Texture("pic/loadingBg.jpg");
    OneSpriteAnimatedActor marancsics = new OneSpriteAnimatedActor(atlas);
    {
        marancsics.setPosition(Gdx.graphics.getWidth()/2-marancsics.getWidth()/2,Gdx.graphics.getHeight()/2);
        marancsics.setFps(12);
        marancsics.setLooping(true);
    }
    OneSpriteStaticActor loadingActor = new OneSpriteStaticActor(loading);
    {
        loadingActor.setPosition(Gdx.graphics.getWidth()/2-loadingActor.getWidth()/2,Gdx.graphics.getHeight()/2 - loadingActor.getHeight()*1.3f);
    }
    OneSpriteStaticActor loadingBarActor = new OneSpriteStaticActor(loading_bar);
    {
        loadingBarActor.setPosition(loadingActor.getX()+15,loadingActor.getY()+25);
        loadingBarActor.setHeight(loadingActor.getHeight()-40);
    }
    OneSpriteStaticActor loadingBg = new OneSpriteStaticActor(loading_bg);
    {
        loadingBg.setPosition(0,0);
        loadingBg.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    public LoadingScreen(marancsicsGame game) {
        super(game);
        marancsicsGame.notch = (keparany()>16);
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
        loadingBg.draw(spriteBatch,1f);
        marancsics.draw(spriteBatch,1f);
        loadingBarActor.draw(spriteBatch,1f);
        loadingActor.draw(spriteBatch,1f);
        marancsics.act(delta);
        loadingBarActor.act(delta);
        loadingBarActor.setWidth(loadingActor.getWidth()/100*Assets.manager.getProgress()*100f);
        bitmapFont.draw(spriteBatch,"Betöltés..." + " (" + ((int)(Assets.manager.getProgress()*100f)) + "%)",loadingActor.getX()+loadingActor.getWidth()/2-50,loadingActor.getY()+loadingActor.getHeight()/2+5);
        spriteBatch.end();
        if (Assets.manager.update()) {
            game.setScreen(new HomeScreen(game));
        }
    }

    public void init() {
        setBackGroundColor(0f, 0f, 0f);
    }
}
