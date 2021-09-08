package Game.Galaga.Entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class EnemyShip extends BaseEntity {
	int row,col;//row 3-4, col 0-7
    boolean justSpawned=true,attacking=false, positioned=false,hit=false,centered = false,noAddScore = false;
    Animation idle,turn90Left;
    int spawnPos;//0 is left 1 is top, 2 is right, 3 is bottom
    int formationX,formationY,speed,centerCoolDown=60;
    int timeAlive=0;
    int ShipScore=0;
	int TimePos=60*random.nextInt(5)+60;
  
    
    public EnemyShip(int x, int y, int width, int height, Handler handler,int row, int col, EntityManager enemies) {
        super(x, y, width, height, Images.galagaEnemyShip[0], handler);
        

        this.row = row;
        this.col = col;
        
        
        BufferedImage[] idleAnimList= new BufferedImage[2];
        idleAnimList[0] = Images.galagaEnemyShip[0];
        idleAnimList[1] = Images.galagaEnemyShip[1];
        idle = new Animation(512,idleAnimList);
        turn90Left = new Animation(128,Images.galagaEnemyShip);
        spawn();
        speed = 4;
        formationX=(handler.getWidth()/4)+(col*((handler.getWidth()/2)/8))+8;
        formationY=(row*(handler.getHeight()/10))+8;
    }

    private void spawn() {
        spawnPos = random.nextInt(3);
        switch (spawnPos){
            case 0://left
                x = (handler.getWidth()/4)-width;
                y = random.nextInt(handler.getHeight()-handler.getHeight()/8);
                break;
            case 1://top
                x = random.nextInt((handler.getWidth()-handler.getWidth()/2))+handler.getWidth()/4;
                y = -height;
                break;
            case 2://right
                x = (handler.getWidth()/2)+ width + (handler.getWidth()/4);
                y = random.nextInt(handler.getHeight()-handler.getHeight()/8);
                break;

        }
        bounds.x=x;
        bounds.y=y;
    }

    @Override
    public void tick() {
        super.tick();
        idle.tick();
        if (hit){
            if (enemyDeath.end){
            	if(!noAddScore) {
            	    ShipScore+=150;
            	    handler.getScoreManager().addGalagaCurrentScore(ShipScore);
            	    if(handler.getScoreManager().getGalagaCurrentScore()>handler.getScoreManager().getGalagaHighScore()) {
            		    handler.getScoreManager().setGalagaHighScore(ShipScore);
            	    }
            	}           	
            	remove = true;
                return;
                
            }

            enemyDeath.tick();
        }
        
            
        
        if (justSpawned){
            timeAlive++;
            if (!centered && Point.distance(x,y,handler.getWidth()/2,handler.getHeight()/2)>speed){//reach center of screen
                switch (spawnPos){
                    case 0://left
                        x+=speed;
                        if (Point.distance(x,y,x,handler.getHeight()/2)>speed) {
                            if (y > handler.getHeight() / 2) {
                                y -= speed;
                            } else {
                                y += speed;
                            }
                        }
                        break;
                    case 1://top
                        y+=speed;
                        if (Point.distance(x,y,handler.getWidth()/2,y)>speed) {
                            if (x > handler.getWidth() / 2) {
                                x -= speed;
                            } else {
                                x += speed;
                            }
                        }
                        break;
                    case 2://right
                        x-=speed;
                        if (Point.distance(x,y,x,handler.getHeight()/2)>speed) {
                            if (y > handler.getHeight() / 2) {
                                y -= speed;
                            } else {
                                y += speed;
                            }
                        }
                        break;

                }
                if (timeAlive>=60*60*2){
                    //more than 2 minutes in this state then die
                    //60 ticks in a second, times 60 is a minute, times 2 is a minute
                    damage(new PlayerLaser(0,0,0,0,Images.galagaPlayerLaser,handler,handler.getGalagaState().entityManager));
                }

            }else {//move to formation
                if (!centered){
                    centered = true;
                    timeAlive = 0;
                }
                if (centerCoolDown<=0){
                    if (Point.distance(x, y, formationX, formationY) > speed) {//reach center of screen
                        if (Math.abs(y-formationY)>6) {
                            y -= speed;
                        }
                        if (Point.distance(x,y,formationX,y)>speed/2) {
                            if (x >formationX) {
                                x -= speed;
                            } else {
                                x += speed;
                            }
                        }
                        
                    }else{
                        positioned =true;
                        justSpawned = false;
                    }
                }else{
                    centerCoolDown--;
                }
                if (timeAlive>=60*60*2){
                    //more than 2 minutes in this state then die
                    //60 ticks in a second, times 60 is a minute, times 2 is a minute
                    damage(new PlayerLaser(0,0,0,0,Images.galagaPlayerLaser,handler,handler.getGalagaState().entityManager));
                }
            }
        }else if (positioned){
        	if(TimePos<=0) {
        		attacking=true;
        		positioned=false;
        	} else {
        		TimePos--;
        	}
        	
        	
        }else if (attacking){  
        		handler.getMusicHandler().playEffect("laser.wav");
        		handler.getGalagaState().entityManager.enemyentities.add(new EnemyLaser(x + (width / 2), y + 3, width / 5, height / 2, Images.galagaEnemyLaser, handler, handler.getGalagaState().entityManager));
        		TimePos=random.nextInt(5)*60+60*2;
        		positioned=true;
        		attacking = false;    	
        		
        	
        }
        
        // kills enemy Ship if out of screen.
    if (centered || !centered) {
    	if(this.y > handler.getHeight()|| this.x < handler.getWidth()/5 || this.x > handler.getWidth()*4/5 ) {
    		noAddScore = true;
    		damage(new PlayerLaser(0,0,0,0,Images.galagaPlayerLaser,handler,handler.getGalagaState().entityManager));
    	}
    }
        
        bounds.x=x;
        bounds.y=y;  
        
            
    }

    @Override
    public void render(Graphics g) {
        ((Graphics2D)g).draw(new Rectangle(formationX,formationY,32,32));
        if (arena.contains(bounds)) {
            if (hit){
                g.drawImage(enemyDeath.getCurrentFrame(), x, y, width, height, null);
            }else{
                g.drawImage(idle.getCurrentFrame(), x, y, width, height, null);

            }
        }
    }

    @Override
    public void damage(BaseEntity damageSource) {
        super.damage(damageSource);
        if (damageSource instanceof PlayerLaser){
            hit=true;
            handler.getMusicHandler().playEffect("explosion.wav");
            damageSource.remove = true;
        }
    }
}