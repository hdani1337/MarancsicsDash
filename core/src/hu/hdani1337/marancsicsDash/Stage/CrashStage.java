package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.Tank;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.Screen.GameScreen;
import hu.hdani1337.marancsicsDash.Screen.HomeScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class CrashStage extends MyStage {
    Background bg;
    MyLabel text;
    MyButton reset;
    MyButton home;

    public CrashStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        bg = new Background(Assets.manager.get(Assets.GAME_BG));
        text = new MyLabel("Vesztettél!",game.getLabelStyle());
        reset = new MyButton("Új játék",game.getButtonStyle());
        home = new MyButton("Főmenü",game.getButtonStyle());

        Tank.pontszam = 0;

        text.setPosition(viewport.getWorldWidth()/2-text.getWidth(),viewport.getWorldHeight()/2+text.getHeight());
        text.setFontScale(2);

        reset.setPosition(viewport.getWorldWidth()/2-reset.getWidth()/2,text.getY()-reset.getHeight()*2);
        home.setPosition(viewport.getWorldWidth()/2-home.getWidth()/2,reset.getY()-home.getHeight()*1.5f);

        reset.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game,0,0,false));
            }
        });

        home.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new HomeScreen(game));
            }
        });

        addActor(bg);
        addActor(text);
        addActor(reset);
        addActor(home);
    }

    @Override
    public void init() {

    }
}
