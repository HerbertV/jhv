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

public abstract class AbstractJXScript 
{

	
	protected JXScriptFactory scriptFactory;
	
	protected ScriptEngine scriptEngine;
	
	protected String scriptCode;
	
	
	public AbstractJXScript(JXScriptFactory sf) 
	{
		this.scriptFactory = sf;
		this.scriptEngine = sf.getEngine();
	}
	
	public void injectScriptCode(String code)
	{
		this.scriptCode = code;
	}
	
	public void loadScriptCode(String filename)
	{
		this.scriptCode = this.scriptFactory.loadScriptResource(
				filename, 
				Charset.defaultCharset().name()
			);
	}
	
	public String toString()
	{
		return "JXScript: " 
				+ scriptFactory.getEngineName()
				+ " " 
				+ scriptFactory.getEngineVersion()
				+"\n"
				+ this.scriptCode;
	}
	
	

}
