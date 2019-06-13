package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.MarancsicsBoss;
import hu.hdani1337.marancsicsDash.Actor.Tank;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.TextBackground;
import hu.hdani1337.marancsicsDash.Screen.GameScreen;
import hu.hdani1337.marancsicsDash.Screen.HomeScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.preferences;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.selectedBackground;

public class CrashStage extends MyStage {
    int highscore = preferences.getInteger("highscore");;

    Background bg;
    MyLabel text = new MyLabel("Vesztettél!",game.getLabelStyle());;
    MyLabel score = new MyLabel("Elért pontszám: "+Tank.pontszam+ "\nRekord: " + highscore,game.getLabelStyle());
    MyButton reset = new MyButton("Új játék",game.getButtonStyle());
    MyButton home = new MyButton("Fömenü",game.getButtonStyle());
    TextBackground textBG = new TextBackground();
    TextBackground resetBG = new TextBackground();
    TextBackground homeBG = new TextBackground();
    TextBackground scoreBG = new TextBackground();

    public CrashStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);

        Tank.pontszam = 0;
        MarancsicsBoss.marancsicsHealth = 99.9f;

        setBackground(viewport);
        record();
        setPositionsAndSizes(viewport);
        addListeners();
        addActors();
    }

    void setBackground(Viewport viewport)
    {
        if(selectedBackground == 0)
        {
            bg = new Background(Assets.manager.get(Assets.GAME_BG), viewport);
        }

        else if(selectedBackground == 1)
        {
            bg = new Background(Assets.manager.get(Assets.GAME_BG2), viewport);;
        }

        else if(selectedBackground == 2)
        {
            bg = new Background(Assets.manager.get(Assets.GAME_BG3), viewport);
        }

        else if(selectedBackground == 3)
        {
            bg = new Background(Assets.manager.get(Assets.GAME_BG4), viewport);
        }

        else if(selectedBackground == 4)
        {
            bg = new Background(Assets.manager.get(Assets.GAME_BG5), viewport);
        }
    }

    void record()
    {
        if(Tank.pontszam > highscore){
            preferences.putInteger("highscore",Tank.pontszam);
            preferences.flush();
            score.setText("Elért pontszám: "+Tank.pontszam+ "\nRekordot döntöttél!");
        }
    }

    void setPositionsAndSizes(Viewport viewport)
    {
        score.setAlignment(0);
        text.setPosition(viewport.getWorldWidth()/2-text.getWidth(),viewport.getWorldHeight()/2+text.getHeight()+150);
        text.setFontScale(2);
        textBG.setPosition(text.getX()-25,text.getY()-25);
        textBG.setHeight(90);
        textBG.setWidth(420);

        reset.setPosition(viewport.getWorldWidth()/2-reset.getWidth()/2,text.getY()-reset.getHeight()*3-120);
        resetBG.setPosition(reset.getX() - 18,reset.getY()-10);
        resetBG.setHeight(60);
        resetBG.setWidth(reset.getWidth() + 36);

        home.setPosition(viewport.getWorldWidth()/2-home.getWidth()/2,reset.getY()-home.getHeight()*2);
        homeBG.setPosition(home.getX()-18,home.getY()-10);
        homeBG.setHeight(60);
        homeBG.setWidth(home.getWidth() + 36);

        score.setPosition(viewport.getWorldWidth()/2-score.getWidth()/2,text.getY()-score.getHeight()*1.9f);
        scoreBG.setHeight(120);
        scoreBG.setWidth(score.getWidth()+72);
        scoreBG.setPosition(score.getX()-36,score.getY()-20);
    }

    void addListeners()
    {
        ClickListener resetListener = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game));
            }
        };
        
        ClickListener homeListener = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new HomeScreen(game));
            }
        };

        resetBG.addListener(resetListener);
        reset.addListener(resetListener);

        homeBG.addListener(homeListener);
        home.addListener(homeListener);
    }

    void addActors()
    {
        addActor(bg);
        addActor(textBG);
        addActor(resetBG);
        addActor(homeBG);
        addActor(scoreBG);
        addActor(text);
        addActor(reset);
        addActor(home);
        addActor(score);
    }

    @Override
    public void init() {

    }
}
