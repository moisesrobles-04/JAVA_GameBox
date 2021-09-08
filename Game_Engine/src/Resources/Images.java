package Resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Images {


	public static BufferedImage titleScreenBackground;
	public static BufferedImage pauseBackground;
	public static BufferedImage selectionBackground;
	public static BufferedImage galagaCopyright;
	public static BufferedImage galagaSelect;
	public static BufferedImage muteIcon;
	public static BufferedImage galagaPlayerLaser;
    public static BufferedImage galagaEnemyLaser;
	public static BufferedImage[] startGameButton;
	public static BufferedImage[] galagaLogo;
    public static BufferedImage[] pacmanLogo;
	public static BufferedImage[] pauseResumeButton;
	public static BufferedImage[] pauseToTitleButton;
	public static BufferedImage[] pauseOptionsButton;
	public static BufferedImage[] galagaPlayer;
	public static BufferedImage[] galagaPlayerDeath;
	public static BufferedImage[] galagaEnemyDeath;
	public static BufferedImage[] galagaEnemyBee;
    public static BufferedImage[] galagaEnemyShip;
	
    public static BufferedImage ghost[];
	public static BufferedImage[] pacmanDots;
	public static BufferedImage[] pacmanRight;
	public static BufferedImage[] pacmanLeft;
	public static BufferedImage[] pacmanUp;
	public static BufferedImage[] pacmanDown;
    public static BufferedImage[] mspacmanRight;
    public static BufferedImage[] mspacmanLeft;
    public static BufferedImage[] mspacmanUp;
    public static BufferedImage[] mspacmanDown;   
    public static BufferedImage[] pacmanDeath;

	public static BufferedImage[] bound;
	public static BufferedImage intro;
	public static BufferedImage start;
    public static BufferedImage[] fruits;
    public static BufferedImage pacmanLives;
    public static BufferedImage mspacmanLives;
    public static BufferedImage gameoverBackground;
    public static BufferedImage[] teleporter;

    public static BufferedImage[] PlayerSelectionLogo;
    public static BufferedImage[] LevelMaps;
    public static BufferedImage[] GhostKillable;

    public static BufferedImage[] BlinkyRight;
    public static BufferedImage[] BlinkyLeft;
    public static BufferedImage[] BlinkyUp;
    public static BufferedImage[] BlinkyDown;
    
    public static BufferedImage[] PinkyRight;
    public static BufferedImage[] PinkyLeft;
    public static BufferedImage[] PinkyUp;
    public static BufferedImage[] PinkyDown;
    
    public static BufferedImage[] InkyRight;
    public static BufferedImage[] InkyLeft;
    public static BufferedImage[] InkyUp;
    public static BufferedImage[] InkyDown;
    
    public static BufferedImage[] ClydeRight;
    public static BufferedImage[] ClydeLeft;
    public static BufferedImage[] ClydeUp;
    public static BufferedImage[] ClydeDown;   
    
	public static BufferedImage galagaImageSheet;
	public SpriteSheet galagaSpriteSheet;

    public static BufferedImage pacmanImageSheet;
    public static BufferedImage pacmanImageSheet2;
    public static BufferedImage pacmanImageSheet3;
    public static BufferedImage pacmanImageOpacity;
    public static BufferedImage mspacmanImageSheet;
    public SpriteSheet pacmanSpriteSheet;
    public SpriteSheet pacmanSpriteSheet2;
    public SpriteSheet pacmanSpriteSheet3;
    public SpriteSheet pacmanSpriteOpacity;
    public SpriteSheet mspacmanSpriteSheet;

	public static BufferedImage zeldaImageSheet;
	public static BufferedImage zeldaLightningSheet;
	public static BufferedImage storyImageSheet;
	public static BufferedImage NPCImageSheet;
	public static BufferedImage ItemsImageSheet;
	public static BufferedImage EnemiesImageSheet;

	public SpriteSheet zeldaSpriteSheet;
	public SpriteSheet storySpriteSheet;
	public SpriteSheet NPCSpriteSheet;
	public SpriteSheet EnemiesSpriteSheet;
	public SpriteSheet ItemsSpriteSheet;
	public SpriteSheet LightningSheet;

	public static BufferedImage zeldaTriforceLogo;
	public static BufferedImage zeldaMap;
	public static BufferedImage deadHeMan;
	public static BufferedImage[] zeldaNPC;
	public static BufferedImage[] zeldaItems;
	public static BufferedImage[] Zol;
	public static BufferedImage[] DarkNut;
	public static BufferedImage heart;
	public static BufferedImage pinkHeart;
	public static BufferedImage[] DarkNutSword;

	public static ArrayList<BufferedImage> zeldaTiles;
	public static BufferedImage[] zeldaTitleFrames;
	public static BufferedImage[] zeldaStoryFrames;
	public static BufferedImage zeldaWorldLayoutTileImage;
	public SpriteSheet zeldaWorldLayoutTileSpriteSheet;
	public static ArrayList<BufferedImage> zeldaWorldLayoutTiles;

	public static BufferedImage zeldaLinkImage;
	public SpriteSheet zeldaLinkSpriteSheet;
	public static BufferedImage zeldaLinkCelebration;
	public static BufferedImage[] zeldaLinkLightning;
	public static BufferedImage[] zeldaLinkFrames;
	public static BufferedImage[] zeldaLinkSwordDownFrames;
	public static BufferedImage[] zeldaLinkSwordUpFrames;
	public static BufferedImage[] zeldaLinkSwordLRFrames;
	public static BufferedImage[] zeldaLinkdamagedDownFrames;
	public static BufferedImage[] zeldaLinkdamagedUpFrames;
	public static BufferedImage[] zeldaLinkdamagedLRFrames;
	public static BufferedImage[] zeldaLinkSilverSwordDownFrames;
	public static BufferedImage[] zeldaLinkSilverSwordUpFrames;
	public static BufferedImage[] zeldaLinkSilverSwordLRFrames;
	public static BufferedImage[] RecursiveTeleporter;

	public static ArrayList<BufferedImage> forestTiles;
	public static ArrayList<BufferedImage> caveTiles;
	public static ArrayList<BufferedImage> mountainTiles;
	public static ArrayList<BufferedImage> graveTiles;

	public static BufferedImage EnemyOverwoldImage;
	public SpriteSheet EnemyOverwoldSpriteSheet;
	public static BufferedImage[] bouncyEnemyFrames;

	public Images() {

		startGameButton = new BufferedImage[3];
		pauseResumeButton = new BufferedImage[2];
		pauseToTitleButton = new BufferedImage[2];
		pauseOptionsButton = new BufferedImage[2];
		galagaLogo = new BufferedImage[3];
		galagaPlayer = new BufferedImage[8];//not full yet, only has second to last image on sprite sheet
		galagaPlayerDeath = new BufferedImage[8];
		galagaEnemyDeath = new BufferedImage[5];
		galagaEnemyBee = new BufferedImage[8];
        galagaEnemyShip= new BufferedImage[2];
	
        pacmanLogo = new BufferedImage[3];
        pacmanDots = new BufferedImage[5];
        pacmanRight = new BufferedImage[2];
        pacmanLeft = new BufferedImage[2];
        pacmanUp = new BufferedImage[2];
        pacmanDown = new BufferedImage[2];
        mspacmanRight = new BufferedImage[2];
        mspacmanLeft = new BufferedImage[2];
        mspacmanUp = new BufferedImage[2];
        mspacmanDown = new BufferedImage[2];
        
        teleporter= new BufferedImage[7];
        bound = new BufferedImage[16];
        pacmanDeath = new BufferedImage[10];
        fruits = new BufferedImage[14];
        ghost = new BufferedImage[4];
        LevelMaps= new BufferedImage[4];
        PlayerSelectionLogo=new BufferedImage[2];
        GhostKillable= new BufferedImage[4];

        BlinkyRight= new BufferedImage[2];
        BlinkyLeft= new BufferedImage[2];
        BlinkyUp= new BufferedImage[2];
        BlinkyDown= new BufferedImage[2];
        
        PinkyRight= new BufferedImage[2];
        PinkyLeft= new BufferedImage[2];
        PinkyUp= new BufferedImage[2];
        PinkyDown= new BufferedImage[2];
        
        InkyRight= new BufferedImage[2];
        InkyLeft= new BufferedImage[2];
        InkyUp= new BufferedImage[2];
        InkyDown= new BufferedImage[2];
        
        ClydeRight= new BufferedImage[2];
        ClydeLeft= new BufferedImage[2];
        ClydeUp= new BufferedImage[2];
        ClydeDown= new BufferedImage[2];

		zeldaTiles = new ArrayList<>();
		zeldaTitleFrames = new BufferedImage[6];
		zeldaStoryFrames = new BufferedImage[8];
		zeldaWorldLayoutTiles = new ArrayList<>();
		zeldaNPC= new BufferedImage[5];
		Zol= new BufferedImage[2];
		DarkNut= new BufferedImage[6];
		zeldaItems = new BufferedImage[3];
		DarkNutSword = new BufferedImage[6];
		
		forestTiles = new ArrayList<>();
		caveTiles = new ArrayList<>();
		graveTiles = new ArrayList<>();
		mountainTiles = new ArrayList<>();

		zeldaLinkFrames = new BufferedImage[8];
		zeldaLinkSwordDownFrames = new BufferedImage[4];
		zeldaLinkSwordUpFrames = new BufferedImage[4];
		zeldaLinkSwordLRFrames = new BufferedImage[4];
		
		
		zeldaLinkSilverSwordDownFrames = new BufferedImage[4];
		zeldaLinkSilverSwordUpFrames = new BufferedImage[4];
		zeldaLinkSilverSwordLRFrames = new BufferedImage[4];
		
		zeldaLinkLightning = new BufferedImage[2];

		zeldaLinkdamagedDownFrames= new BufferedImage[4];
		zeldaLinkdamagedUpFrames=new BufferedImage[4];
		zeldaLinkdamagedLRFrames=new BufferedImage[4];

		bouncyEnemyFrames = new BufferedImage[2];
		
		try {

			startGameButton[0]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/NormalStartButton.png"));
			startGameButton[1]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/HoverStartButton.png"));
			startGameButton[2]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/ClickedStartButton.png"));

			titleScreenBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Title.png"));

			pauseBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Pause.png"));

			selectionBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Selection.png"));

			galagaCopyright = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/Copyright.png"));

			galagaSelect = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/galaga_select.png"));

			muteIcon = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/mute.png"));

			galagaLogo[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/galaga_logo.png"));
			galagaLogo[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/hover_galaga_logo.png"));
			galagaLogo[2] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/pressed_galaga_logo.png"));

			pauseResumeButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/Resume/NormalHoverResume.png"));
			pauseResumeButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/Resume/PressedResume.png"));

			pauseToTitleButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToTitle/NormalHoverToTitleButton.png"));
			pauseToTitleButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToTitle/PressedToTitleButton.png"));

			pauseOptionsButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToOptions/NormalHoverToOptionsButton.png"));
			pauseOptionsButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToOptions/PressedToOptionsButton.png"));

			galagaImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Galaga/Galaga.png"));
			galagaSpriteSheet = new SpriteSheet(galagaImageSheet);

			galagaPlayer[0] = galagaSpriteSheet.crop(160,55,15,16);

			galagaPlayerDeath[0] = galagaSpriteSheet.crop(209,48,32,32);
			galagaPlayerDeath[1] = galagaSpriteSheet.crop(209,48,32,32);
			galagaPlayerDeath[2] = galagaSpriteSheet.crop(247,48,32,32);
			galagaPlayerDeath[3] = galagaSpriteSheet.crop(247,48,32,32);
			galagaPlayerDeath[4] = galagaSpriteSheet.crop(288,47,32,32);
			galagaPlayerDeath[5] = galagaSpriteSheet.crop(288,47,32,32);
			galagaPlayerDeath[6] = galagaSpriteSheet.crop(327,47,32,32);
			galagaPlayerDeath[7] = galagaSpriteSheet.crop(327,47,32,32);

			galagaEnemyDeath[0] = galagaSpriteSheet.crop(201,191,32,32);
			galagaEnemyDeath[1] = galagaSpriteSheet.crop(223,191,32,32);
			galagaEnemyDeath[2] = galagaSpriteSheet.crop(247,191,32,32);
			galagaEnemyDeath[3] = galagaSpriteSheet.crop(280,191,32,32);
			galagaEnemyDeath[4] = galagaSpriteSheet.crop(320,191,32,32);

			galagaEnemyBee[0] = galagaSpriteSheet.crop(188,178,9,10);
			galagaEnemyBee[1] = galagaSpriteSheet.crop(162,178,13,10);
			galagaEnemyBee[2] = galagaSpriteSheet.crop(139,177,11,12);
			galagaEnemyBee[3] = galagaSpriteSheet.crop(113,176,14,13);
			galagaEnemyBee[4] = galagaSpriteSheet.crop(90,177,13,13);
			galagaEnemyBee[5] = galagaSpriteSheet.crop(65,176,13,14);
			galagaEnemyBee[6] = galagaSpriteSheet.crop(42,178,12,11);
			galagaEnemyBee[7] = galagaSpriteSheet.crop(19,177,10,13);


			galagaPlayerLaser = galagaSpriteSheet.crop(365 ,219,3,8);

            galagaEnemyShip[0] = galagaSpriteSheet.crop(160,320,16,16);
            galagaEnemyShip[1] = galagaSpriteSheet.crop(160,320,16,16);

            
            //new sprite for enemy laser.
            galagaEnemyLaser = galagaSpriteSheet.crop(373,51,5,9);
             			
            pacmanImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/Background.png"));
            pacmanImageSheet2 = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/Background2.png"));
            pacmanImageSheet3 = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/DotColorChanged.png"));
            pacmanImageOpacity = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/Dotopacity.png"));
            mspacmanImageSheet =  ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/Ms_Pacman_SpriteSheet.png"));

            gameoverBackground =  ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/GAMEOVER.png"));  
            
            pacmanSpriteSheet = new SpriteSheet(pacmanImageSheet);
            pacmanSpriteSheet2 = new SpriteSheet(pacmanImageSheet2);
            pacmanSpriteSheet3 = new SpriteSheet(pacmanImageSheet3);
            pacmanSpriteOpacity = new SpriteSheet(pacmanImageOpacity);
            mspacmanSpriteSheet = new SpriteSheet(mspacmanImageSheet);
            
            PlayerSelectionLogo[0]=ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/Pacman_Logo.png"));
            PlayerSelectionLogo[1]=ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/Ms_Pacman.png"));
            
            LevelMaps[0]= ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map1test.png"));
            LevelMaps[1]=ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map2.png"));
            LevelMaps[2]=ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map3.png"));
            LevelMaps[3]=ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map1.png"));
        
            teleporter[0] = pacmanSpriteSheet2.crop(287, 532, 16, 16);
            teleporter[1] = pacmanSpriteSheet2.crop(304, 532, 16, 16);
            teleporter[2] = pacmanSpriteSheet2.crop(321, 532, 16, 16);
            teleporter[3] = pacmanSpriteSheet2.crop(338, 532, 16, 16);
            teleporter[4] = pacmanSpriteSheet2.crop(355, 532, 16, 16);
            teleporter[5] = pacmanSpriteSheet2.crop(372, 532, 16, 16);
            teleporter[6] = pacmanSpriteSheet2.crop(389, 532, 16, 16);
      
            
            ghost[0] = pacmanSpriteSheet.crop(456,64,16,16);
            ghost[1] = pacmanSpriteSheet.crop(456,80,16,16);
            ghost[2] = pacmanSpriteSheet.crop(456,96,16,16);
            ghost[3] = pacmanSpriteSheet.crop(456,112,16,16);
            
            BlinkyRight[0] = pacmanSpriteSheet.crop(456, 64, 16, 16);
            BlinkyRight[1] = pacmanSpriteSheet.crop(472, 64, 16, 16);
            
            BlinkyLeft[0] = pacmanSpriteSheet.crop(488, 64, 16, 16);
            BlinkyLeft[1] = pacmanSpriteSheet.crop(504, 64, 16, 16);
            
            BlinkyUp[0] = pacmanSpriteSheet.crop(520, 64, 16, 16);
            BlinkyUp[1] = pacmanSpriteSheet.crop(536, 64, 16, 16);
           
            BlinkyDown[0] = pacmanSpriteSheet.crop(552, 64, 16, 16);
            BlinkyDown[1] = pacmanSpriteSheet.crop(568, 64, 16, 16);
            
            PinkyRight[0] = pacmanSpriteSheet.crop(456, 80, 16, 16);
            PinkyRight[1] = pacmanSpriteSheet.crop(472, 80, 16, 16);
            
            PinkyLeft[0] = pacmanSpriteSheet.crop(488, 80, 16, 16);
            PinkyLeft[1] = pacmanSpriteSheet.crop(504, 80, 16, 16);
            
            PinkyUp[0] = pacmanSpriteSheet.crop(520, 80, 16, 16);
            PinkyUp[1] = pacmanSpriteSheet.crop(536, 80, 16, 16);

            PinkyDown[0] = pacmanSpriteSheet.crop(552, 80, 16, 16);
            PinkyDown[1] = pacmanSpriteSheet.crop(568, 80, 16, 16);
            
            InkyRight[0] = pacmanSpriteSheet.crop(456, 96, 16, 16);
            InkyRight[1] = pacmanSpriteSheet.crop(472, 96, 16, 16);
            
            InkyLeft[0] = pacmanSpriteSheet.crop(488, 96, 16, 16);
            InkyLeft[1] = pacmanSpriteSheet.crop(504, 96, 16, 16);
            
            InkyUp[0] = pacmanSpriteSheet.crop(520, 96, 16, 16);
            InkyUp[1] = pacmanSpriteSheet.crop(536, 96, 16, 16);
            
            InkyDown[0] = pacmanSpriteSheet.crop(552, 96, 16, 16);
            InkyDown[1] = pacmanSpriteSheet.crop(568, 96, 16, 16);
            
            ClydeRight[0] = pacmanSpriteSheet.crop(456, 112, 16, 16);
            ClydeRight[1] = pacmanSpriteSheet.crop(472, 112, 16, 16);
            
            ClydeLeft[0] = pacmanSpriteSheet.crop(488, 112, 16, 16);
            ClydeLeft[1] = pacmanSpriteSheet.crop(504, 112, 16, 16);
            
            ClydeUp[0] = pacmanSpriteSheet.crop(520, 112, 16, 16);
            ClydeUp[1] = pacmanSpriteSheet.crop(536, 112, 16, 16);
            
            ClydeDown[0] = pacmanSpriteSheet.crop(552, 112, 16, 16);
            ClydeDown[1] = pacmanSpriteSheet.crop(568, 112, 16, 16);
            	
            GhostKillable[0] = pacmanSpriteSheet.crop(584, 64, 16, 16);
            GhostKillable[1] = pacmanSpriteSheet.crop(600, 64, 16, 16);
            GhostKillable[2] = pacmanSpriteSheet.crop(616, 64, 16, 16);
            GhostKillable[3] = pacmanSpriteSheet.crop(632, 64, 16, 16);
            
            pacmanDots[0] = pacmanSpriteSheet.crop(643,18,16,16);
            pacmanDots[1] = pacmanSpriteOpacity.crop(643, 18, 16, 16);
            pacmanDots[2] = pacmanSpriteSheet.crop(1, 1, 1, 1);
            pacmanDots[3] = pacmanSpriteSheet.crop(623,18,16,16);
            pacmanDots[4] = pacmanSpriteSheet3.crop(623, 18, 16, 16);
            
            pacmanLogo[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/pacmanLogo1.png"));
            pacmanLogo[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/pacmanLogo2.png"));
            pacmanLogo[2] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/pacmanLogo3.png"));

			bound[0] = pacmanSpriteSheet.crop(603,18,16,16);//single
			bound[1] = pacmanSpriteSheet.crop(615,37,16,16);//right open
			bound[2] = pacmanSpriteSheet.crop(635,37,16,16);//down open
			bound[3] = pacmanSpriteSheet.crop(655,37,16,16);//left open
			bound[4] = pacmanSpriteSheet.crop(655,57,16,16);//up open
			bound[5] = pacmanSpriteSheet.crop(655,75,16,16);//up/down
			bound[6] = pacmanSpriteSheet.crop(656,116,16,16);//left/Right
			bound[7] = pacmanSpriteSheet.crop(656,136,16,16);//up/Right
			bound[8] = pacmanSpriteSheet.crop(655,174,16,16);//up/left
			bound[9] = pacmanSpriteSheet.crop(655,155,16,16);//down/Right
			bound[10] = pacmanSpriteSheet.crop(655,192,16,16);//down/left
			bound[11] = pacmanSpriteSheet.crop(664,232,16,16);//all
			bound[12] = pacmanSpriteSheet.crop(479,191,16,16);//left
			bound[13] = pacmanSpriteSheet.crop(494,191,16,16);//right
			bound[14] = pacmanSpriteSheet.crop(479,208,16,16);//top
			bound[15] = pacmanSpriteSheet.crop(479,223,16,16);//bottom

            pacmanRight[0] = pacmanSpriteSheet.crop(473,1,12,13);
            pacmanRight[1] = pacmanSpriteSheet.crop(489,1,13,13);

            pacmanLeft[0] = pacmanSpriteSheet.crop(474,17,12,13);
            pacmanLeft[1] = pacmanSpriteSheet.crop(489,1,13,13);

            pacmanUp[0] = pacmanSpriteSheet.crop(473,34,13,12);
            pacmanUp[1] = pacmanSpriteSheet.crop(489,1,13,13);

            pacmanDown[0] = pacmanSpriteSheet.crop(473,48,13,12);
            pacmanDown[1] = pacmanSpriteSheet.crop(489,1,13,13);
			
            mspacmanRight[0] = mspacmanSpriteSheet.crop(472, 0, 15, 16);
            mspacmanRight[1] = mspacmanSpriteSheet.crop(488, 0, 17, 16);
          
            mspacmanLeft[0] = mspacmanSpriteSheet.crop(473,16,15,16);
            mspacmanLeft[1] = mspacmanSpriteSheet.crop(487,16,17,16);

            mspacmanUp[0] = mspacmanSpriteSheet.crop(472,33,16,15);
            mspacmanUp[1] = mspacmanSpriteSheet.crop(488,31,16,17);

            mspacmanDown[0] = mspacmanSpriteSheet.crop(472,48,16,15);
            mspacmanDown[1] = mspacmanSpriteSheet.crop(488,48,16,17);         
            
            pacmanDeath[0] = pacmanSpriteSheet.crop(489,1,13,13);
            pacmanDeath[1] = pacmanSpriteSheet.crop(473,34,13,12);
            pacmanDeath[2] = pacmanSpriteSheet.crop(505,3,13,9);
            pacmanDeath[3] = pacmanSpriteSheet.crop(520,4,15,9);
            pacmanDeath[4] = pacmanSpriteSheet.crop(536,6,15,6);
            pacmanDeath[5] = pacmanSpriteSheet.crop(568,8,15,5);
            pacmanDeath[6] = pacmanSpriteSheet.crop(584,8,15,6);
            pacmanDeath[7] = pacmanSpriteSheet.crop(601,8,13,7);
            pacmanDeath[8] = pacmanSpriteSheet.crop(619,8,9,7);
            pacmanDeath[9] = pacmanSpriteSheet.crop(666,6,11,10);

            fruits[0] = pacmanSpriteSheet.crop(490,50,12,12);
            fruits[1] = pacmanSpriteSheet.crop(506,50,11,12);
            fruits[2] = pacmanSpriteSheet.crop(522,50,12,12);
            fruits[3] = pacmanSpriteSheet.crop(538,50,12,12);
            fruits[4] = pacmanSpriteSheet.crop(555,49,11,14);
            fruits[5] = pacmanSpriteSheet.crop(570,51,11,11);
            fruits[6] = pacmanSpriteSheet.crop(586,50,12,13);
            fruits[7] = pacmanSpriteSheet.crop(604,50,7,12);
            fruits[8] = pacmanSpriteSheet2.crop(185,549,16,16);
            fruits[9] = pacmanSpriteSheet2.crop(406,549,16,16);
            fruits[10] = mspacmanSpriteSheet.crop(587,0,11,16);
            fruits[11] = mspacmanSpriteSheet.crop(601,0,14,15);
            fruits[12] = pacmanSpriteSheet2.crop(321,549,16,16);
            fruits[13] = pacmanSpriteSheet2.crop(338,549,16,16);
            pacmanLives = pacmanSpriteSheet2.crop(34, 564, 32, 32);
            mspacmanLives= mspacmanSpriteSheet.crop(472, 0, 15, 16);

			intro = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/intro.png"));
			start = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/startScreen.png"));

			zeldaImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/tileSet.png"));
			zeldaTriforceLogo = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/triforceLogo.png"));
			zeldaMap = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/map.png"));
			zeldaMap = createImageTransparent(zeldaMap,"zelddaMap_0,128,0,green",new Color(0,128,0).getRGB());
			deadHeMan = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/Dead He Man.png"));
			zeldaImageSheet = createImageTransparent(zeldaImageSheet,"tileSets_0,120,0,green",new Color(0,128,0).getRGB());
			zeldaSpriteSheet = new SpriteSheet(zeldaImageSheet);
			zeldaLightningSheet = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/Zelda_Lightning.png"));
			LightningSheet = new SpriteSheet(zeldaLightningSheet);
			

			storyImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/title.png"));
			storySpriteSheet = new SpriteSheet(storyImageSheet);
			zeldaTitleFrames[5] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_0.gif"));
			zeldaTitleFrames[4] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_1.gif"));
			zeldaTitleFrames[3] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_2.gif"));
			zeldaTitleFrames[2] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_3.gif"));
			zeldaTitleFrames[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_4.gif"));
			zeldaTitleFrames[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_5.gif"));

			NPCImageSheet= ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/npc.png"));
			NPCImageSheet = createImageTransparent(NPCImageSheet,"NPC_0,120,0,green",new Color(0,128,0).getRGB());            
			NPCSpriteSheet= new SpriteSheet(createImageTransparent(NPCImageSheet,"NPC_116,116,116_gray",new Color(116,116,116).getRGB()));

			ItemsImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/items.png"));
			ItemsImageSheet = createImageTransparent(ItemsImageSheet,"Items_34,177,76,green",new Color(34,177,76).getRGB());            
			ItemsSpriteSheet= new SpriteSheet(ItemsImageSheet);
			heart = ItemsSpriteSheet.crop(0, 0, 7, 8);
			pinkHeart = ItemsSpriteSheet.crop(16, 0, 7, 8);
			zeldaItems[0] = ItemsSpriteSheet.crop(104, 0, 7, 16);//Wooden Sword
			zeldaItems[1] = ItemsSpriteSheet.crop(136,0,8,14);//Bomb
			zeldaItems[2] = ItemsSpriteSheet.crop(104,16,7,16);//Silver Sword
			zeldaLinkLightning[0] = LightningSheet.crop(27, 0, 110, 638);// Lightning Facing up
			zeldaLinkLightning[1] = LightningSheet.crop(146, 439, 643, 88); //Lightning Facing right

			EnemiesImageSheet= ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/enemy2.png"));
			EnemiesImageSheet=createImageTransparent(EnemiesImageSheet,"Enemies_0,120,0,green",new Color(0,128,0).getRGB());            ;
			EnemiesSpriteSheet=new SpriteSheet(createImageTransparent(EnemiesImageSheet,"Enemies_116,116,116_gray",new Color(116,116,116).getRGB()));

			zeldaStoryFrames[0] = storySpriteSheet.crop(1, 250, 256,223);
			zeldaStoryFrames[1] = storySpriteSheet.crop(258, 250, 256,223);
			zeldaStoryFrames[2] = storySpriteSheet.crop(515, 250, 256,223);
			zeldaStoryFrames[3] = storySpriteSheet.crop(772, 250, 256,223);
			zeldaStoryFrames[4] = storySpriteSheet.crop(1, 475, 256,223);
			zeldaStoryFrames[5] = storySpriteSheet.crop(258, 475, 256,223);
			zeldaStoryFrames[6] = storySpriteSheet.crop(515, 475, 256,223);
			zeldaStoryFrames[7] = storySpriteSheet.crop(772, 475, 256,64);

			zeldaLinkImage = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/link.png"));
			zeldaLinkImage = createImageTransparent(zeldaLinkImage,"link_0,128,0_green",new Color(0,128,0).getRGB());
			zeldaLinkSpriteSheet = new SpriteSheet(createImageTransparent(zeldaLinkImage,"link_116,116,116_gray",new Color(116,116,116).getRGB()));

			zeldaNPC[0]=NPCSpriteSheet.crop(1, 11, 16, 16);
			zeldaNPC[1]=NPCSpriteSheet.crop(18, 11, 16, 16);
			zeldaNPC[2]=NPCSpriteSheet.crop(35, 11, 16, 16);
			zeldaNPC[3]=NPCSpriteSheet.crop(52, 11, 16, 16);
			
			//Enemies
			Zol[0]= EnemiesSpriteSheet.crop(77, 11, 16, 16);
			Zol[1]= EnemiesSpriteSheet.crop(94, 11, 16, 16);

			DarkNut[0]= EnemiesSpriteSheet.crop(1, 90, 16, 16);
			DarkNut[1]= EnemiesSpriteSheet.crop(18, 90, 16, 16);
			DarkNut[2]= EnemiesSpriteSheet.crop(35, 90, 16, 16);
			DarkNut[3]= EnemiesSpriteSheet.crop(52, 90, 16, 16);
			DarkNut[4]= EnemiesSpriteSheet.crop(69, 90, 16, 16);
			DarkNut[5]= EnemiesSpriteSheet.crop(86, 90, 16, 16);
			
			DarkNutSword[0]= zeldaLinkSpriteSheet.crop(71, 165, 8, 5);
			DarkNutSword[1]= zeldaLinkSpriteSheet.crop(71, 160, 8, 10);
			DarkNutSword[2]= zeldaLinkSpriteSheet.crop(71, 154, 8, 16);
			DarkNutSword[3]= zeldaLinkSpriteSheet.crop(80, 154, 5, 16);
			DarkNutSword[4]= zeldaLinkSpriteSheet.crop(80, 154, 10, 16);
			DarkNutSword[5]= zeldaLinkSpriteSheet.crop(80, 154, 16, 16);
			
			zeldaLinkFrames[0] = zeldaLinkSpriteSheet.crop(1,11,16,16);
			zeldaLinkFrames[1] = zeldaLinkSpriteSheet.crop(18,11,16,16);
			zeldaLinkFrames[2] = zeldaLinkSpriteSheet.crop(35,11,16,16);
			zeldaLinkFrames[3] = zeldaLinkSpriteSheet.crop(52,11,16,16);
			zeldaLinkFrames[4] = zeldaLinkSpriteSheet.crop(69,11,16,16);
			zeldaLinkFrames[5] = zeldaLinkSpriteSheet.crop(86,11,16,16);
			zeldaLinkFrames[6] = zeldaLinkSpriteSheet.crop(213,11,16,16);
			zeldaLinkFrames[7] = zeldaLinkSpriteSheet.crop(230,11,16,16);

			zeldaLinkCelebration = zeldaLinkSpriteSheet.crop(213,11,16,16);
			

			zeldaLinkSwordDownFrames[0] = zeldaLinkSpriteSheet.crop(52, 47, 16, 19);
			zeldaLinkSwordDownFrames[1] = zeldaLinkSpriteSheet.crop(35, 47, 16, 23);
			zeldaLinkSwordDownFrames[2] = zeldaLinkSpriteSheet.crop(18, 47, 16, 27);
			zeldaLinkSwordDownFrames[3] = zeldaLinkSpriteSheet.crop(1, 47, 16, 16);//DownWard Thrust End
			
			zeldaLinkSilverSwordDownFrames[0] = zeldaLinkSpriteSheet.crop(145, 47, 16, 19);
			zeldaLinkSilverSwordDownFrames[1] = zeldaLinkSpriteSheet.crop(128, 47, 16, 23);
			zeldaLinkSilverSwordDownFrames[2] = zeldaLinkSpriteSheet.crop(111, 47, 16, 27);
			zeldaLinkSilverSwordDownFrames[3] = zeldaLinkSpriteSheet.crop(94, 47, 16, 16);//Silver DownWard Thrust End

			zeldaLinkSwordUpFrames[0] = zeldaLinkSpriteSheet.crop(52, 106, 16, 19);
			zeldaLinkSwordUpFrames[1] = zeldaLinkSpriteSheet.crop(35, 98, 16, 27);
			zeldaLinkSwordUpFrames[2] = zeldaLinkSpriteSheet.crop(18, 97, 16, 28);
			zeldaLinkSwordUpFrames[3] = zeldaLinkSpriteSheet.crop(1, 109, 16, 16);//Upward Thrust End
			
			zeldaLinkSilverSwordUpFrames[0] = zeldaLinkSpriteSheet.crop(145, 106, 16, 19);
			zeldaLinkSilverSwordUpFrames[1] = zeldaLinkSpriteSheet.crop(128, 98, 16, 27);
			zeldaLinkSilverSwordUpFrames[2] = zeldaLinkSpriteSheet.crop(111,97,16,28);
			zeldaLinkSilverSwordUpFrames[3] = zeldaLinkSpriteSheet.crop(94,109,16,16);// Silver Upward Thrust End

			zeldaLinkSwordLRFrames[0] = zeldaLinkSpriteSheet.crop(70, 77, 19, 17);
			zeldaLinkSwordLRFrames[1] = zeldaLinkSpriteSheet.crop(46, 77 ,23, 17);
			zeldaLinkSwordLRFrames[2] = zeldaLinkSpriteSheet.crop(18, 77, 27, 17);
			zeldaLinkSwordLRFrames[3] = zeldaLinkSpriteSheet.crop(1, 77 ,16, 16); // Left/Right Thrust End
			
			zeldaLinkSilverSwordLRFrames[0] = zeldaLinkSpriteSheet.crop(163, 77, 19, 17);
			zeldaLinkSilverSwordLRFrames[1] = zeldaLinkSpriteSheet.crop(139,77,23,17);
			zeldaLinkSilverSwordLRFrames[2] = zeldaLinkSpriteSheet.crop(111, 77, 27, 17);
			zeldaLinkSilverSwordLRFrames[3] = zeldaLinkSpriteSheet.crop(94,77,16,16);// Left/Right Silver Thrust End
			
			zeldaLinkdamagedDownFrames[0]= zeldaLinkSpriteSheet.crop(1, 11, 16, 16);
			zeldaLinkdamagedDownFrames[1]= zeldaLinkSpriteSheet.crop(58, 224, 16, 16);
			zeldaLinkdamagedDownFrames[2]= zeldaLinkSpriteSheet.crop(200, 227, 16, 16);
			zeldaLinkdamagedDownFrames[3]= zeldaLinkSpriteSheet.crop(223, 227, 16, 16); //Link damaged Down
			
			zeldaLinkdamagedUpFrames[0]= zeldaLinkSpriteSheet.crop(70, 11, 16, 16);
			zeldaLinkdamagedUpFrames[1]= zeldaLinkSpriteSheet.crop(58, 258, 16, 16);
			zeldaLinkdamagedUpFrames[2]= zeldaLinkSpriteSheet.crop(200, 261, 16, 16);
			zeldaLinkdamagedUpFrames[3]= zeldaLinkSpriteSheet.crop(223, 261, 16, 16); //Link damaged Up
			
			zeldaLinkdamagedLRFrames[0]= zeldaLinkSpriteSheet.crop(35, 11, 16, 16);
			zeldaLinkdamagedLRFrames[1]= zeldaLinkSpriteSheet.crop(58, 241, 16, 16);
			zeldaLinkdamagedLRFrames[2]= zeldaLinkSpriteSheet.crop(200, 244, 16, 16);
			zeldaLinkdamagedLRFrames[3]= zeldaLinkSpriteSheet.crop(223, 244, 16, 16); //Link damaged LR
			
			zeldaWorldLayoutTileImage = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/layout.png"));
			zeldaWorldLayoutTileSpriteSheet = new SpriteSheet( createImageTransparent(zeldaWorldLayoutTileImage,"layout_0,128,0_green",new Color(0,128,0).getRGB()));
			zeldaWorldLayoutTiles.add(zeldaWorldLayoutTileSpriteSheet.crop(1,154,152,84));
			zeldaWorldLayoutTiles.add(createImage(zeldaWorldLayoutTiles.get(0),"forest_brown4greeen",brown.getRGB(),new Color(0,168,0).getRGB()));
			zeldaWorldLayoutTiles.add(createImage(zeldaWorldLayoutTiles.get(0),"cave_brown4greeen",brown.getRGB(),new Color(124,8,0).getRGB()));
			zeldaWorldLayoutTiles.add(createImage(zeldaWorldLayoutTiles.get(0),"grave_brown4greeen",brown.getRGB(),new Color(252,252,252).getRGB()));

			EnemyOverwoldImage = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/enemy3.png"));
			EnemyOverwoldImage = createImageTransparent(EnemyOverwoldImage,"enemies_overworld_116,116,116_green",new Color(116,116,116).getRGB());
			EnemyOverwoldSpriteSheet = new SpriteSheet( createImageTransparent(EnemyOverwoldImage,"enemies_overworld_0,128,0_green",new Color(0,128,0).getRGB()));

			bouncyEnemyFrames[0] = EnemyOverwoldSpriteSheet.crop(162,90,16,16);
			bouncyEnemyFrames[1] = EnemyOverwoldSpriteSheet.crop(179,90,16,16);

			//dungeon one tiles
			zeldaTiles.add(zeldaSpriteSheet.crop(815,11,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(848,11,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(881,11,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(914,11,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(947,11,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(848,44,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(815,44,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(881,44,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(914,44,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(947,44,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(815,77,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(848,77,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(881,77,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(914,77,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(947,77,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(815,110,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(848,110,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(881,110,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(914,110,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(947,110,32,32));
			zeldaTiles.add(zeldaSpriteSheet.crop(984,11,16,16));
			zeldaTiles.add(zeldaSpriteSheet.crop(1001,11,16,16));
			zeldaTiles.add(zeldaSpriteSheet.crop(1018,11,16,16));
			zeldaTiles.add(zeldaSpriteSheet.crop(1035,11,16,16));
			zeldaTiles.add(zeldaSpriteSheet.crop(1001,28,16,16));
			zeldaTiles.add(zeldaSpriteSheet.crop(984,28,16,16));
			zeldaTiles.add(zeldaSpriteSheet.crop(1018,28,16,16));
			zeldaTiles.add(zeldaSpriteSheet.crop(1035,28,16,16));
			zeldaTiles.add(zeldaSpriteSheet.crop(984,45,16,16));
			zeldaTiles.add(zeldaSpriteSheet.crop(1001,45,16,16));
			//Recursive Tiles
			zeldaTiles.add(ItemsSpriteSheet.crop(0,26, 16, 16));
			zeldaTiles.add(ItemsSpriteSheet.crop(17, 26, 16, 16));
			zeldaTiles.add(Images.flipVertical(ItemsSpriteSheet.crop(0,26, 16, 16)));
			zeldaTiles.add(Images.flipHorizontal(ItemsSpriteSheet.crop(17, 26, 16, 16)));

			
			//main world tiles
			SpriteSheet mountain = new SpriteSheet(zeldaWorldLayoutTiles.get(0));
			SpriteSheet forest = new SpriteSheet(createImageTransparent(zeldaWorldLayoutTiles.get(1),"forestTransparent_237,28,36_red", new Color(237,28,36).getRGB()));
			SpriteSheet cave = new SpriteSheet(createImageTransparent(zeldaWorldLayoutTiles.get(2),"caveTransparent_252,216,168_crema", new Color(252,216,168).getRGB()));
			SpriteSheet grave = new SpriteSheet(zeldaWorldLayoutTiles.get(3));

			mountainTiles.add(mountain.crop(0,0,16,16));
			mountainTiles.add(mountain.crop(17,0,16,16));
			mountainTiles.add(mountain.crop(34,0,16,16));
			mountainTiles.add(mountain.crop(51,0,16,16));
			mountainTiles.add(mountain.crop(17,17,16,16));
			mountainTiles.add(mountain.crop(34,17,16,16));
			mountainTiles.add(mountain.crop(51,17,16,16));
			mountainTiles.add(mountain.crop(68,0,16,16));
			mountainTiles.add(mountain.crop(85,0,16,16));
			mountainTiles.add(mountain.crop(102,0,16,16));
			mountainTiles.add(mountain.crop(68,17,16,16));
			mountainTiles.add(mountain.crop(85,17,16,16));
			mountainTiles.add(mountain.crop(102,17,16,16));
			mountainTiles.add(mountain.crop(68,34,16,16));
			mountainTiles.add(mountain.crop(85,34,16,16));
			mountainTiles.add(mountain.crop(102,34,16,16));
			mountainTiles.add(mountain.crop(119,0,16,16));
			mountainTiles.add(mountain.crop(136,0,16,16));
			mountainTiles.add(mountain.crop(119,17,16,16));
			mountainTiles.add(mountain.crop(136,17,16,16));
			mountainTiles.add(mountain.crop(119,34,16,16));
			mountainTiles.add(mountain.crop(136,34,16,16));
			mountainTiles.add(mountain.crop(0,51,16,16));
			mountainTiles.add(mountain.crop(17,51,16,16));
			mountainTiles.add(mountain.crop(34,51,16,16));
			mountainTiles.add(mountain.crop(0,68,16,16));
			mountainTiles.add(mountain.crop(34,68,16,16));
			mountainTiles.add(mountain.crop(51,51,16,16));
			mountainTiles.add(mountain.crop(68,51,16,16));
			mountainTiles.add(mountain.crop(85,51,16,16));
			mountainTiles.add(mountain.crop(51,68,16,16));
			mountainTiles.add(mountain.crop(85,68,16,16));
			mountainTiles.add(mountain.crop(0,17,16,16));
			mountainTiles.add(mountain.crop(0,34,16,16));
			mountainTiles.add(mountain.crop(17,34,16,16));
			mountainTiles.add(mountain.crop(34,34,16,16));
			mountainTiles.add(mountain.crop(51,34,16,16));
			mountainTiles.add(mountain.crop(17,68,16,16));
			mountainTiles.add(mountain.crop(68,68,16,16));
			mountainTiles.add(mountain.crop(102,51,16,16));
			mountainTiles.add(mountain.crop(119,51,16,16));
			mountainTiles.add(mountain.crop(136,51,16,16));

			forestTiles.add(forest.crop(0,0,16,16));
			forestTiles.add(forest.crop(17,0,16,16));
			forestTiles.add(forest.crop(34,0,16,16));
			forestTiles.add(forest.crop(51,0,16,16));
			forestTiles.add(forest.crop(17,17,16,16));
			forestTiles.add(forest.crop(34,17,16,16));
			forestTiles.add(forest.crop(51,17,16,16));
			forestTiles.add(forest.crop(68,0,16,16));
			forestTiles.add(forest.crop(85,0,16,16));
			forestTiles.add(forest.crop(102,0,16,16));
			forestTiles.add(forest.crop(68,17,16,16));
			forestTiles.add(forest.crop(85,17,16,16));
			forestTiles.add(forest.crop(102,17,16,16));
			forestTiles.add(forest.crop(68,34,16,16));
			forestTiles.add(forest.crop(85,34,16,16));
			forestTiles.add(forest.crop(102,34,16,16));
			forestTiles.add(forest.crop(119,0,16,16));
			forestTiles.add(forest.crop(136,0,16,16));
			forestTiles.add(forest.crop(119,17,16,16));
			forestTiles.add(forest.crop(136,17,16,16));
			forestTiles.add(forest.crop(119,34,16,16));
			forestTiles.add(forest.crop(136,34,16,16));
			forestTiles.add(forest.crop(0,51,16,16));
			forestTiles.add(forest.crop(17,51,16,16));
			forestTiles.add(forest.crop(34,51,16,16));
			forestTiles.add(forest.crop(0,68,16,16));
			forestTiles.add(forest.crop(34,68,16,16));
			forestTiles.add(forest.crop(51,51,16,16));
			forestTiles.add(forest.crop(68,51,16,16));
			forestTiles.add(forest.crop(85,51,16,16));
			forestTiles.add(forest.crop(51,68,16,16));
			forestTiles.add(forest.crop(85,68,16,16));
			forestTiles.add(forest.crop(0,17,16,16));
			forestTiles.add(forest.crop(0,34,16,16));
			forestTiles.add(forest.crop(17,34,16,16));
			forestTiles.add(forest.crop(34,34,16,16));
			forestTiles.add(forest.crop(51,34,16,16));
			forestTiles.add(forest.crop(17,68,16,16));
			forestTiles.add(forest.crop(68,68,16,16));
			forestTiles.add(forest.crop(102,51,16,16));
			forestTiles.add(forest.crop(119,51,16,16));
			forestTiles.add(forest.crop(136,51,16,16));

			caveTiles.add(cave.crop(0,0,16,16));
			caveTiles.add(cave.crop(17,0,16,16));
			caveTiles.add(cave.crop(34,0,16,16));
			caveTiles.add(cave.crop(51,0,16,16));
			caveTiles.add(cave.crop(17,17,16,16));
			caveTiles.add(cave.crop(34,17,16,16));
			caveTiles.add(cave.crop(51,17,16,16));
			caveTiles.add(cave.crop(68,0,16,16));
			caveTiles.add(cave.crop(85,0,16,16));
			caveTiles.add(cave.crop(102,0,16,16));
			caveTiles.add(cave.crop(68,17,16,16));
			caveTiles.add(cave.crop(85,17,16,16));
			caveTiles.add(cave.crop(102,17,16,16));
			caveTiles.add(cave.crop(68,34,16,16));
			caveTiles.add(cave.crop(85,34,16,16));
			caveTiles.add(cave.crop(102,34,16,16));
			caveTiles.add(cave.crop(119,0,16,16));
			caveTiles.add(cave.crop(136,0,16,16));
			caveTiles.add(cave.crop(119,17,16,16));
			caveTiles.add(cave.crop(136,17,16,16));
			caveTiles.add(cave.crop(119,34,16,16));
			caveTiles.add(cave.crop(136,34,16,16));
			caveTiles.add(cave.crop(0,51,16,16));
			caveTiles.add(cave.crop(17,51,16,16));
			caveTiles.add(cave.crop(34,51,16,16));
			caveTiles.add(cave.crop(0,68,16,16));
			caveTiles.add(cave.crop(34,68,16,16));
			caveTiles.add(cave.crop(51,51,16,16));
			caveTiles.add(cave.crop(68,51,16,16));
			caveTiles.add(cave.crop(85,51,16,16));
			caveTiles.add(cave.crop(51,68,16,16));
			caveTiles.add(cave.crop(85,68,16,16));
			caveTiles.add(cave.crop(0,17,16,16));
			caveTiles.add(cave.crop(0,34,16,16));
			caveTiles.add(cave.crop(17,34,16,16));
			caveTiles.add(cave.crop(34,34,16,16));
			caveTiles.add(cave.crop(51,34,16,16));
			caveTiles.add(cave.crop(17,68,16,16));
			caveTiles.add(cave.crop(68,68,16,16));
			caveTiles.add(cave.crop(102,51,16,16));
			caveTiles.add(cave.crop(119,51,16,16));
			caveTiles.add(cave.crop(136,51,16,16));

			graveTiles.add(grave.crop(0,0,16,16));
			graveTiles.add(grave.crop(17,0,16,16));
			graveTiles.add(grave.crop(34,0,16,16));
			graveTiles.add(grave.crop(51,0,16,16));
			graveTiles.add(grave.crop(17,17,16,16));
			graveTiles.add(grave.crop(34,17,16,16));
			graveTiles.add(grave.crop(51,17,16,16));
			graveTiles.add(grave.crop(68,0,16,16));
			graveTiles.add(grave.crop(85,0,16,16));
			graveTiles.add(grave.crop(102,0,16,16));
			graveTiles.add(grave.crop(68,17,16,16));
			graveTiles.add(grave.crop(85,17,16,16));
			graveTiles.add(grave.crop(102,17,16,16));
			graveTiles.add(grave.crop(68,34,16,16));
			graveTiles.add(grave.crop(85,34,16,16));
			graveTiles.add(grave.crop(102,34,16,16));
			graveTiles.add(grave.crop(119,0,16,16));
			graveTiles.add(grave.crop(136,0,16,16));
			graveTiles.add(grave.crop(119,17,16,16));
			graveTiles.add(grave.crop(136,17,16,16));
			graveTiles.add(grave.crop(119,34,16,16));
			graveTiles.add(grave.crop(136,34,16,16));
			graveTiles.add(grave.crop(0,51,16,16));
			graveTiles.add(grave.crop(17,51,16,16));
			graveTiles.add(grave.crop(34,51,16,16));
			graveTiles.add(grave.crop(0,68,16,16));
			graveTiles.add(grave.crop(34,68,16,16));
			graveTiles.add(grave.crop(51,51,16,16));
			graveTiles.add(grave.crop(68,51,16,16));
			graveTiles.add(grave.crop(85,51,16,16));
			graveTiles.add(grave.crop(51,68,16,16));
			graveTiles.add(grave.crop(85,68,16,16));
			graveTiles.add(grave.crop(0,17,16,16));
			graveTiles.add(grave.crop(0,34,16,16));
			graveTiles.add(grave.crop(17,34,16,16));
			graveTiles.add(grave.crop(34,34,16,16));
			graveTiles.add(grave.crop(51,34,16,16));
			graveTiles.add(grave.crop(17,68,16,16));
			graveTiles.add(grave.crop(68,68,16,16));
			graveTiles.add(grave.crop(102,51,16,16));
			graveTiles.add(grave.crop(119,51,16,16));
			graveTiles.add(grave.crop(136,51,16,16));


		}catch (IOException e) {
			e.printStackTrace();
		}


	}


	public BufferedImage invertImage(BufferedImage bufferedImage, String name) {
		String path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
		String path2;
		try {
			path2 = path.substring(0,path.indexOf("/out/"))+"/res/Edited/"+name+".png";
		}catch(java.lang.StringIndexOutOfBoundsException e) {
			path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
		}
		
		File imagess = new File(path2.replaceAll("%20"," "));
		if (imagess.exists()){
			try {
				return ImageIO.read(imagess.getAbsoluteFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		for (int x = 0; x < bufferedImage.getWidth(); x++) {
			for (int y = 0; y < bufferedImage.getHeight(); y++) {
				int rgba = bufferedImage.getRGB(x, y);
				Color col = new Color(rgba, true);
				col = new Color(255 - col.getRed(),
						255 - col.getGreen(),
						255 - col.getBlue());
				bufferedImage.setRGB(x, y, col.getRGB());
			}
		}
		File f = null;

		try
		{
			path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
			try {
				path2 = path.substring(0,path.indexOf("/out/"))+"/res/Edited/"+name+".png";
			}catch(java.lang.StringIndexOutOfBoundsException e) {
				path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
			}
			f = new File(path2.replaceAll("%20"," "));
			System.out.println("File saved in: "+path2);
			ImageIO.write(bufferedImage, "png", f);
		}
		catch(IOException e)
		{
			System.out.println("Error: " + e);
		}
		return bufferedImage;
	}

	public static Color transparant = new Color(255, 255, 255, 0);
	public static Color brown = new Color(200,76,12);

	public BufferedImage createImageTransparent(BufferedImage image, String name, int RGBToReplace){


		return createImage(image,name,RGBToReplace,transparant.getRGB());
	}

	public BufferedImage createImage(BufferedImage image, String name, int RGBToReplace,int RGBReplaicing){

		int width = image.getWidth();
		int height = image.getHeight();
		String path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
		String path2;
		try {
			path2 = path.substring(0,path.indexOf("/out/"))+"/res/Edited/"+name+".png";
		}catch(java.lang.StringIndexOutOfBoundsException e) {
			path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
		}
		File imagess = new File(path2.replaceAll("%20"," "));
		if (imagess.exists()){
			try {
				return ImageIO.read(imagess.getAbsoluteFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		// Create buffered image object
		BufferedImage img = null;

		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		// file object
		File f = null;

		// create random values pixel by pixel
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				if (image.getRGB(x, y) == RGBToReplace) {
					img.setRGB(x, y, RGBReplaicing);
				} else {
					img.setRGB(x, y, image.getRGB(x, y));
				}


			}
		}

		// write image, AKA save it to pc
		try
		{
			path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
			try {
				path2 = path.substring(0,path.indexOf("/out/"))+"/res/Edited/"+name+".png";
			}catch(java.lang.StringIndexOutOfBoundsException e) {
				path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
			}
			f = new File(path2.replaceAll("%20"," "));
			System.out.println("File saved in: "+path2);
			ImageIO.write(img, "png", f);
		}
		catch(IOException e)
		{
			System.out.println("Error: " + e);
		}
		return img;
	}


	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(Images.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	public static BufferedImage tint(BufferedImage src, float r, float g, float b) {

		// Copy image
		BufferedImage newImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TRANSLUCENT);
		Graphics2D graphics = newImage.createGraphics();
		graphics.drawImage(src, 0, 0, null);
		graphics.dispose();

		// Color image
		for (int i = 0; i < newImage.getWidth(); i++) {
			for (int j = 0; j < newImage.getHeight(); j++) {
				int ax = newImage.getColorModel().getAlpha(newImage.getRaster().getDataElements(i, j, null));
				int rx = newImage.getColorModel().getRed(newImage.getRaster().getDataElements(i, j, null));
				int gx = newImage.getColorModel().getGreen(newImage.getRaster().getDataElements(i, j, null));
				int bx = newImage.getColorModel().getBlue(newImage.getRaster().getDataElements(i, j, null));
				rx *= r;
				gx *= g;
				bx *= b;
				newImage.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx << 0));
			}
		}
		return newImage;
	}

	public static BufferedImage flipHorizontal(BufferedImage image){
		// Flip the image horizontally
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		image = op.filter(image, null);
		return image;
	}
	public static BufferedImage flipVertical(BufferedImage image) {
		AffineTransform ty = AffineTransform.getScaleInstance(1, -1);
		ty.translate(0, -image.getHeight(null));
		AffineTransformOp op = new AffineTransformOp(ty, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		image = op.filter(image, null);
		return image;
	}
	public static BufferedImage rotate(BufferedImage image, double angle) {
		AffineTransform ty = new AffineTransform();
		ty.rotate(angle * (Math.PI/180), image.getWidth()/2, image.getHeight()/2);
		double offset = (image.getWidth() - image.getHeight())/2;
		ty.translate(offset, offset);
		AffineTransformOp op = new AffineTransformOp(ty, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		image = op.filter(image, null);
		return image;
	}

}
