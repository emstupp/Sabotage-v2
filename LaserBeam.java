import greenfoot.*;
import java.awt.*;
import java.util.List;

/**
 * A green laser that can hit multiple targets
 * 
 * @author Eric Stuppard
 * @version July 17, 2015
 */
public class LaserBeam extends Projectile
{
    public LaserBeam(int rotation)
    {
        setRotation(rotation);
        
        //Make a skinny green rectangle
        setImage(new GreenfootImage(85,3));
        GreenfootImage myImage = getImage();
        myImage.setColor(Color.green);
        myImage.fillRect(0,0,85,3);
    }
    
   
    /**
     * Act - travels in the direction in which it was fired; only is removed when it
     * leaves the screen. Destroys anything that touches it.
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
