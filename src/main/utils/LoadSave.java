package main.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import main.Game;

public class LoadSave {

    public static final String PLAYER_ATLAS = "sprite_animations.png";
    public static final String LEVEL_ATLAS = "terrain.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";
    public static final String LEVEL_TWO_DATA = "level_two_data.png";
    public static final String LEVEL_THREE_DATA = "level_three_data.png";
    public static final String LEVEL_FOUR_DATA = "level_four_data.png";

    public static BufferedImage GetSpriteAtlas(String filename) {
        BufferedImage image = null;
        InputStream is = LoadSave.class.getResourceAsStream("/res/" + filename);
        try {
            image = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    public static int[][] GetLevelData() {
        int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage image = GetSpriteAtlas(LEVEL_ONE_DATA);
//        BufferedImage image = GetSpriteAtlas(LEVEL_TWO_DATA);
//        BufferedImage image = GetSpriteAtlas(LEVEL_THREE_DATA);
//        BufferedImage image = GetSpriteAtlas(LEVEL_FOUR_DATA);

        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth(); i++) {
                Color color = new Color(image.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48) {
                    value = 0;
                }
                levelData[j][i] = value;
            }
        }
        return levelData;
    }
}
