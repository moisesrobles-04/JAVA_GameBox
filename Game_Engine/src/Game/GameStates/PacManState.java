package Game.GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import Game.PacMan.World.MapBuilder;
import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.GhostSpawner;
import Game.PacMan.entities.Dynamics.MsPacMan;
import Game.PacMan.entities.Dynamics.PacMan;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BigDot;
import Game.PacMan.entities.Statics.Dot;
import Game.PacMan.entities.Statics.Fruit;
import Main.Handler;
import Resources.Images;


public class PacManState extends State {

	public String Mode = "Intro";
	public int startCooldown = 60*4;//seven seconds for the music to finish
	private int LevelTimer=150; // timer for new level state
	public int level=0, selectPlayers=1; // level to determine map and variable for the menu selection
	private int dotsEaten = 0, addPacHealthScore=10000, addMsPacHealthScore=10000; // Score to get one extra life
	private boolean newmap = false, MusicPlay=true;
	Random random = new Random();
	static boolean GhostEdible=false, multiplayer=false;
	PauseState pause = new PauseState(handler);
	public PacManState(Handler handler){
		super(handler);
	}

	@Override
	public void tick() {
		if (Mode.equals("Stage")){
			
			// If all player lives are gone. we change to the end state
			if((PacMan.getHealth()<=0 && !isMultiplayer()) || (PacMan.getHealth()<=0 && MsPacMan.getHealth()<=0 && isMultiplayer())) {
				Mode = "EndGame";
			}

			//If all dots are eaten, create the new level
			if(dotsEaten == MapBuilder.getTotalDots()) {
				setGhostEdible(false);
				level++;
				Mode="NewLevel";
			}

			//Level limit
			if(level>=3) {
				level=3;
			}
			
			// If reached 10000 points, extra health is given
			if(handler.getScoreManager().getPacmanCurrentScore()>=addPacHealthScore) {
				PacMan.addHealth(1);
				addPacHealthScore+=10000;
			} else if(handler.getScoreManager().getMspacmanCurrentScore()>=addMsPacHealthScore) {
				MsPacMan.addHealth(1);
				addMsPacHealthScore+=10000;
			}
			
			// Create new map after finishing a level
			if(newmap) {
				Mode = "Stage";
				handler.getMap().reset();
				handler.getMusicHandler().stopAllEffects();
				PacMan.facing="Left";
				handler.setMap(MapBuilder.createMap(Images.LevelMaps[level], handler));
				handler.getMusicHandler().playEffect("pacman_beginning.wav");
				startCooldown = 4*60;
				newmap = false;
				if(multiplayer) {
					MsPacMan.facing = "Left";
				}
			}
			// PAUSE
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
				handler.changeState(pause);
			}
			if(Handler.DEBUG) {
				// kill 
				if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P) && startCooldown <= 0 && PacMan.getHealth()>0) {
					PacMan.setPacmanDead(true);
					PacMan.setSpeed(0);
				}
				// Add Health
				if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N) && PacMan.getHealth()<3 && PacMan.getHealth()>0) {
					PacMan.addHealth(1);
				}
				// Spawn Ghost
				if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C)) {
					GhostSpawner spawner = new GhostSpawner(MapBuilder.SpawnerX,MapBuilder.SpawnerY, MapBuilder.pixelMultiplier,MapBuilder.pixelMultiplier, handler);
					spawner.addEnemy();
				}

				// Multiplayer Commands
				if(multiplayer) {
					// Add Health
					if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_L) && MsPacMan.getHealth()<3) {
						MsPacMan.addHealth(1);
					}
					// Kill
					if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_O) && startCooldown <= 0 && MsPacMan.getHealth()>0) {
						MsPacMan.setMsPacmanDead(true);
						MsPacMan.setSpeed(0);
					}
				}

			}
			// Spawn Ghosts
			if(startCooldown==60*4) {
				GhostSpawner Spawn= new GhostSpawner(MapBuilder.SpawnerX,MapBuilder.SpawnerY, MapBuilder.pixelMultiplier,MapBuilder.pixelMultiplier, handler);
				Spawn.EnemyGhosts();
			}
			if (startCooldown<=0) {
				for (BaseDynamic entity : handler.getMap().getEnemiesOnMap()) {
					entity.tick();
				}
				ArrayList<BaseStatic> toREmove = new ArrayList<>();
				for (BaseStatic blocks: handler.getMap().getBlocksOnMap()){
					if (blocks instanceof Dot){
						if (blocks.getBounds().intersects(handler.getPacman().getBounds()) || blocks.getBounds().intersects(handler.getMsPacman().getBounds())){
							handler.getMusicHandler().playEffect("pacman_chomp.wav");
							toREmove.add(blocks);
							if(blocks.getBounds().intersects(handler.getPacman().getBounds())) {
								handler.getScoreManager().addPacmanCurrentScore(10);								
							} else {
								handler.getScoreManager().addMsPacmanCurrentScore(10);
							}
							dotsEaten++;
						}
					}else if (blocks instanceof BigDot){
						if (blocks.getBounds().intersects(handler.getPacman().getBounds()) || blocks.getBounds().intersects(handler.getMsPacman().getBounds())){
							setGhostEdible(true); // PacMan can now eat the ghosts
							PacMan.setPowerCount(27*60);
							handler.getMusicHandler().playEffect("blink sound.wav");
							toREmove.add(blocks);
							if(blocks.getBounds().intersects(handler.getPacman().getBounds())) {
								handler.getScoreManager().addPacmanCurrentScore(100);								
							} else {
								handler.getScoreManager().addMsPacmanCurrentScore(100);
							}
							dotsEaten++;

						}
						
					// Implementation of fruits
						
					}else if(blocks instanceof Fruit) {
						if (blocks.getBounds().intersects(handler.getPacman().getBounds()) || blocks.getBounds().intersects(handler.getMsPacman().getBounds())){
							handler.getMusicHandler().playEffect("pacman_eatfruit.wav");
							toREmove.add(blocks);
							if(blocks.getBounds().intersects(handler.getPacman().getBounds())) {
								handler.getScoreManager().addPacmanCurrentScore(120);								
							} else {
								handler.getScoreManager().addMsPacmanCurrentScore(120);
							}
							dotsEaten++;
						}
					}
				}
				for (BaseStatic removing: toREmove){
					handler.getMap().getBlocksOnMap().remove(removing);
				}
			}else{
				startCooldown--;
			}
			//Change HighScore
			if(handler.getScoreManager().getPacmanCurrentScore()>handler.getScoreManager().getPacmanHighScore()) {
				handler.getScoreManager().setPacmanHighScore(handler.getScoreManager().getPacmanCurrentScore());
			} else if(handler.getScoreManager().getMspacmanCurrentScore()>handler.getScoreManager().getPacmanHighScore() ) {
				handler.getScoreManager().setPacmanHighScore(handler.getScoreManager().getMspacmanCurrentScore());
			}

		} if(Mode.equals("Menu")) {
			if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
				Mode= "GameSelection";
			}
		}

		// Select between single player or multiplayer 
		else if (Mode.equals("GameSelection")){
			handler.getScoreManager().setPacmanCurrentScore(0);
			handler.getScoreManager().setMspacmanCurrentScore(0);
			if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
				selectPlayers=1;
			}else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
				selectPlayers=2;
			}
			
			// Depending on the selection, we set multiplayer		
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
				if(level>=3) {
					level=3;
				}
				MusicPlay=true;
				setMultiplayer(false);
				//Set Multiplayer
				if(selectPlayers==2) {
					MsPacMan.setSpeed(2);
					setMultiplayer(true);
				}
				handler.setMap(MapBuilder.createMap(Images.LevelMaps[level], handler));
				Mode = "Stage";
				handler.getMusicHandler().playEffect("pacman_beginning.wav");
			}
		}else{
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !(Mode.equals("Stage"))){
				Mode = "Menu";
			}
		}
	}

	@Override
	public void render(Graphics g) {

		if (Mode.equals("Stage")){
			for (int i = 0; i< PacMan.getHealth();i++) {
				g.drawImage(Images.pacmanLives, (handler.getWidth() - handler.getWidth() / 4 + handler.getWidth() / 48) + ((PacMan.getPWidth()*3)*i), handler.getHeight()-handler.getHeight()/5, handler.getWidth() / 26, handler.getHeight() / 18, null);
			}
			if(isMultiplayer()) {
				for (int i = 0; i < MsPacMan.getHealth(); i++) {
					g.drawImage(Images.mspacmanLives,(handler.getWidth() - handler.getWidth() / 4 + handler.getWidth() / 48-5) + ((PacMan.getPWidth()*3)*i), handler.getHeight()-handler.getHeight()/8, handler.getWidth() / 26, handler.getHeight() / 18, null);
				}
			}
			Graphics2D g2 = (Graphics2D) g.create(handler.getWidth()/16-20, handler.getHeight()/16-9, 10000, 10000);
			handler.getMap().drawMap(g2);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Tropical Asian", Font.PLAIN, 28));
			if(!isMultiplayer()) {
				g.drawString("Score: " + handler.getScoreManager().getPacmanCurrentScore(),(handler.getWidth()) - handler.getWidth()/4, 35);
				g.drawString("High-Score: " + handler.getScoreManager().getPacmanHighScore(),(handler.getWidth()) - handler.getWidth()/4, 85);
				g.drawString("Level: " + (level+1), handler.getWidth() - handler.getWidth()/4, 135);
			} else {
				g.drawString("Pac Man Score: " + handler.getScoreManager().getPacmanCurrentScore(),handler.getWidth() - handler.getWidth()/4, 35);
				g.drawString("Ms. Pac Man Score: " + handler.getScoreManager().getMspacmanCurrentScore(),(handler.getWidth() - handler.getWidth()/4), 85);
				g.drawString("High-Score: " + handler.getScoreManager().getPacmanHighScore(),handler.getWidth() - handler.getWidth()/4, 135);
				g.drawString("Level: " + (level+1), handler.getWidth() - handler.getWidth()/4, 185);
			}

		}else if (Mode.equals("Menu")){
			handler.getMusicHandler().stopMusic();
			g.drawImage(Images.start,handler.getWidth()*3/10,10,handler.getWidth()*2/5,handler.getHeight()*9/10,null);
		}

		// RESET
		else if(Mode.equals("EndGame")) {
			PacMan.setHealth(3);
			MsPacMan.setHealth(3);
			level=0;
			startCooldown = 4*60;
			g.drawImage(Images.gameoverBackground, 0, 0, handler.getWidth(), handler.getHeight(), null);
			handler.setMap(MapBuilder.createMap(Images.LevelMaps[level], handler));
			g.setFont(new Font("Tropical Asian", Font.BOLD, 40));
			g.setColor(Color.WHITE);
			g.drawString("Press ENTER to Restart!", handler.getWidth()/3+30, handler.getHeight()/2+handler.getHeight()/4-20);
			g.drawString("Press ESCAPE to return to Main Menu!", handler.getWidth()/4+35, handler.getHeight()/2+handler.getHeight()/4+30);

			// Calculate Winner

			if(isMultiplayer()) {
				int highestScore;
				String winner;
				if(handler.getScoreManager().getMspacmanCurrentScore()>handler.getScoreManager().getPacmanCurrentScore()) {
					highestScore = handler.getScoreManager().getMspacmanCurrentScore();
					winner = "Ms. PacMan";
				}else {
					highestScore = handler.getScoreManager().getPacmanCurrentScore();
					winner = "PacMan";
				}
				g.drawString("Winner: " + winner + ": " + highestScore, handler.getWidth()/3+30, handler.getHeight()/2 - 20);
			}

			if(MusicPlay) {
				handler.getMusicHandler().startMusic("pacman_intermission.wav");
				MusicPlay=false;
			}
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
				Mode="Menu";
			}
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
				Mode="Intro";
				handler.getMusicHandler().stopMusic();
				handler.getMusicHandler().startMusic("nature.wav");
				State.setState(handler.getMenuState());
			}
		}

		// A State to indicate new Level
		// Informs the player about his lives, score and level
		else if(Mode.equals("NewLevel")){
			
			g.setColor(Color.WHITE);
			g.setFont(new Font ("Tropical Asian", Font.BOLD, 64));
			g.drawString("Level: " + (level + 1), handler.getWidth()/2-110, handler.getHeight()/2-65);
			g.drawImage(Images.pacmanLives, handler.getWidth()/2-50, handler.getHeight()/2-50, handler.getWidth() / 32, handler.getHeight() / 20, null);
			g.setFont(new Font("Tropical Asian",Font.LAYOUT_LEFT_TO_RIGHT, 44));
			g.drawString("High Score: " + handler.getScoreManager().getPacmanHighScore(), handler.getWidth()/2-110, handler.getHeight()/2-200);
			if(!isMultiplayer()) {	
				g.drawString("Score: " + handler.getScoreManager().getPacmanCurrentScore(), handler.getWidth()/2-55, handler.getHeight()/2-250);
			}
			g.drawString(" x " + (PacMan.getHealth()), handler.getWidth()/2, handler.getHeight()/2-20);
			if(isMultiplayer()) {
				g.drawString("Player 1 Score: " + handler.getScoreManager().getPacmanCurrentScore(), handler.getWidth()/2-145, handler.getHeight()/2-handler.getHeight()/3-50);
				g.drawString("Player 2 Score: " + handler.getScoreManager().getMspacmanCurrentScore(), handler.getWidth()/2-150, handler.getHeight()/2-handler.getHeight()/3);	
				g.drawImage(Images.mspacmanLives, handler.getWidth()/2-55, handler.getHeight()/2+20, handler.getWidth() / 28, handler.getHeight() / 16, null);
				g.drawString(" x " + (MsPacMan.getHealth()), handler.getWidth()/2, handler.getHeight()/2+60);
			}

			if(LevelTimer==150) {
				handler.getMusicHandler().playEffect("pacman_extrapac.wav");
			}
			
			// Creating the new level and reset
			if(LevelTimer<=0) {
				handler.getMap().reset();
				handler.getMusicHandler().stopAllEffects();
				PacMan.facing="Left";
				Mode = "Stage";
				handler.setMap(MapBuilder.createMap(Images.LevelMaps[level], handler));
				handler.getMusicHandler().playEffect("pacman_beginning.wav");
				startCooldown = 4*60;
				LevelTimer=150;
			} else {
				LevelTimer--;
			}
			
		// Selection menu for single player and multiplayer
			
		} else if(Mode.equals("GameSelection")) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Georgia", Font.BOLD, 54));
			g.drawString("1   PLAYER",handler.getWidth()/2-handler.getWidth()/8+50,handler.getHeight()/2+80);
			g.drawString("2   PLAYER",handler.getWidth()/2-handler.getWidth()/8+50,handler.getHeight()/2+handler.getHeight()/5);
			g.setColor(Color.YELLOW);
			g.drawString("Select Game Mode:",handler.getWidth()/2-handler.getWidth()/6+20,handler.getHeight()/2-60);
			if (selectPlayers == 1){
				g.drawImage(Images.galagaSelect,handler.getWidth()/2-handler.getWidth()/8-10,handler.getHeight()/2+50,32,32,null);
				g.drawImage(Images.PlayerSelectionLogo[0], handler.getWidth()/3-50, handler.getHeight()*(3/4)+45, handler.getWidth()/2-120, handler.getHeight()/4-120, null);
			}else{
				g.drawImage(Images.PlayerSelectionLogo[0], handler.getWidth()/3-50, handler.getHeight()*(3/4)+45, handler.getWidth()/2-120, handler.getHeight()/4-120, null);
				g.drawString("&", handler.getWidth()/2, handler.getHeight()/4-20);
				g.drawImage(Images.PlayerSelectionLogo[1], handler.getWidth()/3-50, handler.getHeight()/4, handler.getWidth()/2-120, handler.getHeight()/4-120, null);
				g.drawImage(Images.galagaSelect,handler.getWidth()/2-handler.getWidth()/8-10,handler.getHeight()/2+handler.getHeight()/5-30,32,32,null);
			}
		}else{
			g.drawImage(Images.intro,handler.getWidth()*3/10,10,handler.getWidth()*2/5,handler.getHeight()*9/10,null);
		}
	}

	public boolean getGhostEdible() {
		return GhostEdible;
	}
	public void setGhostEdible(boolean GhostEdible) {
		PacManState.GhostEdible=GhostEdible;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public static boolean isMultiplayer() {
		return multiplayer;
	}

	public static void setMultiplayer(boolean multiplayer) {
		PacManState.multiplayer = multiplayer;
	}

	@Override
	public void refresh() {

	}

}