package main.utils;

import java.awt.geom.Rectangle2D;
import main.Game;

public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] levelData) {
        if (!IsSolid(x, y, levelData))
            if (!IsSolid(x + width, y + height, levelData))
                if (!IsSolid(x + width, y, levelData))
                    return !IsSolid(x, y + height, levelData);
        return false;
    }

    private static boolean IsSolid(float x, float y, int[][] levelData) {

        if (x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if (y < 0)
            return true;
        if (y >= Game.GAME_HEIGHT) {
            return false;
        }

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = levelData[(int) yIndex][(int) xIndex];

        return value >= 48 || value < 0 || value != 11; // 11 is background
    }

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
        if (xSpeed > 0) {
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        } else {
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float GetEntityHeightIfTouching(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Game.TILES_SIZE + 1);
        if (airSpeed > 0) {
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else {
            return (currentTile - 1) * Game.TILES_SIZE;
        }
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData) {
        if(!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, levelData)) {
            if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData)) {
                return false;
            }
        }
        return true;
    }
    
}
