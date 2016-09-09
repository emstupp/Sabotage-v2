import greenfoot.*;

/**
 * The bombs dropped from Planes
 * 
 * @author Eric Stuppard
 * @version July 17, 2015
 */
public class PlaneBomb extends Bomb
{
    /**
     * Falls from Planes, explodes when shot or when it hit the turret
     */
    public void act() 
    {
        World w = getWorld();
        int height = w.getHeight();
        
        setLocation(getX(),getY()+1);
        if (getY() <=0 || getY() >= height || getX() <= 0) // off the world
        {
            w.removeObject(this);
            return;
        }
        
        
        SaboWorld thisWorld = (SaboWorld) getWorld();
        Turret turret = thisWorld.getTurret();
        Actor turretActor = getOneIntersectingObject(Turret.class);
        Actor bullet = getOneIntersectingObject(Projectile.class);
        
        if (turretActor!=null && bullet==null) // hit the turret!
        {
            
            turret.bombed(); //Turret loses health
            explode();
            w.removeObject(this);
        } else if (turret==null && bullet!=null) //hit by a bullet!
        {
            explode();
            w.removeObject(this);
        }
        
    }
    
    /**
     * Make an explosion.
     */
    public void explode() {
        
        getWorld().addObject(new Explosion(), getX(), getY());
        getWorld().removeObject(this);
    }
}
