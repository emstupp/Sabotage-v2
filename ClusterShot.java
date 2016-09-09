import greenfoot.*;
import java.util.List;

/**
 * Fires 3 bullets if the poweurp is active
 * 
 * @author Eric Stuppard
 * @version July 17, 2015
 */
public class ClusterShot extends Projectile
{
    /**
     * The 3 bullets fan out
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
        
        if (killed) { //Adds 1 to the score for every plane shot down
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
