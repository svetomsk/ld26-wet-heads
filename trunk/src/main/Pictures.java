package main;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Pictures 
{
    public static Image pause;
    
    public static Image roll[] = loadAndCut("resources/roll.png", 6, 256);
    public static Image chest[] = loadAndCut("resources/chest.png", 6, 32);
    public static Image complete_level[];
    public static Image blood;
    
    public static Image level[];

    public static Image bodysnake;
    public static Image tailsnake;
    public static Image headsnake;
    
    public Pictures()
    {
        try 
        {
        	pause = ImageIO.read(new File("resources/pause.png"));
        	
        	bodysnake = ImageIO.read(new File("resources/bodysnake.png"));
        	tailsnake = ImageIO.read(new File("resources/tailsnake.png"));
        	headsnake = ImageIO.read(new File("resources/headsnake.png"));
            
            blood = ImageIO.read(new File("resources/blood.png"));
            
            level = new Image[3];
            level[0] = ImageIO.read(new File("resources/level1.png"));
            level[1] = ImageIO.read(new File("resources/level2.png"));
            
            complete_level = new Image[3];
            complete_level[0] = ImageIO.read(new File("resources/level1_complete.png"));
            complete_level[1] = ImageIO.read(new File("resources/level1_complete.png"));
            
            Canvas s = new Canvas();
            
            AreaAveragingScaleFilter aasf = new AreaAveragingScaleFilter((int)128, (int) 128);
            
            aasf = new AreaAveragingScaleFilter(12, 12);
            blood= s.createImage(new FilteredImageSource(blood.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(5130, 2550);
            level[0] = s.createImage(new FilteredImageSource(level[0].getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(5090, 2540);
            level[1] = s.createImage(new FilteredImageSource(level[1].getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(240, 320);
            complete_level[0] = s.createImage(new FilteredImageSource(complete_level[0].getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(32, 32);
            bodysnake = s.createImage(new FilteredImageSource(bodysnake.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(32, 48);
            tailsnake = s.createImage(new FilteredImageSource(tailsnake.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(64, 64);
            headsnake = s.createImage(new FilteredImageSource(headsnake.getSource(), aasf));
            
        } catch (IOException ex) 
        {
        	ex.printStackTrace();
//            Logger.getLogger(Pictures.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Image[] loadAndCut(String name, int sw, int scale) 
    {
    	Image buf = null;
    	try {
			buf = ImageIO.read(new File(name));
		} catch (IOException e) {}
    	
    	Image[] res = new Image[sw];
    	Canvas s = new Canvas();
    	
    	for(int q=0;q<sw;q++)
    	{
    		CropImageFilter filter = new CropImageFilter(q*buf.getWidth(null)/sw, 0, buf.getWidth(null)/sw, buf.getHeight(null));
    		res[q] = s.createImage(new FilteredImageSource(buf.getSource(), filter));
    		
    		AreaAveragingScaleFilter aasf = new AreaAveragingScaleFilter(scale, scale);
    		res[q] = s.createImage(new FilteredImageSource(res[q].getSource(), aasf));
    	}
    	return res;
	}
    
}