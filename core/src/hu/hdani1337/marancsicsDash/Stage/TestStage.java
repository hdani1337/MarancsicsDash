package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.Marancsics;
import hu.hdani1337.marancsicsDash.Actor.Tank;
import hu.hdani1337.marancsicsDash.Actor.Zsolti;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class TestStage extends MyStage {

    Zsolti zsolti;
    Marancsics marancsics;
    Tank tank;
    Background bg;

    public TestStage(Viewport viewport, Batch batch, marancsicsGame game) {
        super(viewport, batch, game);
        zsolti = new Zsolti();
        marancsics = new Marancsics();
        tank = new Tank();
        bg = new Background(Assets.manager.get(Assets.TEST_BG));

        marancsics.setPosition(0,500);
        zsolti.setPosition(0,0);
        tank.setPosition(1000,200);
        addActor(bg);
        addActor(zsolti);
        addActor(marancsics);
        addActor(tank);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void init() {

    }
}
