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
package jhv.component;

/**
 * Interface for awt.components that load
 * their labels from a Properties resource file.
 */
public interface ILabeledComponent 
{
	/**
	 * loads the labels.
	 * 
	 * should be called by constructor.
	 * 
	 * 
	 * Code sample:
	 * 
	 *	protected LabelResource labelResource;
	 * 	
	 * 	public void loadLabels()
	 * 	{
	 *  	GenesisConfig conf = GenesisConfig.getInstance();
	 *
	 * 		labelResource = new LabelResource(
	 * 				this,
	 * 				conf.getLanguage(), 
	 * 				"labels"
	 *			);
	 *	}
	 *
	 * assign label:
	 * 	
	 * 	labelResource.getProperty("myPropname", "fallback");
	 * 
	 */
	public void loadLabels();
	
}
