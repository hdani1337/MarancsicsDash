package hu.hdani1337.marancsicsDash.ParentClasses.UI;

import hu.hdani1337.marancsicsDash.Global.Assets;
import hu.hdani1337.marancsicsDash.ParentClasses.Scene2D.OneSpriteStaticActor;

public class TextBackground extends OneSpriteStaticActor {
    public TextBackground() {
        super(Assets.manager.get(Assets.TEXT_BG));
        setDebug(false);
    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        super.setColor(r, g, b, a);
        sprite.setAlpha(a);
    }
}
