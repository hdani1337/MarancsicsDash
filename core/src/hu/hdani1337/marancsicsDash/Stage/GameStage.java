package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.Coin;
import hu.hdani1337.marancsicsDash.Actor.Mushroom;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.InstantBoss;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.JumpIcon;
import hu.hdani1337.marancsicsDash.Actor.Marancsics;
import hu.hdani1337.marancsicsDash.Actor.Tank;
import hu.hdani1337.marancsicsDash.Actor.Zsolti;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.PauseButton;
import hu.hdani1337.marancsicsDash.Screen.BossScreen;
import hu.hdani1337.marancsicsDash.Screen.CrashScreen;
import hu.hdani1337.marancsicsDash.Screen.PauseScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Actor.Mushroom.superZS;
import static hu.hdani1337.marancsicsDash.Actor.Tank.pontszam;
import static hu.hdani1337.marancsicsDash.Actor.Zsolti.forcejump;
import static hu.hdani1337.marancsicsDash.Actor.Zsolti.multitasking;
import static hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyActor.overlaps;
import static hu.hdani1337.marancsicsDash.MyBaseClasses.UI.PauseButton.paused;
import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.difficulty;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.gamemode;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.preferences;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.selectedBackground;
import static hu.hdani1337.marancsicsDash.Stage.ShopStage.boughtZsolti;

public class GameStage extends MyStage {
    //Hátterek
    Background bg1;
    Background bg2;

    //Actorok
    static Zsolti zsolti = new Zsolti(Assets.manager.get(Assets.ZSOLTI));
    static Marancsics marancsics = new Marancsics();
    Coin coin = new Coin(true);
    static Tank tank = new Tank();
    Mushroom mushroom = new Mushroom();

    //UI
    Coin coinLabel = new Coin(false);
    InstantBoss instantBoss = new InstantBoss();
    JumpIcon jumpIcon = new JumpIcon();
    PauseButton pauseButton = new PauseButton();
    MyLabel scoreLabel = new MyLabel("" + pontszam, game.getLabelStyle());
    MyLabel coinLabelText = new MyLabel("", game.getLabelStyle());

    //Hangok
    Sound hee = Assets.manager.get(Assets.HEE);
    Sound kick = Assets.manager.get(Assets.KICK);
    Sound crash = Assets.manager.get(Assets.CRASH);
    Sound coinSound = Assets.manager.get(Assets.COIN_SOUND);
    Sound powerUp = Assets.manager.get(Assets.POWERUP);
    Music music = Assets.manager.get(Assets.GAMEMUSIC);

    //Egyéb értékek
    int bossScore = (int) (Math.random() * 15 + 10);
    public static float zsoltitempy;
    public static float zsoltitempr;
    private boolean dontRepeat = false;

    //Talaj
    public static int ground = 30;

    public GameStage(Viewport viewport, Batch batch, final marancsicsGame game, float tankX, float tankY, float zsoltiR, float zsoltiY, boolean backFromPause) {
        super(viewport, batch, game);
        defaultValues();//Default értékek
        playMusic();//Háttérzene
        setBackground(viewport);//Háttérkép beállítása
        addListeners();//Listenerek
        setSizes();//Méretek állítása
        setPositions(viewport);//Pozicionálás
        gameContinue(tankX,tankY,zsoltiR,zsoltiY,backFromPause);//Meg volt e állítva, ha igen, állítsa át a pozíciókat
        addActors();//Actorok hozzáadása
    }

    void defaultValues()
    {
        Zsolti.jump = false; //Ne ugorjon magától az elején
        Zsolti.doThings = true;
        if(difficulty != 1 && difficulty != 2 && difficulty != 3){//ha a játékos nem lép be a beállításokba, akkor legyen normál a nehézség
            difficulty = 2;
        }
    }

    void addListeners()
    {
        instantBoss.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                music.stop();
                game.setScreen(new BossScreen(game,0,0,0,0,false));
            }
        });
    }

    void superZsolti()
    {
        mushroom.superZS = true;
        zsoltitempy = zsolti.getY();
        zsoltitempr = zsolti.getRotation();
        zsolti.remove();
        zsolti = new Zsolti(Assets.manager.get(Assets.SUPERZSOLTI));
        Zsolti.doThings = true;
        zsolti.setPosition(250,zsoltitempy);
        zsolti.setRotation(zsoltitempr);
        addActor(zsolti);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                backToNormal();//Default Zsolti
            }
        }, 8);
    }

    void backToNormal()
    {
        superZS = false;
        zsoltitempy = zsolti.getY();
        zsoltitempr = zsolti.getRotation();
        zsolti.remove();
        zsolti = new Zsolti(Assets.manager.get(Assets.ZSOLTI));
        Zsolti.doThings = true;
        zsolti.setPosition(250,zsoltitempy);
        zsolti.setRotation(zsoltitempr);
        addActor(zsolti);
    }

    void setBackground(Viewport viewport)
    {
        if(selectedBackground == 0)
        {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG), viewport);
            ground = 30;
        }

        else if(selectedBackground == 1)
        {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG2), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG2), viewport);
            ground = 30;
        }

        else if(selectedBackground == 2)
        {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG3), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG3), viewport);
            ground = 90;
        }
    }

    void playMusic()
    {
        if (muted) {
            music.stop();

        } else {
            music.setLooping(true);
            music.setVolume(0.5f);
            music.play();
        }
    }

    void gameContinue(float tankX, float tankY, float zsoltiR, float zsoltiY, boolean backFromPause)//megállították e a játékot
    {
        if (backFromPause) {
            zsolti.setRotation(zsoltiR);
            zsolti.setPosition(250, zsoltiY);
            tank.setPosition(tankX, tankY);
            if (!muted) music.play();
            if (zsoltiY > ground && zsoltiR > 0) Zsolti.jump = true; //ekkor ugrik felfelé
            if (zsoltiY > ground && zsoltiR <= 0) Zsolti.fall = true; //ekkor ugrik lefelé
        }
    }

    void setSizes()//actorok méretezése
    {
        tank.setSize(240, 240);
        scoreLabel.setFontScale(1.5f);
        instantBoss.setSize(jumpIcon.getWidth(),jumpIcon.getHeight());
    }

    void setPositions(Viewport viewport)//actorok elhelyezése
    {
        marancsics.setPosition(60, ground);

        coin.setPosition(-100, -100);
        mushroom.setPosition((int)(Math.random() * 8240 + 3840),(int)(Math.random() * 250 + 150));

        tank.setX(viewport.getWorldWidth() * (int)(Math.random() * 4 + 2));
        tank.setY(ground-70);
        tank.setRotation(0);

        zsolti.setPosition(250, ground);
        zsolti.setRotation(0);

        scoreLabel.setPosition(viewport.getWorldWidth() / 2 - scoreLabel.getWidth() / 2, viewport.getWorldHeight() - scoreLabel.getHeight() * 1.5f);

        jumpIcon.setPosition(viewport.getWorldWidth() - jumpIcon.getWidth() * 1.1f, 15);

        pauseButton.setPosition(viewport.getWorldWidth() - pauseButton.getWidth() * 1.1f, viewport.getWorldHeight() - 175);

        instantBoss.setPosition(jumpIcon.getX(),(((pauseButton.getY() + pauseButton.getHeight()) - (jumpIcon.getY()+jumpIcon.getHeight())))/2);

        bg1.setX(0);
        bg2.setX(bg1.getWidth());

        coinLabel.setPosition(15, viewport.getWorldHeight() - 15 - coinLabel.getHeight());
        coinLabelText.setPosition(coinLabel.getX() + coinLabel.getWidth() + 10, coinLabel.getY() + coinLabel.getHeight() / 2);
    }

    void addActors()//actorok hozzáadása
    {
        addActor(bg1);
        addActor(bg2);
        addActor(zsolti);
        addActor(marancsics);
        addActor(tank);
        addActor(scoreLabel);
        addActor(jumpIcon);
        addActor(pauseButton);
        addActor(coin);
        addActor(coinLabel);
        addActor(coinLabelText);

        if (boughtZsolti) addActor(mushroom);
        if (ShopStage.boughtInstantBoss && gamemode != 2) addActor(instantBoss);
    }


    @Override
    public void init() {

    }

    private void crash()
    {
        if (tank.getRotation() <= 3)
            if (zsolti.getY() > ground + tank.getHeight() / 4)
                if (zsolti.getY() <= tank.getY() + tank.getHeight())
                    if (zsolti.getX()> tank.getX())
                        if (zsolti.getX() < tank.getX() + tank.getWidth()) {
                            forcejump = true;//Ekkor van a tank tetején, ugrás
                        }

        if (!forcejump) {
            if (superZS) {
                if (!muted) {
                    if(!dontRepeat) {
                        kick.play();
                        dontRepeat = true;
                    }
                }
                marancsics.tankComing = true;//Super Zsolti belerúg a tankba
                dontRepeat = false;
            } else {
                if (!muted) {
                    if(!dontRepeat) {
                        crash.play();
                        music.stop();
                    }
                }
                preferences.putLong("coin", Coin.coin);
                preferences.flush();//Coin elmentése
                Marancsics.tankComing = false;
                dontRepeat = false;
                game.setScreen(new CrashScreen(game));//Ütközés, vesztés képernyő
            }
        }
    }

    private void crashThread()
    {
        if(!multitasking) {//Ha mobilon megy, akkor menjen külön szálra
            new Thread(new Runnable() {
                public void run() {
                    crash();
                }
            }).start();

        }
        else crash();//Gépen laggolna
    }

    private void backgroundMoving()
    {//Két háttér folyamatosan mozog egymás mellett
        if (difficulty >= 1) {
            bg2.setX(bg2.getX() - difficulty * 6);
            if (bg2.getX() < -bg2.getWidth()) {
                bg2.setX(bg1.getX() + bg1.getWidth() - difficulty * 6);
            }

            bg1.setX(bg1.getX() - difficulty * 6);
            if (bg1.getX() < -bg1.getWidth()) {
                bg1.setX(bg2.getX() + bg2.getWidth() - difficulty * 6);
            }
        }
    }

    void utkozesek()
    {
        if (overlaps(marancsics, tank)) {//Marancsics belerúg a tankba
            if (!muted && !dontRepeat) {
                kick.play();
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        hee.play();
                    }
                }, 0.3f);
            }
            marancsics.tankComing = true;
            dontRepeat = false;
        }

        if (overlaps(zsolti, mushroom))
        {
            superZsolti();//Super Zsolti
            if(!muted &&! dontRepeat) powerUp.play();
            dontRepeat = false;
        }

        if(overlaps(zsolti,coin)){//Felvette a pénzt
            coin.felvette = true;
            if(!muted) {
                coinSound.play(1);
            }
        }

        if(overlaps(marancsics,coin) || coin.getX() < 0-coin.getWidth()) coin.felvette = false;//Nem vette fel a pénzt

        if(overlaps(zsolti,tank)) crashThread();//Zsolti ütközik a tankkal, feladat külön szálra
    }

    void pause()
    {
        if(paused){
            if(!muted){
                music.pause();
            }
            PauseStage.fromBoss = false;
            game.setScreen(new PauseScreen(game, tank.getX(), tank.getY(),zsolti.getRotation(),zsolti.getY()));
        }
    }

    void switchBoss()
    {
        if(pontszam >= bossScore && gamemode != 2)
        {
            music.stop();
            game.setScreen(new BossScreen(game,0,0,0,0,false));
        }
    }

    void refreshLabels()
    {
        scoreLabel.setText("" + pontszam);//Pontszám folyamatos frissítése
        coinLabelText.setText("" + Coin.coin);//Pénzszám folyamatos ismétlése
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        backgroundMoving();//Metódusban levan írva, hogy mi mit csinál
        utkozesek();//Metódusban levan írva, hogy mi mit csinál
        pause();//Ha megállítják a játékot, váltsunk paused képre
        switchBoss();//Ha nem endless és átlép egy random számot, akkor boss
        refreshLabels();//Labelek folyamatos frissítése
    }
}
