import greenfoot.*;
import java.awt.Color;

public class Backdrop extends World
{
    private static final int WIDE = 600;
    private static final int HIGH = 600;
    
    Pix pix = new Pix();
    Box mseBox = new Box("Selected color:");
    int bgImgNum = 0;
    
    
    public Backdrop()
    {    
        super(WIDE + 200, HIGH, 1);
        setPaintOrder(Info.class);
        addObject(mseBox, WIDE + 160, 30);
        adjustBackground();
        ColorBar rBar = new ColorBar("Red", "", 255, 255, mseBox);
        rBar.setBreakPercent(0);
        rBar.setSafeColor(Color.red);
        addObject(rBar, WIDE + 100, 75);
        ColorBar gBar = new ColorBar("Green", "", 255, 255, mseBox);
        gBar.setBreakPercent(0);
        gBar.setSafeColor(Color.green);
        addObject(gBar, WIDE + 100, 100); 
        ColorBar bBar = new ColorBar("Blue", "", 255, 255, mseBox);
        bBar.setBreakPercent(0);
        bBar.setSafeColor(Color.blue);
        addObject(bBar, WIDE + 100, 125); 
        ColorBar aBar = new ColorBar("Alpha", "", 255, 255, mseBox);
        aBar.setBreakPercent(0);
        aBar.setSafeColor(Color.white);
        addObject(aBar, WIDE + 100, 150); 
        Button btn = new Button("Make Transparent", Color.black, new Color(236, 236, 236));
        addObject(btn, WIDE + 100, HIGH / 2);
        btn.setTipText("   Sets all pixels having   \ncolor of box above\ntransparent.");
        btn = new Button("Hold", Color.black, new Color(128, 255, 128));
        addObject(btn, WIDE + 100, HIGH / 2 + 50);
        btn.setTipText("   Saves a copy of the   \ncurrent image on\nthe clipboard.");
        btn = new Button("Recall", Color.black, new Color(128, 255, 128));
        addObject(btn, WIDE + 100, HIGH / 2 + 100);
        btn.setTipText("Resets the current\n   image to that which was   \nsaved on the clipboard\n(or the original).");
        btn = new Button("Change Background", Color.black, new Color(255, 255, 128));
        addObject(btn, WIDE + 100, HIGH / 2 + 150);
        btn.setTipText("   Cycles through various   \nbackground colors\nto give different\nviews of the image.");
        btn = new Button("Load", Color.black, new Color(255, 196, 196));
        addObject(btn, WIDE + 100, HIGH / 2 + 200);
        btn.setTipText("Loads a file\nof your choice\n   from the directory.   ");
        btn = new Button("Save", Color.black, new Color(192, 192, 255));
        addObject(btn, WIDE + 100, HIGH / 2 + 250);
        btn.setTipText("   Saves the current   \nimage to a file\nof your choice.");
        addObject(pix, WIDE / 2, HIGH / 2 - 30);
    }
    
    private void adjustBackground()
    {
        GreenfootImage bg = getBackground();
        bg.setColor(new Color(255*((bgImgNum&4)/4), 255*((bgImgNum&2)/2), 255*(bgImgNum&1)));
        bg.fill();
        bg.setColor(Color.lightGray);
        bg.fillRect(WIDE, 0, WIDE + 200, HIGH);
        int boxW = mseBox.getImage().getWidth(), boxH = mseBox.getImage().getHeight();
        GreenfootImage boxBg = new GreenfootImage(boxW, boxH);
        boxBg.drawImage(new GreenfootImage(bg), 0, 0);
        bg.drawImage(boxBg, mseBox.getX()-boxW/2, mseBox.getY()-boxH/2);
    }
    
    public void act()
    {
        String btnClicked = Button.getButtonClicked();
        if ("".equals(btnClicked)) return;
        if ("Make Transparent".equals(btnClicked)) pix.makeTransparent(mseBox.color);
        if ("Hold".equals(btnClicked)) pix.holdImage();
        if ("Recall".equals(btnClicked)) pix.recallImage();
        if ("Change Background".equals(btnClicked))
        {
            bgImgNum = (++bgImgNum) % 8;
            adjustBackground();
        }
        if ("Undo".equals(btnClicked));
        if ("Load".equals(btnClicked)) pix.loadImage();
        if ("Save".equals(btnClicked)) pix.saveImage();
    }
}
