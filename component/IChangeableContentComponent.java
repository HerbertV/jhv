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
 * Interface for flagging components that contain unsaved content.
 */
public interface IChangeableContentComponent
{
	/**
	 * hasContentChanged
	 * 
	 * becomes true if it contains unsaved content.
	 * 
	 * @return
	 */
	public abstract boolean hasContentChanged();
	
	
	/**
	 * contentSaved
	 * 
	 * resets the hasContentChanged state 
	 */
	public abstract void contentSaved();
	
}
