package Game.PacMan.entities.Statics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Handler;
import Resources.Animation;
import Resources.Images;


public class BigDot extends BaseStatic{
	public BigDot(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.pacmanDots[0]);

	}

}

