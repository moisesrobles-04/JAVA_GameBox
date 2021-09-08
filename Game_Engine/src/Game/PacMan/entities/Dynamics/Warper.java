package Game.PacMan.entities.Dynamics;

import Main.Handler;
import Resources.Images;



/* 
 * Works like the original PacMan teleporters
 * 
 * When an entity touches it, its X and Y gets changed to the other teleporter
 * 
 * Can be placed in different x's and y's
 */
public class Warper extends BaseDynamic{
	Handler handler;
	public Warper(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.teleporter[0]);
	}
}