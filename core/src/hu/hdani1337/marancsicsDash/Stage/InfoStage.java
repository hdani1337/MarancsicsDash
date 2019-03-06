package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyLabel;
import hu.hdani1337.marancsicsDash.Screen.HomeScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class InfoStage extends MyStage {
    Background bg;
    OneSpriteStaticActor textBG;
    MyLabel text;
    MyLabel credits;
    MyButton back;

    public InfoStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        bg = new Background(Assets.manager.get(Assets.MENU_BG));

        textBG = new OneSpriteStaticActor(Assets.manager.get(Assets.TEXT_BG)){
            @Override
            public void act(float delta) {
                super.act(delta);
                setDebug(false);
            }
        };

        text = new MyLabel("A játék valós eseményeken alapul.\nEgy szép napon a főhősünk, Zsolti bemutatott szeretett\nosztályfőnökünknek, Marancsicsnak.\nMarancsics nagyon megharagudott rá, s mindenáron elakarja kapni Zsoltit,\nhogy osztályfőnökit adhasson neki. A Te feladatod az,\nhogy Zsolti minél tovább tudjon menekülni. Vigyázz, mert Marancsics\nneked tudja rúgni az akadályokat!",game.getLabelStyle());
        back = new MyButton("Vissza a menübe",game.getButtonStyle());
        credits = new MyLabel("Készítette: Horváth Dániel\nFelkészítő tanár: Tüske Balázs",game.getLabelStyle());

        text.setAlignment(0);

        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new HomeScreen(game));
            }
        });

        textBG.setSize(textBG.getWidth()*3.1f,textBG.getHeight()*3.1f);
        textBG.setPosition(viewport.getWorldWidth()/2-textBG.getWidth()/2,(viewport.getWorldHeight()/2-textBG.getHeight()/2)+30);
        text.setPosition(viewport.getWorldWidth()/2 - text.getWidth()/2,(viewport.getWorldHeight()/2 - text.getHeight()/2)+30);
        back.setPosition(viewport.getWorldWidth() - (back.getWidth() + 25),125);
        credits.setPosition(15,100);

        addActor(bg);
        addActor(textBG);
        addActor(text);
        addActor(back);
        addActor(credits);
    }

    @Override
    public void init() {

    }
}
