import greenfoot.*;
import java.awt.Color;
/**
 * Shows how many times the turret can get hit
 * Game ends when the health bar is empty
 * 
 * @author Eric Stuppard
 * @version July 17, 2015
 */
public class HealthBar extends Actor
{
    //Initializes the dimensionsof the health bar
    private int health = 20;
    private int healthBarWidth = 80;
    private int healthBarHeight = 15;
    private int pixelsPerPoint = (int)healthBarWidth/health;
    
    /**
     * Constantly keeping track of its own state
     */
    public HealthBar()
    {
        update();
    }
    
    public void act()
    {
        update();
    }

    /**
     * Creates a black rectangle, fills it with green to represent the health
     * 
     * The "health" variable changes, and therefore changes the size of the
     * green area
     */
    public void update()
    {
        setImage(new GreenfootImage(healthBarWidth+2,healthBarHeight+2));
        GreenfootImage myImage = getImage();
        myImage.setColor(Color.BLACK);
        myImage.drawRect(0, 0, healthBarWidth+1 ,healthBarHeight+1);
        
        myImage.setColor(Color.GREEN);
        myImage.fillRect(1, 1, health*pixelsPerPoint,healthBarHeight);
    }
    
    //Lose 1 point of health when the turret is hit
    public void loseHealth()
    {
        health--;
    }
    
    /**
     * Retrieves how much health is left
     * @return The remaining health
     */
    public int getHealth()
    {
        return health;
    }
}
