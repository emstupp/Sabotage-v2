import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
import java.util.List;

/**
 * A plane that occasionally drops a bomb.
 * 
 * @author Gary Lewandowski, Eric Stuppard
 * @version July 17, 2015
 */
public class Plane extends Fighter
{
    
    public Plane()
    {

    }
    
    /**
     * Act - The plane flies from right to left across the screen, at the same height.
     * Occasionally drops a bomb
     */
    public void act() 
    {
       spawnPowerUp();
        
       setLocation(getMoveSpeed(),getY());
       double bombProb = Math.random();
       if (bombProb<=0.01) {
           dropBomb();
       }
       
       //Disappears once it leaves the screen
       if (getX() == 0)
        getWorld().removeObject(this);
    }
    
    //Drops a bomb!
    public void dropBomb()
    {
        Bomb bomb = new PlaneBomb();
        
        getWorld().addObject(bomb, this.getX(),this.getY());
    }
    
    public int getMoveSpeed()
    {
        return this.getX()-2;
    }
    
}
