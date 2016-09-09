import greenfoot.*;
import java.awt.*;

/**
 * Notifies the player that the game has ended; stops the game
 * 
 * @author Eric Stuppard
 * @version July 17, 2015
 */
public class GameOver extends Actor
{
    public GameOver()
    {
        setImage(new GreenfootImage("Game Over", 48, Color.BLACK, Color.RED));
        setLocation(300,150); //Middle of the screen
    }
    
}
