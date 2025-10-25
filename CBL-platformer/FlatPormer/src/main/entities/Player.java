package main.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import static main.utils.Constants.PlayerConstants.*;
import main.utils.LoadSave;

public class Player extends Entity {
    
    private BufferedImage[][] animations;
    private int animationTick, animationIndex;
	private final int animationSpeed = 20;
	private int playerAction = IDLE;
	private final int playerspeed = 1;
	private int playerDirection = 1; // 1: right, -1: left
	private boolean moving = false;
	private boolean left, up, right, down, attacking;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }


    public void update() {
		updatePosition();
		updateAnimationTick();
		setAnimation();
    }

    public void render(Graphics g) {
		g.drawImage(animations[playerAction][animationIndex], (int) x, (int) y, playerDirection * 156, 116, null);
    }

    private void updateAnimationTick() {
		
		animationTick++;
		if (animationTick >= animationSpeed) {
			animationTick = 0;
			animationIndex++;
			if (animationIndex >= GetSpriteAmount(playerAction)) {
				animationIndex = 0;
				attacking = false;
			}
		}
    }

	public void setAnimation() {
		int startAnimation = playerAction;

		if (moving) {
			playerAction = RUNNING;
		} else {
			playerAction = IDLE;
		}

		if (attacking) {
			playerAction = ATTACK;
		}
		
		if (startAnimation != playerAction) {
			resetAnimationTick();
		}
	}

	private void resetAnimationTick() {
		animationTick = 0; animationIndex = 0;
	}

	public void updatePosition() {

		moving = false;

		if (!attacking) {
			if (left && !right) {
				x -= 0.5 * playerspeed * Game.SCALE;
				moving = true;
			} else if (right && !left) {
				x += 0.5 * playerspeed * Game.SCALE;
				moving = true;
			}

			if (up && !down) {
				y -= 0.5 * playerspeed * Game.SCALE;
				moving = true;
			} else if (down && !up) {
				y += 0.5 * playerspeed * Game.SCALE;
				moving = true;
			}
		}
	}


    private void loadAnimations() {

            BufferedImage image = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

            animations = new BufferedImage[8][11];
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = image.getSubimage(i * 78, j * 58, 78, 58);
                }
            }

    }

	public void resetDirectionBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
		if (!attacking) {
			if (playerDirection == 1) {
				x += 116;
			}
			playerDirection = -1;
		}
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
		if (!attacking) {
			if (playerDirection == -1) {
				x -= 116;
			}
			playerDirection = 1;
		}
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
    
}
