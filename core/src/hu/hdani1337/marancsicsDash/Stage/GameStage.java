package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.JumpIcon;
import hu.hdani1337.marancsicsDash.Actor.Marancsics;
import hu.hdani1337.marancsicsDash.Actor.Tank;
import hu.hdani1337.marancsicsDash.Actor.Zsolti;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.PauseButton;
import hu.hdani1337.marancsicsDash.Screen.CrashScreen;
import hu.hdani1337.marancsicsDash.Screen.PauseScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class GameStage extends MyStage {
    Background bg1;
    Background bg2;
    Zsolti zsolti;
    Marancsics marancsics;
    Tank tank;
    JumpIcon jumpIcon;
    PauseButton pauseButton;
    MyLabel scoreLabel;
    Sound hee;
    Sound kick;
    Sound crash;

    public GameStage(Viewport viewport, Batch batch, final marancsicsGame game, float tankX, float tankY, float zsoltiR, float zsoltiY, boolean backFromPause) {
        super(viewport, batch, game);

        Zsolti.jump = false; //Ne ugorjon magától az elején

        hee = Assets.manager.get(Assets.HEE);
        kick = Assets.manager.get(Assets.KICK);
        crash = Assets.manager.get(Assets.CRASH);

        scoreLabel = new MyLabel(""+Tank.pontszam,game.getLabelStyle());

        bg1 = new Background(Assets.manager.get(Assets.GAME_BG)){
            @Override
            public void act(float delta) {
                super.act(delta);
                System.out.println(OptionsStage.difficulty);
                scoreLabel.setText(""+Tank.pontszam);

                if(OptionsStage.difficulty == 0){//ha a játékos nem lép be a beállításokba, akkor legyen normál a nehézség
                    OptionsStage.difficulty = 2;
                }

                if(OptionsStage.difficulty == 1){
                    bg1.setX(bg1.getX()-5);
                    if(bg1.getX() < -bg1.getWidth()){
                        bg1.setX(bg2.getX()+bg2.getWidth()-5);
                    }
                }

                if(OptionsStage.difficulty == 2){
                    bg1.setX(bg1.getX()-10);
                    if(bg1.getX() < -bg1.getWidth()){
                        bg1.setX(bg2.getX()+bg2.getWidth()-10);
                    }
                }

                if(OptionsStage.difficulty == 3){
                    bg1.setX(bg1.getX()-15);
                    if(bg1.getX() < -bg1.getWidth()){
                        bg1.setX(bg2.getX()+bg2.getWidth()-15);
                    }
                }

                if(tank.getX() + 60 <= marancsics.getX() + marancsics.getWidth()){
                    kick.play();
                    Timer.schedule(new Timer.Task(){
                        @Override
                        public void run() {
                           hee.play();
                        }
                    }, 0.3f);
                    marancsics.tankComing = true;
                }

                if(tank.getX() + 30 >= zsolti.getX()){
                    if(tank.getX() + 30 <= zsolti.getX() + zsolti.getWidth()){
                        if(zsolti.getY() <= tank.getY() + 140){
                            crash.play();
                            Tank.pontszam = 0;
                            game.setScreen(new CrashScreen(game));
                            Marancsics.tankComing = false;
                        }
                    }
                }

                if(PauseButton.paused){
                    game.setScreen(new PauseScreen(game, tank.getX(), tank.getY(),zsolti.getRotation(),zsolti.getY()));
                }

            }
        };

        bg2 = new Background(Assets.manager.get(Assets.GAME_BG)){
            @Override
            public void act(float delta) {
                super.act(delta);
                if(OptionsStage.difficulty == 1){
                    bg2.setX(bg2.getX()-5);
                    if(bg2.getX() < -bg2.getWidth()){
                        bg2.setX(bg1.getX()+bg1.getWidth()-5);
                    }
                }

                if(OptionsStage.difficulty == 2){
                    bg2.setX(bg2.getX()-10);
                    if(bg2.getX() < -bg2.getWidth()){
                        bg2.setX(bg1.getX()+bg1.getWidth()-10);
                    }
                }

                if(OptionsStage.difficulty == 3){
                    bg2.setX(bg2.getX()-15);
                    if(bg2.getX() < -bg2.getWidth()){
                        bg2.setX(bg1.getX()+bg1.getWidth()-15);
                    }
                }
            }
        };

        zsolti = new Zsolti();
        if(backFromPause){
            zsolti.setRotation(zsoltiR);
            zsolti.setPosition(250,zsoltiY);
            if(zsoltiY > 30 && zsoltiR > 0)Zsolti.jump = true; //ekkor ugrik felfelé
        }
        else{
            zsolti.setPosition(250,30);
        }

        jumpIcon = new JumpIcon();
        jumpIcon.setPosition(viewport.getWorldWidth() - jumpIcon.getWidth() * 1.1f,15);

        tank = new Tank();
        tank.setSize(240,240);

        if(backFromPause){
            tank.setY(tankY);
            tank.setX(tankX);
        }
        else{
            tank.setY(-40);
            tank.setX(2400);
        }

        marancsics = new Marancsics();
        marancsics.setPosition(60,30);

        scoreLabel.setFontScale(1.5f);
        scoreLabel.setPosition(viewport.getWorldWidth()/2 - scoreLabel.getWidth()/2,viewport.getWorldHeight() - scoreLabel.getHeight()*1.5f);

        pauseButton = new PauseButton();
        pauseButton.setPosition(viewport.getWorldWidth() - pauseButton.getWidth() * 1.1f,545);

        bg1.setX(0);
        bg2.setX(bg1.getWidth());

        addActor(bg1);
        addActor(bg2);
        addActor(zsolti);
        addActor(marancsics);
        addActor(tank);
        addActor(scoreLabel);
        addActor(jumpIcon);
        addActor(pauseButton);
    }

    @Override
    public void init() {

    }
}
