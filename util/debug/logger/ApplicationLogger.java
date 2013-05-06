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
package jhv.util.debug.logger;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import jhv.util.debug.DebugLevel;


/**
 * ApplicationLogger.
 * 
 * Wrapper for the java Logger
 */
public class ApplicationLogger 
{
	// ============================================================================
	//  Constants
	// ============================================================================
	
	private static final String path = "logs"+System.getProperty("file.separator");
	
	
	// ============================================================================
	//  Variable
	// ============================================================================
	
	/**
	 * Logger instance
	 */
	private static Logger logger;
	 
	 /**
	  * File Handler
	  */
	private static Handler handler;
	
	/**
	 * Logger Formatter
	 */
	private static Formatter formatter;
	
	/**
	 * Logger Level
	 */
	private static int level = DebugLevel.ALL;
	
	
	// ============================================================================
	//  Constructors
	// ============================================================================
	
	/**
	 * Constructor
	 * 
	 * @param name
	 * @param appStarupTime
	 * @param format
	 * @param lvl
	 */
    private ApplicationLogger(
    		String name, 
    		long appStartupTime,
    		int lvl,
    		Formatter format 
        )
    {
    	
        try {
        	
            if( handler == null ) 
            {
            	String filename = new SimpleDateFormat( "yyyy_MM_dd__HH_mm_ss" ).format(new Date(appStartupTime)) + ".log";
            
            	File dir = new File(path);
            	if( !dir.exists() )
            		dir.mkdir();
            	
            	handler = new FileHandler(path + filename,true);
            }
            formatter = format;
        	handler.setFormatter(formatter);
        
            if( logger == null ) 
            {
                logger = Logger.getLogger(name);
                logger.addHandler(handler);
            }
            setLevel(level);
            
        } catch (Exception ex) {
            System.err.println("Error getting Logger Instance");
        }
    }
    

	// ============================================================================
	//  Functions
	// ============================================================================
	
	/**
	 * getInstance
	 * 
     * @param name
     * @param appStartupTime
     * 
     * @return
     */
	public static ApplicationLogger getInstance(
			String name, 
			long appStartupTime
    	)
    {
		if( formatter == null )
			formatter = new SimpleFormatter();
		
    	return new ApplicationLogger(
    			name,
    			appStartupTime,
    			level, 
    			formatter
    		);
    }
	
	/**
	 * getInstance
	 * 
	 * @param name
	 * @param long 
	 * @param lvl
	 * 
	 * @return
	 */
    public static ApplicationLogger getInstance(
    		String name, 
    		long appStartupTime,
    		int lvl
    	)
    {
    	if( formatter == null )
			formatter = new SimpleFormatter();
		
    	return new ApplicationLogger(
    			name, 
    			appStartupTime,
    			lvl, 
    			formatter
    		);
    }
    
    /**
     * getInstance
     * 
     * @param name
     * @param long 
	 * @param lvl
     * @param format
     * @return
     */
    public static ApplicationLogger getInstance(
    		String name, 
    		long appStartupTime,
    		int lvl, 
    		Formatter format
    	)
    {
    	return new ApplicationLogger(
    			name,
    			appStartupTime,
    			lvl, 
    			format
    		);
    }
    
    public static void logInfo(String msg)
    {
    	if( logger == null )
    		return;
    	
    	logger.info(msg);
    }
    
    public static void logDebug(String msg)
    {
    	if( logger == null )
    		return;
    	
    	logger.config(msg);
    }
    
    public static void logWarning(String msg)
    {
    	if( logger == null )
    		return;
    	
    	logger.warning(msg);
    }
    
    public static void logError(String msg)
    {
    	if( logger == null )
    		return;
    	
    	logger.severe(msg);
    }
    
    public static void logError(Exception e)
    {
    	if( logger == null )
    		return;
    	
    	String msg = e.toString()+"\n\n";
        StackTraceElement[] stack = e.getStackTrace();
        for (int i=0; i<stack.length; i++) {
            msg = msg + stack[i].toString() +"\n";
        }
        
        logger.severe(msg);
    }
    
    public static void logFatalError(String msg)
    {
    	if( logger == null )
    		return;
    	
    	logger.severe("FATAL: "+ msg);
    }
    
    public static void logFatalError(Exception e)
    {
    	if( logger == null )
    		return;
    	
    	String msg = e.toString()+"\n\n";
        StackTraceElement[] stack = e.getStackTrace();
        for (int i=0; i<stack.length; i++) {
            msg = msg + stack[i].toString() +"\n";
        }
        
        logger.severe(msg);
    }
    
    
    /**
     * crashDump
     * 
     * if you want to write a crash dump
     * use this function
     * 
     * @param message
     */
    public static void crashDump(String message)
    {
    	 try 
    	 {
             String filename = new SimpleDateFormat( "crash_yyyy_MM_dd__HH_mm_ss" ).format(new Date()) + ".log";
             Handler crashhandler = new FileHandler(path + filename,true);
             crashhandler.setFormatter(new SimpleFormatter());
         
             Logger crashlogger = Logger.getLogger("Application Crash Dump");
             crashlogger.addHandler(crashhandler);
             crashlogger.setLevel(Level.SEVERE); 
   			 crashlogger.severe(message);
             
         } catch (Exception ex) {
             System.err.println("Error getting Logger Instance");
         }
    }
    
    /**
     * setLevel
     * 
     * maps our debug levels to logging levels
     * 
     * @param lvl
     */
    public void setLevel(int lvl)
    {
    	level = lvl;
    	
    	switch( level ) 
    	{
    		case DebugLevel.OFF:
    			logger.setLevel(Level.OFF); 
    			break;
    		case DebugLevel.ALL:
    			logger.setLevel(Level.ALL); 
    			break;
    		case DebugLevel.INFO:
    			logger.setLevel(Level.INFO); 
    			break;
    		case DebugLevel.DEBUG:
    			logger.setLevel(Level.CONFIG); 
    			break;
    		case DebugLevel.WARNING:
    			logger.setLevel(Level.WARNING); 
    			break;
    		case DebugLevel.ERROR:
    			logger.setLevel(Level.SEVERE); 
    			break;
    		case DebugLevel.FATAL_ERROR:
    			logger.setLevel(Level.SEVERE); 
    			break;
    		default:
    			break;
    	}
    }
    
    /**
     * getLevel
     * 
     * @return
     */
    public int getLevel()
    {
    	return level;
    }
    
    
}
