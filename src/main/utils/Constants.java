package main.utils;

public class Constants {

    public static class Directions {
        public static final int UP = 0;
        public static final int LEFT = 1;
        public static final int DOWN = 2;
        public static final int RIGHT = 3;
    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMPING = 2;
        public static final int FALLING = 3;
        public static final int ATTACK = 4;
        public static final int GROUND = 5;
        public static final int DEAD = 6;
        public static final int HIT = 7;

        public static int GetSpriteAmount(int playerAction) {
            switch (playerAction) {
                case IDLE:
                    return 11;
                case RUNNING:
                    return 8;
                case ATTACK:
                    return 3;
                case JUMPING:
                case FALLING:
                default:
                    return 1;
            }
        }
    }
    
}
