package hu.hdani1337.marancsicsDash.MyBaseClasses.UI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.hdani1337.marancsicsDash.Actor.Zsolti;
import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;

import static hu.hdani1337.marancsicsDash.Actor.Zsolti.fall;
import static hu.hdani1337.marancsicsDash.Actor.Zsolti.forcejump;
import static hu.hdani1337.marancsicsDash.Actor.Zsolti.jump;
import static hu.hdani1337.marancsicsDash.Stage.GameStage.ground;
import static hu.hdani1337.marancsicsDash.Stage.ShopStage.boughtDouble;

public class JumpIcon extends OneSpriteStaticActor {

    public static int jumpHeight;
    public static boolean doubleJumped;

    public JumpIcon() {
        super(Assets.manager.get(Assets.JUMP));
        setDebug(false);
        jumpHeight = ground + 245;
        doubleJumped = false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                jumpHeight = ground + 220 + (int)(Math.random() * 75 + 1);
                if(fall) {
                    if (boughtDouble && !doubleJumped) {
                        Zsolti.forcejump = true;
                        doubleJumped = true;
                    }
                }
                else jump = true;
            }
        });
    }
}
