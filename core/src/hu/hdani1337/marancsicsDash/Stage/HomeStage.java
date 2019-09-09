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
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.TextBackground;
import hu.hdani1337.marancsicsDash.Screen.InfoScreen;
import hu.hdani1337.marancsicsDash.Screen.IntroScreen;
import hu.hdani1337.marancsicsDash.Screen.OptionsScreen;
import hu.hdani1337.marancsicsDash.Screen.ShopScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Global.Assets.manager;
import static hu.hdani1337.marancsicsDash.Stage.BossStage.bossMusic;

import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.preferences;

public class HomeStage extends MyStage {
    private int speed = 2;
    public static boolean muted = preferences.getBoolean("muted");

    //Gombok
    MyButton start = new MyButton("A játék indítása",game.getButtonStyle());
    MyButton info = new MyButton("A játékról",game.getButtonStyle());
    MyButton options = new MyButton("Beállítások",game.getButtonStyle());
    MyButton exit = new MyButton("Kilépés",game.getButtonStyle());
    MyButton shop = new MyButton("Bolt",game.getButtonStyle());

    Background bg;//háttér

    //Hangok
    public static Sound uraim = manager.get(Assets.URAIM);
    public static Sound hee = manager.get(Assets.HEE);
    public static Music music = manager.get(Assets.MENUMUSIC);

    OneSpriteStaticActor logo;//logo

    //Gombok hátterei
    TextBackground startBG = new TextBackground();
    TextBackground infoBG = new TextBackground();
    TextBackground optionsBG = new TextBackground();
    TextBackground exitBG = new TextBackground();
    TextBackground verBG = new TextBackground();
    TextBackground shopBG = new TextBackground();

    MyLabel ver = new MyLabel("Verzió: 1.1 Delta",game.getLabelStyle());//verziószám

    public HomeStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        bossMusic.stop();
        bg = new Background(manager.get(Assets.MENU_BG),viewport);
        logo();
        playMusic();
        addListeners();
        setPositions(viewport);
        addActors();
    }

    void logo()
    {
        logo = new OneSpriteStaticActor(manager.get(Assets.LOGO)){
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

    void playMusic()
    {
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
    }

    void setPositions(Viewport viewport)
    {
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
        if(marancsicsGame.notch)
        {
            ver.setX(40);
            ver.setY(30);
        }
        else
        {
            ver.setX(30);
            ver.setY(20);
        }

        startBG.setPosition(start.getX()-15,start.getY()-7);
        infoBG.setPosition(info.getX()-15,info.getY()-7);
        optionsBG.setPosition(options.getX()-15,options.getY()-7);
        exitBG.setPosition(exit.getX()-15,exit.getY()-7);
        verBG.setPosition(ver.getX()-15,ver.getY()-7);
        shopBG.setPosition(shop.getX() - 15, shop.getY() + -7);

        startBG.setSize(start.getWidth() + 30, start.getHeight() + 14);
        infoBG.setSize(info.getWidth() + 30, info.getHeight() + 14);
        optionsBG.setSize(options.getWidth() + 30, options.getHeight() + 14);
        exitBG.setSize(exit.getWidth() + 30, exit.getHeight() + 14);
        verBG.setSize(ver.getWidth() + 30, ver.getHeight() + 14);
        shopBG.setSize(shop.getWidth() + 30, shop.getHeight() + 14);

        logo.setWidthWhithAspectRatio(500);
        logo.setPosition(viewport.getWorldWidth()/2 - logo.getWidth()/2, viewport.getWorldHeight() - logo.getHeight()*1.4f);
    }

    void addListeners()
    {
        ClickListener startListener = new ClickListener()
        {
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
        };
        
        ClickListener infoListener = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new InfoScreen(game));
            }
        };
        
        ClickListener optionsListener = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new OptionsScreen(game));
            }
        };

        ClickListener exitListener = new ClickListener()
        {
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
                    System.exit(0);
                    System.exit(-1);
                }
            }
        };
        
        ClickListener shopListener = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new ShopScreen(game));
            }
        };

        startBG.addListener(startListener);
        start.addListener(startListener);

        infoBG.addListener(infoListener);
        info.addListener(infoListener);

        optionsBG.addListener(optionsListener);
        options.addListener(optionsListener);

        exitBG.addListener(exitListener);
        exit.addListener(exitListener);

        shopBG.addListener(shopListener);
        shop.addListener(shopListener);
    }

    void addActors()
    {
        addActor(bg);
        addActor(startBG);
        addActor(infoBG);
        addActor(optionsBG);
        addActor(exitBG);
        addActor(verBG);
        addActor(shopBG);
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
