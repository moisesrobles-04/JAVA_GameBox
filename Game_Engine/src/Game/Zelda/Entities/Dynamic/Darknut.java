package Game.Zelda.Entities.Dynamic;

import static Game.Zelda.Entities.Dynamic.Direction.DOWN;
import static Game.Zelda.Entities.Dynamic.Direction.UP;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Main.Handler;
import Resources.Animation;
import Resources.Images;


public class Darknut extends BaseMovingEntity {
	private final int animSpeed = 120;
	public int anim=20, timer=50, attacking, hit=1, Slash=60*2;
	public boolean Move=false, Attack=false;
	Random movement= new Random();
	public Rectangle swordBounds;
	Animation AttackAnimation;
	
	public Darknut(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		BufferedImage[] animList = new BufferedImage[2];
		BufferedImage[] attackanim= new BufferedImage[3];
		animList[0] = sprite[0];
		animList[1] = sprite[1];
		attackanim[0] = Images.flipVertical(Images.DarkNutSword[0]);
		attackanim[1] = Images.flipVertical(Images.DarkNutSword[1]);
		attackanim[2] = Images.flipVertical(Images.DarkNutSword[2]);
		animation = new Animation(animSpeed,animList);
		AttackAnimation= new Animation(animSpeed,attackanim);
		health = 3;
	}

	@Override
	public void tick() {
		if(anim<=0) {
			AttackAnimation.tick();
			animation.tick();
			anim=20;
		} else {
			anim--;
		}
		bounds = new Rectangle(x,y,width,height);
//		swordBounds = new Rectangle(x+5,y,width,height);
		changeIntersectingBounds();

		if(timer<=0) {
			attacking= movement.nextInt(2)+1;
			//Darknut moves Left or Right
			switch(attacking) {
			case(1):
				if(handler.getZeldaGameState().link.x<x) {
					direction=Direction.LEFT;
				} else {
					direction=Direction.RIGHT;
				}
			Move=true;
			break;
			//Darknut moves Up or Down
			case(2):
				if(handler.getZeldaGameState().link.y<y) {
					direction = UP;
				} else {
					direction = DOWN;
				}
			Move=true;
			break;
			}
		}else {
			timer--;
		}
		if(Move) {
			switch(direction) {
			case UP:
				BufferedImage[] animList = new BufferedImage[2];
				BufferedImage[] attackanim= new BufferedImage[3];
				animList[0] = Images.DarkNut[2];
				animList[1] = Images.DarkNut[3];
				attackanim[0] = Images.DarkNutSword[0];
				attackanim[1] = Images.DarkNutSword[1];
				attackanim[2] = Images.DarkNutSword[2];
				animation = new Animation(animSpeed,animList);
				AttackAnimation= new Animation(animSpeed,attackanim);
				animation.tick();
				y-=10;
				Move=false;
				break;
			case DOWN:
				animList = new BufferedImage[2];
				attackanim= new BufferedImage[3];
				animList[0] = Images.DarkNut[0];
				animList[1] = Images.DarkNut[1];
				swordBounds = new Rectangle(x,y+1,width,height);
				attackanim[0] = Images.flipVertical(Images.DarkNutSword[0]);
				attackanim[1] = Images.flipVertical(Images.DarkNutSword[1]);
				attackanim[2] = Images.flipVertical(Images.DarkNutSword[2]);
				animation = new Animation(animSpeed,animList);
				AttackAnimation= new Animation(animSpeed,attackanim);
				animation.tick();
				y+=10;
				Move=false;
				break;
			case LEFT:
				animList = new BufferedImage[2];
				attackanim= new BufferedImage[3];
				animList[0] = Images.flipHorizontal(Images.DarkNut[4]);
				animList[1] = Images.flipHorizontal(Images.DarkNut[5]);
				swordBounds = new Rectangle(x+1,y,width,height);
				attackanim[0] = Images.flipHorizontal((Images.DarkNutSword[3]));
				attackanim[1] = Images.flipHorizontal((Images.DarkNutSword[4]));
				attackanim[2] = Images.flipHorizontal((Images.DarkNutSword[5]));
				animation = new Animation(animSpeed,animList);
				AttackAnimation= new Animation(animSpeed,attackanim);
				animation.tick();
				x-=10;
				Move=false;
				break;
			default:
				animList = new BufferedImage[2];
				attackanim= new BufferedImage[3];
				animList[0] = Images.DarkNut[4];
				animList[1] = Images.DarkNut[5];
				swordBounds = new Rectangle(x+1,y,width,height);
				attackanim[0] = Images.DarkNutSword[3];
				attackanim[1] = Images.DarkNutSword[4];
				attackanim[2] = Images.DarkNutSword[5];
				animation = new Animation(animSpeed,animList);
				animation.tick();
				AttackAnimation= new Animation(animSpeed,attackanim);
				x+=10;
				Move=false;
				break;
			}
			timer=30;
		}
//		if((handler.getZeldaGameState().link.x+16<x) && !Move) {
//			direction=Direction.LEFT;
//		} else if(handler.getZeldaGameState().link.y>=y && !Move) {
//			direction= DOWN;
//		} else if(handler.getZeldaGameState().link.y+16<y && !Move){
//			direction=UP;
//		} else if(handler.getZeldaGameState().link.x>=x && !Move) {
//			direction= Direction.RIGHT;
//		}
		if(timer<=0) {
			Attack=true;
		}
		if(Attack && !AttackAnimation.end) {
			switch(direction) {
			case UP:
			swordBounds = new Rectangle(x,y-height,width,height);
			break;
			case DOWN:
				swordBounds = new Rectangle(x,y+height,width,height);
				break;
			case LEFT:
				swordBounds = new Rectangle(x-width,y,width,height);
				break;
			default:
				swordBounds = new Rectangle(x+width,y,width,height);
				break;
			}

		}
		if(Slash<=0) {
			AttackAnimation.reset();
			Attack=false;
			Slash=60*2;
		} else {
			Slash--;
		}
		
		for (SolidStaticEntities objects : handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
			if (!(objects instanceof SectionDoor) && objects.bounds.intersects(interactBounds)) {
				//dont move
				return;
			}
		}

	}

	@Override
	public void render(Graphics g) {
		if(ZeldaGameState.DeBug) {
			g.setColor(Color.red);
			((Graphics2D) g).draw(bounds);
			if(Attack) {
				g.setColor(Color.BLUE);
				((Graphics2D) g).draw(swordBounds);
			}
		}
		g.drawImage(animation.getCurrentFrame(),x, y, width , height, null);
		if(Attack) {
			switch(direction){
			case UP:
				g.drawImage(AttackAnimation.getCurrentFrame(), x+16, y-height+16, 16, 32, null);
				break;
				case DOWN:
					g.drawImage(AttackAnimation.getCurrentFrame(), x+16, y+height-16, 16, 32, null);
					break;
				case LEFT:
					g.drawImage(AttackAnimation.getCurrentFrame(), x-width+16, y-height+16*4, 32,24, null);
					break;
				default:
					g.drawImage(AttackAnimation.getCurrentFrame(), x+width, y+16, 32, 24, null);
					break;
				}
		}
	}
}
