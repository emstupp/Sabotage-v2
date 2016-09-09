import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * The default bullets fired by the turret until a powerup is obtained
 * 
 * @author Gary Lewandowski, Eric Stuppard
 * @version July 17, 2015
 */
public class RegularBullet extends Projectile
{
    
    public RegularBullet(int rotation)
    {
        getImage().scale(5,5);
        setRotation(rotation);
    }

    /**
     * Act - do whatever the Projectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        World w = getWorld();
        int width = w.getWidth();
        
        move(5);
        if (getX() <=0 || getX() >= width || getY() <= 0) // off the world
        {
            w.removeObject(this);
            return;
        }
        
        List<Fighter> targets = w.getObjectsAt(getX(), getY(), Fighter.class);
        List<Bomb> bombs = w.getObjectsAt(getX(), getY(), Bomb.class);
        List<Soldier> soldiers = w.getObjectsAt(getX(), getY(), Soldier.class);
        boolean killed=false;
        
        for (Fighter f: targets) // hit the planes!
        {
            w.removeObject(f);
            killed = true;
        }
        for (Bomb b : bombs) { //hit the bombs!
            w.removeObject(b);
        }
        for (Soldier s : soldiers) { //hit the soldiers!
            w.removeObject(s);
        }
        
        //Adds 1 to the score if something is hit
        if (killed) {
            SaboWorld saboworld = (SaboWorld)getWorld();
            Counter counter = saboworld.getCounter();
            counter.addScore();
            w.removeObject(this); // if we hit something, this projectile goes away
        }
    }   
    
    /**
     * @param distance is distance to move in the current rotational direction
     * current Actor.move is not causing movement in rotational direction so I rewrote it here.
     */
    public void move(int distance) 
    {
        int currentX = getX();
        int currentY = getY();
        int nextX = (int) (distance*Math.cos(Math.toRadians(getRotation()))+currentX);
        int nextY = currentY - (int)( distance*Math.sin(Math.toRadians(getRotation())));
        setLocation(nextX, nextY);
    }
}
