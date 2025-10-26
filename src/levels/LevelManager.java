package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import main.utils.LoadSave;

public class LevelManager {
    
    private final Game game;
    private BufferedImage[] levelSprite;
    private final Level levelOne;
    private final Level levelTwo;
    private final Level levelThree;
    private final Level levelFour;
    private final Level[] levelsData = new Level[4];

    public LevelManager(Game game) {
        this.game = game;
        importTerrain();
        levelOne = new Level(LoadSave.GetSelectedLevelData(0));
        levelTwo = new Level(LoadSave.GetSelectedLevelData(1));
        levelThree = new Level(LoadSave.GetSelectedLevelData(2));
        levelFour = new Level(LoadSave.GetSelectedLevelData(3));
        levelsData[0] = levelOne;
        levelsData[1] = levelTwo;
        levelsData[2] = levelThree;
        levelsData[3] = levelFour;
    }

    private void importTerrain() {
        BufferedImage image = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = image.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g, int selectedLevel) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
                int index = levelsData[selectedLevel].getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], i * Game.TILES_SIZE, j * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
    }

    public Level getCurrentLevel(int selectedLevel) {
        return levelsData[selectedLevel];
    }

    public Game getGame() {
        return game;
    }
}
