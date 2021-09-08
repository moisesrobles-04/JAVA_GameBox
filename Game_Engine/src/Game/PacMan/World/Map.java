package Game.PacMan.World;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.Ghost;
import Game.PacMan.entities.Dynamics.MsPacMan;
import Game.PacMan.entities.Dynamics.PacMan;
import Game.PacMan.entities.Dynamics.Warper;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BigDot;
import Game.PacMan.entities.Statics.Dot;
import Main.Handler;
import Resources.Images;

public class Map {

	ArrayList<BaseStatic> blocksOnMap;
	ArrayList<BaseDynamic> enemiesOnMap;
	ArrayList<BaseDynamic> warpersOnMap;
	Handler handler;
	private double bottomBorder;
	private Random rand;
	private int mapBackground;

	public Map(Handler handler) {
		this.handler=handler;
		this.rand = new Random();
		this.blocksOnMap = new ArrayList<>();
		this.enemiesOnMap = new ArrayList<>();
		this.warpersOnMap = new ArrayList<>();
		bottomBorder=handler.getHeight();
		this.mapBackground = this.rand.nextInt(6);
	}

	public void addBlock(BaseStatic block){
		blocksOnMap.add(block);
	}

	public void addEnemy(BaseDynamic entity){

		enemiesOnMap.add(entity);

	}
	public void addWarper(Warper warpers){

		warpersOnMap.add(warpers);

	}

	public void drawMap(Graphics2D g2) {
		// Warpers
		for(BaseDynamic warper: warpersOnMap) {
				g2.drawImage(handler.getPacman().idleW2.getCurrentFrame(),warper.x, warper.y, warper.width, warper.height, null);
		}
		for (BaseStatic block:blocksOnMap) {
			if(block instanceof BigDot) {
				g2.drawImage(handler.getPacman().idleB.getCurrentFrame(), block.x,block.y, block.width, block.height, null);	
			}
			else if(block instanceof Dot) {
				g2.drawImage(handler.getPacman().idleD.getCurrentFrame(), block.x,block.y, block.width, block.height, null);	
			}
			else {
				g2.drawImage(block.sprite, block.x, block.y, block.width, block.height, null);
			}
		}
		for (BaseDynamic entity:enemiesOnMap) {
			if (entity instanceof PacMan) {
				if(PacMan.isPacmanDead()) {
					g2.drawImage(((PacMan) entity).getPacmanDeathAnimation().getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
				} else {
					switch (((PacMan) entity).facing){
					case "Right":
						g2.drawImage(((PacMan) entity).rightAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
						break;
					case "Left":
						g2.drawImage(((PacMan) entity).leftAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
						break;
					case "Up":
						g2.drawImage(((PacMan) entity).upAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
						break;
					case "Down":
						g2.drawImage(((PacMan) entity).downAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
						break;
					}
				}
			}else if (entity instanceof MsPacMan) {
				if(MsPacMan.isMsPacmanDead()) {
					g2.drawImage(((MsPacMan) entity).getPacmanDeathAnimation().getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
				} else {
					switch (((MsPacMan) entity).facing){
					case "Right":
						g2.drawImage(((MsPacMan) entity).rightAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
						break;
					case "Left":
						g2.drawImage(((MsPacMan) entity).leftAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
						break;
					case "Up":
						g2.drawImage(((MsPacMan) entity).upAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
						break;
					case "Down":
						g2.drawImage(((MsPacMan) entity).downAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
						break;
					}
				}
			} else if(entity instanceof Ghost) {				
				switch(((Ghost)entity).facing) {
				case "Up":
					g2.drawImage(((Ghost) entity).UpAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
					break;
				case "Down":
					g2.drawImage(((Ghost) entity).DownAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
					break;
				case "Left":
					g2.drawImage(((Ghost) entity).LeftAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
					break;
				case "Right":
					g2.drawImage(((Ghost) entity).RightAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
					break;

				}
			}
			else {
				g2.drawImage(entity.sprite, entity.x, entity.y, entity.width, entity.height, null);
			}
		}

	}

	public ArrayList<BaseStatic> getBlocksOnMap() {
		return blocksOnMap;
	}

	public ArrayList<BaseDynamic> getEnemiesOnMap() {
		return enemiesOnMap;
	}
	public ArrayList<BaseDynamic> getWarpersOnMap() {
		return warpersOnMap;
	}
	public double getBottomBorder() {
		return bottomBorder;
	}

	public void reset() {
	}
}
