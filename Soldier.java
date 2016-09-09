import greenfoot.*;

/**
 * An infantryman that falls from the sky, lands, and then advanes towards the turret
 * to attack
 * 
 * @author Eric Stuppard 
 * @version July 17, 2015
 */
public class Soldier extends Actor
{
    //Soldiers know where the turret is so they can move towards it upon landing
    private int TurretPosition;
    
    public Soldier()
    {
        getImage().scale(15,25);
    }
    
    /**
     * Falls from the sky, moves towards the turret to latch on to it and try to
     * destroy it
     */
    public void act() 
    {
        World w = getWorld();
        int height = w.getHeight();
        
        //Soldier now knows where the turret is
        SaboWorld thisWorld = (SaboWorld)getWorld();
        TurretPosition = thisWorld.getTurret().getX();
        
        //If the soldier is on the ground, attacks the turret
        if (this.getY() == 340) {
            if (TurretPosition>this.getX()) {
                setLocation(getX()+1, getY());
            } else if (TurretPosition<this.getX()) {
                setLocation(getX()-1, getY());
            }
            
            //Soldier attacks the turret upon contact
            Actor turret = getOneIntersectingObject(Turret.class);
            if (turret!=null) {
                setLocation(getX(), getY());
            }
        }
        else 
        {
            //Soldier keeps falling if he isn't on the ground yet
            setLocation(getX(), getY()+1);
        }

        
    }
}
