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
 * Interface for java.awt.Components that are configurable with 
 * jhv.util.conf.AbstractConfig.
 * 
 * Configurable mean for e.g. storing/restoring a components size
 * if it is resizeable.
 */
public interface IConfigurableComponent 
{
	/**
	 * sets the component from the config file.
	 */
	public void applyConfig();
	
	/**
	 * stores the component in the config file.
	 */
	public void saveConfig();
	
}
