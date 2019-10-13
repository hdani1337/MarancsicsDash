package hu.hdani1337.marancsicsDash.Stage;

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
import hu.hdani1337.marancsicsDash.marancsicsGame;

import static hu.hdani1337.marancsicsDash.marancsicsGame.notch;

public class InfoStage extends MyStage {
    Background bg;
    TextBackground textBG = new TextBackground();
    TextBackground textBG2 = new TextBackground();
    TextBackground textBG3 = new TextBackground();
    MyLabel text = new MyLabel("A játék valós eseményeken alapul.\nEgy szép napon a föhösünk, Zsolti beszólt szeretett\nosztályfönökünknek, Marancsicsnak.\nMarancsics nagyon megharagudott rá, s mindenáron elakarja kapni Zsoltit,\nhogy osztályfönökit adhasson neki. A Te feladatod az,\nhogy Zsolti minél tovább tudjon menekülni. Vigyázz, mert Marancsics\nneked tudja rúgni az akadályokat!",game.getLabelStyle());
    MyButton back = new MyButton("Vissza a menübe",game.getButtonStyle());
    MyLabel credits = new MyLabel("Készítette: Horváth Dániel\nFelkészítö tanár: Tüske Balázs",game.getLabelStyle());

    public InfoStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        bg = new Background(Assets.manager.get(Assets.MENU_BG),viewport);

        ClickListener backListener = new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        };

        textBG2.addListener(backListener);
        back.addListener(backListener);

        setPositionsAndSizes(viewport);
        addActors();
    }

    void setPositionsAndSizes(Viewport viewport)
    {
        text.setAlignment(0);
        text.setFontScale(0.8f);
        textBG.setSize(textBG.getWidth()*3.1f,textBG.getHeight()*3.1f);
        textBG.setPosition(viewport.getWorldWidth()/2-textBG.getWidth()/2,(viewport.getWorldHeight()/2-textBG.getHeight()/2)+30);
        text.setPosition(viewport.getWorldWidth()/2 - text.getWidth()/2,(viewport.getWorldHeight()/2 - text.getHeight()/2)+30);
        if (!notch) back.setPosition(viewport.getWorldWidth() - (back.getWidth() + 25),50);
        else back.setPosition(viewport.getWorldWidth() - (back.getWidth() + 45),50);
        credits.setPosition(40,70);
        textBG2.setWidth(290);
        textBG2.setHeight(60);
        textBG2.setPosition(back.getX()-15,back.getY()-10);
        textBG3.setSize(credits.getWidth() + 56,credits.getHeight()+36);
        textBG3.setPosition(credits.getX()-28,credits.getY()-18);
    }

    void addActors()
    {
        addActor(bg);
        addActor(textBG);
        addActor(text);
        addActor(textBG2);
        addActor(textBG3);
        addActor(back);
        addActor(credits);
    }

    @Override
    public void init() {

    }
}
