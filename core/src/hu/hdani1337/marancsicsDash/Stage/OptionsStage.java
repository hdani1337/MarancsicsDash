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
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.TextBackground;
import hu.hdani1337.marancsicsDash.Screen.HomeScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;
import static hu.hdani1337.marancsicsDash.Stage.ShopStage.boughtDesert;
import static hu.hdani1337.marancsicsDash.Stage.ShopStage.boughtOcean;
import static hu.hdani1337.marancsicsDash.Stage.ShopStage.boughtSiberia;
import static hu.hdani1337.marancsicsDash.Stage.ShopStage.boughtZala;
import static hu.hdani1337.marancsicsDash.marancsicsGame.notch;

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

    TextBackground difficultyBG = new TextBackground();//nehézség háttere
    TextBackground muteBG = new TextBackground();//némítás háttere
    TextBackground backBG = new TextBackground();;//visszalépés háttere
    TextBackground gamemodeBG = new TextBackground();;//játékmód háttere
    TextBackground backgroundBG = new TextBackground();;//háttér háttere

    Music music = Assets.manager.get(Assets.MENUMUSIC);;//zene, hogy lelehessen állítani

    public OptionsStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        muted = preferences.getBoolean("muted");

        if(difficulty != 1 && difficulty != 2 && difficulty !=3){//ha nincs elmentve nehézség, akkor legyen normál
            difficulty = 2;
        }

        if(selectedBackground != 2 && selectedBackground != 1 && selectedBackground != 3 && selectedBackground != 4) selectedBackground = 0;

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
        
        ClickListener backListener = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                preferences.putInteger("difficulty",difficulty);
                preferences.putInteger("gamemode",gamemode);
                preferences.putInteger("selectedBackground",selectedBackground);
                preferences.putBoolean("muted",muted);
                preferences.flush();
                setBack = true;
            }
        };

        backBG.addListener(backListener);
        back.addListener(backListener);

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

                if(selectedBackground < 4) selectedBackground++;
                else selectedBackground = 0;

                if(selectedBackground == 1 && !boughtSiberia) selectedBackground = 2;
                if(selectedBackground == 2 && !boughtZala) selectedBackground = 3;
                if(selectedBackground == 3 && !boughtDesert) selectedBackground = 4;
                if(selectedBackground == 4 && !boughtOcean) selectedBackground = 0;
            }
        });
    }

    void setPositionsAndSizes(Viewport viewport)
    {
        if(marancsicsGame.notch) dif.setPosition(75,viewport.getWorldHeight() - viewport.getWorldHeight() / 3);
        else dif.setPosition(30,viewport.getWorldHeight() - viewport.getWorldHeight() / 3);
        difficultyBG.setPosition(dif.getX() - 20,dif.getY() - 8);
        difficultyBG.setSize(400,dif.getHeight()*1.5f);
        difType.setPosition(dif.getX() + 260,dif.getY());
        if (!notch) back.setPosition(viewport.getWorldWidth() - (back.getWidth() + 25),50);
        else back.setPosition(viewport.getWorldWidth() - (back.getWidth() + 45),50);
        muteBG.setPosition(difficultyBG.getX(),difficultyBG.getY() - 100);
        muteBG.setWidth(difficultyBG.getWidth());
        muteBG.setHeight(difficultyBG.getHeight());
        backBG.setWidth(290);
        backBG.setHeight(difficultyBG.getHeight());
        backBG.setPosition(back.getX()-15,back.getY()-10);
        muting.setPosition(muteBG.getX() + 18,muteBG.getY() + muteBG.getHeight()/6);
        mutedButton.setPosition(muting.getX() + 250,muting.getY());
        gamemodeBG.setPosition(muteBG.getX(), muteBG.getY() - 100);
        gamemodeBG.setWidth(difficultyBG.getWidth());
        gamemodeBG.setHeight(difficultyBG.getHeight());
        mode.setPosition(gamemodeBG.getX() + 18,gamemodeBG.getY() + gamemodeBG.getHeight()/6);
        modeType.setPosition(mode.getX() + mode.getWidth()*1.55f, mode.getY());
        backgroundBG.setWidth(difficultyBG.getWidth());
        backgroundBG.setHeight(difficultyBG.getHeight());
        backgroundBG.setPosition(gamemodeBG.getX(), gamemodeBG.getY() - 100);
        backgroundText.setPosition(backgroundBG.getX() + 18,backgroundBG.getY() + backgroundBG.getHeight()/6);
        backgroundType.setPosition(backgroundText.getX() + backgroundText.getWidth()*2f, backgroundText.getY());
    }

    void addActors()
    {
        addActor(background);
        addActor(difficultyBG);
        addActor(dif);
        addActor(difType);
        addActor(backBG);
        addActor(back);
        addActor(muteBG);
        addActor(muting);
        addActor(mutedButton);
        addActor(gamemodeBG);
        addActor(mode);
        addActor(modeType);
        if(boughtSiberia || boughtZala)
        {
            addActor(backgroundBG);
            addActor(backgroundText);
            addActor(backgroundType);
        }
    }

    @Override
    public void init() {

    }

    float alpha = 0;
    boolean setBack = false;

    @Override
    public void act(float delta) {
        super.act(delta);
        if(!setBack) {
            if (alpha < 0.99) {
                difficultyBG.setColor(1, 1, 1, alpha);
                dif.setColor(1, 1, 1, alpha);
                difType.setColor(1, 1, 1, alpha);
                backBG.setColor(1, 1, 1, alpha);
                back.setColor(1, 1, 1, alpha);
                muteBG.setColor(1, 1, 1, alpha);
                muting.setColor(1, 1, 1, alpha);
                mutedButton.setColor(1, 1, 1, alpha);
                gamemodeBG.setColor(1, 1, 1, alpha);
                mode.setColor(1, 1, 1, alpha);
                modeType.setColor(1, 1, 1, alpha);
                backgroundBG.setColor(1, 1, 1, alpha);
                backgroundText.setColor(1, 1, 1, alpha);
                backgroundType.setColor(1, 1, 1, alpha);
                alpha += 0.02;
            } else alpha = 1;
        }
        else
        {
            if (alpha > 0.01) {
                difficultyBG.setColor(1, 1, 1, alpha);
                dif.setColor(1, 1, 1, alpha);
                difType.setColor(1, 1, 1, alpha);
                backBG.setColor(1, 1, 1, alpha);
                back.setColor(1, 1, 1, alpha);
                muteBG.setColor(1, 1, 1, alpha);
                muting.setColor(1, 1, 1, alpha);
                mutedButton.setColor(1, 1, 1, alpha);
                gamemodeBG.setColor(1, 1, 1, alpha);
                mode.setColor(1, 1, 1, alpha);
                modeType.setColor(1, 1, 1, alpha);
                backgroundBG.setColor(1, 1, 1, alpha);
                backgroundText.setColor(1, 1, 1, alpha);
                backgroundType.setColor(1, 1, 1, alpha);
                alpha -= 0.02;
            } else {
                alpha = 0;
                HomeScreen.setWhatToDraw("home");
                setBack = false;
            }
        }

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
        else if (selectedBackground == 3){
            backgroundType.setText("Szahara");
        }
        else if (selectedBackground == 4){
            backgroundType.setText("Atlanti-óceán");
        }
    }
}
