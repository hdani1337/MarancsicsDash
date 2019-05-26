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

import static hu.hdani1337.marancsicsDash.Actor.Tank.pontszam;
import static hu.hdani1337.marancsicsDash.Actor.Zsolti.forcejump;
import static hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyActor.overlaps;
import static hu.hdani1337.marancsicsDash.MyBaseClasses.UI.PauseButton.paused;
import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.difficulty;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.gamemode;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.preferences;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.selectedBackground;

public class GameStage extends MyStage {
    //Hátterek
    Background bg1;
    Background bg2;

    //Actorok
    Zsolti zsolti = new Zsolti();
    Marancsics marancsics = new Marancsics();
    Coin coin = new Coin(true);
    Tank tank = new Tank();

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
    Music music = Assets.manager.get(Assets.GAMEMUSIC);

    int bossScore = (int) (Math.random() * 15 + 10);

    public static int ground = 30;

    public GameStage(Viewport viewport, Batch batch, final marancsicsGame game, float tankX, float tankY, float zsoltiR, float zsoltiY, boolean backFromPause) {
        super(viewport, batch, game);
        Zsolti.jump = false; //Ne ugorjon magától az elején
        if(difficulty != 1 && difficulty != 2 && difficulty != 3){//ha a játékos nem lép be a beállításokba, akkor legyen normál a nehézség
            difficulty = 2;
        }

        playMusic();

        setBackground(viewport);

        instantBoss.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                music.stop();
                game.setScreen(new BossScreen(game,0,0,0,0,false));
            }
        });

        gameContinue(tankX,tankY,zsoltiR,zsoltiY,backFromPause);
        setSizes();
        setPositions(viewport);
        addActors();
    }

    void setBackground(Viewport viewport)
    {
        if(selectedBackground == 0)
        {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG), viewport);
        }

        else if(selectedBackground == 1)
        {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG2), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG2), viewport);
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
            if (zsoltiY > ground && zsoltiR > 0) Zsolti.jump = true; //ekkor ugrik felfelé
            if (zsoltiY > ground && zsoltiR <= 0) Zsolti.fall = true; //ekkor ugrik lefelé
        } else {
            zsolti.setPosition(250, ground);
        }

        if (backFromPause) {
            tank.setPosition(tankX, tankY);
            if (!muted) music.play();
        } else tank.setPosition(2400, ground - 70);
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

        tank.setX(viewport.getWorldWidth() * 2);

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

        if (ShopStage.boughtInstantBoss && gamemode != 2) addActor(instantBoss);
    }


    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(difficulty >= 1){
            bg2.setX(bg2.getX()-difficulty*6);
            if(bg2.getX() < -bg2.getWidth()){
                bg2.setX(bg1.getX()+bg1.getWidth()-difficulty*6);
            }

            bg1.setX(bg1.getX()-difficulty*6);
            if(bg1.getX() < -bg1.getWidth()){
                bg1.setX(bg2.getX()+bg2.getWidth()-difficulty*6);
            }
        }

        scoreLabel.setText(""+ pontszam);
        coinLabelText.setText(""+Coin.coin);

        if(overlaps(marancsics,tank)){
            if(!muted) {
                kick.play();
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        hee.play();
                    }
                }, 0.3f);
            }
            marancsics.tankComing = true;
        }

        if(pontszam >= bossScore && gamemode != 2)
        {
            music.stop();
            game.setScreen(new BossScreen(game,0,0,0,0,false));
        }

        if(overlaps(zsolti,tank)){
            if (tank.getRotation() <= 3)
                if(zsolti.getY() > ground + tank.getHeight() / 4)
                    if(zsolti.getY() <= tank.getY()+tank.getHeight())
                        if(zsolti.getX() + zsolti.getWidth() > tank.getX())
                            if(zsolti.getX() < tank.getX() + tank.getWidth())
                            {
                                forcejump  = true;
                            }

            if(!forcejump) {
                if (!muted) {
                    crash.play();
                    music.stop();
                }
                preferences.putLong("coin", Coin.coin);
                preferences.flush();
                game.setScreen(new CrashScreen(game));
                Marancsics.tankComing = false;
            }
        }

        if(paused){
            if(!muted){
                music.pause();
            }
            PauseStage.fromBoss = false;
            game.setScreen(new PauseScreen(game, tank.getX(), tank.getY(),zsolti.getRotation(),zsolti.getY()));
        }

        if(overlaps(zsolti,coin)){
            coin.felvette = true;
            if(!muted) {
                coinSound.play(1);
            }
        }

        if(overlaps(marancsics,coin) || coin.getX() < 0-coin.getWidth()){
            coin.felvette = false;
        }
    }
}
