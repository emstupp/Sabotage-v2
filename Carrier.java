import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
import java.util.List;

/**
 * A fast-moving aircraft which drops soldiers that attack the turret
 * 
 * @author Eric Stuppard
 * @version July 17, 2015
 */
public class Carrier extends Fighter
{
    
    public Carrier()
    {

    }
    
    /**
     * Act - The plane flies from right to left across the screen, at the same height.
     * Occasionally drops a soldier
     */
    public void act() 
    {
       spawnPowerUp();
        
       setLocation(getMoveSpeed(),getY());
       double soldierProb = Math.random();
       if (soldierProb<=0.008) {
           dropBomb();
       }
       
       if (getX() == 0)
        getWorld().removeObject(this);
    }
    
    //Drops a soldier (called dropBomb() for simplicity in the abstract Fighter class)
    public void dropBomb()
    {
        Soldier thisGuy = new Soldier();
        
        getWorld().addObject(thisGuy, this.getX(),this.getY());
    }
    
    //How fast the aircraft moves
    public int getMoveSpeed()
    {
        return this.getX()-3;
    }
    
}
