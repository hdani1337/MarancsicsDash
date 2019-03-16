package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.Screen.HomeScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;

public class OptionsStage extends MyStage {
    public static int difficulty;
    public static final Preferences preferences = Gdx.app.getPreferences("marancsicsDashSave");;

    Background background;
    MyButton difPlus;//plusz gomb
    MyButton difMinus;//minusz gomb
    MyButton back;//vissza gomb
    MyLabel difType; //Könnyű/Normál/Nehéz
    MyLabel dif; //Nehézség:
    MyLabel muting;//Némítás:
    MyButton mutedButton;//Nincs némítva/Némítva
    OneSpriteStaticActor textbg;//nehézség háttere
    OneSpriteStaticActor textbg2;//némítás háttere
    OneSpriteStaticActor textbg3;//visszalépés háttere
    Music music;//zene, hogy lelehessen állítani

    public OptionsStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        difficulty = preferences.getInteger("difficulty");
        muted = preferences.getBoolean("muted");

        System.out.println(difficulty);

        if(difficulty != 1 && difficulty != 2 && difficulty !=3){//ha nincs elmentve nehézség, akkor legyen normál
            difficulty = 2;
        }

        background = new Background(Assets.manager.get(Assets.MENU_BG));
        difPlus = new MyButton("+",game.getButtonStyle());
        difMinus = new MyButton("-",game.getButtonStyle());
        dif = new MyLabel("Nehézség: ",game.getLabelStyle());
        difType = new MyLabel("",game.getLabelStyle());
        back = new MyButton("Vissza a menübe",game.getButtonStyle());
        music = Assets.manager.get(Assets.MENUMUSIC);
        textbg = new OneSpriteStaticActor(Assets.manager.get(Assets.TEXT_BG)){
            @Override
            public void act(float delta) {
                super.act(delta);
                if(difficulty == 1){
                    difType.setText("Könnyü");
                }
                else if(difficulty == 2){
                    difType.setText("Normál");
                }
                else if(difficulty == 3){
                    difType.setText("Nehéz");
                }
                else{
                    System.out.println("A fejlesztő egy buzi.");
                    difType.setText("Hiba");
                }
            }
        };
        textbg.setDebug(false);

        muting = new MyLabel("Némítás: ",game.getLabelStyle());
        mutedButton = new MyButton("",game.getButtonStyle());

        textbg2 = new OneSpriteStaticActor(Assets.manager.get(Assets.TEXT_BG)){
            @Override
            public void act(float delta) {
                super.act(delta);
                if(muted){
                    mutedButton.setText("Némítva");
                    music.stop();
                }
                else{
                    mutedButton.setText("Nincs némítva");
                }
            }
        };

        textbg3 = new OneSpriteStaticActor(Assets.manager.get(Assets.TEXT_BG)){
            @Override
            public void setDebug(boolean enabled) {
                super.setDebug(false);
            }
        };

        textbg2.setDebug(false);

        difMinus.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(difficulty == 1){
                    difficulty = 1;
                }
                else{
                    difficulty--;
                }
            }
        });

        difPlus.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(difficulty == 3){
                    difficulty = 3;
                }
                else{
                    difficulty++;
                }
            }
        });

        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                preferences.putInteger("difficulty",difficulty);
                preferences.putBoolean("muted",muted);
                preferences.flush();
                game.setScreen(new HomeScreen(game));
            }
        });

        mutedButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(!muted){
                    muted = true;
                }
                else{
                    muted = false;
                }
            }
        });

        dif.setPosition(30,viewport.getWorldHeight() - viewport.getWorldHeight() / 3);
        textbg.setPosition(dif.getX() - 20,dif.getY() - 8);
        textbg.setSize(400,dif.getHeight()*1.5f);
        difMinus.setSize(difPlus.getWidth()*1.5f,difMinus.getHeight());
        difMinus.setPosition(dif.getX() + dif.getWidth() + 5,dif.getY());
        difType.setPosition(difMinus.getX() + difMinus.getWidth() + 10,dif.getY()+18);
        difPlus.setSize(difPlus.getWidth()*1.5f,difPlus.getHeight());
        difPlus.setPosition(difMinus.getX() + 155,dif.getY());
        back.setPosition(viewport.getWorldWidth() - (back.getWidth() + 25),125);
        textbg2.setPosition(textbg.getX(),textbg.getY() - 100);
        textbg2.setWidth(textbg.getWidth());
        textbg2.setHeight(textbg.getHeight());
        textbg3.setWidth(290);
        textbg3.setHeight(textbg.getHeight());
        textbg3.setPosition(back.getX()-15,back.getY()-10);
        muting.setPosition(textbg2.getX() + 18,textbg2.getY() + textbg2.getHeight()/6);
        mutedButton.setPosition(muting.getX() + 250,muting.getY());


        addActor(background);
        addActor(textbg);
        addActor(dif);
        addActor(difType);
        addActor(difPlus);
        addActor(difMinus);
        addActor(textbg3);
        addActor(back);
        addActor(textbg2);
        addActor(muting);
        addActor(mutedButton);
    }

    @Override
    public void init() {

    }
}
