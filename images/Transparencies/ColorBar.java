import greenfoot.*;

/**
 * Class ColorBar:
 * a subclass of the Bar class that gives the bar self-value setting
 */
public class ColorBar extends Bar
{
    Box box;
    /**
     * ColorBar Constructor:
     * just calls the super-class 'Bar' constructor with the same parameters
     *
     * @param ref A parameter to be used as the title for the bar
     * @param units A parameter to be used as the unit of measure text (after the value text)
     * @param val A parameter to set the initial value of the bar 
     * @param max A parameter to set the maximum value of the bar
     */
    public ColorBar(String ref, String units, int val, int max, Box inBox)
    {
        super(ref, units, val, max);
        box = inBox;
    }
    
    /**
     * Method act:
     * constantly shows the mseBox color part
     */
    public void act()
    {
        switch("RGBA".indexOf(getReferenceText().charAt(0)))
        {
            case 0: setValue(box.color.getRed()); break;
            case 1: setValue(box.color.getGreen()); break;
            case 2: setValue(box.color.getBlue()); break;
            case 3: setValue(box.color.getAlpha()); break;
        }
    }
}
