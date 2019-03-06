package hu.hdani1337.marancsicsDash.MyBaseClasses;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;


public class Assets {

    public static AssetManager manager;

    public static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";

    static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();

    static {
        fontParameter.fontFileName = "fontstyle.ttf";
        fontParameter.fontParameters.size = 30;
        fontParameter.fontParameters.characters = CHARS;
        fontParameter.fontParameters.color = Color.WHITE;
    }

    public static final AssetDescriptor<TextureAtlas> ZSOLTI = new AssetDescriptor<TextureAtlas>("zsolti.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> MARANCSICS = new AssetDescriptor<TextureAtlas>("marancsics.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> TANK = new AssetDescriptor<TextureAtlas>("tank.atlas", TextureAtlas.class);
    public static final AssetDescriptor<Texture> MENU_BG = new AssetDescriptor<Texture>("menuBg.jpg",Texture.class);
    public static final AssetDescriptor<Texture> GAME_BG = new AssetDescriptor<Texture>("bg.png",Texture.class);
    public static final AssetDescriptor<Texture> LOGO = new AssetDescriptor<Texture>("logo.png",Texture.class);
    public static final AssetDescriptor<Texture> TEXT_BG = new AssetDescriptor<Texture>("textBG.png",Texture.class);
    public static final AssetDescriptor<BitmapFont> FONT = new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);

    public static void prepare() {
        manager = new AssetManager();
        Texture.setAssetManager(manager);
    }

    public static void load() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        manager.load(ZSOLTI);
        manager.load(MARANCSICS);
        manager.load(TANK);
        manager.load(FONT);
        manager.load(MENU_BG);
        manager.load(GAME_BG);
        manager.load(LOGO);
        manager.load(TEXT_BG);
    }

    public static void unload() {
        manager.dispose();
    }
}