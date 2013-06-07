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
package jhv.swing.gridbag;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;


/**
 * GridBagPanel
 * 
 * JPanel extended with GridBagLayout.
 */
public class GridBagPanel 
		extends JPanel 
{

	// ============================================================================
	//  Constants
	// ============================================================================
		
	private static final long serialVersionUID = 1L;
	
	
	// ============================================================================
	//  Variables
	// ============================================================================
		
	/**
	 * GridBagConstraints for GridBagLayout
	 */
	protected GridBagConstraints gbc;
	
	/**
	 * the factory for GridBagConstraints initialize it if you need it.
	 */
	protected GridBagConstraintsFactory gbcFactory;
	
	
	// ============================================================================
	//  Constructors
	// ============================================================================
		
	/**
	 * Constructor 1.
	 */
	public GridBagPanel() 
	{
		super();
		this.setupLayout();
	}

	/**
	 * Constructor 2.
	 * 
	 * @param isDoubleBuffered
	 */
	public GridBagPanel(boolean isDoubleBuffered) 
	{
		super(isDoubleBuffered);
		this.setupLayout();
	}

	
	// ============================================================================
	//  Functions
	// ============================================================================
	
	/**
	 * setupLayout
	 */
	protected void setupLayout()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
        
		this.gbc = GridBagConstraintsFactory.createDefaultContraints();
	}
	
	/**
	 * getConstraints
	 * 
	 * @return
	 */
	public GridBagConstraints getConstraints()
	{
		return this.gbc;
	}
}
