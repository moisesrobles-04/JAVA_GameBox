package Game.PacMan.entities.Dynamics;

import java.util.Random;

import Game.PacMan.World.MapBuilder;
import Main.Handler;
import Resources.Images;

public class GhostSpawner extends BaseDynamic {

	Ghost Blinky= new Ghost(MapBuilder.SpawnerX,MapBuilder.SpawnerY, MapBuilder.pixelMultiplier,MapBuilder.pixelMultiplier, handler, 0);
	Ghost Pinky= new Ghost(MapBuilder.SpawnerX,MapBuilder.SpawnerY, MapBuilder.pixelMultiplier,MapBuilder.pixelMultiplier, handler, 1);
	Ghost Inky= new Ghost(MapBuilder.SpawnerX,MapBuilder.SpawnerY, MapBuilder.pixelMultiplier,MapBuilder.pixelMultiplier, handler, 2);
	Ghost Clyde= new Ghost(MapBuilder.SpawnerX,MapBuilder.SpawnerY, MapBuilder.pixelMultiplier,MapBuilder.pixelMultiplier, handler, 3);
	public boolean BlinkyDead= false, InkyDead=false, PinkyDead=false, ClydeDead=false;
	Random random = new Random();
	
	public GhostSpawner(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.pacmanDots[2]);
	}

	// Add ghosts manually
	public void addEnemy () {
		switch (random.nextInt(4)) {
		case 1:
			handler.getMap().addEnemy(Inky);
			break;
		case 2:
			handler.getMap().addEnemy(Blinky);
			break;
			
		case 3:
			handler.getMap().addEnemy(Pinky);
			break;
			
		default:
			handler.getMap().addEnemy(Clyde);
			break;
		}
	}
	
	// Adding the 4 ghosts a the start
	public void EnemyGhosts() {
		handler.getMap().addEnemy(Inky);
		handler.getMap().addEnemy(Blinky);
		handler.getMap().addEnemy(Pinky);
		handler.getMap().addEnemy(Clyde);
	}
	
}

