package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.Tank;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.TextBackground;
import hu.hdani1337.marancsicsDash.Screen.GameScreen;
import hu.hdani1337.marancsicsDash.Screen.HomeScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.preferences;

public class CrashStage extends MyStage {
    int highscore = preferences.getInteger("highscore");;

    Background bg;
    MyLabel text;
    MyLabel score;
    MyButton reset;
    MyButton home;
    TextBackground text1;
    TextBackground text2;
    TextBackground text3;
    TextBackground text4;

    public CrashStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        bg = new Background(Assets.manager.get(Assets.GAME_BG));
        text = new MyLabel("Vesztettél!",game.getLabelStyle());
        reset = new MyButton("Új játék",game.getButtonStyle());
        home = new MyButton("Fömenü",game.getButtonStyle());

        score = new MyLabel("Elért pontszám: "+Tank.pontszam+ "\nRekord: " + highscore,game.getLabelStyle());
        score.setAlignment(0);

        if(Tank.pontszam > highscore){
            preferences.putInteger("highscore",Tank.pontszam);
            preferences.flush();
            score.setText("Elért pontszám: "+Tank.pontszam+ "\nRekordot döntöttél!");
        }

        Tank.pontszam = 0;

        text1 = new TextBackground();
        text2 = new TextBackground();
        text3 = new TextBackground();
        text4 = new TextBackground();

        text.setPosition(viewport.getWorldWidth()/2-text.getWidth(),viewport.getWorldHeight()/2+text.getHeight()+150);
        text.setFontScale(2);
        text1.setPosition(text.getX()-25,text.getY()-25);
        text1.setHeight(90);
        text1.setWidth(420);

        reset.setPosition(viewport.getWorldWidth()/2-reset.getWidth()/2,text.getY()-reset.getHeight()*3-120);
        text2.setPosition(reset.getX() - 18,reset.getY()-10);
        text2.setHeight(60);
        text2.setWidth(reset.getWidth() + 36);

        home.setPosition(viewport.getWorldWidth()/2-home.getWidth()/2,reset.getY()-home.getHeight()*2);
        text3.setPosition(home.getX()-18,home.getY()-10);
        text3.setHeight(60);
        text3.setWidth(home.getWidth() + 36);

        score.setPosition(viewport.getWorldWidth()/2-score.getWidth()/2,text.getY()-score.getHeight()*1.9f);
        text4.setHeight(120);
        text4.setWidth(score.getWidth()+72);
        text4.setPosition(score.getX()-36,score.getY()-20);

        reset.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game,0,0,0,0,false));
            }
        });

        home.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new HomeScreen(game));
            }
        });

        addActor(bg);
        addActor(text1);
        addActor(text2);
        addActor(text3);
        addActor(text4);
        addActor(text);
        addActor(reset);
        addActor(home);
        addActor(score);
    }

    @Override
    public void init() {

    }
}
