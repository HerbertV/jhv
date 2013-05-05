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
package jhv.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import jhv.image.ImageResource;

/**
 * JPanel with background image.
 */
public class JImagePanel 
		extends JPanel 
{
	// ============================================================================
	//  Variables
	// ============================================================================
		
	static final long serialVersionUID = 1;
    
    private Image image = null;

    
    // ============================================================================
 	// Constructors
 	// ============================================================================
 	
    /**
     * Constructor with filename
     * 
     * @param filename
     */
    public JImagePanel(String filename) 
    {
    	super();
    	this.setLayout(null);
    	this.setOpaque(false);
    	
    	ImageResource ir = new ImageResource(filename,this);
    	this.image = ir.getImage();
    }

    /**
     * Constructor with image
     * 
     * @param image
     */
    public JImagePanel(Image image) 
    {
		super();
		this.setLayout(null);
		this.setOpaque(false);
		
		this.image = image;
    }

    // ============================================================================
 	//  Functions
 	// ============================================================================
 	
    /**
     * paint
     * 
     * @param g
     */
    @Override
    public void paint(Graphics g)
    {
		Graphics2D g2d = (Graphics2D)g;
		if( image != null )
		{
		    g2d.drawImage(image, 0, 0, this);
		}
		super.paintChildren(g2d);
    }
    
    
    /**
     * update
     * 
     * @param g
     */
    @Override
    public void update(Graphics g)
    {
    	paint(g);
    }
}
