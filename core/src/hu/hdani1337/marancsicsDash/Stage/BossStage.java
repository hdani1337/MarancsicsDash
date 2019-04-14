package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.Marancsics;
import hu.hdani1337.marancsicsDash.Actor.MarancsicsBoss;
import hu.hdani1337.marancsicsDash.Actor.Zsolti;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class BossStage extends MyStage {
    public BossStage(Viewport viewport, Batch batch, marancsicsGame game) {
        super(viewport, batch, game);
        Background background;
        MarancsicsBoss marancsicsBoss;
        Zsolti zsolti;

        background = new Background(Assets.manager.get(Assets.GAME_BG));
        marancsicsBoss = new MarancsicsBoss();
        zsolti = new Zsolti();

        marancsicsBoss.setX(viewport.getWorldWidth()-marancsicsBoss.getWidth());

        addActor(background);
        addActor(marancsicsBoss);
        addActor(zsolti);
    }

    @Override
    public void init() {

    }
}
