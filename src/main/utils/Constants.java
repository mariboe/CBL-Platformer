package main.utils;

public class Constants {

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMPING = 2;
        public static final int FALLING = 3;
        public static final int ATTACK = 4;
        public static final int GROUND = 5;
        public static final int DEAD = 6;
        public static final int HIT = 7;


        //Returns the amount of frames in an animation
        public static int GetSpriteAmount(int playerAction) {
            switch (playerAction) {
                case IDLE:
                    return 11;
                case RUNNING:
                    return 8;
                case DEAD:
                    return 4;
                case ATTACK:
                    return 3;
                case HIT:
                    return 2;
                case JUMPING:
                case FALLING:
                case GROUND:
                default:
                    return 1;
            }
        }
    }
    
}
