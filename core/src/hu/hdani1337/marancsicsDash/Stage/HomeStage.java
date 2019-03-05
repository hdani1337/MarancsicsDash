package hu.hdani1337.marancsicsDash.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.hdani1337.marancsicsDash.Actor.Background;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.MyStage;
import hu.hdani1337.marancsicsDash.MyBaseClasses.UI.MyButton;
import hu.hdani1337.marancsicsDash.Screen.GameScreen;
import hu.hdani1337.marancsicsDash.marancsicsGame;

public class HomeStage extends MyStage {
    MyButton start;
    MyButton exit;
    Background bg;

    public HomeStage(Viewport viewport, Batch batch, final marancsicsGame game) {
        super(viewport, batch, game);
        start = new MyButton("A játék indítása",game.getButtonStyle());
        exit = new MyButton("Kilépés",game.getButtonStyle());
        bg = new Background(Assets.manager.get(Assets.MENU_BG));

        start.setX(viewport.getWorldWidth()/2 - start.getWidth()/2);
        start.setY(viewport.getWorldHeight()/2 - start.getHeight()/2);
        exit.setY(start.getY() - exit.getHeight()*2);
        exit.setX((viewport.getWorldWidth()/2 - exit.getWidth()/2));

        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game));
            }
        });

        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });

        addActor(bg);
        addActor(start);
        addActor(exit);
    }

    @Override
    public void init() {

    }
}
