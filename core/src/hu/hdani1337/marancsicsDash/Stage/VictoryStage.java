package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.MarancsicsBoss;
import hu.hdani1337.marancsicsDash.Actor.Tank;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.TextBackground;
import hu.hdani1337.marancsicsDash.Screen.HomeScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Stage.BossStage.bossMusic;

public class VictoryStage extends MyStage {

    Background background;
    MyLabel congratulations = new MyLabel("Gratulálok! Legyözted Marancsicsot!",game.getLabelStyle());
    MyLabel thanks = new MyLabel("Köszönöm, hogy végigjátszottad a játékot!\nPár hónap kódolás, kép és zenelopkodás után végre \nelkészült a Marancsics Dash címü játék. Köszönöm a\nközremüködést Marancsics Tamás tanárúrnak a hangokért,\ntovábbá a családomnak és osztálytársaimnak a támogatását!",game.getLabelStyle());
    TextBackground textBackground1 = new TextBackground();
    TextBackground textBackground2 = new TextBackground();

    public VictoryStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        background = new Background(Assets.manager.get(Assets.GAME_BG),viewport);

        setPositionsAndSizes(viewport);
        addActors();
        timers();
    }

    void setPositionsAndSizes(Viewport viewport)
    {
        congratulations.setPosition(viewport.getWorldWidth()/2-congratulations.getWidth()/2,viewport.getWorldHeight()/2-congratulations.getHeight()/2);
        thanks.setPosition(viewport.getWorldWidth()/2-thanks.getWidth()/2,viewport.getWorldHeight()/2-thanks.getHeight()/2);
        thanks.setAlignment(0);

        textBackground1.setSize(congratulations.getWidth() + 64, congratulations.getHeight() + 32);
        textBackground1.setPosition(congratulations.getX() - 32,congratulations.getY() - 16);

        textBackground2.setSize(thanks.getWidth() + 90, thanks.getHeight() + 64);
        textBackground2.setPosition(thanks.getX() - 45,thanks.getY() - 32);
    }

    void addActors()
    {
        addActor(background);
        addActor(textBackground1);
        addActor(congratulations);
    }

    void timers()
    {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                congratulations.remove();
                textBackground1.remove();
                addActor(textBackground2);
                addActor(thanks);
            }
        }, 5);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                bossMusic.stop();
                Tank.pontszam = 0;
                MarancsicsBoss.marancsicsHealth = 99.9f;
                game.setScreen(new HomeScreen(game));
            }
        }, 15);
    }

    @Override
    public void init() {

    }
}
