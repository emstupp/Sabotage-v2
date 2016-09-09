import greenfoot.*;
/**
 * A bomb that falls from a plane, damaging the turret and exploding upon impact
 * with the turret or a bullet
 * 
 * @author Eric Stuppard
 * @version July 17, 2015
 */
public abstract class Bomb extends Actor
{

    public Bomb()
    {
        getImage().scale(15,15);
    }
    
    abstract public void act();
    
    abstract public void explode();
    
    
}
