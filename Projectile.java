import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * A projectile moves in some direction. When it moves off the screen, it disapears. If it hits a plane
 * a bomb, or a soldier, the item is removed.
 * 
 * @author Eric Stuppard
 * @version July 17, 2015
 */
public abstract class Projectile extends Actor
{
/**
 * the projectile is provided an intial rotation from the object that fired it. This impacts its
 * directional movement.
 */
    public Projectile()
    {
    }
    
    abstract public void act();
    
    abstract public void move(int distance);
}
