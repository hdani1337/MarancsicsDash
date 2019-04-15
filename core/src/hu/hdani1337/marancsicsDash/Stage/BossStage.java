package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.Marancsics;
import hu.hdani1337.marancsicsDash.Actor.MarancsicsBoss;
import hu.hdani1337.marancsicsDash.Actor.Zsolti;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.AntiHealth;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.Health;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class BossStage extends MyStage {

    MyLabel marancsicsElete;

    public BossStage(Viewport viewport, Batch batch, marancsicsGame game) {
        super(viewport, batch, game);
        Background background;
        MarancsicsBoss marancsicsBoss;
        Zsolti zsolti;
        Health health;
        AntiHealth antiHealth;

        marancsicsElete = new MyLabel("Marancsics élete",game.getLabelStyle());
        health = new Health(viewport);
        antiHealth = new AntiHealth(health.getWidth(),health.getY(),health.getX());

        marancsicsElete.setPosition(health.getX()+health.getWidth()/2-marancsicsElete.getWidth()/2,health.getY()+health.getHeight()/2-marancsicsElete.getHeight()/2);

        background = new Background(Assets.manager.get(Assets.GAME_BG));
        marancsicsBoss = new MarancsicsBoss();
        zsolti = new Zsolti();

        marancsicsBoss.setX(viewport.getWorldWidth()-marancsicsBoss.getWidth());

        addActor(background);
        addActor(marancsicsBoss);
        addActor(zsolti);
        addActor(health);
        addActor(antiHealth);
        addActor(marancsicsElete);

    }

    @Override
    public void init() {

    }
}
