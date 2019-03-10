package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.Screen.GameScreen;
import hu.hdani1337.marancsicsDash.Screen.InfoScreen;
import hu.hdani1337.marancsicsDash.Screen.OptionsScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class HomeStage extends MyStage {
    private int speed = 2;

    MyButton start;
    MyButton info;
    MyButton options;
    MyButton exit;
    Background bg;
    Sound uraim;
    Sound hee;
    OneSpriteStaticActor logo;

    public HomeStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        start = new MyButton("A játék indítása",game.getButtonStyle());
        info = new MyButton("A játékról",game.getButtonStyle());
        options = new MyButton("Beállítások",game.getButtonStyle());
        exit = new MyButton("Kilépés",game.getButtonStyle());
        bg = new Background(Assets.manager.get(Assets.MENU_BG));
        uraim = Assets.manager.get(Assets.URAIM);
        hee = Assets.manager.get(Assets.HEE);

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

        start.setX(viewport.getWorldWidth()/2 - start.getWidth()/2);
        start.setY(viewport.getWorldHeight()/2 - start.getHeight()/2);
        info.setY(start.getY() - info.getHeight()*2);
        info.setX((viewport.getWorldWidth()/2 - info.getWidth()/2));
        options.setY(info.getY() - options.getHeight()*2);
        options.setX((viewport.getWorldWidth()/2 - options.getWidth()/2));
        exit.setY(options.getY() - exit.getHeight()*2);
        exit.setX((viewport.getWorldWidth()/2 - exit.getWidth()/2));

        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                uraim.play();
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        game.setScreen(new GameScreen(game,0,0,0,0,false));
                    }
                }, 1);
            }
        });

        info.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new InfoScreen(game));
            }
        });

        options.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new OptionsScreen(game));
            }
        });

        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                hee.play();
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        Gdx.app.exit();
                    }
                }, 0.65f);
            }
        });

        logo.setPosition(viewport.getWorldWidth()/2 - logo.getWidth()/2, viewport.getWorldHeight() - logo.getHeight()*1.5f);

        addActor(bg);
        addActor(start);
        addActor(info);
        addActor(options);
        addActor(exit);
        addActor(logo);
    }

    @Override
    public void init() {

    }
}
