package Game.PacMan.entities.Dynamics;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Game.GameStates.PacManState;
import Game.PacMan.World.MapBuilder;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BoundBlock;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class MsPacMan extends BaseDynamic{
	protected double velX,velY;
	protected static double speed = 2;
	public static String facing = "Left";
	public static boolean moving = true;
	public boolean turnFlag = false;
	public Animation leftAnim,rightAnim,upAnim,downAnim;
	int turnCooldown = 22, timeB= 10, timeD=30;

	private static int health=3, width, height; 
	private int deathCounter = 47*3; // Death Animation 
	private static boolean MspacmanDead;
	private static Animation pacmanDeathAnimation;


	public MsPacMan(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.mspacmanRight[0]);
		leftAnim = new Animation(128,Images.mspacmanLeft);
		rightAnim = new Animation(128,Images.mspacmanRight);
		upAnim = new Animation(128,Images.mspacmanUp);
		downAnim = new Animation(128,Images.mspacmanDown);
		MsPacMan.height = height;
		MsPacMan.width = width;
		pacmanDeathAnimation = new Animation(256,Images.pacmanDeath);
	}
	@Override
	public void tick(){
		int pacmanSpawnX = MapBuilder.mspacmanSpawnX;
		int pacmanSpawnY = MapBuilder.mspacmanSpawnY;

		
		// Death Animation
		if(MspacmanDead) {
			if(deathCounter==3*47) {
				handler.getMusicHandler().playEffect("pacman_death.wav");
			}
			if(deathCounter<=0) {
				health--;
				facing="Left";
				pacmanDeathAnimation.reset();
				deathCounter = 3*47;
				this.setX(pacmanSpawnX);
				this.setY(pacmanSpawnY);
				speed = 2;
				MspacmanDead=false;
			}
			else {
				pacmanDeathAnimation.tick();
				deathCounter--;
			}
		}
		// Get rid of Ms PacMan when lives <=0
		if(health<=0) {
			this.setX(-5000000);
			this.setY(0);
			facing="Left";
		}

		switch (facing){
		case "Right":
			x+=velX;
			rightAnim.tick();
			break;
		case "Left":
			x-=velX;
			leftAnim.tick();
			break;
		case "Up":
			y-=velY;
			upAnim.tick();
			break;
		case "Down":
			y+=velY;
			downAnim.tick();
			break;
		}
		if (turnCooldown<=0){
			turnFlag= false;
		}
		if (turnFlag){
			turnCooldown--;
		}	
		handler.getPacManState();
		
		// Controls
		if(PacManState.isMultiplayer() && health>0) {
			if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT) && !turnFlag && handler.getMsPacman().checkPreHorizontalCollision("Right")){
				facing = "Right";
				turnFlag = true;
				turnCooldown = 15;
			}else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT) && !turnFlag&& handler.getMsPacman().checkPreHorizontalCollision("Left")){
				facing = "Left";
				turnFlag = true;
				turnCooldown = 15;
			}else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) && !turnFlag&& handler.getMsPacman().checkPreVerticalCollisions("Up")){
				facing = "Up";
				turnFlag = true;
				turnCooldown = 15;
			}else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN) && !turnFlag&& handler.getMsPacman().checkPreVerticalCollisions("Down")){
				facing = "Down";
				turnFlag = true;
				turnCooldown = 15;
			}
		} else {
			speed=0;
		}
		if (facing.equals("Right") || facing.equals("Left")){
			checkHorizontalCollision();
		}else{
			checkVerticalCollisions();
		}
	}

	public void checkVerticalCollisions() {
		MsPacMan mspacman = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();
		ArrayList<BaseDynamic> warpers = handler.getMap().getWarpersOnMap();
		boolean mspacmanDies = false;
		boolean toUp = moving && facing.equals("Up");

		Rectangle pacmanBounds = toUp ? mspacman.getTopBounds() : mspacman.getBottomBounds();

		velY = speed;
		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
				if (pacmanBounds.intersects(brickBounds)) {
					velY = 0;
					if (toUp)
						mspacman.setY(brick.getY() + mspacman.getDimension().height);
					else
						mspacman.setY(brick.getY() - brick.getDimension().height);
				}
			}
		}
		//Ms PacMan can't enter the cage
		if(facing.equals("Down") && (this.x <= MapBuilder.SpawnerX+ 16 && this.x >= MapBuilder.SpawnerX - 16) && this.y + 24 >= MapBuilder.SpawnerY - 16 && this.y + 16 <= MapBuilder.SpawnerY) {
			velY=0;
		}		
		for(BaseDynamic enemy : enemies){
			Rectangle enemyBounds = !toUp ? enemy.getTopBounds() : enemy.getBottomBounds();
			if (pacmanBounds.intersects(enemyBounds) && !handler.getPacManState().getGhostEdible()) {
				speed = 0;
				mspacmanDies = true;
				break;	
			}
		}
		if(mspacmanDies) {
			MspacmanDead = true;
			handler.getMap().reset();
		}
		// Teleport
		for(BaseDynamic warper: warpers) {
			Rectangle warperBounds = !toUp ? warper.getTopBounds() : warper.getBottomBounds();
			if(warper instanceof Warper) {
				if(pacmanBounds.intersects(warperBounds)) {
					if(mspacman.getX()<MapBuilder.SpawnerX) {
						// Ms. PacMan is on the left of map
						mspacman.setX(MapBuilder.rightWarperX-20);
						mspacman.setY(MapBuilder.rightWarperY);
					}else { // Right of the map
						mspacman.setX(MapBuilder.leftWarperX+20);
						mspacman.setY(MapBuilder.leftWarperY);
					}
				}
			}
		}
	}

	public boolean checkPreVerticalCollisions(String facing) {
		MsPacMan mspacman = this;;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();        
		boolean toUp = moving && facing.equals("Up");
		Rectangle pacmanBounds = toUp ? mspacman.getTopBounds() : mspacman.getBottomBounds();

		velY = speed;
		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
				if (pacmanBounds.intersects(brickBounds)) {
					return false;
				}
			}
		}
		return true;

	}
	public void checkHorizontalCollision(){
		MsPacMan mspacman = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();
		ArrayList<BaseDynamic> warpers = handler.getMap().getWarpersOnMap();
		velX = speed;
		boolean MspacmanDies = false;
		boolean toRight = moving && facing.equals("Right");

		Rectangle pacmanBounds = toRight ? mspacman.getRightBounds() : mspacman.getLeftBounds();

		for(BaseDynamic enemy : enemies){
			Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
			if (pacmanBounds.intersects(enemyBounds) && !handler.getPacManState().getGhostEdible()) {
				speed = 0;
				MspacmanDies = true;
				break;
			}
		}
		
		// Teleport Ms. PacMan
		for(BaseDynamic warper: warpers) {
			Rectangle warperBounds = !toRight ? warper.getRightBounds() : warper.getLeftBounds();
			if(warper instanceof Warper) {
				if(pacmanBounds.intersects(warperBounds)) {
					if(mspacman.getX()<MapBuilder.SpawnerX) {
						// PacMan is on the left of map
						mspacman.setX(MapBuilder.rightWarperX-20);
						mspacman.setY(MapBuilder.rightWarperY);
					}else { // Right of the map
						mspacman.setX(MapBuilder.leftWarperX+20);
						mspacman.setY(MapBuilder.leftWarperY);
					}
				}
			}
		}
		if(MspacmanDies) {
			handler.getMap().reset();
			MspacmanDead = true;
		}else {

			for (BaseStatic brick : bricks) {
				if (brick instanceof BoundBlock) {
					Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
					if (pacmanBounds.intersects(brickBounds)) {
						velX = 0;
						if (toRight)
							mspacman.setX(brick.getX() - mspacman.getDimension().width);
						else
							mspacman.setX(brick.getX() + brick.getDimension().width);
					}
				}
			}
		}
	}


	public boolean checkPreHorizontalCollision(String facing){
		MsPacMan mspacman = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		velX = speed;
		boolean toRight = moving && facing.equals("Right");

		Rectangle pacmanBounds = toRight ? mspacman.getRightBounds() : mspacman.getLeftBounds();

		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
				if (pacmanBounds.intersects(brickBounds)) {
					return false;
				}
			}
		}
		return true;
	}


	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}

	/**
	 * @return the health
	 */
	public static int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public static void setHealth(int health) {
		MsPacMan.health = health;
	}
	public static void addHealth(int health) {
		MsPacMan.health += health;
	}
	public static void removeHealth(int health) {
		MsPacMan.health -= health;
	}
	
	/**
	 * @return the width
	 */
	public static int getMsPWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public static void setMsPWidth(int width) {
		MsPacMan.width = width;
	}

	/**
	 * @return the pacmanDeathAnimation
	 */
	public Animation getPacmanDeathAnimation() {
		return pacmanDeathAnimation;
	}
	/**
	 * @param pacmanDeathAnimation the pacmanDeathAnimation to set
	 */
	public static void setPacmanDeathAnimation(Animation pacmanDeathAnimation) {
		MsPacMan.pacmanDeathAnimation = pacmanDeathAnimation;
	}


	/**
	 * @return the height
	 */
	public static int getMsPHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public static void setMsPHeight(int height) {
		MsPacMan.height = height;
	}

	/**
	 * @return the pacmanDead
	 */
	public static boolean isMsPacmanDead() {
		return MspacmanDead;
	}


	/**
	 * @param MspacmanDead the pacmanDead to set
	 */
	public static void setMsPacmanDead(boolean mspacmanDead) {
		MsPacMan.MspacmanDead = mspacmanDead;
	}


	/**
	 * @return the speed
	 */
	public static double getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public static void setSpeed(double speed) {
		MsPacMan.speed = speed;
	}


}