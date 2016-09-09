import greenfoot.*;
import java.awt.Color;

public class Text extends Actor
{
    public Text(String text, int size)
    {
        setImage(new GreenfootImage(text, size, Color.black, new Color(0, 0, 0, 0)));
    }
    
    public Text(String text, int size, boolean framed)
    {
        this(text, size);
        getImage().drawRect(0, 0, getImage().getWidth() - 1, getImage().getHeight() - 1);
    }
}
