package hu.hdani1337.marancsicsDash.MyBaseClasses;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class Assets {

    public static AssetManager manager;

    public static final AssetDescriptor<TextureAtlas> ZSOLTI = new AssetDescriptor<TextureAtlas>("zsolti.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> MARANCSICS = new AssetDescriptor<TextureAtlas>("marancsics.atlas", TextureAtlas.class);
    public static final AssetDescriptor<Texture> TEST_BG = new AssetDescriptor<Texture>("testbg.png",Texture.class);

    public static void prepare() {
        manager = new AssetManager();
        Texture.setAssetManager(manager);
    }

    public static void load() {
        manager.load(ZSOLTI);
        manager.load(MARANCSICS);
        manager.load(TEST_BG);
    }

    public static void unload() {
        manager.dispose();
    }
}