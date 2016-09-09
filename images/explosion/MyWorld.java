import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        super(500, 400, 1);
        placeRocks();
        
        //setBackground("greenfoot.png"); 
    }
    
    public void placeRocks()
    {
        for (int i = 0; i < 10; i++) {
            int x = Greenfoot.getRandomNumber ( getWidth() );
            int y = Greenfoot.getRandomNumber ( getHeight() );
            addObject ( new Rock(), x, y);
        }
    }
}
