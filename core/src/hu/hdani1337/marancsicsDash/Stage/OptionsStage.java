package hu.hdani1337.marancsicsDash.Stage;

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
    public static byte difficulty;

    Background background;
    MyButton difPlus;
    MyButton difMinus;
    MyButton back;
    MyLabel difType; //Könnyű/Normál/Nehéz
    MyLabel dif; //Nehézség:
    MyLabel muting;//Némítás:
    MyButton mutedButton;//Nincs némítva/Némítva
    OneSpriteStaticActor textbg;//nehézség háttere
    OneSpriteStaticActor textbg2;//némítás háttere
    Music music;

    public OptionsStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        difficulty = 2;//normál
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
        textbg.setPosition(dif.getX() - 18,dif.getY() - 8);
        textbg.setSize(330,dif.getHeight()*1.5f);
        difMinus.setPosition(dif.getX() + dif.getWidth() + 20,dif.getY());
        difType.setPosition(difMinus.getX() + difMinus.getWidth() + 10,dif.getY()+16);
        difPlus.setPosition(difMinus.getX() + 120,dif.getY());
        back.setPosition(viewport.getWorldWidth() - (back.getWidth() + 25),125);
        textbg2.setPosition(textbg.getX(),textbg.getY() - 100);
        textbg2.setHeight(textbg.getHeight());
        muting.setPosition(textbg2.getX() + 18,textbg2.getY() + textbg2.getHeight()/6);
        mutedButton.setPosition(muting.getX() + 210,muting.getY());


        addActor(background);
        addActor(textbg);
        addActor(dif);
        addActor(difType);
        addActor(difPlus);
        addActor(difMinus);
        addActor(back);
        addActor(textbg2);
        addActor(muting);
        addActor(mutedButton);
    }

    @Override
    public void init() {

    }
}
