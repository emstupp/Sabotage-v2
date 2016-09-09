import greenfoot.*;
import java.awt.Color;

public class Box extends Actor
{
    private String caption = "";
    Color color;
    
    public Box(String text)
    {
        caption = text;
    }
    
    public void addedToWorld(World world)
    {
        setFillColor(((Backdrop) world).pix.getImage().getColorAt(0, 0));        
        Text text = new Text(caption, 24);
        world.addObject(text, 0, 0);
        text.setLocation(getX() - text.getImage().getWidth() / 2 - 25, getY());
    }
    
    public void setFillColor(Color fill) 
    {
        GreenfootImage box = new GreenfootImage(30, 30);
        box.setColor(fill);
        box.fill();
        box.setColor(Color.black);
        box.drawRect(0, 0, 29, 29);
        box.drawRect(1, 1, 27, 27);
        setImage(box);
        color = fill;
    }
}
