import greenfoot.*;

/**
 * A powerup that falls from a plane and gives the player the laser beams
 * if the boost is struck
 * 
 * @author Eric Stuppard 
 * @version July 17, 2015
 */
public class LaserPowerUp extends PowerUp
{
    private LaserPowerUp laser;
    
    /**
     * Randomly falls from the sky when a plane is shot
     * Causes the turret to fire lasers if the powerup is hit
     */
    public void act() 
    {
        setLocation(getX(),getY()+1);
        Actor bullet = getOneIntersectingObject(Projectile.class);
        SaboWorld thisWorld = (SaboWorld) getWorld();
        Turret turret = thisWorld.getTurret();
        
        //Shooting the powerup gives the user this ability
        if (bullet!=null) {
            turret.setPowerUp(this);
            turret.fireLaser();
            getWorld().removeObject(this);
        }
    } 
    
}
