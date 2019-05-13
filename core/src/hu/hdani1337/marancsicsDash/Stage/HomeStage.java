package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.TextBackground;
import hu.hdani1337.marancsicsDash.Screen.InfoScreen;
import hu.hdani1337.marancsicsDash.Screen.IntroScreen;
import hu.hdani1337.marancsicsDash.Screen.OptionsScreen;
import hu.hdani1337.marancsicsDash.Screen.ShopScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;
import static hu.hdani1337.marancsicsDash.Stage.BossStage.bossMusic;

import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.preferences;

public class HomeStage extends MyStage {
    private int speed = 2;
    public static boolean muted = preferences.getBoolean("muted");

    MyButton start;
    MyButton info;
    MyButton options;
    MyButton exit;
    MyButton shop;
    Background bg;
    public static Sound uraim;
    public static Sound hee;
    OneSpriteStaticActor logo;
    public static Music music;
    TextBackground textBackground1;
    TextBackground textBackground2;
    TextBackground textBackground3;
    TextBackground textBackground4;
    TextBackground textBackground5;
    TextBackground textBackground6;
    MyLabel ver;

    public HomeStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        bossMusic.stop();
        start = new MyButton("A játék indítása",game.getButtonStyle());
        info = new MyButton("A játékról",game.getButtonStyle());
        shop = new MyButton("Bolt",game.getButtonStyle());
        options = new MyButton("Beállítások",game.getButtonStyle());
        exit = new MyButton("Kilépés",game.getButtonStyle());
        ver = new MyLabel("Verzió: 0.6.2 Beta",game.getLabelStyle());
        bg = new Background(Assets.manager.get(Assets.MENU_BG));
        uraim = Assets.manager.get(Assets.URAIM);
        hee = Assets.manager.get(Assets.HEE);
        music = Assets.manager.get(Assets.MENUMUSIC);
        textBackground1 = new TextBackground();
        textBackground2 = new TextBackground();
        textBackground3 = new TextBackground();
        textBackground4 = new TextBackground();
        textBackground5 = new TextBackground();
        textBackground6 = new TextBackground();

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

        if(muted){
            music.stop();
            uraim.setVolume(0,0.0f);
            hee.setVolume(0,0.0f);
        }
        else {
            music.setLooping(true);
            music.setVolume(0.5f);
            music.play();
        }

        start.setX(viewport.getWorldWidth()/2 - start.getWidth()/2);
        start.setY(viewport.getWorldHeight()/2 - start.getHeight()/2);
        info.setY(start.getY() - info.getHeight()*2);
        info.setX((viewport.getWorldWidth()/2 - info.getWidth()/2));
        shop.setY(info.getY() - shop.getHeight()*2);
        shop.setX((viewport.getWorldWidth()/2 - shop.getWidth()/2));
        options.setY(shop.getY() - options.getHeight()*2);
        options.setX((viewport.getWorldWidth()/2 - options.getWidth()/2));
        exit.setY(options.getY() - exit.getHeight()*2);
        exit.setX((viewport.getWorldWidth()/2 - exit.getWidth()/2));
        ver.setX(20);
        ver.setY(20);

        textBackground1.setPosition(start.getX()-15,start.getY()-7);
        textBackground2.setPosition(info.getX()-15,info.getY()-7);
        textBackground3.setPosition(options.getX()-15,options.getY()-7);
        textBackground4.setPosition(exit.getX()-15,exit.getY()-7);
        textBackground5.setPosition(ver.getX()-15,ver.getY()-7);
        textBackground6.setPosition(shop.getX() - 15, shop.getY() + -7);

        textBackground1.setSize(start.getWidth() + 30, start.getHeight() + 14);
        textBackground2.setSize(info.getWidth() + 30, info.getHeight() + 14);
        textBackground3.setSize(options.getWidth() + 30, options.getHeight() + 14);
        textBackground4.setSize(exit.getWidth() + 30, exit.getHeight() + 14);
        textBackground5.setSize(ver.getWidth() + 30, ver.getHeight() + 14);
        textBackground6.setSize(shop.getWidth() + 30, shop.getHeight() + 14);

        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(!muted) {
                    uraim.play();
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            music.stop();
                            game.setScreen(new IntroScreen(game));
                        }
                    }, 1);
                }
                else{
                    game.setScreen(new IntroScreen(game));
                }
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
                if(!muted) {
                    hee.play();
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            music.stop();
                            Gdx.app.exit();
                        }
                    }, 0.65f);
                }
                else{
                    Gdx.app.exit();
                }
            }
        });

        shop.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new ShopScreen(game));
            }
        });

        logo.setPosition(viewport.getWorldWidth()/2 - logo.getWidth()/2, viewport.getWorldHeight() - logo.getHeight()*1.5f);

        addActor(bg);
        addActor(textBackground1);
        addActor(textBackground2);
        addActor(textBackground3);
        addActor(textBackground4);
        addActor(textBackground5);
        addActor(textBackground6);
        addActor(start);
        addActor(info);
        addActor(options);
        addActor(shop);
        addActor(exit);
        addActor(ver);
        addActor(logo);
    }

    @Override
    public void init() {

    }
}
