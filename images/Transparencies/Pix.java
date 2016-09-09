import greenfoot.*;
import java.awt.Color;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFrame;

public class Pix extends Actor
{
    static final Color trans = new Color(0, 0, 0, 0);

    String filename = "";
    GreenfootImage savedImage;
    JFrame jf;
    JFileChooser jfc;

    public Pix()
    {
        savedImage = new GreenfootImage(getImage());
        try
        {
            jfc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Picture Files", "jpg", "jpeg", "png");
            jfc.setFileFilter(filter);
            File file = new File(System.getProperties().getProperty("user.dir"));
            jfc.setCurrentDirectory(file);
            jf = new JFrame();
        }
        catch (Exception e)
        {
            jfc=null;
        }
    }
    
    public void makeTransparent(Color color)
    {
        GreenfootImage img = new GreenfootImage(getImage());
        for (int j = 0; j < img.getHeight(); j++) for (int i = 0; i < img.getWidth(); i++)
            if (img.getColorAt(i,j).equals(color)) img.setColorAt(i, j, trans);
        while (img.getWidth() > 2 && removableLine(img, 1, 0, 1, 1)) img = removeLine(img, 0);
        while (img.getHeight() > 2 && removableLine(img, 0, 1, 1, 1)) img = removeLine(img, 1);
        while (img.getWidth() > 2 && removableLine(img, 0, 0, 0, 1)) img = removeLine(img, 2);
        while (img.getHeight() > 2 && removableLine(img, 0, 0, 1, 0)) img = removeLine(img, 3);
        setImage(img);
    }
    
    public void saveImage()
    {
        String filename = getSaveFileName();
        if (!"".equals(filename))
        {
            try
            {
                BufferedImage bi = getImage().getAwtImage();
                File outputfile = new File(filename);
                String ext = filename.substring(filename.indexOf('.')+1);
                if(!"jpeg".equals(ext.toLowerCase()) && !"jpg".equals(ext.toLowerCase()) && !"png".equals(ext.toLowerCase())) throw new IOException();
                ImageIO.write(bi, ext, outputfile);
            }
            catch (IOException e)
            {
                String text = "Save failed.\nEither on site,\nsave cancelled or\nor invalid format.";
                getWorld().addObject(new Info(text), getWorld().getWidth()/2, getWorld().getHeight()/2);
            }
        }
        else
        {
            String text = "Save failed.\nEither on site\nno filename given\nor save cancelled.";
            getWorld().addObject(new Info(text), getWorld().getWidth()/2, getWorld().getHeight()/2);
        }            
    }
    
    public void loadImage()
    {
        String filename = chooseFile();
        if (!"".equals(filename))
        {
            GreenfootImage newImg = new GreenfootImage(filename);
            if (newImg.getWidth()>=600 || newImg.getHeight()>=600)
            {
                newImg.scale(50,50);
                String text = "Load failed.\nImage too large.";
                getWorld().addObject(new Info(text), getWorld().getWidth()/2, getWorld().getHeight()/2);
            }
            else
            {
                setImage(newImg);
                holdImage();
                makeTransparent(trans);
            }
        }
        else
        {
            String text = "Load failed.\nEither on site\nor load cancelled.";
            getWorld().addObject(new Info(text), getWorld().getWidth()/2, getWorld().getHeight()/2);
        }
    }
    
    public void holdImage()
    {
        savedImage = new GreenfootImage(getImage());
    }
    
    public void recallImage()
    {
        setImage(savedImage);
    }

    public void act() 
    {
        if (Greenfoot.mouseClicked(this))
        {
            MouseInfo info = Greenfoot.getMouseInfo();
            int imgX = info.getX()+getImage().getWidth()/2-getX();
            int imgY = info.getY()+getImage().getHeight()/2-getY();
            ((Backdrop) getWorld()).mseBox.setFillColor(getImage().getColorAt(imgX, imgY));
        }
    }    
    
    private String chooseFile()
    {
        if (jfc == null)return "";
        int rc = jfc.showOpenDialog(jf);
        if (rc == JFileChooser.APPROVE_OPTION) 
        {
            File file = jfc.getSelectedFile();
            return file.getAbsolutePath();
        }
        return "";
    }
    
    private String getSaveFileName()
    {
        if (jfc == null) return "";
        int rc = jfc.showSaveDialog(jf);
        if (rc == JFileChooser.APPROVE_OPTION)
        {
            File file = jfc.getSelectedFile();
            return file.getPath();
        }
        return "";
    }
    
    private boolean removableLine(GreenfootImage img, int x0, int y0, int x1, int y1)
    {
        for (int x=(img.getWidth()-1)*x0; x<img.getWidth(); x++) for (int y=(img.getHeight()-1)*y0; y<img.getHeight(); y++)
        {
            int x2 = x, y2 = y;
            if (x1 == 0) x2 = 0;
            if (y1 == 0) y2 = 0;
            if (!img.getColorAt(x2, y2).equals(trans)) return false;
        }
        return true;
    }
    
    private GreenfootImage removeLine(GreenfootImage img, int side)
    {
        int[] line = { 0, 0, 0, 0 };
        line[side] = 1;
        GreenfootImage newImg = new GreenfootImage(img.getWidth()-line[0]-line[2], img.getHeight()-line[1]-line[3]);
        newImg.drawImage(img, -line[2], -line[3]);
        return newImg;
    }

}
