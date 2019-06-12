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
    public static final AssetDescriptor<TextureAtlas> SUPERZSOLTI = new AssetDescriptor<TextureAtlas>("atlas/superZsolti.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> MARANCSICS = new AssetDescriptor<TextureAtlas>("atlas/marancsics.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> TANK = new AssetDescriptor<TextureAtlas>("atlas/tank.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> COIN = new AssetDescriptor<TextureAtlas>("atlas/coin.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> MARANCSICS_BOSS = new AssetDescriptor<TextureAtlas>("atlas/marancsicsBoss.atlas", TextureAtlas.class);
    //KÉPEK
    public static final AssetDescriptor<Texture> MENU_BG = new AssetDescriptor<Texture>("pic/menuBg.jpg",Texture.class);
    public static final AssetDescriptor<Texture> GAME_BG = new AssetDescriptor<Texture>("pic/bg.png",Texture.class);
    public static final AssetDescriptor<Texture> GAME_BG2 = new AssetDescriptor<Texture>("pic/bg2.png",Texture.class);
    public static final AssetDescriptor<Texture> GAME_BG3 = new AssetDescriptor<Texture>("pic/bg3.jpg",Texture.class);
    public static final AssetDescriptor<Texture> GAME_BG4 = new AssetDescriptor<Texture>("pic/bg4.png",Texture.class);
    public static final AssetDescriptor<Texture> GAME_BG5 = new AssetDescriptor<Texture>("pic/bg5.jpg",Texture.class);
    public static final AssetDescriptor<Texture> LOGO = new AssetDescriptor<Texture>("pic/logo.png",Texture.class);
    public static final AssetDescriptor<Texture> SHOP_LOGO = new AssetDescriptor<Texture>("pic/marancshop.png",Texture.class);
    public static final AssetDescriptor<Texture> TEXT_BG = new AssetDescriptor<Texture>("pic/textBG.png",Texture.class);
    public static final AssetDescriptor<Texture> JUMP = new AssetDescriptor<Texture>("pic/jump.png",Texture.class);
    public static final AssetDescriptor<Texture> PAUSE = new AssetDescriptor<Texture>("pic/pause.png",Texture.class);
    public static final AssetDescriptor<Texture> PLAY = new AssetDescriptor<Texture>("pic/play.png",Texture.class);
    public static final AssetDescriptor<Texture> RED = new AssetDescriptor<Texture>("pic/piros.png",Texture.class);
    public static final AssetDescriptor<Texture> GREEN = new AssetDescriptor<Texture>("pic/zöld.png",Texture.class);
    public static final AssetDescriptor<Texture> LEFT = new AssetDescriptor<Texture>("pic/left.png",Texture.class);
    public static final AssetDescriptor<Texture> RIGHT = new AssetDescriptor<Texture>("pic/right.png",Texture.class);
    public static final AssetDescriptor<Texture> INSTANTBOSS = new AssetDescriptor<Texture>("pic/instantBoss.png",Texture.class);
    public static final AssetDescriptor<Texture> GOMBA = new AssetDescriptor<Texture>("pic/mushroom.png",Texture.class);
    //BETŰTÍPUSOK
    public static final AssetDescriptor<BitmapFont> FONT = new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);
    //HANGOK
    public static final AssetDescriptor<Sound> URAIM = new AssetDescriptor<Sound>("sound/uraim.wav",Sound.class);
    public static final AssetDescriptor<Sound> HEE = new AssetDescriptor<Sound>("sound/héé.wav",Sound.class);
    public static final AssetDescriptor<Sound> KICK = new AssetDescriptor<Sound>("sound/kick.wav",Sound.class);
    public static final AssetDescriptor<Sound> CRASH = new AssetDescriptor<Sound>("sound/crash.wav",Sound.class);
    public static final AssetDescriptor<Sound> COIN_SOUND = new AssetDescriptor<Sound>("sound/coin.mp3",Sound.class);
    public static final AssetDescriptor<Sound> GLASSBREAK = new AssetDescriptor<Sound>("sound/glass.wav",Sound.class);
    public static final AssetDescriptor<Sound> PAY = new AssetDescriptor<Sound>("sound/pay.mp3",Sound.class);
    public static final AssetDescriptor<Sound> POWERUP = new AssetDescriptor<Sound>("sound/powerup.mp3",Sound.class);
    public static final AssetDescriptor<Sound> ERROR = new AssetDescriptor<Sound>("sound/error.mp3",Sound.class);
    //ZENE
    public static final AssetDescriptor<Music> MENUMUSIC = new AssetDescriptor<Music>("music/menuMusic.mp3",Music.class);
    public static final AssetDescriptor<Music> GAMEMUSIC = new AssetDescriptor<Music>("music/gameMusic.mp3",Music.class);
    public static final AssetDescriptor<Music> BOSSMUSIC = new AssetDescriptor<Music>("music/bossMusic.mp3",Music.class);

    public static void prepare() {
        manager = new AssetManager();
        Texture.setAssetManager(manager);
    }

    public static void load() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        manager.load(ZSOLTI);
        manager.load(SUPERZSOLTI);
        manager.load(MARANCSICS);
        manager.load(TANK);
        manager.load(COIN);
        manager.load(FONT);
        manager.load(MENU_BG);
        manager.load(GAME_BG);
        manager.load(GAME_BG2);
        manager.load(GAME_BG3);
        manager.load(GAME_BG4);
        manager.load(GAME_BG5);
        manager.load(LOGO);
        manager.load(GOMBA);
        manager.load(SHOP_LOGO);
        manager.load(TEXT_BG);
        manager.load(URAIM);
        manager.load(HEE);
        manager.load(JUMP);
        manager.load(KICK);
        manager.load(ERROR);
        manager.load(CRASH);
        manager.load(PAUSE);
        manager.load(PLAY);
        manager.load(MENUMUSIC);
        manager.load(GAMEMUSIC);
        manager.load(BOSSMUSIC);
        manager.load(COIN_SOUND);
        manager.load(GLASSBREAK);
        manager.load(MARANCSICS_BOSS);
        manager.load(RED);
        manager.load(GREEN);
        manager.load(LEFT);
        manager.load(RIGHT);
        manager.load(INSTANTBOSS);
        manager.load(PAY);
        manager.load(POWERUP);
    }

    public static void unload() {
        manager.dispose();
    }
}