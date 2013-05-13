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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;


/**
 * AbstractGridBagPanel
 * 
 * JPanel extended by GridBag layout helpers.
 */
public abstract class AbstractGridBagPanel 
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
	
	
	// ============================================================================
	//  Constructors
	// ============================================================================
		
	/**
	 * Constructor 1.
	 */
	public AbstractGridBagPanel() 
	{
		super();
		this.setupLayout();
	}

	/**
	 * Constructor 2.
	 * 
	 * @param isDoubleBuffered
	 */
	public AbstractGridBagPanel(boolean isDoubleBuffered) 
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
        
		this.gbc = new GridBagConstraints();
		this.gbc.anchor = GridBagConstraints.NORTH;
		this.gbc.weighty = 0;

		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.gbc.weightx = 0.8;
		this.gbc.insets = new Insets(10,10,0,10);
	}
	
	
	/**
	 * addInfoPanel
	 * 
	 * Helper for initializing an info label with titled border
	 * returns the info label for later use.
	 * 
	 * @param title
	 * @param text
	 * @param gridx gridbag coord
	 * @param gridy gridbag coord
	 * 
	 * @return
	 */
	protected JLabel addInfoPanel(
			String title,
			String text,
			int gridx, 
			int gridy
		)
	{
		this.gbc.gridwidth = 2;
		this.gbc.gridx = gridx;
		this.gbc.gridy = gridy;
		
		TitledBorder titleBorder = BorderFactory.createTitledBorder(title);
		JLabel label = new JLabel(text);
		label.setBorder(titleBorder);
		
		this.add(label, this.gbc);
		return label;
	}
	
	/**
	 * addEmptyPanel
	 * 
	 * Adds an empty panel.
	 * This is used on the end of each TabPanel.
	 * To adjust the distribution of the previous components.
	 * 
	 * @param gridy
	 */
	protected void addEmptyPanel(int gridy)
	{
		this.gbc.weighty = 1;
		this.gbc.gridwidth = 2;
		this.gbc.gridx = 0;
		this.gbc.gridy = gridy;
		this.add(new JPanel(), this.gbc);
	}
	
	/**
	 * addCheckbox
	 * 
	 * Helper for initializing a checkbox with label
	 * 
	 * @param label
	 * @param val
	 * @param gridx
	 * @param gridy
	 * 
	 * @return
	 */
	protected JCheckBox addCheckbox(
			String label, 
			boolean val, 
			int gridx, 
			int gridy
		)
	{
		this.gbc.gridwidth = 2;
		this.gbc.gridx = gridx;
		this.gbc.gridy = gridy;
		
		JCheckBox cbx = new JCheckBox(
        		label, 
        		val
        	);
		this.add(cbx, this.gbc);
		
		return cbx;
	}
	
	/**
	 * addLabeledComboBox
	 * 
	 * Helper for initializing a combo box with a label
	 * 
	 * @param label
	 * @param vals	can be null
	 * @param gridx
	 * @param gridy
	 * @param preselected can be null 
	 * 
	 * @return Array of JComponents, 
	 * 		[0] = Label 
	 * 		[1] = Combobox
	 */
	protected JComponent[] addLabeledComboBox(
			String label, 
			Object[] vals,
			int gridx, 
			int gridy,
			Object preselected
		)
	{
		JComponent[] comps = new JComponent[2];
		
		this.gbc.gridwidth = 1;
		this.gbc.gridx = gridx;
		this.gbc.gridy = gridy;
		
		comps[0] = new JLabel(label);
		this.add(comps[0] , this.gbc);
		
		this.gbc.gridx = gridx + 1;
		
		if( vals == null )
			comps[1] = new JComboBox<Object>();
		else	
			comps[1] = new JComboBox<Object>(vals);
		
		this.add(comps[1], this.gbc);
		
		if( vals != null 
				&& preselected != null )
			((JComboBox<?>)comps[1]).setSelectedItem(preselected); 
		
		return comps;
	}
	
	/**
	 * addLabeledInput
	 * 
	 * creates a Input TextField with label
	 * 
	 * @param label
	 * @param val
	 * @param gridx
	 * @param gridy
	 * 
	 * @return Array of JComponents, 
	 * 		[0] = Label 
	 * 		[1] = TextField
	 */
	protected JComponent[] addLabeledInput( 
			String label, 
			String val,
			int gridx, 
			int gridy
		)
	{
		JComponent[] comps = new JComponent[2];
		
		this.gbc.gridwidth = 1;
		this.gbc.gridx = gridx;
		this.gbc.gridy = gridy;
		
		comps[0] = new JLabel(label);
		this.add(comps[0] , this.gbc);
		
		this.gbc.gridx = gridx + 1;
		
		comps[1] = new JTextField();
		this.add(comps[1], this.gbc);
		((JTextField)comps[1]).setText(val);
		
		return comps;
	}
	
	/**
	 * addLabeledNumericSpinner
	 * 
	 * creates a numeric spinner with label.
	 * 
	 * @param label
	 * @param val
	 * @param minVal
	 * @param maxVal
	 * @param step
	 * @param gridx
	 * @param gridy
	 * @return
	 */
	protected JComponent[] addLabeledNumericSpinner( 
			String label, 
			int val,
			int minVal,
			int maxVal,
			int step,
			int gridx, 
			int gridy
		)
	{
		JComponent[] comps = new JComponent[2];
		
		this.gbc.gridwidth = 1;
		this.gbc.gridx = gridx;
		this.gbc.gridy = gridy;

		comps[0] = new JLabel(label);
		this.add(comps[0] , this.gbc);
		
		this.gbc.gridx = gridx + 1;
		
		SpinnerModel model = new SpinnerNumberModel(
				val, 		
				minVal,		
				maxVal, 	
				step 		
			);
		
		comps[1] = new JSpinner(model);
		this.add(comps[1], this.gbc);
		
		return comps;
	}
	
}
