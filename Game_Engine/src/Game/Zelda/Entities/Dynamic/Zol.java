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

public class Zol extends BaseMovingEntity {

	private final int animSpeed = 120;
	public int anim=20, timer=20, attacking, hit=1;
	public boolean attack=false;
	Random movement= new Random();

	public Zol(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		BufferedImage[] animList = new BufferedImage[2];
		animList[0] = sprite[0];
		animList[1] = sprite[1];
		animation = new Animation(animSpeed,animList);
		health = 1;
	}
	@Override
	public void tick() {
		if(anim<=0) {
			animation.tick();
			anim=20;
		} else {
			anim--;
		}
		bounds = new Rectangle(x,y,width,height);
		changeIntersectingBounds();
		if(timer<=0) {
			attacking=movement.nextInt(4);
			//Zol moves Left or Right
			switch(attacking) {
			case(0):
				direction=Direction.LEFT;
			break;
			case(1):
				direction=Direction.RIGHT;
			break;
			//Zol moves Up or Down
			case(2):
				direction = UP;
			break;
			case(3):
				direction = DOWN;
			break;
			}			
			attack=true;
		}else {
			timer--;
		}
		if(attack) {
			switch(direction) {
			case UP:
				y-=10;
				timer=20;
				attack=false;
				break;
			case DOWN:
				y+=10;
				timer=20;
				attack=false;
				break;
			case LEFT:
				x-=10;
				timer=20;
				attack=false;
				break;
			case RIGHT:
				x+=10;
				timer=20;
				attack=false;
				break;
			}
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
		}
		g.drawImage(animation.getCurrentFrame(),x , y, width , height  , null);
	}
}
