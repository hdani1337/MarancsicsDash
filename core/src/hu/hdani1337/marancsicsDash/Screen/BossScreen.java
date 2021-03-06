package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.JumpIcon;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.PauseButton;
import hu.hdani1337.marancsicsDash.Stage.BossStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Actor.Zsolti.jump;
import static hu.hdani1337.marancsicsDash.marancsicsGame.desktop;
import static hu.hdani1337.marancsicsDash.marancsicsGame.keparany;

public class BossScreen extends MyScreen {
    BossStage bossStage;

    public BossScreen(marancsicsGame game) {
        super(game);
        if(desktop) bossStage = new BossStage(new FitViewport(keparany(),900),spriteBatch,game);
        else bossStage = new BossStage(new FitViewport(keparany(),720),spriteBatch,game);
    }

    @Override
    public void init() {

    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(bossStage);
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
