package hu.hdani1337.marancsicsDash.Global;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
        fontParameter.fontFileName = "font/fontstyle.ttf";
        fontParameter.fontParameters.size = 30;
        fontParameter.fontParameters.characters = CHARS;
        fontParameter.fontParameters.color = Color.WHITE;
    }

    //ATLASOK
    public static final AssetDescriptor<TextureAtlas> ZSOLTI = new AssetDescriptor<TextureAtlas>("atlas/zsolti.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> MARANCSICS = new AssetDescriptor<TextureAtlas>("atlas/marancsics.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> TANK = new AssetDescriptor<TextureAtlas>("atlas/tank.atlas", TextureAtlas.class);
    //KÉPEK
    public static final AssetDescriptor<Texture> MENU_BG = new AssetDescriptor<Texture>("pic/menuBg.jpg",Texture.class);
    public static final AssetDescriptor<Texture> GAME_BG = new AssetDescriptor<Texture>("pic/bg.png",Texture.class);
    public static final AssetDescriptor<Texture> LOGO = new AssetDescriptor<Texture>("pic/logo.png",Texture.class);
    public static final AssetDescriptor<Texture> TEXT_BG = new AssetDescriptor<Texture>("pic/textBG.png",Texture.class);
    public static final AssetDescriptor<Texture> JUMP = new AssetDescriptor<Texture>("pic/jump.png",Texture.class);
    public static final AssetDescriptor<Texture> PAUSE = new AssetDescriptor<Texture>("pic/pause.png",Texture.class);
    public static final AssetDescriptor<Texture> PLAY = new AssetDescriptor<Texture>("pic/play.png",Texture.class);
    //BETŰTÍPUSOK
    public static final AssetDescriptor<BitmapFont> FONT = new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);
    //HANGOK
    public static final AssetDescriptor<Sound> URAIM = new AssetDescriptor<Sound>("sound/uraim.wav",Sound.class);
    public static final AssetDescriptor<Sound> HEE = new AssetDescriptor<Sound>("sound/héé.wav",Sound.class);
    public static final AssetDescriptor<Sound> KICK = new AssetDescriptor<Sound>("sound/kick.wav",Sound.class);
    public static final AssetDescriptor<Sound> CRASH = new AssetDescriptor<Sound>("sound/crash.wav",Sound.class);
    //ZENE
    public static final AssetDescriptor<Music> MENUMUSIC = new AssetDescriptor<Music>("music/menuMusic.mp3",Music.class);
    public static final AssetDescriptor<Music> GAMEMUSIC = new AssetDescriptor<Music>("music/gameMusic.mp3",Music.class);

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
        manager.load(URAIM);
        manager.load(HEE);
        manager.load(JUMP);
        manager.load(KICK);
        manager.load(CRASH);
        manager.load(PAUSE);
        manager.load(PLAY);
        manager.load(MENUMUSIC);
        manager.load(GAMEMUSIC);
    }

    public static void unload() {
        manager.dispose();
    }
}