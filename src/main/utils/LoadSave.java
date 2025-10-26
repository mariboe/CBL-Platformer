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
        BufferedImage level1 = GetSpriteAtlas(LEVEL_ONE_DATA);
        BufferedImage level2 = GetSpriteAtlas(LEVEL_TWO_DATA);
        BufferedImage level3 = GetSpriteAtlas(LEVEL_THREE_DATA);
        BufferedImage level4 = GetSpriteAtlas(LEVEL_FOUR_DATA);
        BufferedImage[] levelsData = new BufferedImage[4];
        levelsData[0] = level1;
        levelsData[1] = level2;
        levelsData[2] = level3;
        levelsData[3] = level4;
        int[][][] levelsTerrain = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH][4];

        for (int h = 0; h < levelsData.length; h++) {
            BufferedImage currentLevel = level1;
            for (int j = 0; j < currentLevel.getHeight(); j++) {
                for (int i = 0; i < currentLevel.getWidth(); i++) {
                    Color color = new Color(currentLevel.getRGB(i, j));
                    int value = color.getRed();
                    if (value >= 48) {
                        value = 0;
                    }
                    levelData[j][i] = value;
                }
            }
            levelsTerrain[h] = levelData;
        }
        return levelsTerrain[0];
    }
}
