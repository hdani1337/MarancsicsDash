package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.JumpIcon;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.PauseButton;
import hu.hdani1337.marancsicsDash.Stage.BossStage;
import hu.hdani1337.marancsicsDash.Stage.CrashStage;
import hu.hdani1337.marancsicsDash.Stage.PauseStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Actor.Zsolti.jump;
import static hu.hdani1337.marancsicsDash.Stage.BossStage.bossMusic;
import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;

public class BossScreen extends MyScreen {
    BossStage bossStage;

    public BossScreen(marancsicsGame game, float bossX, float bossY, float zsoltiR, float zsoltiY, boolean backFromPause) {
        super(game);
        float keparany = Gdx.graphics.getWidth() / (Gdx.graphics.getHeight()/1.0f);
        if (keparany >= (21/9.0f)) bossStage = new BossStage(new FitViewport(1680,720),spriteBatch,game,bossX,bossY,zsoltiR,zsoltiY,backFromPause);
        else if (keparany >= (19/9.0f)) bossStage = new BossStage(new FitViewport(1520,720),spriteBatch,game,bossX,bossY,zsoltiR,zsoltiY,backFromPause);
        else if (keparany >= (18.67/9.0f)) bossStage = new BossStage(new FitViewport(1493,720),spriteBatch,game,bossX,bossY,zsoltiR,zsoltiY,backFromPause);
        else if (keparany >= (18.5f/9.0f)) bossStage = new BossStage(new FitViewport(1480,720),spriteBatch,game,bossX,bossY,zsoltiR,zsoltiY,backFromPause);
        else if (keparany >= (18/9.0f)) bossStage = new BossStage(new FitViewport(1440,720),spriteBatch,game,bossX,bossY,zsoltiR,zsoltiY,backFromPause);
        else bossStage = new BossStage(new FitViewport(1280,720),spriteBatch,game,bossX,bossY,zsoltiR,zsoltiY,backFromPause);
        Gdx.input.setInputProcessor(bossStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        {
            JumpIcon.jumpHeight = 250 + (int)(Math.random() * 75 + 1);
            jump = true;
        }

        else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    PauseButton.paused = true;
                }
            }, 0.1f);
        }

        if(delta >= 0)
        {
            bossStage.act(delta);
            bossStage.draw();
        }
    }
}
