package hu.hdani1337.marancsicsDash.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import hu.hdani1337.marancsicsDash.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Background extends OneSpriteStaticActor {
    public Background(Texture Háttér) {
        super(Háttér);
        setDebug(false);
        setWidth(Gdx.graphics.getWidth());
        setHeight(Gdx.graphics.getHeight());
        setPosition(0,0);
    }

}
