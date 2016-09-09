import greenfoot.*;
import java.awt.Color;

/**
 * Keeps count of how many planes have been destroyed
 * 
 * @author Eric Stuppard 
 * @version July 17, 2015
 */
public class Counter extends Actor
{
    private int score = 0;
    /**
     * Keeps score for the user
     * Planes = 10 pts.
     * Choppers = 20 pts.
     * Soldiers = 40 pts.
     */
    public void act() 
    {
        setImage(new GreenfootImage("Score: " + score, 24, Color.BLUE, Color.WHITE));
        
    }
    
    //Increments the score by 1
    public void addScore()
    {
        score++;
    }
}
