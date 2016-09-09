import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** 
 * The sabotage world has planes, choppers, and carriers flying across and 
 * trying to destroy the lone gun (the Turret) with bombs and soldiers.
 * 
 * @author Gary Lewandowski, Eric Stuppard
 * @version July 17,2015
 */
public class SaboWorld extends World
{
    Counter counter = new Counter();
    HealthBar healthBar = new HealthBar();
    Turret turret = new Turret();
    /**
     * Constructor for objects of class SaboWorld.
     * 
     */
    public SaboWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        addObject(counter, 100, 380); //Add the scoreboard
        addObject(healthBar, 300, 370); //Add the health bar
        addObject(turret, getWidth()/2-5, getHeight()-turret.getImage().getWidth());   
    }
    
    /**
     * Retrieves the scoreboard
     * 
     * @return The scoreboard
     */
    public Counter getCounter()
    {
        return counter;
    }
    
    
    public void act()
    {
        if (Greenfoot.getRandomNumber(100) < 1)
        {
            int y = Greenfoot.getRandomNumber(getHeight()/2);
            addObject(new Plane(), getWidth(), y);
            addObject(new Chopper(), getWidth(), y);
            addObject(new Carrier(), getWidth(), y);
        }
    }
    
    /**
     * Called when game is up. Stop running
     */
    public void gameOver() 
    {
        new GameOver();
        Greenfoot.stop();
    }
    
    /** 
     * Retrieve healthbar information
     * 
     * @return The health bar
     */
    public HealthBar getHealthBar()
    {
        return healthBar;
    }
    
    /** 
     * Retrieve the turret
     * 
     * @return The turret
     */
    public Turret getTurret()
    {
        return turret;
    }
}
