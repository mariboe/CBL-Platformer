package main.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import static main.utils.Constants.PlayerConstants.*;
import static main.utils.HelpMethods.*;
import main.utils.LoadSave;

public class Player extends Entity {
    
    private BufferedImage[][] animations;
    private int animationTick, animationIndex;
	private final int animationSpeed = 20;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down, jump;
	private final float playerSpeed = 1 * Game.SCALE; // 1 pixel per update
	private int playerDirection = 1; // 1: right, -1: left
	private int[][] levelData;
	private final float xDrawOffset = 32 * Game.SCALE;
	private final float yDrawOffset = 27 * Game.SCALE;

	private float airSpeed = 0f;
	private final float gravity = 0.03f * Game.SCALE;
	private final float jumpSpeed = -2f *Game.SCALE;
	private boolean inAir = false;


    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
		initHitbox(x, y, 20 * Game.SCALE, 32 * Game.SCALE);
    }


    public void update() {
		updatePosition();
		updateAnimationTick();
		setAnimation();
    }

    public void render(Graphics g) {
		if (playerDirection == -1) {
			g.drawImage(animations[playerAction][animationIndex], (int) (hitbox.x - xDrawOffset) + 126, (int) (hitbox.y - yDrawOffset) + 1, -156, 116, null);
		} else {
			g.drawImage(animations[playerAction][animationIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset) + 1, 156, 116, null);
		}
		drawHitbox(g);
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

		if (inAir) {
			if (airSpeed < 0) {
				playerAction = JUMPING;
			} else {
				playerAction = FALLING;
			}
		}

		if (attacking) {
			playerAction = ATTACK;
		}
		
		if (startAnimation != playerAction) {
			resetAnimationTick();
		}
	}

	private void resetAnimationTick() {
		animationTick = 0;
		animationIndex = 0;
	}

	public void updatePosition() {

		moving = false;

		if (jump) {
			jump();
		}
		
		if (!left && !right && !inAir) {
			return;
		}

		float xSpeed = 0;


		if (!attacking) {
			if (left) {
				xSpeed = -0.5f * playerSpeed * Game.SCALE;
			}
			
			if (right) {
				xSpeed = 0.5f * playerSpeed * Game.SCALE;
			}

		}

		if (!inAir) {
			if (!IsEntityOnFloor(hitbox, levelData)){
				inAir = true;
			}
		}

		if (inAir) {
			if (hitbox.y > Game.GAME_HEIGHT) {
				hitbox.x = 100;
				hitbox.y = 300;
				airSpeed = 0;
			}
			if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				hitbox.y = GetEntityHeightIfTouching(hitbox, airSpeed);
				resetInAir();
				updateXPos(xSpeed);
			}
		} else {
			updateXPos(xSpeed);
		}

		moving = true;
	}

	private void jump() {
		if (inAir) {
			return;
		}
		inAir = true;
		airSpeed = jumpSpeed;
	}


	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
	}


    private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
			hitbox.x += xSpeed;
		} else {
			hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
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

	public void loadLevelData(int[][] levelData) {
		this.levelData = levelData;
		if (!IsEntityOnFloor(hitbox, levelData)) {
			inAir = true;
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
		if (left) playerDirection = -1;
		moving = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
		moving = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
		if (right) playerDirection = 1;
		moving = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
		moving = down;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}
    
}
