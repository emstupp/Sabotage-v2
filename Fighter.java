import greenfoot.*;
import java.util.List;
import java.lang.Math;

/**
 * Abstract class representing all planes in the game
 * 
 * @author Eric Stuppard 
 * @version July 17, 2015
 */
public abstract class Fighter extends Actor
{
    
    /**
     * 
     */
    public Fighter()
    {
        getImage().mirrorHorizontally();
        getImage().scale(40,20);
    }
    
    
    /**
     * Randomly spawns a powerup 20% of the time
     */
    public void spawnPowerUp()
    {
        
        double powerProb = Math.random();
        Actor killedThis = this.getOneIntersectingObject(Projectile.class);
        
        /**
         * Of that 20% of the time a powerup appears, spawns a Cluster half the 
         * time and a laser the other half of the time
         */
        if (killedThis!=null && powerProb<=0.20) {
            double whichOne = Math.random();
            
            if (whichOne<0.50) {
                PowerUp boost = new ClusterPowerUp();
                getWorld().addObject(boost, this.getX(), this.getY());
            } else if (whichOne>=050) {
                PowerUp boost = new LaserPowerUp();
                getWorld().addObject(boost, this.getX(), this.getY());
            }
        }
    }
    

    abstract public void act();
    
    
    abstract protected void dropBomb();

}
