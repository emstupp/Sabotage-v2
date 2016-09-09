import greenfoot.*;

/**
 * Class MseActBar:
 * a subclass of the Bar class that gives the bar user click functionality,
 * and informs the world of any value changes.
 */
public class MseActBar extends Bar
{
    // The following fields are used during the time a mouse button is held down;
    // this one informs that a button is held
    private boolean changing = false;
    // this one adds a delay count between changes
    private int timer = 0;
    // this one holds the 'direction' of the change (increasing = 1, decreasing = -1)
    private int changeValue = 0;
    
    /**
     * MseActBar Constructor:
     * just calls the super-class 'Bar' constructor with the same parameters
     *
     * @param ref A parameter to be used as the title for the bar
     * @param units A parameter to be used as the unit of measure text (after the value text)
     * @param val A parameter to set the initial value of the bar 
     * @param max A parameter to set the maximum value of the bar
     */
    public MseActBar(String ref, String units, int val, int max)
    {
        super(ref, units, val, max);
    }
    
    /**
     * Method act:
     * allows the user to perform mouse presses and clicks on (and beside) the bar to change its value
     */
    public void act()
    { // check for initial mouse button pressing
        if (!changing && Greenfoot.mousePressed(this))
        { // a mouse button was pressed down on the bar object; set flag and set timer
            changing = true;
            timer = 50;
            // get mouse info; need to see where the mouse action took place
            MouseInfo mi = Greenfoot.getMouseInfo();
            // check to see if mouse action was outside the color porion of the bar
            if (Math.abs(getX() - mi.getX()) > getBarWidth() / 2)
            { // mouse was pressed on the outside (left or right) of the color portion of the bar
                if (mi.getX() < getX()) setValue(getMinimumValue()); // if left side, minimize value
                if (mi.getX() > getX()) setValue(getMaximumValue()); // if right side, maximize value
            }
            else
            { // mouse pressed on the color portion of the bar; save direction of change, and do initial change
                changeValue = (int) Math.signum(mi.getX() - getX());
                setValue(getValue() + changeValue);
            }
//             ((Backdrop) getWorld()).change = true;
        }
        if (changing)
        { // mouse button was previously found to be pressed down; check if the button is still down
            if (!Greenfoot.mouseClicked(null))
            { // mouse button still being held down; check if time to adjust value again
                timer--;
                if (timer == 0)
                { // time to adjust value again; change the value, inform the world of change, and reset timer
                    setValue(getValue() + changeValue);
//                     ((Backdrop) getWorld()).change = true;
                    timer = 2;
                }
            }
            // mouse button clicked (released), action over
            else changing = false;
        }
    }
}
