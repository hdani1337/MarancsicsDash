package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.Coin;
import hu.hdani1337.marancsicsDash.Actor.Zsolti;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.InstantBoss;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.Left;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.Right;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.Siberia;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.TextBackground;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.Zala;
import hu.hdani1337.marancsicsDash.Screen.HomeScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.preferences;

public class ShopStage extends MyStage {
    Background background;
    TextBackground textBackground = new TextBackground();
    TextBackground textBackground2 = new TextBackground();
    TextBackground textBackground3 = new TextBackground();
    MyButton myButton = new MyButton("Vissza a menübe",game.getButtonStyle());
    MyButton purchase = new MyButton("Vásárlás",game.getButtonStyle());
    MyLabel myLabel = new MyLabel("Instant Boss\nÁr: 100",game.getLabelStyle());
    Coin coinLabel = new Coin(false);
    MyLabel coinLabelText;
    OneSpriteStaticActor logo;

    InstantBoss instantBoss = new InstantBoss();
    Siberia siberia = new Siberia();
    Zala zala = new Zala();
    Zsolti superZS = new Zsolti(Assets.manager.get(Assets.SUPERZSOLTI));

    Left left = new Left();
    Right right = new Right();

    Sound paySound = Assets.manager.get(Assets.PAY);

    int itemID = 0;
    int speed = 2;

    public static boolean boughtInstantBoss = preferences.getBoolean("boughtInstantBoss");
    public static boolean boughtSiberia = preferences.getBoolean("boughtSiberia");
    public static boolean boughtZala = preferences.getBoolean("boughtZala");
    public static boolean boughtZsolti = preferences.getBoolean("boughtZsolti");

    public ShopStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        background = new Background(Assets.manager.get(Assets.MENU_BG),viewport);

        logo = new OneSpriteStaticActor(Assets.manager.get(Assets.SHOP_LOGO)){
            @Override
            public void act(float delta) {
                super.act(delta);
                setRotation(getRotation() + delta * speed);

                if(getRotation() >= 12 || getRotation() <= -12){
                    speed *= -1;
                }

                setDebug(false);
            }
        };

        setTexts();
        addListeners();
        setSizesAndPositions(viewport);
        addActors();
    }

    void setActor()
    {
        if (itemID == 0){
            siberia.remove();
            zala.remove();
            superZS.remove();
            addActor(instantBoss);
            if(boughtInstantBoss)
            {
                purchase.remove();
                textBackground3.remove();
            }
            else
            {
                addActor(textBackground3);
                addActor(purchase);
            }
        }

        else if (itemID == 1){
            instantBoss.remove();
            zala.remove();
            superZS.remove();
            addActor(siberia);
            if(boughtSiberia)
            {
                purchase.remove();
                textBackground3.remove();
            }
            else
            {
                addActor(textBackground3);
                addActor(purchase);
            }
        }

        else if (itemID == 2){
            siberia.remove();
            superZS.remove();
            instantBoss.remove();
            addActor(zala);
            if(boughtZala)
            {
                purchase.remove();
                textBackground3.remove();
            }
            else
            {
                addActor(textBackground3);
                addActor(purchase);
            }
        }

        else if (itemID == 3){
            siberia.remove();
            zala.remove();
            instantBoss.remove();
            addActor(superZS);
            if(boughtZala)
            {
                purchase.remove();
                textBackground3.remove();
            }
            else
            {
                addActor(textBackground3);
                addActor(purchase);
            }
        }
    }

    void setTexts()
    {
        if(Coin.coin >= 0) coinLabelText = new MyLabel(""+Coin.coin,game.getLabelStyle());
        else coinLabelText = new MyLabel("0",game.getLabelStyle());

        if(itemID == 0)
        {
            if (boughtInstantBoss) myLabel.setText("Instant Boss\nMár megvetted!");
            else myLabel.setText("Instant Boss\nÁr: 100");
        }

        if(itemID == 1)
        {
            if (boughtSiberia) myLabel.setText("Szibéria\nMár megvetted!");
            else myLabel.setText("Szibéria\nÁr: 200");
        }

        if(itemID == 2)
        {
            if (boughtZala) myLabel.setText("Zala\nMár megvetted!");
            else myLabel.setText("Zala\nÁr: 200");
        }

        if(itemID == 3)
        {
            Zsolti.jump = false;
            Zsolti.fall = false;
            Zsolti.forcejump = false;
            Zsolti.intro = false;
            if (boughtZala) myLabel.setText("Super Zsolti\nMár megvetted!");
            else myLabel.setText("Super Zsolti\nÁr: 250");
        }
    }

    void addListeners()
    {
        right.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (itemID == 3) {
                    itemID = 3;
                } else {
                    itemID++;
                }
                setTexts();
                setActor();
            }
        });

        left.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (itemID == 0) {
                    itemID = 0;
                } else {
                    itemID--;
                }
                setTexts();
                setActor();
            }
        });

        purchase.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (itemID == 0)
                {
                    if(!boughtInstantBoss) {
                        if (Coin.coin >= 100) {
                            if (!muted) paySound.play();
                            Coin.coin -= 100;
                            coinLabelText.setText(""+Coin.coin);
                            myLabel.setText("Instant Boss\nMár megvetted!");
                            boughtInstantBoss = true;
                            preferences.putLong("coin", Coin.coin);
                            preferences.putBoolean("boughtInstantBoss", boughtInstantBoss);
                            preferences.flush();
                        }
                    }
                }

                else if (itemID == 1)
                {
                    if(!boughtSiberia) {
                        if (Coin.coin >= 200) {
                            if (!muted) paySound.play();
                            Coin.coin -= 200;
                            coinLabelText.setText(""+Coin.coin);
                            myLabel.setText("Szibéria\nMár megvetted!");
                            boughtSiberia = true;
                            preferences.putLong("coin", Coin.coin);
                            preferences.putBoolean("boughtSiberia", boughtSiberia);
                            preferences.flush();
                        }
                    }
                }

                else if (itemID == 2)
                {
                    if(!boughtZala) {
                        if (Coin.coin >= 200) {
                            if (!muted) paySound.play();
                            Coin.coin -= 200;
                            coinLabelText.setText(""+Coin.coin);
                            myLabel.setText("Zala\nMár megvetted!");
                            boughtZala = true;
                            preferences.putLong("coin", Coin.coin);
                            preferences.putBoolean("boughtZala", boughtZala);
                            preferences.flush();
                        }
                    }
                }

                else if (itemID == 3)
                {
                    if(!boughtSiberia) {
                        if (Coin.coin >= 250) {
                            if (!muted) paySound.play();
                            Coin.coin -= 250;
                            coinLabelText.setText(""+Coin.coin);
                            myLabel.setText("Super Zsolti\nMár megvetted!");
                            boughtZsolti = true;
                            preferences.putLong("coin", Coin.coin);
                            preferences.putBoolean("boughtZsolti", boughtZsolti);
                            preferences.flush();
                        }
                    }
                }
            }
        });

        myButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                preferences.putLong("coin", Coin.coin);
                preferences.putBoolean("boughtInstantBoss", boughtInstantBoss);
                preferences.putBoolean("boughtSiberia", boughtSiberia);
                preferences.putBoolean("boughtZala", boughtZala);
                preferences.putBoolean("boughtZsolti", boughtZsolti);
                preferences.flush();
                game.setScreen(new HomeScreen(game));
            }
        });
    }

    void setSizesAndPositions(Viewport viewport)
    {
        instantBoss.setPosition(viewport.getWorldWidth()/2-instantBoss.getWidth()/2,viewport.getWorldHeight()/2-instantBoss.getHeight()/2);
        siberia.setPosition(viewport.getWorldWidth()/2-siberia.getWidth()/2,viewport.getWorldHeight()/2-siberia.getHeight()/2 + 50);
        zala.setPosition(viewport.getWorldWidth()/2-zala.getWidth()/2,viewport.getWorldHeight()/2-zala.getHeight()/2 + 50);
        superZS.setPosition(viewport.getWorldWidth()/2-superZS.getWidth()/2,viewport.getWorldHeight()/2-superZS.getHeight()/2 + 25);

        coinLabel.setPosition(15, viewport.getWorldHeight()-15-coinLabel.getHeight());
        coinLabelText.setPosition(coinLabel.getX() + coinLabel.getWidth() + 10, coinLabel.getY() + coinLabel.getHeight()/4);

        textBackground.setSize(myLabel.getWidth() + 48,myLabel.getHeight()+24);
        textBackground2.setSize(myButton.getWidth() + 30,myButton.getHeight()+16);
        textBackground3.setSize(purchase.getWidth() + 30,purchase.getHeight()+16);

        textBackground.setPosition(viewport.getWorldWidth()/2-textBackground.getWidth()/2,siberia.getY() - 150);
        myLabel.setPosition(textBackground.getX() + 24,textBackground.getY() + 12);
        myLabel.setAlignment(0);

        left.setSize(120,120);
        right.setSize(120,120);

        left.setPosition(textBackground.getX() - left.getWidth() - 30,textBackground.getY());
        right.setPosition(textBackground.getX() + textBackground.getWidth() + 30,textBackground.getY());

        myButton.setPosition(viewport.getWorldWidth() - (myButton.getWidth() + 25),50);
        textBackground2.setPosition(myButton.getX() - 15,myButton.getY() - 8);

        purchase.setPosition(viewport.getWorldWidth()/2-purchase.getWidth()/2,myLabel.getY() - 90);
        textBackground3.setPosition(purchase.getX() - 15, purchase.getY() - 8);

        logo.setWidthWhithAspectRatio(520);
        logo.setPosition(viewport.getWorldWidth()/2 - logo.getWidth()/2, viewport.getWorldHeight() - logo.getHeight()*1.8f);
    }

    void addActors()
    {
        addActor(background);
        addActor(textBackground);
        addActor(textBackground2);
        addActor(myLabel);
        addActor(myButton);
        addActor(coinLabel);
        addActor(coinLabelText);
        addActor(left);
        addActor(right);
        addActor(logo);
        setActor();
    }

    @Override
    public void init() {

    }
}
