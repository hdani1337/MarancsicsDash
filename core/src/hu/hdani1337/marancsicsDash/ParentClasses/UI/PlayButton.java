package hu.hdani1337.marancsicsDash.ParentClasses.UI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteStaticActor;

public class PlayButton extends OneSpriteStaticActor {
    public PlayButton() {
        super(Assets.manager.get(Assets.PLAY));
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                PauseButton.paused = false;
            }
        });
    }
}
