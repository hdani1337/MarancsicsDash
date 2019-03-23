package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.Actor.Coin;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyScreen;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.TextBackground;
import hu.hdani1337.marancsicsDash.Screen.HomeScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class ShopStage extends MyStage {
    Background background;
    TextBackground textBackground;
    TextBackground textBackground2;
    MyButton myButton;
    MyLabel myLabel;
    Coin coinLabel;
    MyLabel coinLabelText;

    public ShopStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        background = new Background(Assets.manager.get(Assets.MENU_BG));
        textBackground = new TextBackground();
        textBackground2 = new TextBackground();
        myLabel = new MyLabel("Hamarosan!",game.getLabelStyle());
        myButton = new MyButton("Vissza a menübe",game.getButtonStyle());
        coinLabel = new Coin(false);
        if(Coin.coin >= 0) coinLabelText = new MyLabel(""+Coin.coin,game.getLabelStyle());
        else coinLabelText = new MyLabel("0",game.getLabelStyle());

        coinLabel.setPosition(15, Gdx.graphics.getHeight()-15-coinLabel.getHeight());
        coinLabelText.setPosition(coinLabel.getX() + coinLabel.getWidth() + 10, coinLabel.getY() + coinLabel.getHeight()/4);

        textBackground.setSize(myLabel.getWidth() + 30,myLabel.getHeight()+16);
        textBackground2.setSize(myButton.getWidth() + 30,myButton.getHeight()+16);

        textBackground.setPosition(viewport.getWorldWidth()/2-textBackground.getWidth()/2,viewport.getWorldHeight()/2-textBackground.getHeight()/2);
        textBackground2.setPosition(viewport.getWorldWidth()/2-textBackground2.getWidth()/2,textBackground.getY() - textBackground2.getHeight()*2f);

        myLabel.setPosition(textBackground.getX() + 15,textBackground.getY() + 8);
        myButton.setPosition(textBackground2.getX() + 15,textBackground2.getY() + 8);

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
    }

    @Override
    public void init() {

    }
}
