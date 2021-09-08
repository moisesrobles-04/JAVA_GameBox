package Game.PacMan.entities.Dynamics;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import Game.GameStates.PacManState;
import Game.PacMan.World.MapBuilder;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BoundBlock;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Ghost extends BaseDynamic{

	protected double velX,velY;
	protected double speed = 0;
	public String facing = "Up";
	//GhostSpawn= Ghost can't move, follow= ghost changes direction every few ticks, SpawnCollision= ghost not get stuck on cage 
	public boolean moving = true, GhostInky = false, GhostSpawn=true, follow=false, SpawningCollision=false; 
	//Boolean to ReAnimate Ghost after PacmanPowerUp
	public static boolean BAnim=false, IAnim= false, PAnim=false, CAnim=false, Respawn=false;
	public Animation UpAnim, DownAnim, LeftAnim, RightAnim;
	Random move= new Random();
	//GhostMove= GhostAlgorithm & ReAnimation
	int Position, GhostMove, Inky;
	int pixelMultiplier = MapBuilder.pixelMultiplier;
	//GhostTimer= Ghost in cage
	int GhostTimer=move.nextInt(5)*60+60;
	//InkyCooldown= Randomizer Cooldown (Inky changes algorithm)
	int InkyCooldown = move.nextInt(8)*60+180;
	//MoveTimer= Every few ticks change direction closer to pacman (16=pixels)
	int MoveTimer =16*15+move.nextInt(5)*16;
	int startGhostAnimation = 1600;
	public Ghost(int x, int y, int width, int height, Handler handler, int ghostType) {
		super(x, y, width, height, handler, Images.ghost[ghostType]);
		GhostMove=ghostType;
		this.x=x;
		this.y=y;
		boolean GhostColor=true;
		//BlinkyAnimation
		if(GhostMove==0 && GhostColor) {
			LeftAnim = new Animation(128,Images.BlinkyLeft);
			RightAnim = new Animation(128,Images.BlinkyRight);
			UpAnim = new Animation(128,Images.BlinkyUp);
			DownAnim = new Animation(128,Images.BlinkyDown);
			GhostColor=false;
		} 

		//PinkyAnimation
		else if(GhostMove==1 && GhostColor){
			LeftAnim = new Animation(128,Images.PinkyLeft);
			RightAnim = new Animation(128,Images.PinkyRight);
			UpAnim = new Animation(128,Images.PinkyUp);
			DownAnim = new Animation(128,Images.PinkyDown);
			GhostColor=false;
		}

		//InkyAnimation
		else if(GhostMove==2 && GhostColor) {
			LeftAnim = new Animation(128,Images.InkyLeft);
			RightAnim = new Animation(128,Images.InkyRight);
			UpAnim = new Animation(128,Images.InkyUp);
			DownAnim = new Animation(128,Images.InkyDown);
			GhostColor=false;
		}
		//ClydeAnimation
		else {				
			LeftAnim = new Animation(128,Images.ClydeLeft);
			RightAnim = new Animation(128,Images.ClydeRight);
			UpAnim = new Animation(128,Images.ClydeUp);
			DownAnim= new Animation(128,Images.ClydeDown);
			GhostColor=false;
		}
	}
	@Override
	public void tick(){

		switch (facing){
		case "Right":
			x+=velX;
			RightAnim.tick();
			break;
		case "Left":
			x-=velX;
			LeftAnim.tick();
			break;
		case "Up":
			y-=velY;
			UpAnim.tick();
			break;
		case "Down":
			y+=velY;
			DownAnim.tick();
			break;
		}

		if (InkyCooldown<=0){
			GhostInky=false;
			GhostMove=2;
			InkyCooldown = move.nextInt(8)*60+180;
		} if(GhostInky){
			InkyCooldown--;
		}
		if(GhostTimer==0 && GhostSpawn) {
			MoveTimer=16*15+move.nextInt(5)*16;
			if(GhostMove<3 || GhostMove==4) {
				speed=2;
			} else {
				speed=1;
			}
			GhostSpawn=false;
		} else {
			GhostTimer--;
		}
		if(MoveTimer<=0 && !GhostSpawn) {
			follow=true;
			MoveTimer= move.nextInt(2)*16+9*16;
		} else {
			MoveTimer--;
		}

		if(handler.getPacManState().getGhostEdible() && PacMan.getPowerCount()>=startGhostAnimation) {
			LeftAnim = new Animation(128,Images.GhostKillable);
			RightAnim = new Animation(128,Images.GhostKillable);
			UpAnim = new Animation(128,Images.GhostKillable);
			DownAnim= new Animation(128,Images.GhostKillable);
		}	
		if(GhostMove==0 && BAnim) {
			LeftAnim = new Animation(128,Images.BlinkyLeft);
			RightAnim = new Animation(128,Images.BlinkyRight);
			UpAnim = new Animation(128,Images.BlinkyUp);
			DownAnim = new Animation(128,Images.BlinkyDown);
			setBAnim(false);
		} 

		//PinkyAnimation
		else if(GhostMove==1 && PAnim){
			LeftAnim = new Animation(128,Images.PinkyLeft);
			RightAnim = new Animation(128,Images.PinkyRight);
			UpAnim = new Animation(128,Images.PinkyUp);
			DownAnim = new Animation(128,Images.PinkyDown);
			setPAnim(false);
		}

		//InkyAnimation
		else if((GhostMove==2 || GhostMove==4 || GhostMove==5) && IAnim) {
			LeftAnim = new Animation(128,Images.InkyLeft);
			RightAnim = new Animation(128,Images.InkyRight);
			UpAnim = new Animation(128,Images.InkyUp);
			DownAnim = new Animation(128,Images.InkyDown);
			setIAnim(false);	
		}
		//ClydeAnimation
		else if(GhostMove==3 && CAnim) {				
			LeftAnim = new Animation(128,Images.ClydeLeft);
			RightAnim = new Animation(128,Images.ClydeRight);
			UpAnim = new Animation(128,Images.ClydeUp);
			DownAnim= new Animation(128,Images.ClydeDown);
			setCAnim(false);
		}
		//Moving algorithm
		Point2D a= new Point2D.Double(this.x,this.y);
		Point2D b= new Point2D.Double(handler.getPacman().x, handler.getPacman().y);
		Point2D c= new Point2D.Double(handler.getPacman().x, handler.getPacman().y);
		double DisX = a.getX();
		double PacX = b.getX();
		double DisY = a.getY();
		double PacY = b.getY();
		handler.getPacManState();
		if(PacManState.isMultiplayer()) {
			c= new Point2D.Double(handler.getMsPacman().x, handler.getMsPacman().y);
		}
		double MisX=c.getX();
		double MisY=c.getY();

		// Algorithm to follow PacMan
		if (facing.equals("Right") || facing.equals("Left")){
			checkHorizontalCollision();
			handler.getPacman();
			if((GhostMove<3 || GhostMove==4) && (PacX==DisX || MisX==DisX) && !PacMan.isPacmanDead() && SpawningCollision) {
				if(b.distance(a)<c.distance(a)) {
					if(DisY>PacY) {
						facing= "Up";
					} else {
						facing= "Down";
					}
				} else {
					if(DisY>MisY) {
						facing="Up";
					} else {
						facing= "Down";
					}
				}
			}
			else if((GhostMove<3 || GhostMove==4) && (follow && !handler.getPacManState().getGhostEdible()) && SpawningCollision) {
				if(b.distance(a)<c.distance(a)) {
					if((checkPreVerticalCollisions("Down") && DisY<PacY) ) {
						facing = "Down";
					} else {
						facing = "Up";
					} 
					follow=false;
				} else {
					if((checkPreVerticalCollisions("Down") && DisY<MisY) ) {
						facing = "Down";
					} else {
						facing = "Up";
					} 
					follow=false;
				}
			}
		}else{
			checkVerticalCollisions();
			handler.getPacman();
			if((GhostMove<3 || GhostMove==4) && (PacY==DisY || MisY==DisY)&& !PacMan.isPacmanDead() && SpawningCollision) {
				if(b.distance(a)<c.distance(a)) {
					if(DisX>PacX) {
						facing= "Left";
					} else {
						facing= "Right";
					}
				} else {
					if(DisX>MisX) {
						facing="Left";
					} else {
						facing= "Right";
					}
				}
			} else if((GhostMove<3 || GhostMove==4) && (follow && !handler.getPacManState().getGhostEdible()) && SpawningCollision) {
				if(b.distance(a)<c.distance(a)) {
					if(checkPreHorizontalCollision("Left") && DisX<PacX) {
						facing= "Right";
					} else {
						facing="Left";
					}
				} else {
					if(DisX>MisX) {
						facing="Left";
					} else {
						facing= "Right";
					}
				}
				follow=false;
			}
		}
	}
	public void checkVerticalCollisions() {
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamic> warpers = handler.getMap().getWarpersOnMap();

		boolean ghostDies=false;
		boolean toUp = moving && facing.equals("Up");

		Rectangle ghostBounds = toUp ? ghost.getTopBounds() : ghost.getBottomBounds();
		GhostOutOfBounds();

		velY = speed;
		//Ghost not to get stuck on cage
		if( facing.equals("Down") && (this.x <= MapBuilder.SpawnerX+ 16 && this.x >= MapBuilder.SpawnerX - 16) && this.y + 30 >= MapBuilder.SpawnerY - 16) {
			velY=0;
			Position= move.nextInt(2)+1;
			switch(Position) {
			case(1):
				facing= "Right";
			Position=0;
			break;
			case(2):
				facing= "Left";
			Position=0;
			break;
			}
		}

		// Teleport
		for(BaseDynamic warper: warpers) {
			Rectangle warperBounds = !toUp ? warper.getTopBounds() : warper.getBottomBounds();
			if(warper instanceof Warper) {
				if(ghostBounds.intersects(warperBounds)) {
					if(getX()<MapBuilder.SpawnerX) {
						// PacMan is on the left of map
						setX(MapBuilder.rightWarperX-20);
						setY(MapBuilder.rightWarperY);
					}else { // Right of the map
						setX(MapBuilder.leftWarperX+20);
						setY(MapBuilder.leftWarperY);
					}
				}
			}
		}

		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
				if (ghostBounds.intersects(brickBounds)) {
					velY = 0;	
					Point2D a= new Point2D.Double(this.x,this.y);
					Point2D b= new Point2D.Double(handler.getPacman().x, handler.getPacman().y);
					Point2D c= new Point2D.Double(handler.getPacman().x, handler.getPacman().y);
					handler.getPacManState();
					if(PacManState.isMultiplayer()) {
						c= new Point2D.Double(handler.getMsPacman().x, handler.getMsPacman().y);
					}
					double DisX = a.getX();
					double PacX = b.getX();
					double MisX = c.getX();

					if (toUp) {
						SpawningCollision=true;
						ghost.setY(brick.getY() + ghost.getDimension().height);
					}else
						ghost.setY(brick.getY() - brick.getDimension().height);

					//Ghost Specialities
					if(velY==0 && !handler.getPacManState().getGhostEdible()) {
						//Pinky Speed
						if(GhostMove==1||Inky==1) {
							int pos=move.nextInt(5);
							if(pos==1) {
								speed=3;
							} else {
								speed=2;
							}
						}
						//Inky Randomizer (changes to another Ghost Characteristic)
						if(GhostMove==2) {
							GhostInky=true;
							speed=2;
							Inky=move.nextInt(3);
							if(Inky==2) {
								GhostMove=5;
							} else {
								GhostMove=4;
							}
						}
						//Clyde Movement
						if(GhostMove==3||Inky==2 || GhostMove==5) {
							int Pos= move.nextInt(2)+1;
							speed=1;
							switch(Pos) {
							case(1):
								facing="Left";
							Pos=0;
							break;
							case(2):
								facing="Right";
							Pos=0;
							break;
							}
						}
					}
					checkGhostStuck();
					// PacmanPowerUp Ghost Running Away Algorithm
					if(velY==0 && handler.getPacManState().getGhostEdible()) {
						if(DisX<PacX || DisX<MisX) {					
							facing="Left";	
						} 
						else if(DisX>PacX || DisX>MisX){
							facing="Right";
						} else {
							Position= move.nextInt(2)+1;
							switch(Position) {
							case(1):
								facing="Left";
							Position=0;
							break;
							case(2):
								facing="Right";
							Position=0;
							}
						}
						checkGhostStuck();
					}
				}
			}
		}

		if ((ghostBounds.intersects(handler.getMsPacman().getBounds()) || ghostBounds.intersects(handler.getPacman().getBounds())) && !handler.getPacManState().getGhostEdible()){
			if(ghostBounds.intersects(handler.getPacman().getBounds())) {
				PacMan.setSpeed(0);
				PacMan.setPacmanDead(true);
			} else {
				MsPacMan.setSpeed(0);
				MsPacMan.setMsPacmanDead(true);
			}
		}
		if ((ghostBounds.intersects(handler.getMsPacman().getBounds()) || ghostBounds.intersects(handler.getPacman().getBounds())) && handler.getPacManState().getGhostEdible()) {
			ghostDies=true;
			Respawn=true;
		}

		//Change Ghost coordinate to spawner, change speed to 0, facing up, timer (1-5 seconds)
		if(Respawn) {
			if(ghostDies) {
				handler.getMusicHandler().playEffect("pacman_eatghost.wav");
				speed=0;
				GhostTimer=move.nextInt(5)*60+120;
				if(ghostBounds.intersects(handler.getPacman().getBounds())) {
					handler.getScoreManager().addPacmanCurrentScore(500);
				} else {
					handler.getScoreManager().addMsPacmanCurrentScore(500);
				}
			}
			this.x= (MapBuilder.SpawnerX);
			this.y= (MapBuilder.SpawnerY);
			facing="Up";
			GhostSpawn=true;
			follow=false;
			if(GhostMove==0) {
				setBAnim(true);
			}
			else if(GhostMove==1) {
				setPAnim(true);
			}
			else if(GhostMove==2 || GhostMove==4 || GhostMove==5) {
				setIAnim(true);
			} else {
				setCAnim(true);
			}
			handler.getMap().reset();
			SpawningCollision= false;
			ghostDies=false;
			Respawn=false;
		}
	}

	public boolean checkPreVerticalCollisions(String facing) {
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();

		boolean toUp = moving && facing.equals("Up");

		Rectangle ghostBounds = toUp ? ghost.getTopBounds() : ghost.getBottomBounds();

		velY = speed;
		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
				if (ghostBounds.intersects(brickBounds)) {
					return false;
				}
			}
		}
		return true;
	}
	public void checkHorizontalCollision(){
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamic> warpers = handler.getMap().getWarpersOnMap();

		velX = speed;
		boolean ghostDies= false;
		boolean toRight = moving && facing.equals("Right");

		Rectangle ghostBounds = toRight ? ghost.getRightBounds() : ghost.getLeftBounds();

		if ((ghostBounds.intersects(handler.getMsPacman().getBounds()) || ghostBounds.intersects(handler.getPacman().getBounds())) && !handler.getPacManState().getGhostEdible()) {
			if(ghostBounds.intersects(handler.getPacman().getBounds())) {
				PacMan.setSpeed(0);
				PacMan.setPacmanDead(true);
			} else {
				MsPacMan.setSpeed(0);
				MsPacMan.setMsPacmanDead(true);
			}
		}

		if ((ghostBounds.intersects(handler.getMsPacman().getBounds()) || ghostBounds.intersects(handler.getPacman().getBounds())) && handler.getPacManState().getGhostEdible()){
			ghostDies=true;
			Respawn=true;
		}
		GhostOutOfBounds();
		//Change Ghost coordinate to spawner, change speed to 0, facing up, timer (1-5 seconds)
		if(Respawn) {
			if(ghostDies) {
				handler.getMusicHandler().playEffect("pacman_eatghost.wav");
				speed=0;
				GhostTimer=move.nextInt(5)*60+120;
				if(ghostBounds.intersects(handler.getPacman().getBounds())) {
					handler.getScoreManager().addPacmanCurrentScore(500);
				} else {
					handler.getScoreManager().addMsPacmanCurrentScore(500);
				}
			}
			this.x= (MapBuilder.SpawnerX);
			this.y= (MapBuilder.SpawnerY);
			facing="Up";
			GhostSpawn=true;
			follow=false;
			if(GhostMove==0) {
				setBAnim(true);
			}
			else if(GhostMove==1) {
				setPAnim(true);
			}
			else if(GhostMove==2 || GhostMove==4 || GhostMove==5) {
				setIAnim(true);
			} else {
				setCAnim(true);
			}
			handler.getMap().reset();
			SpawningCollision= false;
			ghostDies=false;
			Respawn=false;
		}
		for(BaseDynamic warper: warpers) {
			Rectangle warperBounds = !toRight ? warper.getTopBounds() : warper.getBottomBounds();
			if(warper instanceof Warper) {
				if(ghostBounds.intersects(warperBounds)) {
					if(getX()<MapBuilder.SpawnerX) {
						// PacMan is on the left of map
						setX(MapBuilder.rightWarperX-20);
						setY(MapBuilder.rightWarperY);
					}else { // Right of the map
						setX(MapBuilder.leftWarperX+20);
						setY(MapBuilder.leftWarperY);
					}
				}
			}
		}	

		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
				if (ghostBounds.intersects(brickBounds)) {
					velX = 0;
					Point2D a= new Point2D.Double(this.x,this.y);
					Point2D b= new Point2D.Double(handler.getPacman().x, handler.getPacman().y);
					Point2D c= new Point2D.Double(handler.getPacman().x, handler.getPacman().y);
					if(PacManState.isMultiplayer()) {
						c= new Point2D.Double(handler.getMsPacman().x, handler.getMsPacman().y);
					}
					double DisY = a.getY();
					double PacY = b.getY();
					double MisY = c.getY();
					if (toRight)
						ghost.setX(brick.getX() - ghost.getDimension().width);
					else
						ghost.setX(brick.getX() + brick.getDimension().width);
					//Ghost Specialities
					if(velX==0 && !handler.getPacManState().getGhostEdible()) {
						//Pinky Speed
						if(GhostMove==1||Inky==1) {
							int pos=move.nextInt(5);
							if(pos==1) {
								speed=3;
							} else {
								speed=2;
							}
						}
						//Inky Randomizer
						else if(GhostMove==2) {
							GhostInky=true;
							speed=2;
							Inky=move.nextInt(3);
							if(Inky==2) {
								GhostMove=5;
							} else {
								GhostMove=4;
							}
						}
						//Clyde Movement
						if(GhostMove==3|| Inky==2 || GhostMove==5) {
							int Pos= move.nextInt(2)+1;
							speed=1;
							switch(Pos) {
							case(1):
								facing="Down";
							Pos=0;
							break;
							case(2):
								facing="Up";
							Pos=0;
							break;
							} 
						}
					}
					checkGhostStuck();

					// PacmanPowerUp Ghost Running Away Algorithm
					if(velX==0 && handler.getPacManState().getGhostEdible()) {
						if(DisY<PacY || DisY<MisY) {					
							facing="Up";	
						} 
						else if(DisY>PacY || DisY>MisY){
							facing="Down";
						} else {
							Position= move.nextInt(2)+1;
							switch(Position) {
							case(1):
								facing="Up";
							Position=0;
							break;
							case(2):
								facing="Down";
							Position=0;
							}
						}
						checkGhostStuck();
					}
				}
			}
		}
	}


	public boolean checkPreHorizontalCollision(String facing){
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		velX = speed;
		boolean toRight = moving && facing.equals("Right");

		Rectangle ghostBounds = toRight ? ghost.getRightBounds() : ghost.getLeftBounds();

		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
				if (ghostBounds.intersects(brickBounds)) {
					return false;
				}
			}
		}
		return true;
	}

	public void checkGhostStuck() {
		int Stuck=move.nextInt(25);
		if(Stuck==10) {
			Position= move.nextInt(4)+1;
			switch(Position) {
			case(1):
				facing="Up";
			Position=0;
			break;
			case(2):
				facing="Down";
			Position=0;
			case(3):
				facing="Left";
			Position=0;
			break;
			case(4):
				facing="Right";
			Position=0;
			}
		} else {
			follow=true;
		}
	}
	public void GhostOutOfBounds() {
		//If ghost leaves bounds, respawn it
		if(this.x< handler.getHeight()/16-9-18*3 || this.x>Images.LevelMaps[handler.getPacManState().getLevel()].getTileWidth()*26
				|| this.y<handler.getWidth()/16-18*6 || this.y>Images.LevelMaps[handler.getPacManState().getLevel()].getTileHeight()*26) {
			if(this.x< handler.getHeight()/16-9-pixelMultiplier*3 || this.x>Images.LevelMaps[handler.getPacManState().getLevel()].getTileWidth()*pixelMultiplier
					|| this.y<handler.getWidth()/16-pixelMultiplier*6 || this.y>Images.LevelMaps[handler.getPacManState().getLevel()].getTileHeight()*pixelMultiplier) {
				Respawn=true;
				facing="Up";
				this.x=MapBuilder.SpawnerX;
				this.y=MapBuilder.SpawnerY;
				GhostSpawn=true;
			}
		}	
	}

	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}

	public double getSpeed() {
		return speed;
	}

	public static boolean isBAnim() {
		return BAnim;
	}

	public static void setBAnim(boolean bAnim) {
		BAnim = bAnim;
	}

	public static boolean isIAnim() {
		return IAnim;
	}

	public static void setIAnim(boolean iAnim) {
		IAnim = iAnim;
	}

	public static boolean isPAnim() {
		return PAnim;
	}

	public static void setPAnim(boolean pAnim) {
		PAnim = pAnim;
	}

	public static boolean isCAnim() {
		return CAnim;
	}

	public static void setCAnim(boolean cAnim) {
		CAnim = cAnim;
	}
}