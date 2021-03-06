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
package jhv.util.script;

import java.nio.charset.Charset;

import javax.script.ScriptEngine;

/**
 * AbstractJXScript
 * 
 * abstract class for a script executed by javax.script API
 */
public abstract class AbstractJXScript 
{
	// ============================================================================
	//  Variables
	// ============================================================================
	
	/**
	 * the factory.
	 */
	protected JXScriptFactory scriptFactory;
	
	/**
	 * the script engine.
	 */
	protected ScriptEngine scriptEngine;
	
	/**
	 * script code to invoke
	 */
	protected String scriptCode;
	
	// ============================================================================
	//  Constructors
	// ============================================================================
	
	/**
	 * Constructor
	 * 
	 * @param sf
	 */
	public AbstractJXScript(JXScriptFactory sf) 
	{
		this.scriptFactory = sf;
		this.scriptEngine = sf.getEngine();
	}
	
	// ============================================================================
	//  Functions
	// ============================================================================

	/**
	 * getScriptCode
	 * 
	 * @return
	 */
	public String getScriptCode()
	{
		return this.scriptCode;
	}
	
	/**
	 * injectScriptCode
	 * 
	 * @param code
	 */
	public void injectScriptCode(String code)
	{
		this.scriptCode = code;
	}
	
	/**
	 * loadScriptCode
	 * 
	 * @param filename
	 */
	public void loadScriptCode(String filename)
	{
		this.scriptCode = this.scriptFactory.loadScriptResource(
				filename, 
				Charset.defaultCharset().name()
			);
	}
	
	/**
	 * toString
	 */
	public String toString()
	{
		return  this.getClass().getSimpleName() 
				+": " 
				+ scriptFactory.getEngineName()
				+ " " 
				+ scriptFactory.getEngineVersion()
				+"\n"
				+ this.scriptCode;
	}
	
}
