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

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import jhv.util.debug.logger.ApplicationLogger;

/**
 * Abstract base class for configuring your application, using property files.
 * 
 * System Properties are set by you and can't be altered by the user
 * User Properties can be altered by the application user.
 * The User Defaults are also set in the system properties.
 * 
 * If you have no user settings you can also use only system settings.
 * 
 * it is also used to store global variables, like app start time and app icon.
 */
public abstract class AbstractConfig 
{
	// ============================================================================
	//  Constants
	// ============================================================================
	
	public static final String filenameSystem = 
			"config"+System.getProperty("file.separator")+"system.conf";
	
	public static final String filenameUser = 
			"config"+System.getProperty("file.separator")+"user.conf";
	
	// ============================================================================
	//  Variables
	// ============================================================================
	
	/**
	 * Singleton instance
	 */
	protected static AbstractConfig instance;
	
	/**
	 * all system wide properties.
	 * Including also default user properties for resetting
	 */
	protected static Properties propertiesSystem;
	
	/**
	 * user properties 
	 * e.g. Window size and position.
	 */
    protected static Properties propertiesUser;

    /**
	 *  Start time of the app. 
	 */
	public static long APP_START_TIME = 0;
	
	/**
	 * the apps icon.
	 */
	public static Image APP_ICON;
	
	
    
	// ============================================================================
	//  Function
	// ============================================================================
		
    /**
     * loads the system config file
     */
    protected void loadSystem()
    {
    	propertiesSystem = new Properties();
    	
    	try {
            FileInputStream fis = new FileInputStream(filenameSystem);
            propertiesSystem.load(fis);
            fis.close();
            
        } catch (IOException ex) {
        	
        	File dir = new File("config");
        	if( !dir.exists() )
        		dir.mkdir();
        	
        	this.setSystemDefaults();
        	this.saveSystem();
        }
    }
    
    /**
     * loads the user config file
     */
	protected void loadUser()
    {
    	propertiesUser = new Properties();
    	
    	try {
            FileInputStream fis = new FileInputStream(filenameUser);
            propertiesUser.load(fis);
            fis.close();
            
        } catch (IOException ex) {
        	this.setUserDefaults();
        }
    }
    
	/**
	 * saves the system file
	 */
    public void saveSystem()
    {
        try {
            FileOutputStream fos = new FileOutputStream(filenameSystem);
            propertiesSystem.store(fos,null);
            fos.close();
            
        } catch (IOException ex) {
        	ApplicationLogger.logError(ex);
        }
    }
    
    /**
     * Saves the user file
     */
    public void saveUser()
    {
        try {
            FileOutputStream fos = new FileOutputStream(filenameUser);
            propertiesUser.store(fos,null);
            fos.close();
            
        } catch (IOException ex) {
        	ApplicationLogger.logError(ex);
        }
    }
    
    /**
     * Basic property getter
     * called by all public getters.
     * 
     * Searches user properties (if available) 
     * and then system properties for the given key.
     * 
     * @param propKey
     * @return
     */
    private String getProperty(String propKey)
    {
    	if( propertiesUser != null )
    	{
    		String userProp = propertiesUser.getProperty(propKey,"").trim();
	        if (!userProp.equals(""))
	        {
	            return userProp;
	        }
    	}    
        return propertiesSystem.getProperty(propKey,"").trim();
    }
        
    /**
     *
     * @param propKey
     * @return the value as a String
     */
    public String getString(String propKey)
    {
    	return getProperty(propKey);
    }
    
    /**
     * 
     * @param propKey
     * @return the value as int
     */
    public int getInt(String propKey)
    {
    	return  Integer.parseInt(getProperty(propKey));
    }
    
    /**
     * 
     * @param propKey
     * @return the value as float
     */
    public float getFloat(String propKey)
    {
    	return  Float.parseFloat(getProperty(propKey));
    }
    
    /**
     * 
     * @param propKey
     * @return the value as boolean
     */
    public boolean getBoolean(String propKey)
    {
    	return Boolean.parseBoolean(getProperty(propKey));
    }
    
    
    /**
     * 
     * @param propKey
     * @param value
     */
    public void setSystemProperty(String propKey, String value)
    {
    	propertiesSystem.setProperty(propKey,value);
    }
    
    /**
     * 
     * @param propKey
     * @param value
     */
    public void setUserProperty(String propKey, String value)
    {
        propertiesUser.setProperty(propKey,value);
    }
    
    /**
     * 
     */
    protected abstract void setSystemDefaults();
 
    /**
     * 
     */
	protected abstract void setUserDefaults();
    
    /**
     * 
     */
    public abstract void resetUser();
    
    
}
