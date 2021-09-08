package Game.Zelda.Entities.Statics;

import Game.GameStates.Zelda.ZeldaMMGameState;
import Game.Zelda.Entities.MMBaseEntity;
import Game.Zelda.Entities.Dynamic.Direction;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MMRecursiveTile extends MMBaseEntity{



	public MMRecursiveTile(int x, int y, BufferedImage sprite, Handler handler) {
		super(x, y, sprite, handler);
		bounds= new Rectangle(x,y,width, height);
	}
	@Override
	public void tick() {
		//Non recursive
//				  if (handler.getState() instanceof ZeldaMMGameState && ((ZeldaMMGameState)handler.getState()).map.link.interactBounds.intersects(bounds) && sprite.equals(Images.zeldaTiles.get(30))){
//					  ((ZeldaMMGameState)handler.getState()).map.link.move(Direction.UP);
//			        }else if (handler.getState() instanceof ZeldaMMGameState && ((ZeldaMMGameState)handler.getState()).map.link.interactBounds.intersects(bounds) && sprite.equals(Images.zeldaTiles.get(31))){
//					  ((ZeldaMMGameState)handler.getState()).map.link.move(Direction.RIGHT);
//			        }else if (handler.getState() instanceof ZeldaMMGameState && ((ZeldaMMGameState)handler.getState()).map.link.interactBounds.intersects(bounds) && sprite.equals(Images.zeldaTiles.get(32))){
//					  ((ZeldaMMGameState)handler.getState()).map.link.move(Direction.DOWN);
//			        }else if (handler.getState() instanceof ZeldaMMGameState && ((ZeldaMMGameState)handler.getState()).map.link.interactBounds.intersects(bounds) && sprite.equals(Images.zeldaTiles.get(33))){
//					  ((ZeldaMMGameState)handler.getState()).map.link.move(Direction.LEFT);
//			        }
		Direction move= ((ZeldaMMGameState)handler.getState()).map.link.direction;
		moveLinkRecursively(move);
	}

	public Direction moveLinkRecursively(Direction move) {
		if(handler.getState() instanceof ZeldaMMGameState && ((ZeldaMMGameState)handler.getState()).map.link.interactBounds.intersects(bounds)) {
			if (handler.getState() instanceof ZeldaMMGameState && ((ZeldaMMGameState)handler.getState()).map.link.interactBounds.intersects(bounds) && sprite.equals(Images.zeldaTiles.get(30))){
				((ZeldaMMGameState)handler.getState()).map.link.move(Direction.UP);
				return moveLinkRecursively(Direction.UP);
			}else if (handler.getState() instanceof ZeldaMMGameState && ((ZeldaMMGameState)handler.getState()).map.link.interactBounds.intersects(bounds) && sprite.equals(Images.zeldaTiles.get(31))){
				((ZeldaMMGameState)handler.getState()).map.link.move(Direction.RIGHT);
				return moveLinkRecursively(Direction.RIGHT);
			}else if (handler.getState() instanceof ZeldaMMGameState && ((ZeldaMMGameState)handler.getState()).map.link.interactBounds.intersects(bounds) && sprite.equals(Images.zeldaTiles.get(32))){
				((ZeldaMMGameState)handler.getState()).map.link.move(Direction.DOWN);
				return moveLinkRecursively(Direction.DOWN);
			}else if (handler.getState() instanceof ZeldaMMGameState && ((ZeldaMMGameState)handler.getState()).map.link.interactBounds.intersects(bounds) && sprite.equals(Images.zeldaTiles.get(33))){
				((ZeldaMMGameState)handler.getState()).map.link.move(Direction.LEFT);
				return moveLinkRecursively(Direction.LEFT);
			}
		}
		return null;
	}
}
