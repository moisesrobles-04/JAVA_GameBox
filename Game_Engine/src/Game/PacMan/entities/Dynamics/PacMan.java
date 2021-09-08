package Game.PacMan.entities.Dynamics;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.GameStates.PacManState;
import Game.PacMan.World.MapBuilder;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BoundBlock;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class PacMan extends BaseDynamic{

	protected double velX,velY;
	protected static double speed = 2;
	public static String facing = "Left";
	public static boolean moving = true;
	public boolean turnFlag = false;
	public Animation leftAnim,rightAnim,upAnim,downAnim, idleB, idleD, idleW2;
	int turnCooldown = 22, timeB= 10, timeD=30, timeW=9;

	private static int health=3, width, height, PowerUpCountDown=5*60, PowerCount; //5 seconds 
	private int deathCounter = 47*3; // Death Animation 
	private static boolean pacmanDead;
	private static Animation pacmanDeathAnimation;

	public PacMan(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.pacmanRight[0]);
		leftAnim = new Animation(128,Images.pacmanLeft);
		rightAnim = new Animation(128,Images.pacmanRight);
		upAnim = new Animation(128,Images.pacmanUp);
		downAnim = new Animation(128,Images.pacmanDown);
		PacMan.height = height;
		PacMan.width = width;
		pacmanDeathAnimation = new Animation(256,Images.pacmanDeath);

		//        Dots Animation
		BufferedImage[] idleBig= new BufferedImage[3];
		idleBig[0]=Images.pacmanDots[0];
		idleBig[1]=Images.pacmanDots[1];
		idleBig[2]=Images.pacmanDots[2];
		idleB= new Animation(128,idleBig);
		BufferedImage [] idleDot= new BufferedImage[2];
		idleDot[0]=Images.pacmanDots[3];
		idleDot[1]=Images.pacmanDots[4];
		idleD= new Animation(128,idleDot);
		idleW2= new Animation(128,Images.teleporter);

	}

	@Override
	public void tick(){
		int pacmanSpawnX = MapBuilder.pacmanSpawnX;
		int pacmanSpawnY = MapBuilder.pacmanSpawnY;
		PowerUpCountDown= getPowerCount();

		// Death Animation 
		if(pacmanDead) {
			if(deathCounter==3*47) {
				handler.getMusicHandler().playEffect("pacman_death.wav");
			}
			if(deathCounter<=0) {
				health--;
				facing= "Left";
				pacmanDeathAnimation.reset();
				deathCounter = 3*47;
				this.setX(pacmanSpawnX);
				this.setY(pacmanSpawnY);
				speed = 2;
				pacmanDead=false;
			}
			else {
				pacmanDeathAnimation.tick();
				deathCounter--;
			}
		}
		// Get rid of Pac Man on multiplayer when lives <= 0
		handler.getPacManState();
		if(health<=0 && PacManState.isMultiplayer()) {
			this.setX(-5000000);
			this.setY(0);
			facing="Left";
		} 

		//Ticks para la animacion de los Dots
		if(timeD==0) {
			idleD.tick();
			timeD=30;
		} else {
			timeD--;
		}
		if (timeB==0) {
			idleB.tick();
			timeB=10;
		} else {
			timeB--;
		}
		if(timeW==0) {
			idleW2.tick();
			timeW=9;
		} else {
			timeW--;
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
		
		// Multiplayerr controls
		if(PacManState.isMultiplayer() && PacMan.getHealth() > 0) {
			if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_D) && !turnFlag && checkPreHorizontalCollision("Right")){
				facing = "Right";
				turnFlag = true;
				turnCooldown = 15;
			}else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_A) && !turnFlag&& checkPreHorizontalCollision("Left")){
				facing = "Left";
				turnFlag = true;
				turnCooldown = 15;
			}else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W) && !turnFlag&& checkPreVerticalCollisions("Up")){
				facing = "Up";
				turnFlag = true;
				turnCooldown = 15;
			}else if ( handler.getKeyManager().keyJustPressed(KeyEvent.VK_S) && !turnFlag&& checkPreVerticalCollisions("Down")){
				facing = "Down";
				turnFlag = true;
				turnCooldown = 15;
			}
		}
		
		// Single player controls
		else {
			if (((handler.getKeyManager().keyJustPressed(KeyEvent.VK_D)|| handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)) && !turnFlag && checkPreHorizontalCollision("Right"))){
				facing = "Right";
				turnFlag = true;
				turnCooldown = 15;
			}else if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_A) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)) && !turnFlag&& checkPreHorizontalCollision("Left")){
				facing = "Left";
				turnFlag = true;
				turnCooldown = 15;
			}else if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_W) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) && !turnFlag&& checkPreVerticalCollisions("Up")){
				facing = "Up";
				turnFlag = true;
				turnCooldown = 15;
			}else if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_S) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) && !turnFlag&& checkPreVerticalCollisions("Down")){
				facing = "Down";
				turnFlag = true;
				turnCooldown = 15;
			}

		}
		// Exchange Health
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_U) && PacMan.getHealth() >=0 && MsPacMan.getHealth()>1 && PacMan.getHealth() < 3 && PacManState.isMultiplayer()) {
			PacMan.addHealth(1);
			MsPacMan.removeHealth(1);
			handler.getMusicHandler().playEffect("pacman_extrapac.wav");
			handler.getPacman().setX(MapBuilder.pacmanSpawnX);
			handler.getPacman().setY(MapBuilder.pacmanSpawnY);
			PacMan.setSpeed(2);
		} 
		else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_I) && MsPacMan.getHealth() >=0 && PacMan.getHealth()>1 && MsPacMan.getHealth() < 3 && PacManState.isMultiplayer()) {
			MsPacMan.addHealth(1);
			PacMan.removeHealth(1);
			handler.getMusicHandler().playEffect("pacman_extrapac.wav");
			handler.getMsPacman().setX(MapBuilder.mspacmanSpawnX);
			handler.getMsPacman().setY(MapBuilder.mspacmanSpawnY);
			MsPacMan.setSpeed(2);
		}


		if (facing.equals("Right") || facing.equals("Left")){
			checkHorizontalCollision();
		}else{
			checkVerticalCollisions();
		}
	}


	public void checkVerticalCollisions() {
		PacMan pacman = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();
		ArrayList<BaseDynamic> warpers = handler.getMap().getWarpersOnMap();
		boolean pacmanDies = false;
		boolean toUp = moving && facing.equals("Up");

		Rectangle pacmanBounds = toUp ? pacman.getTopBounds() : pacman.getBottomBounds();

		velY = speed;
		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
				if (pacmanBounds.intersects(brickBounds)) {
					velY = 0;
					if (toUp)
						pacman.setY(brick.getY() + pacman.getDimension().height);
					else
						pacman.setY(brick.getY() - brick.getDimension().height);
				}
			}
		}
		//PacMan can't enter the cage
		if(facing.equals("Down") && (this.x <= MapBuilder.SpawnerX+ 16 && this.x >= MapBuilder.SpawnerX - 16) && this.y + 24 >= MapBuilder.SpawnerY - 28 && this.y + 28 <= MapBuilder.SpawnerY) {
			velY=0;
		}	
		for(BaseDynamic enemy : enemies){
			Rectangle enemyBounds = !toUp ? enemy.getTopBounds() : enemy.getBottomBounds();
			if (pacmanBounds.intersects(enemyBounds) && !handler.getPacManState().getGhostEdible()) {
				speed = 0;
				pacmanDies = true;
				break;	
			}

			//timer for PacmanPowerUp (5 sec aprox.)
			if(handler.getPacManState().getGhostEdible()) {
				if(getPowerCount()==1) {
					Ghost.setBAnim(true);
					Ghost.setPAnim(true);
					Ghost.setIAnim(true);
					Ghost.setCAnim(true);
				}
				if(getPowerCount()<=0) {
					setPowerCount(0);
					handler.getPacManState().setGhostEdible(false);
				} else {
					setPowerCount(PowerUpCountDown--);
				} 
			}
		}
		if(pacmanDies) {
			pacmanDead = true;
			handler.getMap().reset();
		}
		
		// Teleport PacMan
		for(BaseDynamic warper: warpers) {
			Rectangle warperBounds = !toUp ? warper.getTopBounds() : warper.getBottomBounds();
			if(warper instanceof Warper) {
				if(pacmanBounds.intersects(warperBounds)) {
					if(pacman.getX()<MapBuilder.SpawnerX) {
						// PacMan is on the left of map
						pacman.setX(MapBuilder.rightWarperX-20);
						pacman.setY(MapBuilder.rightWarperY);
					}else { // Right of the map
						pacman.setX(MapBuilder.leftWarperX+20);
						pacman.setY(MapBuilder.leftWarperY);
					}
				}
			}
		}
	}

	public boolean checkPreVerticalCollisions(String facing) {
		PacMan pacman = this;;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();        
		boolean toUp = moving && facing.equals("Up");
		Rectangle pacmanBounds = toUp ? pacman.getTopBounds() : pacman.getBottomBounds();

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
		PacMan pacman = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();
		ArrayList<BaseDynamic> warpers = handler.getMap().getWarpersOnMap();
		velX = speed;
		boolean pacmanDies = false;
		boolean toRight = moving && facing.equals("Right");

		Rectangle pacmanBounds = toRight ? pacman.getRightBounds() : pacman.getLeftBounds();

		for(BaseDynamic enemy : enemies){
			Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
			if (pacmanBounds.intersects(enemyBounds) && !handler.getPacManState().getGhostEdible()) {
				speed = 0;
				pacmanDies = true;
				break;
			}
			//PacmanPowerUp timer (5sec aprox.)
			if(handler.getPacManState().getGhostEdible()) {
				if(getPowerCount()==1) {
					Ghost.setBAnim(true);
					Ghost.setPAnim(true);
					Ghost.setIAnim(true);
					Ghost.setCAnim(true);
				}
				if(getPowerCount()<=0) {
					setPowerCount(0);
					handler.getPacManState().setGhostEdible(false);
				} else {
					setPowerCount(PowerUpCountDown--);
				}
			}
		}
		for(BaseDynamic warper: warpers) {
			Rectangle warperBounds = !toRight ? warper.getRightBounds() : warper.getLeftBounds();
			if(warper instanceof Warper) {
				if(pacmanBounds.intersects(warperBounds)) {
					if(pacman.getX()<MapBuilder.SpawnerX) {
						// PacMan is on the left of map
						pacman.setX(MapBuilder.rightWarperX-20);
						pacman.setY(MapBuilder.rightWarperY);
					}else { // Right of the map
						pacman.setX(MapBuilder.leftWarperX+20);
						pacman.setY(MapBuilder.leftWarperY);
					}
				}
			}
		}
		if(pacmanDies) {
			handler.getMap().reset();
			pacmanDead = true;
		}else {

			for (BaseStatic brick : bricks) {
				if (brick instanceof BoundBlock) {
					Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
					if (pacmanBounds.intersects(brickBounds)) {
						velX = 0;
						if (toRight)
							pacman.setX(brick.getX() - pacman.getDimension().width);
						else
							pacman.setX(brick.getX() + brick.getDimension().width);
					}
				}
			}
		}
	}


	public boolean checkPreHorizontalCollision(String facing){
		PacMan pacman = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		velX = speed;
		boolean toRight = moving && facing.equals("Right");

		Rectangle pacmanBounds = toRight ? pacman.getRightBounds() : pacman.getLeftBounds();

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
		PacMan.health = health;
	}
	public static void addHealth(int health) {
		PacMan.health += health;
	}
	public static void removeHealth(int health) {
		PacMan.health -= health;
	}

	/**
	 * @return the width
	 */
	public static int getPWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public static void setPWidth(int width) {
		PacMan.width = width;
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
		PacMan.pacmanDeathAnimation = pacmanDeathAnimation;
	}


	/**
	 * @return the height
	 */
	public static int getPHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public static void setPHeight(int height) {
		PacMan.height = height;
	}

	/**
	 * @return the pacmanDead
	 */
	public static boolean isPacmanDead() {
		return pacmanDead;
	}


	/**
	 * @param pacmanDead the pacmanDead to set
	 */
	public static void setPacmanDead(boolean pacmanDead) {
		PacMan.pacmanDead = pacmanDead;
	}


	/**
	 * @return the speed
	 */
	public static  double getSpeed() {
		return speed;
	}


	/**
	 * @param speed the speed to set
	 */
	public static void setSpeed(double speed) {
		PacMan.speed = speed;
	}

	//Timer for PowerUp
	public static int getPowerCount() {
		return PowerCount;
	}
	public static void setPowerCount(int PowerCount) {
		PacMan.PowerCount = PowerCount;
	}


}