package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.Tank;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.PauseButton;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.PlayButton;
import hu.hdani1337.marancsicsDash.Screen.GameScreen;
import hu.hdani1337.marancsicsDash.Screen.HomeScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class PauseStage extends MyStage {
    Background background;
    PlayButton playButton;
    OneSpriteStaticActor logo;
    MyLabel text;
    MyLabel score;
    MyButton back;
    private int speed = 2;

    public PauseStage(Viewport viewport, Batch batch, final marancsicsGame game, final float tankX, final float tankY, final float zsoltiR, final float zsoltiY) {
        super(viewport, batch, game);
        background = new Background(Assets.manager.get(Assets.GAME_BG));

        back = new MyButton("Vissza a menübe",game.getButtonStyle());

        playButton = new PlayButton();
        playButton.setSize(160,160);
        playButton.setPosition(viewport.getWorldWidth() / 2 - playButton.getWidth() / 2, viewport.getWorldHeight() / 2 - playButton.getHeight()/2);

        text = new MyLabel("Megállítva",game.getLabelStyle());
        text.setFontScale(1.7f);

        score = new MyLabel("Jelenlegi pontszámod: "+ Tank.pontszam,game.getLabelStyle());
        score.setFontScale(1.4f);

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                PauseButton.paused = false;
                game.setScreen(new GameScreen(game, tankX, tankY,zsoltiR,zsoltiY, true));
            }
        });

        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                PauseButton.paused = false;
                Tank.pontszam = 0;
                game.setScreen(new HomeScreen(game));
            }
        });

        logo = new OneSpriteStaticActor(Assets.manager.get(Assets.LOGO)){
            @Override
            public void act(float delta) {
                super.act(delta);
                setRotation(getRotation() + delta * speed);

                if(getRotation() >= 12 || getRotation() <= -12){
                    speed *= -1;
                }

                setDebug(false);
            }
        };

        logo.setPosition(viewport.getWorldWidth()/2-logo.getWidth()/2,viewport.getWorldHeight()/2 + logo.getHeight()/2.5f);

        text.setPosition(viewport.getWorldWidth()/2 - text.getWidth() / 1.15f, playButton.getY() - text.getHeight()*2);

        score.setPosition(viewport.getWorldWidth() / 2 - score.getWidth() / 1.45f, text.getY() - score.getHeight()*1.5f);

        back.setPosition(viewport.getWorldWidth()/2-back.getWidth()/2,score.getY() - 90);

        addActor(background);
        addActor(logo);
        addActor(playButton);
        addActor(text);
        addActor(score);
        addActor(back);
    }

    @Override
    public void init() {

    }
}
