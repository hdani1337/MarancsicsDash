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
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.Desert;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.InstantBoss;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.Left;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.Ocean;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.Right;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.Siberia;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.TextBackground;
import hu.hdani1337.marancsicsDash.ParentClasses.UI.Zala;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Stage.GameStage.ground;
import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.preferences;
import static hu.hdani1337.marancsicsDash.marancsicsGame.notch;

public class ShopStage extends MyStage {
    Background background;
    TextBackground textBackground = new TextBackground();
    TextBackground textBackground2 = new TextBackground();
    TextBackground textBackground3 = new TextBackground();
    TextBackground bgbg = new TextBackground();
    MyButton myButton = new MyButton("Vissza a menübe",game.getButtonStyle());
    MyButton purchase = new MyButton("Vásárlás",game.getButtonStyle());
    MyLabel myLabel = new MyLabel("Instant Boss\nÁr: 100",game.getLabelStyle());
    Coin coinLabel = new Coin(false);
    MyLabel coinLabelText;
    OneSpriteStaticActor logo;

    InstantBoss instantBoss = new InstantBoss();
    Siberia siberia = new Siberia();
    Zala zala = new Zala();
    Desert desert = new Desert();
    Ocean ocean = new Ocean();
    Zsolti superZS = new Zsolti();
    Zsolti doubleJump = new Zsolti();

    Left left = new Left();
    Right right = new Right();

    Sound paySound = Assets.manager.get(Assets.PAY);
    Sound noMoney = Assets.manager.get(Assets.ERROR);

    int itemID = 0;
    int speed = 2;

    public static boolean boughtInstantBoss = preferences.getBoolean("boughtInstantBoss");
    public static boolean boughtSiberia = preferences.getBoolean("boughtSiberia");
    public static boolean boughtZala = preferences.getBoolean("boughtZala");
    public static boolean boughtDesert = preferences.getBoolean("boughtDesert");
    public static boolean boughtOcean = preferences.getBoolean("boughtOcean");
    public static boolean boughtZsolti = preferences.getBoolean("boughtZsolti");
    public static boolean boughtDouble = preferences.getBoolean("boughtDouble");

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
            left.remove();
            siberia.remove();
            zala.remove();
            bgbg.remove();
            doubleJump.remove();
            superZS.remove();
            desert.remove();
            ocean.remove();
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
            doubleJump.remove();
            desert.remove();
            ocean.remove();
            addActor(bgbg);
            addActor(siberia);
            addActor(left);
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
            addActor(right);
            siberia.remove();
            superZS.remove();
            instantBoss.remove();
            doubleJump.remove();
            desert.remove();
            ocean.remove();
            addActor(bgbg);
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
            addActor(right);
            siberia.remove();
            superZS.remove();
            instantBoss.remove();
            doubleJump.remove();
            zala.remove();
            ocean.remove();
            addActor(bgbg);
            addActor(desert);
            if(boughtDesert)
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

        else if (itemID == 4){
            addActor(right);
            siberia.remove();
            superZS.remove();
            instantBoss.remove();
            doubleJump.remove();
            zala.remove();
            desert.remove();
            addActor(bgbg);
            addActor(ocean);
            if(boughtOcean)
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

        else if (itemID == 5){
            addActor(right);
            siberia.remove();
            zala.remove();
            bgbg.remove();
            instantBoss.remove();
            doubleJump.remove();
            desert.remove();
            ocean.remove();
            Zsolti.doThings = false;
            addActor(superZS);
            if(boughtZsolti)
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

        else if (itemID == 6){
            right.remove();
            siberia.remove();
            zala.remove();
            bgbg.remove();
            instantBoss.remove();
            superZS.remove();
            desert.remove();
            ocean.remove();
            doubleJump = new Zsolti();
            Zsolti.doThings = true;
            doubleJump.setPosition(getViewport().getWorldWidth()/2-superZS.getWidth()/2,getViewport().getWorldHeight()/2-superZS.getHeight()/2 + 25);
            addActor(doubleJump);
            if(boughtDouble)
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
            if (boughtDesert) myLabel.setText("Szahara\nMár megvetted!");
            else myLabel.setText("Szahara\nÁr: 200");
        }

        if(itemID == 4)
        {
            if (boughtOcean) myLabel.setText("Atlanti-óceán\nMár megvetted!");
            else myLabel.setText("Atlanti-óceán\nÁr: 200");
        }

        if(itemID == 5)
        {
            doubleJump.setPosition(getViewport().getWorldWidth()/2-superZS.getWidth()/2,getViewport().getWorldHeight()/2-superZS.getHeight()/2 + 25);
            Zsolti.jump = false;
            Zsolti.fall = false;
            Zsolti.forcejump = false;
            Zsolti.intro = false;
            Zsolti.doThings = false;
            Zsolti.superTime = 1337;
            if (boughtZsolti) myLabel.setText("Super Zsolti\nMár megvetted!");
            else myLabel.setText("Super Zsolti\nÁr: 250");
        }

        if(itemID == 6)
        {
            ground = (int)doubleJump.getY();
            Zsolti.jump = true;
            Zsolti.fall = false;
            Zsolti.forcejump = false;
            Zsolti.intro = false;
            Zsolti.doThings = true;
            Zsolti.superTime = 0;
            if (boughtDouble) myLabel.setText("Double Jump\nMár megvetted!");
            else myLabel.setText("Double Jump\nÁr: 250");
        }
    }

    void addListeners()
    {
        right.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (itemID == 6) {
                    itemID = 6;
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
                            textBackground3.remove();
                            purchase.remove();
                            boughtInstantBoss = true;
                            preferences.putLong("coin", Coin.coin);
                            preferences.putBoolean("boughtInstantBoss", boughtInstantBoss);
                            preferences.flush();
                        }

                        else noMoney.play();
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
                            textBackground3.remove();
                            purchase.remove();
                            preferences.putLong("coin", Coin.coin);
                            preferences.putBoolean("boughtSiberia", boughtSiberia);
                            preferences.flush();
                        }

                        else noMoney.play();
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
                            textBackground3.remove();
                            purchase.remove();
                            preferences.putLong("coin", Coin.coin);
                            preferences.putBoolean("boughtZala", boughtZala);
                            preferences.flush();
                        }

                        else noMoney.play();
                    }
                }

                else if (itemID == 3)
                {
                    if(!boughtDesert) {
                        if (Coin.coin >= 200) {
                            if (!muted) paySound.play();
                            Coin.coin -= 200;
                            coinLabelText.setText(""+Coin.coin);
                            myLabel.setText("Szahara\nMár megvetted!");
                            boughtDesert = true;
                            textBackground3.remove();
                            purchase.remove();
                            preferences.putLong("coin", Coin.coin);
                            preferences.putBoolean("boughtDesert", boughtDesert);
                            preferences.flush();
                        }

                        else noMoney.play();
                    }
                }

                else if (itemID == 4)
                {
                    if(!boughtOcean) {
                        if (Coin.coin >= 200) {
                            if (!muted) paySound.play();
                            Coin.coin -= 200;
                            coinLabelText.setText(""+Coin.coin);
                            myLabel.setText("Atlanti-óceán\nMár megvetted!");
                            boughtOcean = true;
                            textBackground3.remove();
                            purchase.remove();
                            preferences.putLong("coin", Coin.coin);
                            preferences.putBoolean("boughtOcean", boughtOcean);
                            preferences.flush();
                        }

                        else noMoney.play();
                    }
                }

                else if (itemID == 5)
                {
                    if(!boughtZsolti) {
                        if (Coin.coin >= 250) {
                            if (!muted) paySound.play();
                            Coin.coin -= 250;
                            coinLabelText.setText(""+Coin.coin);
                            myLabel.setText("Super Zsolti\nMár megvetted!");
                            boughtZsolti = true;
                            textBackground3.remove();
                            purchase.remove();
                            preferences.putLong("coin", Coin.coin);
                            preferences.putBoolean("boughtZsolti", boughtZsolti);
                            preferences.flush();
                        }

                        else noMoney.play();
                    }
                }

                else if (itemID == 6)
                {
                    if(!boughtDouble) {
                        if (Coin.coin >= 250) {
                            if (!muted) paySound.play();
                            Coin.coin -= 250;
                            coinLabelText.setText(""+Coin.coin);
                            myLabel.setText("Double Jump\nMár megvetted!");
                            boughtDouble = true;
                            textBackground3.remove();
                            purchase.remove();
                            preferences.putLong("coin", Coin.coin);
                            preferences.putBoolean("boughtDouble", boughtDouble);
                            preferences.flush();
                        }

                        else noMoney.play();
                    }
                }
            }
        });

        ClickListener backListener = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                preferences.putLong("coin", Coin.coin);
                preferences.putBoolean("boughtInstantBoss", boughtInstantBoss);
                preferences.putBoolean("boughtSiberia", boughtSiberia);
                preferences.putBoolean("boughtZala", boughtZala);
                preferences.putBoolean("boughtDesert", boughtDesert);
                preferences.putBoolean("boughtOcean", boughtOcean);
                preferences.putBoolean("boughtZsolti", boughtZsolti);
                preferences.putBoolean("boughtDouble", boughtDouble);
                preferences.flush();
                game.setScreenBackByStackPop();
            }
        };

        textBackground2.addListener(backListener);
        myButton.addListener(backListener);
    }

    void setSizesAndPositions(Viewport viewport)
    {
        instantBoss.setPosition(viewport.getWorldWidth()/2-instantBoss.getWidth()/2,viewport.getWorldHeight()/2-instantBoss.getHeight()/2);
        siberia.setPosition(viewport.getWorldWidth()/2-siberia.getWidth()/2,viewport.getWorldHeight()/2-siberia.getHeight()/2 + 50);
        zala.setPosition(viewport.getWorldWidth()/2-zala.getWidth()/2,viewport.getWorldHeight()/2-zala.getHeight()/2 + 50);
        ocean.setPosition(viewport.getWorldWidth()/2-zala.getWidth()/2,viewport.getWorldHeight()/2-zala.getHeight()/2 + 50);
        desert.setPosition(viewport.getWorldWidth()/2-zala.getWidth()/2,viewport.getWorldHeight()/2-zala.getHeight()/2 + 50);
        superZS.setPosition(viewport.getWorldWidth()/2-superZS.getWidth()/2,viewport.getWorldHeight()/2-superZS.getHeight()/2 + 25);
        doubleJump.setPosition(viewport.getWorldWidth()/2-superZS.getWidth()/2,viewport.getWorldHeight()/2-superZS.getHeight()/2 + 25);

        bgbg.setSize(siberia.getWidth() + 16, siberia.getHeight() + 18);
        bgbg.setPosition(siberia.getX() - 8, siberia.getY() - 10);

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

        if (!notch) myButton.setPosition(viewport.getWorldWidth() - (myButton.getWidth() + 25),50);
        else myButton.setPosition(viewport.getWorldWidth() - (myButton.getWidth() + 45),50);
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
