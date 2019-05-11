package hu.hdani1337.marancsicsDash.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.JumpIcon;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.PauseButton;
import hu.hdani1337.marancsicsDash.Stage.GameStage;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Actor.Zsolti.jump;

public class GameScreen extends MyScreen {

    GameStage gameStage;

    public GameScreen(marancsicsGame game, float tankX, float tankY, float zsoltiR, float zsoltiY, boolean backFromPause) {
        super(game);
        gameStage = new GameStage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()),spriteBatch,game,tankX,tankY,zsoltiR,zsoltiY,backFromPause);
        Gdx.input.setInputProcessor(gameStage);
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

        gameStage.act(delta);
        gameStage.draw();
        if(delta >= 0){
            gameStage.draw();
        }
    }
}
