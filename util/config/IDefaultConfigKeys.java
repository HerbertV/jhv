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
package jhv.util.config;

/**
 * This interface contains default config keys.
 * Used with the properties of your implementation of AbstractConfig.
 */
public interface IDefaultConfigKeys 
{
	/**
	 * Key for Properties.
	 * 
	 * application Title Shown in Frame Title and
	 * tray etc.
	 */
	public static final String KEY_APPTITLE = "app.title";
	
	/**
	 * Key for Properties.
	 * 
	 * icon File name 
	 */
	public static final String KEY_APPICON = "app.icon";
	
	/**
	 * Key for Properties.
	 * 
	 * Used language of the app, 
	 * if you have a multilingual app.
	 */
	public static final String KEY_LANGUAGE = "app.language";
	
	/**
	 * Key for Properties.
	 * 
	 * Debugging level @see DebugLevel
	 */
	public static final String KEY_DEBUG_LEVEL = "app.debugging.level";
	
	/**
	 * Key for Properties.
	 * 
	 * Enables Logging with ApplicationLogger.
	 */
	public static final String KEY_IS_LOGGER_ENABLED = "app.debugging.isLoggerEnabled";
	
	/**
	 * Key for Properties.
	 * 
	 * Enables Logging with ApplicationLogger.
	 */
	public static final String KEY_IS_FIRST_LAUNCH = "app.isFirstLaunch";
	
	
}
