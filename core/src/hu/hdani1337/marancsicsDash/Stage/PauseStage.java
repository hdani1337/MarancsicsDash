package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.MarancsicsBoss;
import hu.hdani1337.marancsicsDash.Actor.Tank;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.PauseButton;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.PlayButton;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.TextBackground;
import hu.hdani1337.marancsicsDash.Screen.HomeScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.selectedBackground;

public class PauseStage extends MyStage {
    Background bg;
    PlayButton playButton = new PlayButton();
    OneSpriteStaticActor logo;
    MyLabel text = new MyLabel("Megállítva",game.getLabelStyle());
    MyLabel score = new MyLabel("Jelenlegi pontszámod: "+ Tank.pontszam,game.getLabelStyle());
    MyButton back = new MyButton("Vissza a menübe",game.getButtonStyle());
    TextBackground backBG = new TextBackground();
    TextBackground textbackground = new TextBackground();
    private int speed = 2;
    public static boolean fromBoss;

    public PauseStage(Viewport viewport, Batch batch, final marancsicsGame game, final Screen screen) {
        super(viewport, batch, game);
        setValues();
        setBackground(viewport);
        addListeners(screen);
        setSizesAndPositions(viewport);
        addActors();
    }

    void setBackground(Viewport viewport)
    {
        if(selectedBackground == 0)
        {
            bg = new Background(Assets.manager.get(Assets.GAME_BG), viewport);
        }

        else if(selectedBackground == 1)
        {
            bg = new Background(Assets.manager.get(Assets.GAME_BG2), viewport);;
        }

        else if(selectedBackground == 2)
        {
            bg = new Background(Assets.manager.get(Assets.GAME_BG3), viewport);
        }

        else if(selectedBackground == 3)
        {
            bg = new Background(Assets.manager.get(Assets.GAME_BG4), viewport);
        }

        else if(selectedBackground == 4)
        {
            bg = new Background(Assets.manager.get(Assets.GAME_BG5), viewport);
        }
    }
    
    void setValues()
    {
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
    }

    void addListeners(final Screen screen)
    {
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                PauseButton.paused = false;
                game.setScreen(screen);
            }
        });
        
        ClickListener backListener = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                PauseButton.paused = false;
                Tank.pontszam = 0;
                MarancsicsBoss.marancsicsHealth = 99.9f;
                game.setScreen(new HomeScreen(game));
            }
        };

        backBG.addListener(backListener);
        back.addListener(backListener);
    }

    void setSizesAndPositions(Viewport viewport)
    {
        playButton.setSize(160,160);
        playButton.setPosition(viewport.getWorldWidth() / 2 - playButton.getWidth() / 2, viewport.getWorldHeight() / 2 - playButton.getHeight()/2 + 30);

        text.setFontScale(1.7f);
        score.setFontScale(1.4f);

        logo.setPosition(viewport.getWorldWidth()/2-logo.getWidth()/2,viewport.getWorldHeight()/2 + logo.getHeight()/2.3f);

        text.setPosition(viewport.getWorldWidth()/2 - text.getWidth() / 1.15f, playButton.getY() - text.getHeight()*2.75f);

        score.setPosition(viewport.getWorldWidth() / 2 - score.getWidth() / 1.45f, text.getY() - score.getHeight()*1.5f);

        back.setPosition(viewport.getWorldWidth()/2-back.getWidth()/2,score.getY() - 105);
        backBG.setSize(back.getWidth() + 36, back.getHeight() + 20);
        backBG.setPosition(back.getX() - 18,back.getY() - 10);

        textbackground.setSize(600,150);
        textbackground.setPosition(viewport.getWorldWidth()/2-textbackground.getWidth()/2,score.getY() - 25);
    }

    void addActors()
    {
        addActor(bg);
        addActor(logo);
        addActor(playButton);
        addActor(textbackground);
        addActor(text);
        addActor(backBG);
        addActor(score);
        addActor(back);
    }

    @Override
    public void init() {

    }
}
