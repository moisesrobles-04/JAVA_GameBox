package Game.GameStates.Zelda;


import Game.GameStates.State;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Game.Zelda.Entities.Dynamic.Darknut;
import Game.Zelda.Entities.Dynamic.Direction;
import Game.Zelda.Entities.Dynamic.Link;
import Game.Zelda.Entities.Dynamic.Zol;
import Game.Zelda.Entities.Statics.DungeonDoor;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SilverSword;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Game.Zelda.Entities.Statics.Sword;
import Game.Zelda.Entities.Statics.TeleporterDoor;
import Input.KeyManager;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by AlexVR on 3/14/2020
 */
public class ZeldaGameState extends State {


	public static int xOffset,yOffset,stageWidth,stageHeight,worldScale;
	public int cameraOffsetX,cameraOffsetY;
	//map is 16 by 7 squares, you start at x=7,y=7 starts counting at 0
	public int mapX,mapY,mapWidth,mapHeight;
	public static boolean playmusic=true;
	public ArrayList<ArrayList<ArrayList<SolidStaticEntities>>> objects;
	public ArrayList<ArrayList<ArrayList<BaseMovingEntity>>> enemies;
	public Link link;
	public Zol zol;
	public static boolean inCave = false;
	public static boolean DeBug=true, hurt = false;


	public ArrayList<SolidStaticEntities> caveObjects;

	public ZeldaGameState(Handler handler) {
		super(handler);
		xOffset = handler.getWidth()/4;
		yOffset = handler.getHeight()/4;
		stageWidth = handler.getWidth()/3 + (handler.getWidth()/15);
		stageHeight = handler.getHeight()/2;
		worldScale = 3;
		mapX = 7;
		mapY = 7;
		mapWidth = 256;
		mapHeight = 176;
		cameraOffsetX =  ((mapWidth*mapX) + mapX +1)*worldScale;
		cameraOffsetY = ((mapHeight*mapY) + mapY +1)*worldScale;
		objects = new ArrayList<>();
		enemies = new ArrayList<>();
		caveObjects = new ArrayList<>();
		for (int i =0;i<22;i++){
			objects.add(new ArrayList<>());
			enemies.add(new ArrayList<>());
			for (int j =0;j<8;j++) {
				objects.get(i).add(new ArrayList<>());
				enemies.get(i).add(new ArrayList<>());
			}
		}

		addWorldObjects();

		link = new Link(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.zeldaLinkFrames,handler);
	}

	@Override
	public void tick() {
		removeEnemies();
		link.tick();
		if(DeBug) {
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_NUMPAD4) && mapX > 0) {mapX--; cameraOffsetX-=(256.6*worldScale);}
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_NUMPAD6) && mapX < 21) {mapX++; cameraOffsetX+=(256.6*worldScale);}
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_NUMPAD8) && mapY > 0) {mapY--; cameraOffsetY-=(176.6*worldScale);}
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_NUMPAD2) && mapY < 7) {mapY++; cameraOffsetY+=(176.6*worldScale);}

		}

		if (inCave){
			handler.getMusicHandler().stopMusic();
		}else {
			if(playmusic) {
				handler.getMusicHandler().startMusic("TLOZ_OverWorld.wav");
				playmusic=false;
			}
			if (!link.movingMap) {
				for (SolidStaticEntities entity : objects.get(mapX).get(mapY)) {
					entity.tick();
				}
				for (BaseMovingEntity entity : enemies.get(mapX).get(mapY)) {
					entity.tick();
					if(link.attacking) {
						if(link.swordBounds.intersects(entity.getInteractBounds())) {
							if(!hurt) {
								handler.getMusicHandler().playEffect("LOZ_Enemy_Hit.wav");
								hurt = true;
								entity.damage(link.hasSilverSword ? 2 : 1);
							}
						}
						if(link.hasSilverSword) {
							if(link.lightningBounds.intersects(entity.getInteractBounds())) {
								if(!hurt) {
									handler.getMusicHandler().playEffect("LOZ_Enemy_Hit.wav");
									hurt = true;
									entity.damage(2);
								}
							}
						}
					}
					if(entity instanceof Darknut) {
						if(((Darknut) entity).Attack) {
							//Check if swordBounds intersect Link's interact bounds\
							if(((Darknut) entity).swordBounds.intersects(link.getInteractBounds()) && !link.isHit()) {
								damageLink();
							}
						}
					}
					if (entity.getInteractBounds().intersects(link.getInteractBounds()) && !link.isHit()){
						damageLink();
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if (inCave){
			handler.getMusicHandler().stopMusic();
			for (SolidStaticEntities entity : caveObjects) {
				entity.render(g);
			}
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.BOLD, 38));
			g.drawString("  IT ' S  DANGEROUS  TO  GO",(3 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset-5,(2 * (ZeldaGameState.stageHeight/12)) + ZeldaGameState.yOffset+ ((16*worldScale)));
			g.drawString("  ALONE !   TAKE  THIS",(4 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(4 * (ZeldaGameState.stageHeight/12)) + ZeldaGameState.yOffset+ ((16*worldScale)/6));
			link.render(g);
		}else {
			g.drawImage(Images.zeldaMap, -cameraOffsetX + xOffset, -cameraOffsetY + yOffset, Images.zeldaMap.getWidth() * worldScale, Images.zeldaMap.getHeight() * worldScale, null);
			if (!link.movingMap) {
				for (SolidStaticEntities entity : objects.get(mapX).get(mapY)) {
					entity.render(g);
				}
				for (BaseMovingEntity entity : enemies.get(mapX).get(mapY)) {
					entity.render(g);
				}
			}
			link.render(g);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, xOffset, handler.getHeight());
			g.fillRect(xOffset + stageWidth, 0, handler.getWidth(), handler.getHeight());
			g.fillRect(0, 0, handler.getWidth(), yOffset);
			g.fillRect(0, yOffset + stageHeight, handler.getWidth(), handler.getHeight());
			if(DeBug) {
				g.setColor(Color.WHITE);
				g.drawString("MapX: " + Integer.toString(mapX), 0, 20);
				g.drawString("MapY: " + Integer.toString(mapY), 0, 40);
			}
		}
		//Link health display (separated in two lines)

		int line1=link.maxHealth;
		if(line1>=6) {
			line1=6;
		}
		for (int i = 0; i < line1; i++) {
			g.drawImage(Images.pinkHeart,(ZeldaGameState.stageWidth/12) + ZeldaGameState.xOffset+24*i,ZeldaGameState.stageHeight/2-50, 16, 16, null);	

			if(link.maxHealth>=6) {
				for (int j = 0; j < link.maxHealth-6; j++) {
					g.drawImage(Images.pinkHeart,(ZeldaGameState.stageWidth/12) + ZeldaGameState.xOffset+24*j,ZeldaGameState.stageHeight/2-25, 16, 16, null);
				}
			}
			if(link.maxHealth>=13) {
				link.maxHealth=12;
			}
		}

		int line2=link.health;
		if(line2>=6) {
			line2=6;
		}
		for (int i = 0; i < line2; i++) {
			g.drawImage(Images.heart,(ZeldaGameState.stageWidth/12) + ZeldaGameState.xOffset+24*i,ZeldaGameState.stageHeight/2-50, 16, 16, null);	

			if(link.health>=6) {
				for (int j = 0; j < link.health-6; j++) {
					g.drawImage(Images.heart,(ZeldaGameState.stageWidth/12) + ZeldaGameState.xOffset+24*j,ZeldaGameState.stageHeight/2-25, 16, 16, null);
				}
			}
			if(link.health>=13) {
				link.health=12;
			}
		}
		if(mapX == 16 && mapY == 4 && !link.movingMap) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.BOLD, 20));
			g.drawString("Inside this structure lies an ancient warrior long forgotten.",(3 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset-5,(2 * (ZeldaGameState.stageHeight/12)) + ZeldaGameState.yOffset+ ((16*worldScale)));
			g.drawString("His soul will not rest until his weapon is branded once again.",(3 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(4 * (ZeldaGameState.stageHeight/12)) + ZeldaGameState.yOffset+ ((16*worldScale)/6));
		}
	}

	public void addWorldObjects() {
		//cave
		for (int i = 0;i < 17;i++){
			for (int j = 0;j < 11;j++) {
				if (i>=2 && i<=14 && j>=2 && j< 9 ) {
					continue;
				}else{
					if (j>=9){
						if (i>1 && i<15) {
							if ((i == 7 || i==8 || i==9)){
								continue;
							}else {
								caveObjects.add(new SolidStaticEntities(i, j, Images.caveTiles.get(2), handler));
							}
						}else{
							caveObjects.add(new SolidStaticEntities(i,j,Images.caveTiles.get(5),handler));
						}
					}else{
						caveObjects.add(new SolidStaticEntities(i,j,Images.caveTiles.get(5),handler));
					}
				}
			}
		}
		caveObjects.add(new DungeonDoor(7,9,16*worldScale*3,16*worldScale * 2,Direction.DOWN,"caveStartLeave",handler,(4 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(2 * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset));
		caveObjects.add(new SolidStaticEntities(8,4,Images.zeldaNPC[0],handler));
		caveObjects.add(new SolidStaticEntities(5,4,Images.zeldaNPC[3],handler));
		caveObjects.add(new SolidStaticEntities(11,4,Images.zeldaNPC[3],handler));
		caveObjects.add(new Sword(8,6,Images.zeldaItems[0],handler));
//		if(DeBug) {
//			caveObjects.add(new SilverSword(6, 6, Images.zeldaItems[2], handler));
//		}

		//7,7
		ArrayList<SolidStaticEntities> solids = new ArrayList<>();
		ArrayList<BaseMovingEntity> monster = new ArrayList<>();
		monster.add(new Zol(xOffset+(stageWidth/2)-90,yOffset + (stageHeight/2)-16,Images.Zol,handler));
//		if(DeBug) {monster.add(new Darknut(xOffset+(stageWidth/2)-110,yOffset + (stageHeight/2)-40,Images.DarkNut,handler));}
		solids.add(new SectionDoor( 0,5,16*worldScale,16*worldScale, Direction.LEFT,handler));
		solids.add(new SectionDoor( 7,0,16*worldScale * 2,16*worldScale,Direction.UP,handler));
		solids.add(new DungeonDoor( 4,1,16*worldScale+8,16*worldScale+4,Direction.UP,"caveStartEnter",handler,(7 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset+32,(9 * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset));
		solids.add(new SectionDoor( 14,5,16*worldScale,16*worldScale,Direction.RIGHT,handler));
		solids.add(new SolidStaticEntities(0,4,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(1,6,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(6,0,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(5,1,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(6,1,Images.forestTiles.get(6),handler));
		solids.add(new SolidStaticEntities(3,2,Images.forestTiles.get(6),handler));
		solids.add(new SolidStaticEntities(2,3,Images.forestTiles.get(6),handler));
		solids.add(new SolidStaticEntities(1,4,Images.forestTiles.get(6),handler));
		solids.add(new SolidStaticEntities(1,6,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(1,7,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(1,8,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(2,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(3,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(4,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(5,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(6,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(7,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(8,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(9,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(10,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(11,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(12,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(13,9,Images.forestTiles.get(2),handler));		
		solids.add(new SolidStaticEntities(14,8,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(14,7,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(14,6,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(14,4,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(13,4,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(12,4,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(11,4,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(10,4,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(9,4,Images.forestTiles.get(4),handler));
		solids.add(new SolidStaticEntities(9,3,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(9,2,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(9,1,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(9,0,Images.forestTiles.get(5),handler));
		enemies.get(7).set(7, monster);
		objects.get(7).set(7,solids);

		//6,7
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		monster.add(new Zol(xOffset+(stageWidth/2)-100,yOffset + (stageHeight/2)-16,Images.Zol,handler));
		solids.add(new SectionDoor( 0,2,16*worldScale,16*worldScale*7, Direction.LEFT,handler));
		solids.add(new SectionDoor( 12,0,16*worldScale * 2,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 15,5,16*worldScale,16*worldScale,Direction.RIGHT,handler));
		enemies.get(6).set(7, monster);
		objects.get(6).set(7,solids);


		//7,6
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 0,4,16*worldScale,16*worldScale*3, Direction.LEFT,handler));
		solids.add(new SectionDoor( 7,10,16*worldScale * 2,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor( 13,4,16*worldScale,16*worldScale*3,Direction.RIGHT,handler));
		objects.get(7).set(6,solids);

		//6,6
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 0,2,16*worldScale,16*worldScale*8, Direction.LEFT,handler));
		solids.add(new SectionDoor( 11,10,16*worldScale * 2,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor( 14,4,16*worldScale,16*worldScale*3,Direction.RIGHT,handler));
		objects.get(6).set(6,solids);

		//5,6
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(14,2,16*worldScale,16*worldScale*8,Direction.RIGHT,handler));
		solids.add(new SectionDoor(6,0,16*worldScale *8,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor(2,0,16*worldScale *4,16*worldScale,Direction.UP,handler));
		objects.get(5).set(6, solids);

		//5,5
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(14,5,16*worldScale,16*worldScale*2,Direction.RIGHT,handler));
		solids.add(new SectionDoor( 6,10,16*worldScale * 8,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor( 2,10,16*worldScale * 4,16*worldScale,Direction.DOWN,handler));
		objects.get(5).set(5, solids);

		//6,5
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(0,5,16*worldScale,16*worldScale*2,Direction.LEFT,handler));
		solids.add(new SectionDoor(14,2,16*worldScale,16*worldScale*8,Direction.RIGHT,handler));
		solids.add(new SectionDoor(6,0,16*worldScale *2,16*worldScale,Direction.UP,handler));
		objects.get(6).set(5, solids);

		//6,4
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(14,5,16*worldScale,16*worldScale*2,Direction.RIGHT,handler));
		solids.add(new SectionDoor(6,10,16*worldScale*2,16*worldScale,Direction.DOWN,handler));
		objects.get(6).set(4, solids);

		//7,4
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(14,5,16*worldScale,16*worldScale*2,Direction.RIGHT,handler));
		solids.add(new SectionDoor(0,5,16*worldScale,16*worldScale*2,Direction.LEFT,handler));
		objects.get(7).set(4, solids);

		//7,5
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(0,2,16*worldScale,16*worldScale*8,Direction.LEFT,handler));
		solids.add(new SectionDoor(14,2,16*worldScale,16*worldScale*8,Direction.RIGHT,handler));
		objects.get(7).set(5, solids);


		//8,4
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(0,5,16*worldScale,16*worldScale*2,Direction.LEFT,handler));
		solids.add(new SectionDoor(6,10,16*worldScale*2,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor(6,0,16*worldScale *2,16*worldScale,Direction.UP,handler));
		objects.get(8).set(4, solids);

		//8,3
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(0,5,16*worldScale,16*worldScale*2,Direction.LEFT,handler));
		solids.add(new SectionDoor(6,10,16*worldScale*2,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor(6,0,16*worldScale *2,16*worldScale,Direction.UP,handler));
		objects.get(8).set(3,solids);

		//8,5
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(6,0,16*worldScale *2,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor(0,3,16*worldScale,16*worldScale*8,Direction.LEFT,handler));
		solids.add(new SectionDoor(3,10,16*worldScale*8,16*worldScale,Direction.DOWN,handler));
		objects.get(8).set(5,solids);

		//8,6
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(0,4,16*worldScale,16*worldScale*3,Direction.LEFT,handler));
		solids.add(new SectionDoor(2,10,16*worldScale*14,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor(2,0,16*worldScale *14,16*worldScale,Direction.UP,handler));
		objects.get(8).set(6,solids);

		//8,7
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(2,0,16*worldScale *14,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor(0,5,16*worldScale,16*worldScale,Direction.LEFT,handler));
		objects.get(8).set(7,solids);



		//7,3
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(13,5,16*worldScale,16*worldScale*2,Direction.RIGHT,handler));
		solids.add(new TeleporterDoor(7,4,16*worldScale,16*worldScale, 18, 7, 32, 116, Direction.UP,handler));
		objects.get(7).set(3,solids);

		//18,7
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new TeleporterDoor(8,10,16*worldScale,16*worldScale,  7, 3, -36, -140, Direction.DOWN,handler));
		solids.add(new SectionDoor(8,0,16*worldScale,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor(0, 4, 16*worldScale, 16* worldScale*2, Direction.LEFT,handler));
		solids.add(new SectionDoor(16, 4, 16*worldScale, 16* worldScale*2, Direction.RIGHT,handler));
		objects.get(18).set(7,solids);

		//17,7
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(16, 6, 16*worldScale, 16*worldScale, Direction.RIGHT,handler));
		objects.get(17).set(7,solids);
		
		//19,7
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(0, 4, 16*worldScale, 16* worldScale*2, Direction.LEFT,handler));
		objects.get(19).set(7,solids);
		
		//18,6
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(8,0,16*worldScale,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor(8,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		objects.get(18).set(6,solids);
		
		//18,5
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(8,0,16*worldScale,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor(8,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor(0, 4, 16*worldScale, 16* worldScale*2, Direction.LEFT,handler));
		solids.add(new SectionDoor(16, 4, 16*worldScale, 16* worldScale*2, Direction.RIGHT,handler));
		objects.get(18).set(5,solids);
		
		//17,5
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(8,0,16*worldScale,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor(16, 4, 16*worldScale, 16* worldScale*2, Direction.RIGHT,handler));
		objects.get(17).set(5,solids);
		
		//19,5
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(8,0,16*worldScale,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor(0, 4, 16*worldScale, 16* worldScale*2, Direction.LEFT,handler));
		objects.get(19).set(5,solids);
		
		//18,4
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(8,0,16*worldScale,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor(8,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor(0, 4, 16*worldScale, 16* worldScale*2, Direction.LEFT,handler));
		solids.add(new SectionDoor(16, 4, 16*worldScale, 16* worldScale*2, Direction.RIGHT,handler));
		objects.get(18).set(4,solids);
		
		//17,4
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(8,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor(0, 4, 16*worldScale, 16* worldScale*2, Direction.LEFT,handler));
		solids.add(new SectionDoor(16, 4, 16*worldScale, 16* worldScale*2, Direction.RIGHT,handler));
		objects.get(17).set(4,solids);
		
		//16,4
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(16, 4, 16*worldScale, 16* worldScale*2, Direction.RIGHT,handler));
		solids.add(new SolidStaticEntities(8,4,Images.zeldaNPC[0],handler));
		solids.add(new SolidStaticEntities(5,4,Images.zeldaNPC[3],handler));
		solids.add(new SolidStaticEntities(11,4,Images.zeldaNPC[3],handler));
		objects.get(16).set(4,solids);
		
		//19,4
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(8,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor(0, 4, 16*worldScale, 16* worldScale*2, Direction.LEFT,handler));
		solids.add(new SectionDoor(15,4, 16*worldScale, 16* worldScale*2, Direction.RIGHT,handler));
		objects.get(19).set(4,solids);
		
		//20,4
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(0, 4, 16*worldScale, 16* worldScale*2, Direction.LEFT,handler));
		solids.add(new SectionDoor(8,0,16*worldScale,16*worldScale,Direction.UP,handler));
		objects.get(20).set(4,solids);
		
		//18,3
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(8,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor(8,0,16*worldScale,16*worldScale,Direction.UP,handler));
		objects.get(18).set(3,solids);
		
		//20,3
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(16, 4, 16*worldScale, 16* worldScale*2, Direction.RIGHT,handler));
		solids.add(new SectionDoor(8,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		monster.add(new Darknut(xOffset+(stageWidth/2)-60,yOffset + (stageHeight/2)-40,Images.DarkNut,handler));
		enemies.get(20).set(3, monster);
		objects.get(20).set(3,solids);
		
		//21,3
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(0, 4, 16*worldScale, 16* worldScale*2, Direction.LEFT,handler));
		objects.get(21).set(3,solids);
		
		//18,2
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(0, 4, 16*worldScale, 16* worldScale*2, Direction.LEFT,handler));
		solids.add(new SectionDoor(8,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		objects.get(18).set(2, solids);
		
		//17,2
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(16, 4, 16*worldScale, 16* worldScale*2, Direction.RIGHT,handler));
		solids.add(new TeleporterDoor(8, 5 ,16*worldScale*2,16*worldScale,  16, 2, -232, -96, false, Direction.DOWN,handler));
		solids.add(new TeleporterDoor(8, 5 ,16*worldScale*2,16*worldScale,  16, 2, -232, -212, false, Direction.UP,handler));
		solids.add(new TeleporterDoor(8, 5 ,16*worldScale*2,16*worldScale,  16, 2, -160, -172, false, Direction.RIGHT,handler));
		solids.add(new TeleporterDoor(8, 5 ,16*worldScale*2,16*worldScale,  16, 2, -316, -172, false, Direction.LEFT,handler));
		objects.get(17).set(2, solids);
		
		//16,2
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new TeleporterDoor(3, 0 ,16*worldScale*2,16*worldScale,  17, 2, 168, 184, false, Direction.UP,handler));
		solids.add(new SolidStaticEntities(13,3,16*worldScale*2,16*worldScale*2,Images.deadHeMan,handler));
		solids.add(new SilverSword(8, 3, Images.zeldaItems[2], handler));
		objects.get(16).set(2, solids);
		
	}

	public void removeEnemies() {
		ArrayList<BaseMovingEntity> toRemove = new ArrayList<>();
		for(BaseMovingEntity entity: enemies.get(mapX).get(mapY)) {
			if(entity.getDead()) {
				toRemove.add(entity);
				continue;
			}
		}
		for(BaseMovingEntity toErase: toRemove) {
			enemies.get(mapX).get(mapY).remove(toErase);
		}
	}
	public void swapMusic() {
		if(mapX >= 15) {
			handler.getMusicHandler().changeMusic("Chimera.wav");			
		}else {
			handler.getMusicHandler().changeMusic("TLOZ_OverWorld.wav");
		}
	}
	
	public void damageLink() {
		switch (link.getDirection()) {
		case RIGHT:
			link.posX=link.x-30;
			link.posY=link.y;
			break;
		case LEFT:
			link.posX=link.x+30;
			link.posY=link.y;
			break;
		case UP:
			link.posY=link.y+30;
			link.posX=link.x;
			break;
		case DOWN:
			link.posY=link.y-30;
			link.posX=link.x;
			break;
		}
		handler.getMusicHandler().playEffect("LOZ_Link_Hurt.wav");
		link.x=link.posX;
		link.y=link.posY;
		link.setHit(true);
		link.setSpeed(0);
		link.damage(1);
	}



	@Override
	public void refresh() {

	}

}
