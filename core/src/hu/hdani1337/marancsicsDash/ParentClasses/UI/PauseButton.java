package hu.hdani1337.marancsicsDash.ParentClasses.UI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteStaticActor;

public class PauseButton extends OneSpriteStaticActor {
    public static boolean paused = false;

    public PauseButton() {
        super(Assets.manager.get(Assets.PAUSE));
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                paused = true;
            }
        });
    }
}
