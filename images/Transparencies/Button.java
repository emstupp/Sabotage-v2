import greenfoot.*;
import java.awt.Color;

public class Button extends Actor
{
    static String buttonClicked = "";
    
    String btnText = "";
    boolean mouseOn = false;
    String tipText = "";
    Text tip = null;
    
    public Button(String text, Color txtColor, Color bgColor)
    {
        btnText = text;
        GreenfootImage txtImg = new GreenfootImage(text, 18, txtColor, bgColor);
        GreenfootImage btnImg = new GreenfootImage(24, 24);
        btnImg.setColor(bgColor);
        btnImg.fillOval(0, 0, 23, 23);
        btnImg.setColor(txtColor);
        btnImg.drawOval(0, 0, 23, 23);
        btnImg.drawOval(3, 3, 17, 17);
        while (btnImg.getWidth() < txtImg.getWidth() + 20) btnImg.scale(btnImg.getWidth() + 1, btnImg.getHeight());
        while (btnImg.getHeight() < txtImg.getHeight() + 10) btnImg.scale(btnImg.getWidth(), btnImg.getHeight() + 1);
        btnImg.drawImage(txtImg, (btnImg.getWidth() - txtImg.getWidth()) / 2, (btnImg.getHeight() - txtImg.getHeight()) / 2);
        setImage(btnImg);
    }
    
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) buttonClicked = btnText;
        if (tip == null) return;
        if (!mouseOn && Greenfoot.mouseMoved(this))
        {
            mouseOn = true;
            getWorld().addObject(tip, 700, 225);
        }
        if (mouseOn && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
        {
            mouseOn = false;
            getWorld().removeObject(tip);
        }
    }
    
    public void setTipText(String helpText)
    {
        tipText = helpText;
        tip = new Text(tipText, 18, true);
    }
    
    public static String getButtonClicked()
    {
        String text = buttonClicked;
        buttonClicked = "";
        return text;
    }
}
