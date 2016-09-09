import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
import java.util.List;

/**
 * A slow-moving aircraft that drops bombs more often than the Planes
 * 
 * @author Eric Stuppard
 * @version July 17, 2015
 */
public class Chopper extends Fighter
{
    
    public Chopper()
    {
        

    }
    
    /**
     * Act - The Chopper flies from right to left across the screen, at the same height.
     * Occasionally drops a bomb (more often than the plane)
     */
    public void act() 
    {
       spawnPowerUp();
        
       setLocation(getMoveSpeed(),getY());
       double bombProb = Math.random();
       if (bombProb<=0.012) {
           dropBomb();
       }
       
       if (getX() == 0)
        getWorld().removeObject(this);
    }
    
    //Drops a bomb
    public void dropBomb()
    {
        Bomb bomb = new ChopperBomb();
        
        getWorld().addObject(bomb, this.getX(),this.getY());
    }
    
    //How fast the aircraft moves
    public int getMoveSpeed()
    {
        return this.getX()-1;
    }
    
    
}
