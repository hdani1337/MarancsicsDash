package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteActor;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class Home extends MyScreen {

    OneSpriteAnimatedActor zsolti;
    OneSpriteAnimatedActor marancsics;

    MyStage test = new MyStage(new ExtendViewport(1280,720),spriteBatch,game) {
        @Override
        public void init() {
            zsolti = new OneSpriteAnimatedActor(Assets.manager.get(Assets.ZSOLTI)){
                @Override
                public void act(float delta) {
                    super.act(delta);
                    setX(getX() + delta * 50);
                    setFps(30);
                }
            };

            marancsics = new OneSpriteAnimatedActor(Assets.manager.get(Assets.MARANCSICS)){
                @Override
                public void act(float delta) {
                    super.act(delta);
                    setX(getX() + delta * 360);
                    setFps(60);
                }
            };

            zsolti.setPosition(10,10);
            marancsics.setPosition(300,300);

            addActor(zsolti);
            addActor(marancsics);
        }
    };

    public Home(marancsicsGame game) {
        super(game);
    }

    @Override
    public void init() {

    }

    public void render(float delta){
        super.render(delta);
        test.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(test);
    }
}
