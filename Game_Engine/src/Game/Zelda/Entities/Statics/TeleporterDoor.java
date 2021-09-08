package Game.Zelda.Entities.Statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Dynamic.Direction;
import Main.Handler;

public class TeleporterDoor extends SolidStaticEntities {
	public Direction direction;
	public int toMapX;
	public int toMapY;
	public int toNewX;
	public int toNewY;
	public boolean swapsMusic = true;
	public TeleporterDoor(int x, int y,int widht, int height, int toMapX, int toMapY, int toNewX, int toNewY, Direction direction, Handler handler) {
		super(x, y, null, handler);
		this.toMapX = toMapX;
		this.toMapY = toMapY;
		this.toNewX = (toNewX/3) * ZeldaGameState.worldScale;
		this.toNewY = (toNewY/3) * ZeldaGameState.worldScale;
		this.width = widht;
		this.height = height;
		bounds = new Rectangle((x * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height);
		this.direction = direction;
	}
	public TeleporterDoor(int x, int y,int widht, int height, int toMapX, int toMapY, int toNewX, int toNewY, boolean swapsMusic, Direction direction, Handler handler) {
		super(x, y, null, handler);
		this.toMapX = toMapX;
		this.toMapY = toMapY;
		this.toNewX = (toNewX/3) * ZeldaGameState.worldScale;
		this.toNewY = (toNewY/3) * ZeldaGameState.worldScale;
		this.width = widht;
		this.height = height;
		bounds = new Rectangle((x * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height);
		this.direction = direction;
		this.swapsMusic = swapsMusic;
	}

	@Override
	public void tick() {
		super.tick();
	}
	public int getToMapX() {return toMapX;}
	public int getToMapY() {return toMapY;}
	@Override
	public void render(Graphics g) {
		if(ZeldaGameState.DeBug) {
			g.setColor(Color.RED);
			g.fillRect((x * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height);
		}


	}
}
