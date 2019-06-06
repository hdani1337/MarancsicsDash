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
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.TextBackground;
import hu.hdani1337.marancsicsDash.Screen.HomeScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;
import static hu.hdani1337.marancsicsDash.Stage.ShopStage.boughtSiberia;
import static hu.hdani1337.marancsicsDash.Stage.ShopStage.boughtZala;

public class OptionsStage extends MyStage {
    public static final Preferences preferences = Gdx.app.getPreferences("marancsicsDashSave");
    public static int difficulty = preferences.getInteger("difficulty");;
    public static int gamemode = preferences.getInteger("gamemode");
    public static int selectedBackground = preferences.getInteger("selectedBackground");

    Background background;
    MyButton back = new MyButton("Vissza a menübe",game.getButtonStyle());//vissza gomb

    MyButton difType = new MyButton("",game.getButtonStyle()); //Könnyű/Normál/Nehéz
    MyLabel dif = new MyLabel("Nehézség: ",game.getLabelStyle()); //Nehézség:

    MyButton mutedButton = new MyButton("",game.getButtonStyle());//Nincs némítva/Némítva
    MyLabel muting = new MyLabel("Némítás: ",game.getLabelStyle());//Némítás:

    MyLabel mode = new MyLabel("Játékmód: ",game.getLabelStyle());//Játékmód:
    MyButton modeType = new MyButton("",game.getButtonStyle());//Story/Endless

    MyLabel backgroundText = new MyLabel("Háttér: ",game.getLabelStyle());//Háttér:
    MyButton backgroundType = new MyButton("",game.getButtonStyle());//Csernobil/Szibéria/Zala

    TextBackground textbg = new TextBackground();//nehézség háttere
    TextBackground textbg2 = new TextBackground();//némítás háttere
    TextBackground textbg3 = new TextBackground();;//visszalépés háttere
    TextBackground textbg4 = new TextBackground();;//játékmód háttere
    TextBackground textbg5 = new TextBackground();;//háttér háttere

    Music music = Assets.manager.get(Assets.MENUMUSIC);;//zene, hogy lelehessen állítani

    public OptionsStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        muted = preferences.getBoolean("muted");

        if(difficulty != 1 && difficulty != 2 && difficulty !=3){//ha nincs elmentve nehézség, akkor legyen normál
            difficulty = 2;
        }

        if(selectedBackground != 2 && selectedBackground != 1) selectedBackground = 0;

        if(gamemode != 1 && gamemode != 2) gamemode = 1;//ugyanez a játékmóddal is

        background = new Background(Assets.manager.get(Assets.MENU_BG),viewport);

        addListeners();
        setPositionsAndSizes(viewport);
        addActors();
    }

    void addListeners()
    {
        difType.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(difficulty == 3){
                    difficulty = 1;
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
                preferences.putInteger("gamemode",gamemode);
                preferences.putInteger("selectedBackground",selectedBackground);
                preferences.putBoolean("muted",muted);
                preferences.flush();
                game.setScreenBackByStackPop();
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

        modeType.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(gamemode == 1){
                    gamemode = 2;
                }
                else{
                    gamemode = 1;
                }
            }
        });

        backgroundType.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println(selectedBackground);
                if(selectedBackground == 2 && (boughtSiberia || boughtZala)){
                    selectedBackground = 0;
                }
                else if(selectedBackground == 1 && boughtSiberia && !boughtZala){
                    selectedBackground = 0;
                }
                else{
                    selectedBackground++;
                    if(selectedBackground == 1)
                    {
                        if(boughtZala && !boughtSiberia) selectedBackground = 2;
                    }
                }

            }
        });
    }

    void setPositionsAndSizes(Viewport viewport)
    {
        dif.setPosition(30,viewport.getWorldHeight() - viewport.getWorldHeight() / 3);
        textbg.setPosition(dif.getX() - 20,dif.getY() - 8);
        textbg.setSize(400,dif.getHeight()*1.5f);
        difType.setPosition(dif.getX() + 260,dif.getY());
        back.setPosition(viewport.getWorldWidth() - (back.getWidth() + 25),50);
        textbg2.setPosition(textbg.getX(),textbg.getY() - 100);
        textbg2.setWidth(textbg.getWidth());
        textbg2.setHeight(textbg.getHeight());
        textbg3.setWidth(290);
        textbg3.setHeight(textbg.getHeight());
        textbg3.setPosition(back.getX()-15,back.getY()-10);
        muting.setPosition(textbg2.getX() + 18,textbg2.getY() + textbg2.getHeight()/6);
        mutedButton.setPosition(muting.getX() + 250,muting.getY());
        textbg4.setPosition(textbg2.getX(), textbg2.getY() - 100);
        textbg4.setWidth(textbg.getWidth());
        textbg4.setHeight(textbg.getHeight());
        mode.setPosition(textbg4.getX() + 18,textbg4.getY() + textbg4.getHeight()/6);
        modeType.setPosition(mode.getX() + mode.getWidth()*1.55f, mode.getY());
        textbg5.setWidth(textbg.getWidth());
        textbg5.setHeight(textbg.getHeight());
        textbg5.setPosition(textbg4.getX(), textbg4.getY() - 100);
        backgroundText.setPosition(textbg5.getX() + 18,textbg5.getY() + textbg5.getHeight()/6);
        backgroundType.setPosition(backgroundText.getX() + backgroundText.getWidth()*2f, backgroundText.getY());
    }

    void addActors()
    {
        addActor(background);
        addActor(textbg);
        addActor(dif);
        addActor(difType);
        addActor(textbg3);
        addActor(back);
        addActor(textbg2);
        addActor(muting);
        addActor(mutedButton);
        addActor(textbg4);
        addActor(mode);
        addActor(modeType);
        if(boughtSiberia || boughtZala)
        {
            addActor(textbg5);
            addActor(backgroundText);
            addActor(backgroundType);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //Némítás
        if(muted){
            mutedButton.setText("Némítva");
            music.stop();
        }
        else{
            mutedButton.setText("Nincs némítva");
        }

        //Nehézségek
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

        //Játékmód
        if(gamemode == 1){
            modeType.setText("Story");
        }
        else{
            modeType.setText("Endless");
        }

        //Háttér
        if(selectedBackground == 0){
            backgroundType.setText("Csernobil");
        }
        else if (selectedBackground == 1){
            backgroundType.setText("Szibéria");
        }
        else if (selectedBackground == 2){
            backgroundType.setText("Zala");
        }
    }
}
