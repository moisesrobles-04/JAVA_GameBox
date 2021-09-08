package Game.Zelda.Entities.Dynamic;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Statics.DungeonDoor;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SilverSword;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Game.Zelda.Entities.Statics.Sword;
import Game.Zelda.Entities.Statics.TeleporterDoor;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static Game.GameStates.Zelda.ZeldaGameState.worldScale;
import static Game.Zelda.Entities.Dynamic.Direction.DOWN;
import static Game.Zelda.Entities.Dynamic.Direction.UP;

/**
 * Created by AlexVR on 3/15/2020
 */
public class Link extends BaseMovingEntity {


	private final int animSpeed = 120;
	public int newMapX=0,newMapY=0,xExtraCounter=0,yExtraCounter=0, damagetimer=1, posX, posY, spawnSpeed, celebrationCooldown = 60, attackedCooldown=180, maxHealth;
	public boolean movingMap = false, attack=false, hasSword=false, hasSilverSword = false, attacking=false, celebrating = false, hit=false, playedAttackEffect = false;
	Direction movingTo;
	Animation attackAnimation, damagedAnimation;
	public Rectangle swordBounds, lightningBounds;
	Image celebratingTo;

	public Link(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		speed = 4;
		spawnSpeed = speed;
		health = 3;
		maxHealth = health;
		BufferedImage[] animList = new BufferedImage[2];
		animList[0] = sprite[4];
		animList[1] = sprite[5];
		BufferedImage[] attackAnim = new BufferedImage[4];
		attackAnim = Images.zeldaLinkSwordUpFrames;
		BufferedImage[] damaged= new BufferedImage[4];
		damaged= Images.zeldaLinkdamagedUpFrames;
		damagedAnimation= new Animation(animSpeed,damaged);
		attackAnimation = new Animation(animSpeed,attackAnim);
		animation = new Animation(animSpeed,animList);
	}

	@Override
	public void tick() {
		handler.getZeldaGameState();
		if(ZeldaGameState.DeBug) {
			//Debug Commands for health
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_H)) {
				health++;//Increase health
				if(health >= maxHealth) {
					maxHealth++;
				}
			}
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_L)){
				health--;//Decrease health
			}
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_NUMPAD5)) {
				System.out.println("Link X: " + x);
				System.out.println("Link Y: " + y);
			}
		}
		if(celebrating) {
			if(celebrationCooldown<=0) {
				celebrating = false;
				celebrationCooldown = 60;
			}else {
				celebrationCooldown--;
				speed = 0;
			}
		}
		if(hasSword) {
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !celebrating && !hit) {
				if(!playedAttackEffect) {
					if(hasSilverSword) {	
						handler.getMusicHandler().playEffect("LOZ_Sword_Shoot.wav");
					}
					handler.getMusicHandler().playEffect("LOZ_Sword_Slash.wav");
					
					playedAttackEffect = true;
				}
				attacking = true;
			}
			if(attacking) {
				if(attackAnimation.end) {
					ZeldaGameState.hurt = false;
					playedAttackEffect = false;
					attacking = false;
					speed = spawnSpeed;
					attackAnimation.reset();

				}else {
					switch(direction) {
					case RIGHT:
						swordBounds = new Rectangle(x+width,y,width,height);
						if(hasSilverSword) {
							lightningBounds = new Rectangle(swordBounds.x + width, y + (height/4), 2 * width * ZeldaGameState.worldScale, height/2);
						}
						break;
					case LEFT:
						swordBounds = new Rectangle(x-width,y,width,height);
						if(hasSilverSword) {
							lightningBounds = new Rectangle(swordBounds.x-(2 * width * ZeldaGameState.worldScale), y + (height/4), width * ZeldaGameState.worldScale * 2, height/2);
						}
						break;
					case UP:
						swordBounds = new Rectangle(x-(width/8),y-height,width,height);
						if(hasSilverSword) {
							lightningBounds = new Rectangle(x + (width/4),swordBounds.y-(height * ZeldaGameState.worldScale * 2),width/2, 2 * height * ZeldaGameState.worldScale);
						}
						break;
					default:
						swordBounds = new Rectangle(x,y+height,width,height);
						if(hasSilverSword) {
							lightningBounds = new Rectangle(x + (width/4),swordBounds.y + height,width/2,2 *height * ZeldaGameState.worldScale);
						}
						break;
					}
					attackAnimation.tick();
					speed = 0;
				}
			}
		}
		if(hit) {
			if(attackedCooldown<=0) {
				attackedCooldown=180;
				hit=false;
			} else {
				attackedCooldown--;
			}
			damagedAnimation.tick();
		}
		if (movingMap){
			handler.getZeldaGameState().addWorldObjects();
			switch (movingTo) {
			case RIGHT:
				handler.getZeldaGameState().cameraOffsetX+=5;
				newMapX+=5;
				if(newMapX>=0) {
					newMapX=0;
				}
				if (xExtraCounter>0){
					x+=2;
					xExtraCounter--;
					animation.tick();
				} else{
					x-=5;
				}
				break;
			case LEFT:
				handler.getZeldaGameState().cameraOffsetX-=5;
				newMapX-=5;
				if(newMapX<=0) {
					newMapX=0;
				}
				if (xExtraCounter>0){
					x-=2;
					xExtraCounter--;
					animation.tick();

				}else{
					x+=5;
				}
				break;
			case UP:
				handler.getZeldaGameState().cameraOffsetY-=5;
				newMapY+=5;
				if(newMapY>=0) {
					newMapY=0;
				}
				if (yExtraCounter>0){
					y-=1;
					yExtraCounter--;
					animation.tick();
				}else{
					y+=5;
				}
				break;
			case DOWN:
				handler.getZeldaGameState().cameraOffsetY+=5;
				newMapY-=5;
				if(newMapY<=0) {
					newMapY=0;
				}
				if (yExtraCounter>0){
					y+=1;
					yExtraCounter--;
					animation.tick();
				}else{
					y-=5;
				}
				break;
			}
			bounds = new Rectangle(x,y,width,height);
			changeIntersectingBounds();
			if (newMapX == 0 && newMapY == 0){
				movingMap = false;
				movingTo = null;
				newMapX = 0;
				newMapY = 0;
			}
		}else {
			if (handler.getKeyManager().up && !attacking && !celebrating) {
				if (direction != UP) {
					BufferedImage[] animList = new BufferedImage[2];
					BufferedImage[] attackAnim = new BufferedImage[4];
					BufferedImage[] damaged= new BufferedImage[4];
					animList[0] = sprites[4];
					animList[1] = sprites[5];
					if(!hasSilverSword) {
						attackAnim = Images.zeldaLinkSwordUpFrames;
					}else {
						attackAnim = Images.zeldaLinkSilverSwordUpFrames;
					}
					
					damaged= Images.zeldaLinkdamagedUpFrames;
					damagedAnimation= new Animation(animSpeed,damaged);
					attackAnimation = new Animation(animSpeed,attackAnim);
					animation = new Animation(animSpeed, animList);
					direction = UP;
					sprite = sprites[4];
				}
				animation.tick();
				move(direction);
				
			} else if (handler.getKeyManager().down && !attacking && !celebrating) {
				if (direction != DOWN) {
					BufferedImage[] animList = new BufferedImage[2];
					BufferedImage[] attackAnim = new BufferedImage[4];
					BufferedImage[] damaged= new BufferedImage[4];
					animList[0] = sprites[0];
					animList[1] = sprites[1];
					if(!hasSilverSword) {
						attackAnim = Images.zeldaLinkSwordDownFrames;
					}else {
						attackAnim = Images.zeldaLinkSilverSwordDownFrames;
					}
					
					damaged=Images.zeldaLinkdamagedDownFrames;
					damagedAnimation= new Animation(animSpeed,damaged);
					attackAnimation = new Animation(animSpeed,attackAnim);
					animation = new Animation(animSpeed, animList);
					direction = DOWN;
					sprite = sprites[0];
				}

				animation.tick();
				move(direction);
			} else if (handler.getKeyManager().left && !attacking && !celebrating) {
				if (direction != Direction.LEFT) {
					BufferedImage[] animList = new BufferedImage[2];
					BufferedImage[] attackAnim = new BufferedImage[4];
					BufferedImage[] damaged= new BufferedImage[4];
					animList[0] = Images.flipHorizontal(sprites[2]);
					animList[1] = Images.flipHorizontal(sprites[3]);
					if(!hasSilverSword) {
						attackAnim[0] = Images.flipHorizontal(Images.zeldaLinkSwordLRFrames[0]);
						attackAnim[1] = Images.flipHorizontal(Images.zeldaLinkSwordLRFrames[1]);
						attackAnim[2] = Images.flipHorizontal(Images.zeldaLinkSwordLRFrames[2]);
						attackAnim[3] = Images.flipHorizontal(Images.zeldaLinkSwordLRFrames[3]);
					}else {
						attackAnim[0] = Images.flipHorizontal(Images.zeldaLinkSilverSwordLRFrames[0]);
						attackAnim[1] = Images.flipHorizontal(Images.zeldaLinkSilverSwordLRFrames[1]);
						attackAnim[2] = Images.flipHorizontal(Images.zeldaLinkSilverSwordLRFrames[2]);
						attackAnim[3] = Images.flipHorizontal(Images.zeldaLinkSilverSwordLRFrames[3]);
					}
					
					damaged[0]=Images.flipHorizontal(Images.zeldaLinkdamagedLRFrames[0]);
					damaged[1]=Images.flipHorizontal(Images.zeldaLinkdamagedLRFrames[1]);
					damaged[2]=Images.flipHorizontal(Images.zeldaLinkdamagedLRFrames[2]);
					damaged[3]=Images.flipHorizontal(Images.zeldaLinkdamagedLRFrames[3]);
					damagedAnimation= new Animation(animSpeed,damaged);
					attackAnimation = new Animation(animSpeed,attackAnim);
					animation = new Animation(animSpeed, animList);
					direction = Direction.LEFT;
					sprite = Images.flipHorizontal(sprites[3]);
				}

				animation.tick();
				move(direction);
			} else if (handler.getKeyManager().right && !attacking && !celebrating) {
				if (direction != Direction.RIGHT) {
					BufferedImage[] animList = new BufferedImage[2];
					BufferedImage[] attackAnim = new BufferedImage[4];
					BufferedImage[] damaged= new BufferedImage[4];
					animList[0] = sprites[2];
					animList[1] = sprites[3];
					if(!hasSilverSword) {
						attackAnim = Images.zeldaLinkSwordLRFrames;
					}else {
						attackAnim = Images.zeldaLinkSilverSwordLRFrames;
					}
					damaged=Images.zeldaLinkdamagedLRFrames;
					damagedAnimation = new Animation(animSpeed,damaged);
					attackAnimation = new Animation(animSpeed,attackAnim);
					animation = new Animation(animSpeed, animList);
					direction = Direction.RIGHT;
					sprite = (sprites[3]);
				}
				animation.tick();
				move(direction);
			} else {
				moving = false;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(celebrating) {
			g.drawImage(Images.zeldaLinkCelebration, x, y, width, height, null);
			g.drawImage(celebratingTo, x, y - (height), Images.zeldaItems[0].getWidth()*2, Images.zeldaItems[0].getHeight()*2, null);

		}else if(attacking) {
			handler.getZeldaGameState();
			if(ZeldaGameState.DeBug) {
				g.setColor(Color.red);
				((Graphics2D) g).draw(swordBounds);
				if(hasSilverSword) {
					g.setColor(Color.blue);
					((Graphics2D) g).draw(lightningBounds);
				}
			}
			switch(direction) {
			case LEFT:
				g.drawImage(attackAnimation.getCurrentFrame(),x +(width - (attackAnimation.getCurrentFrame().getWidth()*worldScale)) , y, attackAnimation.getCurrentFrame().getWidth()*worldScale , attackAnimation.getCurrentFrame().getHeight()*worldScale  , null);
				if(hasSilverSword) {
					g.drawImage(Images.flipHorizontal(Images.zeldaLinkLightning[1]), lightningBounds.x ,lightningBounds.y,lightningBounds.width,lightningBounds.height, null);
				}
				break;
			case UP:
				g.drawImage(attackAnimation.getCurrentFrame(),x , y+ (height - (attackAnimation.getCurrentFrame().getHeight()*worldScale)), attackAnimation.getCurrentFrame().getWidth()*worldScale , attackAnimation.getCurrentFrame().getHeight()*worldScale , null);
				if(hasSilverSword) {
					g.drawImage(Images.zeldaLinkLightning[0], lightningBounds.x ,lightningBounds.y,lightningBounds.width,lightningBounds.height, null);
				}
				break;
			case RIGHT:
				g.drawImage(attackAnimation.getCurrentFrame(),x , y, attackAnimation.getCurrentFrame().getWidth()*worldScale , attackAnimation.getCurrentFrame().getHeight()*worldScale  , null);
				if(hasSilverSword) {
					g.drawImage(Images.zeldaLinkLightning[1], lightningBounds.x ,lightningBounds.y,lightningBounds.width,lightningBounds.height, null);
				}
				break;
			default:
				g.drawImage(attackAnimation.getCurrentFrame(),x , y, attackAnimation.getCurrentFrame().getWidth()*worldScale , attackAnimation.getCurrentFrame().getHeight()*worldScale  , null);
				if(hasSilverSword) {
					g.drawImage(Images.flipVertical(Images.zeldaLinkLightning[0]), lightningBounds.x ,lightningBounds.y,lightningBounds.width,lightningBounds.height, null);
				}
				break;
					

			}

		}else if (moving && !hit) {
			if(!attacking && !celebrating) {
				g.drawImage(animation.getCurrentFrame(),x , y, width , height  , null);
			}

		}else if(hit) {
			g.drawImage(damagedAnimation.getCurrentFrame(),x,y,width,height,null);
		}else {
			if (movingMap){
				g.drawImage(animation.getCurrentFrame(),x , y, width, height  , null);
			}
			g.drawImage(sprite, x , y, width , height , null);
		}
	}
	@Override
	public void move(Direction direction) {
		moving = true;
		speed=4;
		changeIntersectingBounds();
		//chack for collisions
		if (ZeldaGameState.inCave){
			for (SolidStaticEntities objects : handler.getZeldaGameState().caveObjects) {
				if((objects instanceof Sword) && objects.bounds.intersects(bounds)) {
					celebrating = true;
					hasSword = true;
					celebratingTo = Images.zeldaItems[0];
					if(objects instanceof SilverSword) {
						hasSilverSword = true;
						celebratingTo = Images.zeldaItems[2];
					}
					handler.getMusicHandler().playEffect("LOZ_Fanfare.wav");
					objects.width = 0;
					objects.height = 0;
					objects.bounds = new Rectangle(objects.x,objects.y,0,0);
				}
				if ((objects instanceof DungeonDoor) && objects.bounds.intersects(bounds) && direction == ((DungeonDoor) objects).direction) {
					if (((DungeonDoor) objects).name.equals("caveStartLeave")) {
						ZeldaGameState.inCave = false;
						ZeldaGameState.playmusic=true;
						x = ((DungeonDoor) objects).nLX;
						y = ((DungeonDoor) objects).nLY;
						direction = DOWN;
					}
				} else {
					handler.getZeldaGameState();
					if (!(objects instanceof DungeonDoor)  && (objects.bounds.intersects(interactBounds) && 
							y<=16*7+10+ZeldaGameState.stageHeight)) {
						//dont move
						return;
					}
					if(y>16*7+10+ZeldaGameState.stageHeight) {
						x=(7 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset+32;
					}
				}
			}
		}
		else {
			for (SolidStaticEntities objects : handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
				if((objects instanceof TeleporterDoor ) && objects.bounds.intersects(bounds) && direction == ((TeleporterDoor) objects).direction) {
					handler.getZeldaGameState().addWorldObjects();
					handler.getZeldaGameState().cameraOffsetX+= (((TeleporterDoor) objects).toMapX - handler.getZeldaGameState().mapX) * (256.6*worldScale);
					handler.getZeldaGameState().mapX = ((TeleporterDoor) objects).toMapX;
					
					handler.getZeldaGameState().cameraOffsetY+= (((TeleporterDoor) objects).toMapY - handler.getZeldaGameState().mapY) * (176.6*worldScale);
					handler.getZeldaGameState().mapY = ((TeleporterDoor) objects).toMapY;
					
					x+=((TeleporterDoor) objects).toNewX;
					y+=((TeleporterDoor) objects).toNewY;
					if(((TeleporterDoor) objects).swapsMusic) {handler.getZeldaGameState().swapMusic();}
						
				}else
				if ((objects instanceof SectionDoor) && objects.bounds.intersects(bounds) && direction == ((SectionDoor) objects).direction) {
					if (!(objects instanceof DungeonDoor)) {
						movingMap = true;
						movingTo = ((SectionDoor) objects).direction;
						switch (((SectionDoor) objects).direction) {
						case RIGHT:
							newMapX = -(((handler.getZeldaGameState().mapWidth) + 1) * worldScale);
							newMapY = 0;
							handler.getZeldaGameState().mapX++;
							xExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case LEFT:
							newMapX = (((handler.getZeldaGameState().mapWidth) + 1) * worldScale);
							newMapY = 0;
							handler.getZeldaGameState().mapX--;
							xExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case UP:
							newMapX = 0;
							newMapY = -(((handler.getZeldaGameState().mapHeight) + 1) * worldScale);
							handler.getZeldaGameState().mapY--;
							yExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case DOWN:
							newMapX = 0;
							newMapY = (((handler.getZeldaGameState().mapHeight) + 1) * worldScale);
							handler.getZeldaGameState().mapY++;
							yExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						}
						return;
					}
					else {
						if (((DungeonDoor) objects).name.equals("caveStartEnter")) {
							ZeldaGameState.inCave = true;
							x = ((DungeonDoor) objects).nLX;
							y = ((DungeonDoor) objects).nLY;
							direction = UP;
						}
					}
				}
				else if (!(objects instanceof SectionDoor) && objects.bounds.intersects(interactBounds) && !(objects instanceof TeleporterDoor)) {
					//dont move
					return;
				}else if((objects instanceof Sword) && objects.bounds.intersects(bounds)) {
					celebrating = true;
					hasSword = true;
					celebratingTo = Images.zeldaItems[0];
					if(objects instanceof SilverSword) {
						hasSilverSword = true;
						celebratingTo = Images.zeldaItems[2];
					}
					handler.getMusicHandler().playEffect("LOZ_Fanfare.wav");
					objects.width = 0;
					objects.height = 0;
					objects.bounds = new Rectangle(objects.x,objects.y,0,0);
				}
			}
		}
		switch (direction) {
		case RIGHT:
			x += speed;
			break;
		case LEFT:
			x -= speed;

			break;
		case UP:
			y -= speed;
			break;
		case DOWN:
			y += speed;

			break;
		}
		bounds.x = x;
		bounds.y = y;
		changeIntersectingBounds();
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}
}
