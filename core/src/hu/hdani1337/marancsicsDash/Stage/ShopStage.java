package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.Coin;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.InstantBoss;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.TextBackground;
import hu.hdani1337.marancsicsDash.Screen.HomeScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.Stage.HomeStage.muted;
import static hu.hdani1337.marancsicsDash.Stage.OptionsStage.preferences;

public class ShopStage extends MyStage {
    Background background;
    TextBackground textBackground;
    TextBackground textBackground2;
    MyButton myButton;
    MyLabel myLabel;
    Coin coinLabel;
    MyLabel coinLabelText;
    InstantBoss instantBoss;
    Sound paySound = Assets.manager.get(Assets.PAY);

    public static boolean boughtInstantBoss = preferences.getBoolean("boughtInstantBoss");

    public ShopStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        background = new Background(Assets.manager.get(Assets.MENU_BG));
        textBackground = new TextBackground();
        textBackground2 = new TextBackground();
        myLabel = new MyLabel("Instant Boss\nÁr: 100",game.getLabelStyle());
        myButton = new MyButton("Vissza a menübe",game.getButtonStyle());
        coinLabel = new Coin(false);
        if(Coin.coin >= 0) coinLabelText = new MyLabel(""+Coin.coin,game.getLabelStyle());
        else coinLabelText = new MyLabel("0",game.getLabelStyle());

        if(boughtInstantBoss) myLabel.setText("Instant Boss\nMár megvetted!");

        instantBoss = new InstantBoss();
        instantBoss.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
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
        });

        instantBoss.setPosition(viewport.getWorldWidth()/2-instantBoss.getWidth()/2,viewport.getWorldHeight()/2-instantBoss.getHeight()/2);

        coinLabel.setPosition(15, viewport.getWorldHeight()-15-coinLabel.getHeight());
        coinLabelText.setPosition(coinLabel.getX() + coinLabel.getWidth() + 10, coinLabel.getY() + coinLabel.getHeight()/4);

        textBackground.setSize(myLabel.getWidth() + 48,myLabel.getHeight()+24);
        textBackground2.setSize(myButton.getWidth() + 30,myButton.getHeight()+16);

        textBackground.setPosition(viewport.getWorldWidth()/2-textBackground.getWidth()/2,instantBoss.getY() - 120);
        myLabel.setPosition(textBackground.getX() + 24,textBackground.getY() + 12);
        myLabel.setAlignment(0);

        myButton.setPosition(viewport.getWorldWidth() - myButton.getWidth() - 15,15);
        textBackground2.setPosition(myButton.getX() - 15,myButton.getY() - 8);

        myButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new HomeScreen(game));
            }
        });

        addActor(background);
        addActor(textBackground);
        addActor(textBackground2);
        addActor(myLabel);
        addActor(myButton);
        addActor(coinLabel);
        addActor(coinLabelText);
        addActor(instantBoss);
    }

    @Override
    public void init() {

    }
}
