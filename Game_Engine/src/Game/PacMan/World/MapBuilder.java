package Game.PacMan.World;

import Game.GameStates.PacManState;
import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.GhostSpawner;
import Game.PacMan.entities.Dynamics.MsPacMan;
import Game.PacMan.entities.Dynamics.PacMan;
import Game.PacMan.entities.Dynamics.Warper;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BigDot;
import Game.PacMan.entities.Statics.BoundBlock;
import Game.PacMan.entities.Statics.Dot;
import Game.PacMan.entities.Statics.Fruit;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MapBuilder {
	public static int pixelMultiplier =28;//change this for size of blocks
	public static int boundBlock = new Color(0,0,0).getRGB();
	public static int pacman = new Color(255, 255,0).getRGB();
	public static int mspacman = new Color(255,174,201).getRGB();
	public static int ghostSpawner = new Color(25, 255,0).getRGB();
	public static int dotC = new Color(255, 10, 0).getRGB();
	public static int bigDotC = new Color(135, 249, 255).getRGB();
	public static int leftWarper = new Color(240, 84, 255).getRGB();
	public static int rightWarper = new Color(66, 72, 255).getRGB();
	//To get Spawner location
	public static int SpawnerX;
	public static int SpawnerY;
	//To get Warpers location
	public static int leftWarperX;
	public static int leftWarperY;
	public static int rightWarperX;
	public static int rightWarperY;

	public static int totalDots=0;
	public static int pacmanSpawnX;
	public static int pacmanSpawnY;
	public static int mspacmanSpawnX;
	public static int mspacmanSpawnY;	
	public static int level=0;

	public static Map createMap(BufferedImage mapImage, Handler handler){
		int fruitChance;
		Random random = new Random();
		Map mapInCreation = new Map(handler);
		for (int i = 0; i < mapImage.getWidth(); i++) {
			for (int j = 0; j < mapImage.getHeight(); j++) {
				int currentPixel = mapImage.getRGB(i, j);
				int xPos = i*pixelMultiplier;
				int yPos = j*pixelMultiplier;
				if(currentPixel == boundBlock){
					BaseStatic BoundBlock = new BoundBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler,getSprite(mapImage,i,j));
					mapInCreation.addBlock(BoundBlock);
				}else if(currentPixel == pacman){
					BaseDynamic PacMan = new PacMan(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(PacMan);
					handler.setPacman((Game.PacMan.entities.Dynamics.PacMan) PacMan);
					pacmanSpawnX = xPos;
					pacmanSpawnY = yPos;
					handler.getPacman();
					handler.getPacManState();

					// Add Ms PacMan
				} else if(currentPixel == mspacman) {
					BaseDynamic MPacMan = new MsPacMan(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					if(!PacManState.isMultiplayer()) {
						MPacMan.x=-50000000;
						MPacMan.y=0;
						BaseStatic dot = new Dot(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
						mapInCreation.addBlock(dot);
						totalDots++;
					}
					mapInCreation.addEnemy(MPacMan);
					handler.setMsPacman((Game.PacMan.entities.Dynamics.MsPacMan) MPacMan);
					mspacmanSpawnX=xPos;
					mspacmanSpawnY=yPos;

					// SPAWNER
				}else if(currentPixel == ghostSpawner){
					BaseDynamic ghostSpawner = new GhostSpawner(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					SpawnerX=xPos;
					SpawnerY=yPos;
					mapInCreation.addEnemy(ghostSpawner);
				}
				// Set warpers
				else if(currentPixel == leftWarper){
					Warper  warper1 = new Warper(xPos,yPos,pixelMultiplier,pixelMultiplier, handler);
					leftWarperX = xPos;
					leftWarperY = yPos;
					mapInCreation.addWarper(warper1);
				}
				else if(currentPixel == rightWarper){
					Warper warper2 = new Warper(xPos,yPos,pixelMultiplier,pixelMultiplier, handler);
					rightWarperX = xPos;
					rightWarperY = yPos;
					mapInCreation.addWarper(warper2);

				}else if(currentPixel == dotC){
					// Fruits algorithm
					fruitChance = random.nextInt(30);
					if(fruitChance == 10) {
						BaseStatic fruit = new Fruit(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
						mapInCreation.addBlock(fruit);
						totalDots++;
					}else {
						BaseStatic dot = new Dot(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
						mapInCreation.addBlock(dot);
						totalDots++;
					}
				}else if(currentPixel == bigDotC){
					BaseStatic bigDot = new BigDot(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(bigDot);
					totalDots++;
				}
			}
		}
		return mapInCreation;
	}

	/**
	 * @return the totalDots
	 */
	public static int getTotalDots() {
		return totalDots;
	}

	/**
	 * @param totalDots the totalDots to set
	 */
	public static void setTotalDots(int totalDots) {
		MapBuilder.totalDots = totalDots;
	}

	private static BufferedImage getSprite(BufferedImage mapImage, int i, int j) {
		int currentPixel = boundBlock;
		int leftPixel;
		int rightPixel;
		int upPixel;
		int downPixel;
		if (i>0) {
			leftPixel = mapImage.getRGB(i - 1, j);
		}else{
			leftPixel = pacman;

		}
		if (i<mapImage.getWidth()-1) {
			rightPixel = mapImage.getRGB(i + 1, j);
		}else{
			rightPixel= pacman;

		}
		if (j>0) {
			upPixel = mapImage.getRGB(i, j - 1);
		}else{
			upPixel = pacman;

		}
		if (j<mapImage.getHeight()-1) {
			downPixel = mapImage.getRGB(i, j + 1);
		}else{
			downPixel = pacman;

		}

		if (currentPixel != leftPixel && currentPixel != upPixel && currentPixel != downPixel && currentPixel == rightPixel){

			return Images.bound[1];
		}else if (currentPixel != leftPixel && currentPixel != upPixel && currentPixel == downPixel && currentPixel != rightPixel){

			return Images.bound[2];
		}else if (currentPixel == leftPixel && currentPixel != upPixel && currentPixel != downPixel && currentPixel != rightPixel){

			return Images.bound[3];
		}else if (currentPixel != leftPixel && currentPixel == upPixel && currentPixel != downPixel && currentPixel != rightPixel){

			return Images.bound[4];
		}else if (currentPixel != leftPixel && currentPixel == upPixel && currentPixel == downPixel && currentPixel != rightPixel){

			return Images.bound[5];
		}else if (currentPixel == leftPixel && currentPixel != upPixel && currentPixel != downPixel && currentPixel == rightPixel){

			return Images.bound[6];
		}else if (currentPixel != leftPixel && currentPixel == upPixel && currentPixel != downPixel && currentPixel == rightPixel){

			return Images.bound[7];
		}else if (currentPixel == leftPixel && currentPixel == upPixel && currentPixel != downPixel && currentPixel != rightPixel){

			return Images.bound[8];
		}else if (currentPixel != leftPixel && currentPixel != upPixel && currentPixel == downPixel && currentPixel == rightPixel){

			return Images.bound[9];
		}else if (currentPixel == leftPixel && currentPixel != upPixel && currentPixel == downPixel && currentPixel != rightPixel){

			return Images.bound[10];
		}else if (currentPixel == leftPixel && currentPixel == upPixel && currentPixel == downPixel && currentPixel == rightPixel){

			return  Images.bound[11];
		}else if (currentPixel != leftPixel && currentPixel == upPixel && currentPixel == downPixel && currentPixel == rightPixel){

			return Images.bound[12];
		}else if (currentPixel == leftPixel && currentPixel == upPixel && currentPixel == downPixel && currentPixel != rightPixel){

			return Images.bound[13];
		}else if (currentPixel == leftPixel && currentPixel != upPixel && currentPixel == downPixel && currentPixel == rightPixel){

			return Images.bound[14];
		}else if (currentPixel == leftPixel && currentPixel == upPixel && currentPixel != downPixel && currentPixel == rightPixel){

			return Images.bound[15];
		}else{

			return  Images.bound[0];
		}


	}


}