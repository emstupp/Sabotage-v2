import greenfoot.*;

/**
 * Contains information of the two powerups
 * 
 * @author Eric Stuppard 
 * @version July 17, 2015
 */
public abstract class PowerUp extends Actor
{
    
    public PowerUp()
    {
        getImage().scale(30,30);
        
    }
    
    /**
     * Falls from a destroyed Fighter 20% of the time
     */
    abstract public void act();
    
}
