package hu.hdani1337.marancsicsDash.Stage;

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

public class OptionsStage extends MyStage {
    public static byte difficulty;

    Background background;
    MyButton difPlus;
    MyButton difMinus;
    MyButton back;
    MyLabel difType; //Könnyű/Normál/Nehéz
    MyLabel dif; //Nehézség:
    OneSpriteStaticActor textbg;

    public OptionsStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        difficulty = 2;//normál
        background = new Background(Assets.manager.get(Assets.MENU_BG));
        difPlus = new MyButton("+",game.getButtonStyle());
        difMinus = new MyButton("-",game.getButtonStyle());
        dif = new MyLabel("Nehézség: ",game.getLabelStyle());
        difType = new MyLabel("",game.getLabelStyle());
        back = new MyButton("Vissza a menübe",game.getButtonStyle());
        textbg = new OneSpriteStaticActor(Assets.manager.get(Assets.TEXT_BG)){
            @Override
            public void act(float delta) {
                super.act(delta);
                if(difficulty == 1){
                    difType.setText("Könnyű");
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
                game.setScreen(new HomeScreen(game));
            }
        });

        dif.setPosition(30,viewport.getWorldHeight() - viewport.getWorldHeight() / 3);
        textbg.setPosition(dif.getX() - 18,dif.getY() - 8);
        textbg.setSize(330,dif.getHeight()*1.5f);
        difMinus.setPosition(dif.getX() + dif.getWidth() + 20,dif.getY());
        difType.setPosition(difMinus.getX() + difMinus.getWidth() + 10,dif.getY()+16);
        difPlus.setPosition(difMinus.getX() + 120,dif.getY());
        back.setPosition(viewport.getWorldWidth() - (back.getWidth() + 25),125);

        addActor(background);
        addActor(textbg);
        addActor(dif);
        addActor(difType);
        addActor(difPlus);
        addActor(difMinus);
        addActor(back);
    }

    @Override
    public void init() {

    }
}
