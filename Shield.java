import greenfoot.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * Write a description of class Shield here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shield extends Actor
{
    // instance variables - replace the example below with your own
    

    /**
     * Constructor for objects of class Shield
     */
    public Shield()
    {
             
        setImage(new GreenfootImage(190,190));
        GreenfootImage myImage = getImage();
        myImage.setColor(Color.BLUE);
        myImage.fillOval(190,190,190,190);
        
                
    }
 
}
