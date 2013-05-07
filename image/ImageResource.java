/*
 *  __  __      
 * /\ \/\ \  __________   
 * \ \ \_\ \/_______  /\   
 *  \ \  _  \  ____/ / /  
 *   \ \_\ \_\ \ \/ / / 
 *    \/_/\/_/\ \ \/ /  
 *             \ \  /
 *              \_\/
 *
 * -----------------------------------------------------------------------------
 * @author: Herbert Veitengruber 
 * @version: 1.0.0
 * -----------------------------------------------------------------------------
 *
 * Copyright (c) 2013 Herbert Veitengruber 
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/mit-license.php
 */
package jhv.image;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import jhv.util.debug.logger.ApplicationLogger;

/**
 * Helper class for loading images either from the jar file or external.
 */
public class ImageResource 
{
	
	// ============================================================================
	//  Variables
	// ============================================================================
	
	private Image image;
	
	// ============================================================================
	//  Constructors
	// ============================================================================
	
	/**
	 * Constructor.
	 *  
	 * @param filename
	 * @param c
	 */
	public ImageResource(
			String filename, 
			Component c
		)
	{
		
		MediaTracker mt = new MediaTracker(c);
		
		try 
		{
			image = this.loadFromResource(mt,filename);
		} catch( Exception e1 ) {
	      	// no resource found try to load from file
	    	try {
	    		image = this.loadFromFile(mt,filename);
	    	} catch( Exception e2 ) {
	    		// both loading tries failed
	    		// so no icon is available
	    		ApplicationLogger.logError("ImageResource " + filename + " not found!");
	    	}
	    }
	}
	
	// ============================================================================
	//  Functions
	// ============================================================================
	
	/**
	 * loadFromResource
	 * 
	 * @param mt
	 * @param filename
	 * 
	 * @return
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
    private Image loadFromResource(
    		MediaTracker mt, 
    		String filename
    	) 
    	throws IOException, InterruptedException
    {
        Image image = ImageIO.read(
        		getClass().getClassLoader().getResource(filename)
        	);
        mt.addImage(image, 0);
        mt.waitForAll();
        
        return image;
    }
    
    /**
     * loadFromFile
     * 
     * @param mt
     * @param filename
     * 
     * @return
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    private Image loadFromFile(
    		MediaTracker mt, 
    		String filename 
    	)
    	throws IOException, InterruptedException
    {
    	Image image = ImageIO.read(new File(filename));
		mt.addImage(image, 0);
		mt.waitForAll();
		
		return image;
	}
    
    /**
     * getImage
     * 
     * @return
     */
    public Image getImage()
    {
    	return image;
    }
	
    /**
     * getImageIcon
     * 
     * returns the image as ImageIcon
     * 
     * @return
     */
    public ImageIcon getImageIcon()
    {
    	return new ImageIcon(image);
    }

}
