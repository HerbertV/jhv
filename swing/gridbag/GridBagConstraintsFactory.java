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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

/**
 * GridBagContraintsFactory
 * 
 * Helper class for my favorite layout manager.
 * It gives you more comfort to handle with the GridBagLayout.
 *  
 * You can access it direct or relative.
 * 
 * Direct usage:
 * 		 addCheckbox( "label", true, 1, 1, 1 );
 * 
 * Relative usage:
 * 		 nextLine();
 * 		 addCheckbox( "label", true, CURRENT, CURRENT, CURRENT );
 * 		 nextX();
 * 
 */
public class GridBagConstraintsFactory 
{
	// ============================================================================
	//  Constants
	// ============================================================================
		
	/**
	 * can be used for gridx, gridy, gridw parameters
	 */
	public static final int CURRENT = -1;
	
	/**
	 * can be used for gridw parameter
	 */
	public static final int USE_FULL_WIDTH = -2;
	
	/**
	 * all components generated with this class have this default height.
	 */
	public static final int DEFAULT_COMPONENT_HEIGHT = 24;
	
	// ============================================================================
	//  Variables
	// ============================================================================
	
	/**
	 * parent JComponent that uses GridBagLayout
	 */
	private JComponent parent;
	
	/**
	 * the constraints
	 */
	private GridBagConstraints gbc;
	
	/**
	 * maximum grid width
	 */
	private int gridWidth;
	
	
	// ============================================================================
	//  Constructors
	// ============================================================================
	
	/**
	 * Constructor 1.
	 * 
	 * Initializes the factory with default settings 
	 * and a grid width of 2
	 * 
	 * @param parent
	 */
	public GridBagConstraintsFactory(JComponent parent)
	{
		this(parent,createDefaultContraints(),2);
	}
	
	/**
	 * Constructor 2.
	 * 
	 * Initializes the factory with default settings 
	 * 
	 * @param parent
	 * @param width
	 */
	public GridBagConstraintsFactory(
			JComponent parent,
			int width
		)
	{
		this(parent,createDefaultContraints(),width);
	}
	
	/**
	 * Constructor 3.
	 * 
	 * all custom
	 * 
	 * @param parent
	 * @param gbc
	 * @param width
	 */
	public GridBagConstraintsFactory(
			JComponent parent,
			GridBagConstraints gbc, 
			int width
		)
	{
		this.parent = parent;
		this.gbc = gbc;
		this.gridWidth = width;
	}
	
	
	// ============================================================================
	//  Functions
	// ============================================================================
	
	/**
	 * createDefaultContraints
	 * 
	 * creates the default constraints.
	 * 
	 * @return
	 */
	public static GridBagConstraints createDefaultContraints()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.weighty = 0;

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.8;
		gbc.insets = new Insets(10,10,0,10);
		
		return gbc;
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
	
	/**
	 * updateGridCoords
	 * 
	 * updates everything that is NOT GridBagContraintsFactory.CURRENT
	 * 
	 * @param gridx
	 * @param gridy
	 * @param gridw
	 */
	private void updateGridCoords(
			int gridx, 
			int gridy,
			int gridw
		)
	{
		if( gridx != CURRENT )
			this.gbc.gridx = gridx;
		
		if( gridy != CURRENT )
			this.gbc.gridy = gridy;
		
		if( gridw == USE_FULL_WIDTH ) {
			this.gbc.gridwidth = this.gridWidth;
		} else if( gridw != CURRENT ) {
			this.gbc.gridwidth = gridw;
		} 
	}
	
	/**
	 * nextX
	 * 
	 * @return new gridx value
	 */
	public int nextX()
	{
		this.gbc.gridx++;
		
		return this.gbc.gridx;
	}
	
	/**
	 * nextY
	 * 
	 * @return new gridy value
	 */
	public int nextY()
	{
		this.gbc.gridy++;
		
		return this.gbc.gridy;
	}
	
	/**
	 * nextLine
	 * 
	 * @return new gridy value
	 */
	public int nextLine()
	{
		this.gbc.gridy++;
		this.gbc.gridx = 0;
		
		return this.gbc.gridy;
	}
	
	/**
	 * addInfoPanel
	 * 
	 * Helper for initializing an info label with titled border
	 * need  the whole grid width 
	 * returns the info label for later use.
	 * 
	 * @param title
	 * @param text
	 * @param gridy grid coord
	 * 
	 * @return
	 */
	public JLabel addInfoPanel(
			String title,
			String text,
			int gridy
		)
	{
		int oldw = this.gbc.gridwidth;
		
		updateGridCoords(0,gridy,USE_FULL_WIDTH);
		
		TitledBorder titleBorder = BorderFactory.createTitledBorder(title);
		JLabel label = new JLabel(text);
		label.setBorder(titleBorder);
		parent.add(label, this.gbc);
		
		this.gbc.gridwidth = oldw;
		
		return label;
	}
	
	/**
	 * addEmptyPanel
	 * 
	 * Adds an empty panel int a new line over the whole width.
	 * 
	 * This is used on the end of each TabPanel.
	 * To adjust the distribution of the previous components.
	 * if you want to align your components to the top.
	 * 
	 * @param gridy
	 */
	public void addEmptyPanel(int gridy)
	{
		updateGridCoords(0,gridy,this.gridWidth);
		
		this.gbc.weighty = 1;
		
		JPanel p = new JPanel();
		
		Dimension d  = p.getPreferredSize();
		d.height = 5;
		p.setPreferredSize(d);
		parent.add(new JPanel(), this.gbc);
	}
	
	/**
	 * addHorizontalSeparator
	 * 
	 * adds a horizontal separator across the whole grid width in a new line.
	 * 
	 * @param gridy
	 */
	public void addHorizontalSeparator(int gridy)
	{
		updateGridCoords(0,gridy,this.gridWidth);
		
		JSeparator s = new JSeparator(SwingConstants.HORIZONTAL);
		parent.add(s, this.gbc);
	}
	
	/**
	 * addFooter
	 * 
	 * combines addEmptyPanel and addHorizontalSeparator
	 * @param gridy
	 */
	public void addFooter(int gridy)
	{
		addEmptyPanel(gridy);
		nextLine();
		addHorizontalSeparator(CURRENT);
	}
	
	
	/**
	 * addComponent
	 * 
	 * adds a custom jcomponent.
	 * 
	 * @param c
	 * @param gridx
	 * @param gridy
	 * @param gridw
	 */
	public void addComponent(JComponent c, 
			int gridx, 
			int gridy,
			int gridw
		)
	{
		updateGridCoords(gridx,gridy,gridw);
		parent.add(c, this.gbc);
	}
	
	/**
	 * addLabel
	 * 
	 * Helper for initializing label
	 * 
	 * @param label
	 * @param gridx
	 * @param gridy
	 * @param gridw
	 * 
	 * @return
	 */
	public JLabel addLabel(
			String label, 
			int gridx, 
			int gridy,
			int gridw
		)
	{
		updateGridCoords(gridx,gridy,gridw);
		
		JLabel lbl = new JLabel(label);
		
		Dimension d = lbl.getPreferredSize();
		d.height = DEFAULT_COMPONENT_HEIGHT;
		lbl.setPreferredSize(d);
		
		d = lbl.getMaximumSize();
		d.height = DEFAULT_COMPONENT_HEIGHT;
		lbl.setMaximumSize(d);
		
		d = lbl.getMinimumSize();
		d.height = DEFAULT_COMPONENT_HEIGHT;
		lbl.setMinimumSize(d);
		
		parent.add(lbl, this.gbc);
		
		return lbl;
	}
	
	/**
	 * addButton
	 * 
	 * @param label
	 * @param gridx
	 * @param gridy
	 * @param gridw
	 * 
	 * @return
	 */
	public JButton addButton(
			String label, 
			int gridx, 
			int gridy,
			int gridw
		)
	{
		updateGridCoords(gridx,gridy,gridw);
		
		JButton btn = new JButton(label);
		
		Dimension d = btn.getPreferredSize();
		d.height = DEFAULT_COMPONENT_HEIGHT -2;
		btn.setPreferredSize(d);
		
		d = btn.getMaximumSize();
		d.height = DEFAULT_COMPONENT_HEIGHT -2;
		btn.setMaximumSize(d);
		
		d = btn.getMinimumSize();
		d.height = DEFAULT_COMPONENT_HEIGHT -2;
		btn.setMinimumSize(d);
		
		parent.add(btn, this.gbc);
		
		return btn;
	}
	
	/**
	 * addCheckbox
	 * 
	 * Helper for initializing a check box with label
	 * 
	 * @param label
	 * @param val
	 * @param gridx
	 * @param gridy
	 * @param gridw
	 * 
	 * @return
	 */
	public JCheckBox addCheckbox(
			String label, 
			boolean val, 
			int gridx, 
			int gridy,
			int gridw
		)
	{
		updateGridCoords(gridx,gridy,gridw);
		
		JCheckBox cbx = new JCheckBox(
        		label, 
        		val
        	);
		parent.add(cbx, this.gbc);
		
		return cbx;
	}
	
	/**
	 * addComboBox
	 * 
	 * Helper for initializing a combo boxs
	 * 
	 * @param vals	can be null
	 * @param preselected can be null 
	 * @param gridx
	 * @param gridy
	 * @param gridw
	 * 
	 * @return 
	 */
	public JComboBox<Object> addComboBox(
			Object[] vals,
			Object preselected,
			int gridx, 
			int gridy,
			int gridw
		)
	{
		updateGridCoords(gridx,gridy,gridw);
		
		JComboBox<Object> cb = null;
		
		if( vals == null )
			cb = new JComboBox<Object>();
		else	
			cb = new JComboBox<Object>(vals);
		
		Dimension d = cb.getPreferredSize();
		d.height = DEFAULT_COMPONENT_HEIGHT-2;
		cb.setPreferredSize(d);
		
		parent.add(cb, this.gbc);
		
		if( vals != null 
				&& preselected != null )
			cb.setSelectedItem(preselected); 
		
		return cb;
	}
	
	/**
	 * addInput
	 * 
	 * creates a Input TextField
	 * 
	 * @param val
	 * @param gridx
	 * @param gridy
	 * @param gridw
	 * 
	 * @return 
	 */
	public JTextField addInput( 
			String val,
			int gridx, 
			int gridy,
			int gridw
		)
	{
		updateGridCoords(gridx,gridy,gridw);
		
		JTextField txt = new JTextField();
		
		Dimension d = txt.getPreferredSize();
		d.height = DEFAULT_COMPONENT_HEIGHT-2;
		txt.setPreferredSize(d);
		
		parent.add(txt, this.gbc);
		txt.setText(val);
		
		return txt;
	}
	
	/**
	 * addNumericSpinner
	 * 
	 * creates a numeric spinner.
	 * 
	 * @param val
	 * @param minVal
	 * @param maxVal
	 * @param step
	 * @param gridx
	 * @param gridy
	 * @param gridw
	 * 
	 * @return 
	 */
	public JSpinner addNumericSpinner( 
			int val,
			int minVal,
			int maxVal,
			int step,
			int gridx, 
			int gridy,
			int gridw
		)
	{
		updateGridCoords(gridx,gridy,gridw);
		
		SpinnerModel model = new SpinnerNumberModel(
				val, 		
				minVal,		
				maxVal, 	
				step 		
			);
		
		JSpinner s = new JSpinner(model);
		
		Dimension d = s.getPreferredSize();
		d.height = DEFAULT_COMPONENT_HEIGHT-2;
		s.setPreferredSize(d);
		
		parent.add(s, this.gbc);
		
		return s;
	}
	
	
	/**
	 * addFileChooser
	 * 
	 * adds a TextField and button [...]
	 * allows only the selection of one file
	 * 
	 * @param val
	 * @param path
	 * @param filter
	 * @param gridx
	 * @param gridy
	 * 
	 * @return Array of JComponents, 
	 * 		[1] = JTextField
	 * 		[2] = JButton
	 */
	public JComponent[] addFileChooser( 
			String val,
			final String path,
			final FileFilter filter,
			int gridx, 
			int gridy
		)
	{
		updateGridCoords(gridx,gridy,1);
		
		JComponent[] comps = new JComponent[2];
		
		final JTextField txt = new JTextField();
		comps[0] = txt;
		parent.add(txt, this.gbc);
		txt.setText(val);
		
		Dimension d = txt.getPreferredSize();
		d.height = DEFAULT_COMPONENT_HEIGHT-2;
		d.width = 150;
		txt.setPreferredSize(d);
		
		nextX();
		
		final JButton btn = new JButton();
		btn.setPreferredSize(new Dimension(40,DEFAULT_COMPONENT_HEIGHT));
		comps[1] = btn;
		parent.add(btn, this.gbc);
		btn.setText("...");
		
		
		btn.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent ae) 
				{
					JFileChooser chooser = new JFileChooser(path);
					chooser.setMultiSelectionEnabled(false);
					chooser.setFileFilter(filter);
					int result = chooser.showOpenDialog(parent);
					
					if( result == JFileChooser.APPROVE_OPTION )
						txt.setText(chooser.getSelectedFile().getName());
				}
			});
		
		return comps;
	}
	
	
	/**
	 * addColorPicker
	 * 
	 * creates a color picker panel.
	 * 
	 * @param dlgTitle
	 * @param val
	 * @param gridx
	 * @param gridy
	 * @param gridw
	 * 
	 * @return
	 */
	public JPanel addColorPicker( 
			final String dlgTitle,
			Color val,
			int gridx, 
			int gridy,
			int gridw
		)
	{
		updateGridCoords(gridx,gridy,gridw);
		
		final JPanel pickPanel = new JPanel();
		pickPanel.setPreferredSize(new Dimension(28, DEFAULT_COMPONENT_HEIGHT));
		pickPanel.setBorder(new LineBorder(Color.BLACK, 1, false));
		pickPanel.setBackground(val);
		pickPanel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					Color newColor =  JColorChooser.showDialog(
							parent, 
							dlgTitle,
							pickPanel.getBackground()
						);
					if( newColor != null ) 
						pickPanel.setBackground(newColor);
				}
			});
		
		parent.add(pickPanel, this.gbc);
		
		return pickPanel;
	}

}
