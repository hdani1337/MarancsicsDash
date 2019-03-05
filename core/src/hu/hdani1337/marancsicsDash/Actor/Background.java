package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.graphics.Texture;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Background extends OneSpriteStaticActor {
    public Background(Texture Háttér) {
        super(Háttér);
        setDebug(false);
        setWidth(1280);
        setHeight(720);
        setPosition(0,0);
    }

}
