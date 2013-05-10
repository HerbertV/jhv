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
package jhv.swing.launcher;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

import jhv.image.ImageResource;
import jhv.swing.JImagePanel;

/**
 * Abstract class for launcher and splash screens
 */
abstract public class AbstractLauncher 
		extends JFrame 
{

	// ============================================================================
	//  Variables
	// ============================================================================
		
	private static final long serialVersionUID = 1L;

	/**
	 * singleton instance
	 */
	public static AbstractLauncher openLauncher;
	
	protected JImagePanel imgPanel;
	
	protected int defaultWidth = 600;
	
	protected int defaultHeight = 150;
	
	private Point offsetDrag;
	private JFrame me;
	
	
	// ============================================================================
	// Constructors
	// ============================================================================
	 
	/**
	 * Constructor with images.
	 * 
	 * @param title
	 * @param icon
	 * @param background
	 */
	public AbstractLauncher(
			String title,
			Image icon,
			Image background
		)
	{
		super();
		
		this.setupLayout(title, icon, background);
		
        openLauncher = this;
    }
	
	/**
	 * Constructor with filenames.
	 * 
	 * @param title
	 * @param iconfile
	 * @param bgfile
	 */
	public AbstractLauncher(
			String title,
			String iconfile,
			String bgfile
		)
	{
		super();
		
		ImageResource irIcon = new ImageResource(iconfile,this);
		ImageResource irBackground = new ImageResource(bgfile,this);
		this.setupLayout(title, irIcon.getImage(), irBackground.getImage());
		
        openLauncher = this;
	}
	
	
	// ============================================================================
	// Functions
	// ============================================================================
	
	/**
	 * setupLayout
	 * 
	 * @param title
	 * @param icon
	 * @param background
	 */
	protected void setupLayout(
			String title,
			Image icon,
			Image background 
		) 
	{
		this.setTitle(title);
        this.setIconImage(icon);
        
		this.getContentPane().setLayout(null);
        this.setUndecorated(true);
        this.setSize(defaultWidth, defaultHeight);
        
        imgPanel = new JImagePanel(background);
        this.getContentPane().add(imgPanel,0);
        imgPanel.setLayout(null);
        imgPanel.setBounds(0,0,defaultWidth,defaultHeight);
        
        this.setLocationRelativeTo(null);
        this.me = this;
        
        // enable dragging
        imgPanel.addMouseListener(new MouseAdapter(){
        		public void mousePressed(MouseEvent e) {
        			offsetDrag = e.getPoint();
        		}
        	
        	});
        
        imgPanel.addMouseMotionListener(new MouseMotionAdapter(){
				@Override
		    	public void mouseDragged(MouseEvent e)
		    	{
					Point p = me.getLocation();
					
					p.x = e.getX() + p.x - offsetDrag.x;
					p.y = e.getY() + p.y - offsetDrag.y;
					
		    		me.setLocation(p);
		    	}
		    	
			});
    }
		
	/**
	 * open
	 */
	public static void open()
	{
		if( openLauncher == null )
			return;
		
		openLauncher.setVisible(true);
	}
	
	/**
	 * close
	 */
	public static void close()
	{
		if( openLauncher == null )
			return;
			
		openLauncher.setVisible(false);
		openLauncher.dispose();
	}
	
	/**
	 * bringToFront
	 */
	public static void bringToFront()
	{
		if( openLauncher == null )
			return;
		
		openLauncher.toFront();
	}
		
}
