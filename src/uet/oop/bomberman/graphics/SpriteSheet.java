package uet.oop.bomberman.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * A big image that contains all game's sprites
 */
public class SpriteSheet {

    private String _path;
    public final int SIZE;
    public int[] _pixels;
    public BufferedImage image;

    public static SpriteSheet tiles = new SpriteSheet("/textures/classic.png", 256);
    public static SpriteSheet tiles2 = new SpriteSheet("/textures/modern2.png", 272);
    public static SpriteSheet tiles3 = new SpriteSheet("/textures/modern3.png", 272);
    public static SpriteSheet tiles4 = new SpriteSheet("/textures/modern4.png", 272);
    public static SpriteSheet tiles5 = new SpriteSheet("/textures/modern5.png", 272);
    public static SpriteSheet tiles6 = new SpriteSheet("/textures/modern6.png", 272);

    public SpriteSheet(String path, int size) {
        _path = path;
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        try {
            URL a = SpriteSheet.class.getResource(_path);
            image = ImageIO.read(a);
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, _pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}