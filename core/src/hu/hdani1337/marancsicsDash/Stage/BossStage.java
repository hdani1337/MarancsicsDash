package hu.hdani1337.marancsicsDash.Stage;

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
import hu.hdani1337.marancsicsDash.Screen.CrashScreen;
import hu.hdani1337.marancsicsDash.Screen.VictoryScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Actor.MarancsicsBoss.marancsicsHealth;
import static hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyActor.overlaps;
import static hu.hdani1337.marancsicsDash.Stage.HomeStage.hee;
import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;

public class BossStage extends MyStage {

    MyLabel marancsicsElete;
    Background background;
    Background background2;
    MarancsicsBoss marancsicsBoss;
    Zsolti zsolti;
    Health health;
    AntiHealth antiHealth;
    JumpIcon jumpIcon;
    Sound hee = Assets.manager.get(Assets.HEE);
    Sound crash = Assets.manager.get(Assets.CRASH);
    Sound glassbreak = Assets.manager.get(Assets.GLASSBREAK);
    public static Music bossMusic = Assets.manager.get(Assets.BOSSMUSIC);

    public BossStage(Viewport viewport, Batch batch, marancsicsGame game) {
        super(viewport, batch, game);

        if(!muted)
        {
            bossMusic.play();
            bossMusic.setVolume(0.5f);
            bossMusic.setLooping(true);
        }

        marancsicsElete = new MyLabel("Marancsics élete",game.getLabelStyle());
        health = new Health(viewport);
        antiHealth = new AntiHealth(health.getWidth(),health.getY(),health.getX());
        jumpIcon = new JumpIcon();

        marancsicsElete.setPosition(health.getX()+health.getWidth()/2-marancsicsElete.getWidth()/2,health.getY()+health.getHeight()/2-marancsicsElete.getHeight()/2);

        background = new Background(Assets.manager.get(Assets.GAME_BG));
        background2 = new Background(Assets.manager.get(Assets.GAME_BG));
        background.setPosition(0,0);
        background2.setPosition(background.getWidth(),0);
        marancsicsBoss = new MarancsicsBoss(viewport);
        zsolti = new Zsolti();

        zsolti.setPosition(30,30);

        marancsicsBoss.setX(viewport.getWorldWidth()+marancsicsBoss.getWidth());

        addActor(background);
        addActor(background2);
        addActor(marancsicsBoss);
        addActor(zsolti);
        addActor(health);
        addActor(antiHealth);
        addActor(marancsicsElete);
        addActor(jumpIcon);

        jumpIcon.setPosition(viewport.getWorldWidth() - jumpIcon.getWidth() * 1.1f,15);

        zsolti.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                marancsicsHealth -= 5;
                if(marancsicsHealth < 0) marancsicsHealth = 0;
                else if(marancsicsHealth > 99.9) marancsicsHealth = 99.9f;
                System.out.println(marancsicsHealth);
            }
        });

    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        background.setX(background.getX()-5);
        background2.setX(background2.getX()-5);

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
