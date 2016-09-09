import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * The player controls the turrent and uses it to fire at things in the sky.
 * 
 * @author Gary Lewandowski, Eric Stuppard
 * @version July 17, 2015
 */
public class Turret extends Actor
{

    private int projectileAngle; //angle at which a projectile is fired
    private PowerUp powerup; //currently active powerup
    private boolean bombHit; //whether or not the turret is hit by a bomb
    private int powerTimer; //how long a powerup lasts
    
    public Turret()
    {
        /* first, build a lame-looking turret by drawing a rectangle on the screen. :-) */
      getImage().mirrorHorizontally();
      getImage().scale(80,50);
      
      powerup = null; //No powerups currently active
      
      powerTimer = 30; //How long each powerup lasts
      
      //set the turret to be straight up 
      setRotation(90);
      projectileAngle = 90; 
      
    }
    
    /**
     * Act - do whatever the Turret wants to do. Take user input and either move the turret or
     * fire.
     */
    public void act() 
    {
        /**
         * Check for Turret turn
         */
        String key = Greenfoot.getKey();
        if (key == null) return;
        if (key.equals("right") && projectileAngle >= 10)
        {
            setRotation(getRotation() +10);
            projectileAngle -=10;
        }
        else if (key.equals("left")&& projectileAngle+10 <= 180)
        {
            projectileAngle +=10;
            setRotation(getRotation()-10);
        }
       
        if (key.equals("space"))
            {
                /**
                 * Fires a cluster shot OR a laser if the corresponding powerup is active
                 * If not, fires a normal bullet
                 */
                if (powerup instanceof ClusterPowerUp && powerTimer>0) {
                    fireCluster();
                    powerTimer--;
                } else if (powerup instanceof LaserPowerUp && powerTimer>0) {
                    fireLaser();
                    powerTimer--;
                }
                
                fireReg();
            }
        
            
    }
    
    
    /**
     * Sets the currently active powerup
     * @param The powerup to activate
     */
    public void setPowerUp(PowerUp power)
    {
        powerup = power;
    }
    
    /**
     * fire() causes a projectile to be spawned, in the direction of the current rotational angle.
     */
    public void fireReg()
    {
        Projectile p = new RegularBullet(projectileAngle);
        getWorld().addObject(p, getX(), getY());
        p.move(8);
    }
    
    /**
     * Fires three bullets if a Cluster powerup is active
     * After 30 cluster bullets, powerup goes away
     */
    public void fireCluster()
    {
        //Lasts for 30 seconds
        SaboWorld thisWorld = (SaboWorld) getWorld();
        
        
        
        Projectile cCenter = new RegularBullet(projectileAngle);
        Projectile cLeft = new RegularBullet(projectileAngle-3);
        Projectile cRight = new RegularBullet(projectileAngle+3);
    
        getWorld().addObject(cCenter, getX(), getY());
        getWorld().addObject(cLeft, getX(), getY());
        getWorld().addObject(cRight, getX(), getY());
    
        cCenter.move(8);
        cLeft.move(8);
        cRight.move(8);
            
        
    }
    
    /**
     * Fires a laser if a Laser powerup is hit
     * Any plane/bomb touching the laser is destroyed
     * After 30 lasers, powerup goes away
     */
    public void fireLaser()
    {
        Projectile l = new LaserBeam(projectileAngle);
        getWorld().addObject(l, getX(), getY());
        l.move(10);
            
        
    }
    
    
    /**
     * Causes the turret to lose health if it's hit by a bomb
     * Causes the game to end if 4 soldiers are on the turret or if health runs out
     */
    public void bombed()
    {
        //Retrieve health bar
        World thisWorld = getWorld();
        SaboWorld myWorld = (SaboWorld)thisWorld;
        HealthBar healthbar = myWorld.getHealthBar();
        
        Actor bomb = getOneIntersectingObject(Bomb.class);
        Actor soldier = getOneIntersectingObject(Soldier.class);
        int soldierCount = 0;
        
        //The game ends if 4 soldiers successfully latch on to the turret
        if (soldier!=null) {
            soldierCount++;
            if (soldierCount==4) {
                GameOver gameover = new GameOver();
                thisWorld.addObject(gameover, thisWorld.getWidth()/2, thisWorld.getHeight()/2);
                Greenfoot.stop();
            }
        }
        
        //A bomb has struck the turret!
        if(bomb!=null && bombHit == false) {
            //Decreases health if hit by a bomb
            healthbar.loseHealth();
            if(healthbar.getHealth() <=0) {    //Ends the game if health runs out
                GameOver gameover = new GameOver();
                thisWorld.addObject(gameover, thisWorld.getWidth()/2, thisWorld.getHeight()/2);
                Greenfoot.stop();
            }
    
        } else {
            bombHit = false;
        }
    }
}
