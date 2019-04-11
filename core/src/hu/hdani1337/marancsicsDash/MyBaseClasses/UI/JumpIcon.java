package hu.hdani1337.marancsicsDash.MyBaseClasses.UI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;

import static hu.hdani1337.marancsicsDash.Actor.Zsolti.jump;

public class JumpIcon extends OneSpriteStaticActor {

    public static int jumpHeight = 275;

    public JumpIcon() {
        super(Assets.manager.get(Assets.JUMP));
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                jumpHeight = 250 + (int)(Math.random() * 75 + 1);
                jump = true;
            }
        });
    }
}
