package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;

public class BossStage extends MyStage {

    MyLabel marancsicsElete = new MyLabel("Marancsics élete",game.getLabelStyle());
    Background background;
    Background background2;
    MarancsicsBoss marancsicsBoss;
    Zsolti zsolti = new Zsolti();
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

        if(!muted)
        {
            bossMusic.play();
            bossMusic.setVolume(0.5f);
            bossMusic.setLooping(true);
        }

        health = new Health(viewport);
        antiHealth = new AntiHealth(health.getWidth(),health.getY(),health.getX());

        background = new Background(Assets.manager.get(Assets.GAME_BG),viewport);
        background2 = new Background(Assets.manager.get(Assets.GAME_BG),viewport);

        marancsicsBoss = new MarancsicsBoss(viewport);

        gameContinue(bossX,bossY,zsoltiR,zsoltiY,backFromPause);
        setPositions(viewport);
        addActors();
    }

    void gameContinue(float bossX, float bossY, float zsoltiR, float zsoltiY, boolean backFromPause)
    {
        if(backFromPause){
            zsolti.setRotation(zsoltiR);
            zsolti.setPosition(30, zsoltiY);
            if(zsoltiY > 30 && zsoltiR > 0) Zsolti.jump = true; //ekkor ugrik felfelé
            else if(zsoltiY > 30 && zsoltiR <= 0) Zsolti.fall = true; //ekkor ugrik lefelé
            marancsicsBoss.setPosition(bossX,bossY);
        }
        else{
            zsolti.setPosition(30,30);
        }
    }

    void setPositions(Viewport viewport)
    {
        background.setPosition(0,0);
        background2.setPosition(background.getWidth(),0);
        marancsicsElete.setPosition(health.getX()+health.getWidth()/2-marancsicsElete.getWidth()/2,health.getY()+health.getHeight()/2-marancsicsElete.getHeight()/2);
        jumpIcon.setPosition(viewport.getWorldWidth() - jumpIcon.getWidth() * 1.1f,15);
        pauseButton.setPosition(jumpIcon.getX(),viewport.getWorldHeight() - pauseButton.getHeight() - 15);
        marancsicsBoss.setX(viewport.getWorldWidth()+marancsicsBoss.getWidth());
    }

    void addActors()
    {
        addActor(background);
        addActor(background2);
        addActor(marancsicsBoss);
        addActor(zsolti);
        addActor(health);
        addActor(antiHealth);
        addActor(marancsicsElete);
        addActor(jumpIcon);
        addActor(pauseButton);
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        background.setX(background.getX()-5);
        background2.setX(background2.getX()-5);

        if(PauseButton.paused){
            if(!muted){
                bossMusic.pause();
            }
            PauseStage.fromBoss = true;
            game.setScreen(new PauseScreen(game,marancsicsBoss.getX(),marancsicsBoss.getY(),zsolti.getRotation(),zsolti.getY()));
        }

        if (background.getX() + background.getWidth() <= 0) background.setX(background2.getX()+background2.getWidth());
        if (background2.getX() + background2.getWidth() <= 0) background2.setX(background.getX()+background.getWidth());

        if (marancsicsHealth <= 0) game.setScreen(new VictoryScreen(game));

        else if(overlaps(zsolti,marancsicsBoss)) {
            if (marancsicsBoss.getRotation() <= 3)
                if (zsolti.getY() > 30 + marancsicsBoss.getHeight() / 2) {
                    if (zsolti.getY() <= marancsicsBoss.getY() + marancsicsBoss.getHeight())
                        if (zsolti.getX() + zsolti.getWidth() > marancsicsBoss.getX())
                            if (zsolti.getX() < marancsicsBoss.getX() + marancsicsBoss.getWidth()) {
                                if(Zsolti.fall) {
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
                    {
                        game.setScreen(new CrashScreen(game));
                        if(!muted)
                        {
                            crash.play();
                            bossMusic.stop();
                        }
                    }
        }
    }
}
