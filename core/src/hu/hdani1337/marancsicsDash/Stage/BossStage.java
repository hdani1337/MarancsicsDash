package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.MarancsicsBoss;
import hu.hdani1337.marancsicsDash.Actor.Zsolti;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.AntiHealth;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.Health;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.JumpIcon;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.PauseButton;
import hu.hdani1337.marancsicsDash.Screen.CrashScreen;
import hu.hdani1337.marancsicsDash.Screen.PauseScreen;
import hu.hdani1337.marancsicsDash.Screen.VictoryScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Actor.MarancsicsBoss.marancsicsHealth;
import static hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyActor.overlaps;
import static hu.hdani1337.marancsicsDash.Stage.GameStage.ground;
import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.difficulty;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.selectedBackground;

public class BossStage extends MyStage {

    MyLabel marancsicsElete = new MyLabel("Marancsics élete", game.getLabelStyle());
    Background bg1;
    Background bg2;
    MarancsicsBoss marancsicsBoss;
    Zsolti zsolti = new Zsolti(Assets.manager.get(Assets.ZSOLTI));
    Health health;
    AntiHealth antiHealth;
    JumpIcon jumpIcon = new JumpIcon();
    PauseButton pauseButton = new PauseButton();
    Sound hee = Assets.manager.get(Assets.HEE);
    Sound crash = Assets.manager.get(Assets.CRASH);
    Sound glassbreak = Assets.manager.get(Assets.GLASSBREAK);
    public static Music bossMusic = Assets.manager.get(Assets.BOSSMUSIC);

    public BossStage(Viewport viewport, Batch batch, final marancsicsGame game, float bossX, float bossY, float zsoltiR, float zsoltiY, boolean backFromPause) {
        super(viewport, batch, game);
        Zsolti.jump = false;
        Zsolti.fall = false;
        Zsolti.doThings = true;

        if (!muted) {
            bossMusic.play();
            bossMusic.setVolume(0.5f);
            bossMusic.setLooping(true);
        }

        health = new Health(viewport);
        antiHealth = new AntiHealth(health.getWidth(), health.getY(), health.getX());

        marancsicsBoss = new MarancsicsBoss(viewport);

        setBackground(viewport);
        setPositions(viewport);
        gameContinue(bossX, bossY, zsoltiR, zsoltiY, backFromPause);
        addActors();
    }

    void setBackground(Viewport viewport) {
        if (selectedBackground == 0) {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG), viewport);
        } else if (selectedBackground == 1) {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG2), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG2), viewport);
        } else if (selectedBackground == 2) {
            bg1 = new Background(Assets.manager.get(Assets.GAME_BG3), viewport);
            bg2 = new Background(Assets.manager.get(Assets.GAME_BG3), viewport);
        }
    }

    void gameContinue(float bossX, float bossY, float zsoltiR, float zsoltiY, boolean backFromPause) {
        if (backFromPause) {
            zsolti.setRotation(zsoltiR);
            zsolti.setPosition(50, zsoltiY);
            if (zsoltiY > ground && zsoltiR > 0) Zsolti.jump = true; //ekkor ugrik felfelé
            else if (zsoltiY > ground && zsoltiR <= 0) Zsolti.fall = true; //ekkor ugrik lefelé
            marancsicsBoss.setPosition(bossX, bossY);
        } else {
            zsolti.setPosition(50, ground);
        }
    }

    void setPositions(Viewport viewport) {
        bg1.setPosition(0, 0);
        bg2.setPosition(bg1.getWidth(), 0);
        marancsicsElete.setPosition(health.getX() + health.getWidth() / 2 - marancsicsElete.getWidth() / 2, health.getY() + health.getHeight() / 2 - marancsicsElete.getHeight() / 2);
        jumpIcon.setPosition(viewport.getWorldWidth() - jumpIcon.getWidth() * 1.1f, 15);
        pauseButton.setPosition(jumpIcon.getX(), viewport.getWorldHeight() - pauseButton.getHeight() - 15);
        marancsicsBoss.setX(viewport.getWorldWidth() + marancsicsBoss.getWidth());
    }

    void addActors() {
        addActor(bg1);
        addActor(bg2);
        addActor(marancsicsBoss);
        addActor(zsolti);
        addActor(health);
        addActor(antiHealth);
        addActor(marancsicsElete);
        addActor(jumpIcon);
        addActor(pauseButton);
    }

    void pause() {
        if (PauseButton.paused) {
            if (!muted) {
                bossMusic.pause();
            }
            PauseStage.fromBoss = true;
            game.setScreen(new PauseScreen(game, marancsicsBoss.getX(), marancsicsBoss.getY(), zsolti.getRotation(), zsolti.getY()));
        }
    }

    void moveBackgrounds()
    {
        bg1.setX(bg1.getX()-difficulty*3);
        bg2.setX(bg2.getX()-difficulty*3);
        if (bg1.getX() + bg1.getWidth() <= 0) bg1.setX(bg2.getX()+bg2.getWidth());
        if (bg2.getX() + bg2.getWidth() <= 0) bg2.setX(bg1.getX()+bg1.getWidth());

    }

    void jumpingOnCar()
    {
        if (marancsicsHealth <= 0) game.setScreen(new VictoryScreen(game));

        else if(overlaps(zsolti,marancsicsBoss)) {
            if (marancsicsBoss.getRotation() <= 3)
                if (zsolti.getY() > 30 + marancsicsBoss.getHeight() / 2) {
                    if (zsolti.getY() <= marancsicsBoss.getY() + marancsicsBoss.getHeight())
                        if (zsolti.getX() + zsolti.getWidth() > marancsicsBoss.getX())
                            if (zsolti.getX() < marancsicsBoss.getX() + marancsicsBoss.getWidth()) {
                                if(Zsolti.fall) {//Ekkor van az autón
                                    Zsolti.forcejump = true;
                                    marancsicsHealth -= (int) (Math.random() * 5 + 3);
                                    if(!muted)
                                    {
                                        glassbreak.play(0.5f);
                                        hee.play();
                                    }
                                }
                            }
                }
                else
                {//Ekkor ütközik szemből
                    game.setScreen(new CrashScreen(game));
                    if(!muted)
                    {
                        crash.play();
                        bossMusic.stop();
                    }
                }
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        pause();//Megvan e állítva
        moveBackgrounds();//Háttér mozgatása
        jumpingOnCar();//Ütközés
    }
}
