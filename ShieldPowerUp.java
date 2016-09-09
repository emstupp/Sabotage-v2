import greenfoot.*;

/**
 * Write a description of class Invincibility here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShieldPowerUp extends PowerUp
{
    /**
     * Act - do whatever the Invincibility wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setLocation(getX(),getY()+1);
    }    
}
