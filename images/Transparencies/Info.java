import greenfoot.*;
import java.awt.Color;

public class Info extends Actor
{
    String text;
    
    public Info(String txt)
    {
        text = txt;
    }
    
    public void addedToWorld(World world)
    {
        GreenfootImage image = new GreenfootImage(world.getWidth(), world.getHeight());
        image.setColor(new Color(192, 192, 192));
        image.fill();
        image.setColor(new Color(48, 48, 48));
        image.fillRect(image.getWidth()/10, image.getHeight()/8, image.getWidth()*4/5, image.getHeight()*3/4);
        GreenfootImage textImg = new GreenfootImage(text, 96, Color.white, new Color(0, 0, 0, 0));
        image.drawImage(textImg, (image.getWidth()-textImg.getWidth())/2, (image.getHeight()-textImg.getHeight())/3);
        GreenfootImage clikImg = new GreenfootImage("Click to remove", 36, Color.white, new Color(0, 0, 0, 0));
        image.drawImage(clikImg, (image.getWidth()-clikImg.getWidth())/2, (image.getHeight()-clikImg.getHeight())*4/5);
        setImage(image);
    }
    
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) getWorld().removeObject(this);
    }    
}
