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

import java.util.ArrayList;

import javax.script.Invocable;
import javax.script.ScriptException;

import jhv.util.debug.logger.ApplicationLogger;

/**
 * JXJavaScriptMethod
 * 
 * Class the encapsulates a javascript code snippet
 * into a method body.
 * 
 * In most cases you just want to execute a chunk of code
 * that is dynamically generated or acquired.
 * 
 * This class helps to put that chunk into a method body
 * and executes it with javax.script API in this case
 * with JavaScript.
 * 
 * Since this is a method it needs a return value but no
 * arguments.
 * 
 * If you want to load a script and use it with this class,
 * here is the code sample:
 * 
 * var obj = new Object();
 * obj.calc = function([your arg names])
 * {
 * 		//do your calculation here
 * 		//don't forget to return a value
 * }
 * 
 * @param <T extends Object> return type
 */
public class JXJavaScriptMethod<T extends Object>
		extends AbstractJXScript 
{
	// ============================================================================
	//  Variables
	// ============================================================================
	
	/**
	 * for casting the return value;
	 */
	protected Class<T> type;
	
	
	// ============================================================================
	//  Constructors
	// ============================================================================
		
	/**
	 * Constructor
	 * 
	 * @param sf
	 * @param type
	 */
	public JXJavaScriptMethod(
			JXScriptFactory sf,
			Class<T> type
		)
	{
		super(sf);
		this.type = type;
	}
	
	
	// ============================================================================
	//  Functions
	// ============================================================================
	
	/**
	 * getMethodHeader
	 * 
	 * @param arguments
	 * @return
	 */
	protected String getMethodHeader(ArrayList<JXScriptArgument<?>> arguments)
	{
		String methodHeader = "var obj = new Object();\n"
				+ "obj.calc = function(";
		
		if( arguments != null )
		{
			for( int i=0; i<arguments.size(); i++ )
			{
				methodHeader += arguments.get(i).getName();
				if( i < (arguments.size()-1) )
					methodHeader += ", ";
			}
		}	
		methodHeader += ")\n{\n";
		
		return methodHeader;
	}
	
	/**
	 * getMethodFooter
	 * 
	 * @return
	 */
	protected String getMethodFooter()
	{
		return "\n}\n";
	}
	
	/**
	 * invoke
	 * 
	 * invokes a previously generated or loaded script with new arguments.
	 * Note:
	 * The argument names must match!
	 * 
	 * @param arguments can be null
	 * @return
	 * 
	 * @throws ScriptException
	 */
	public T invoke( 
			ArrayList<JXScriptArgument<?>> arguments
		) throws ScriptException
	{
		try 
		{
			this.scriptEngine.eval(this.scriptCode);
			Invocable inv = (Invocable) scriptEngine;
			Object obj = scriptEngine.get("obj");
			
			Object[] args = null;
			if( arguments != null )
			{
				args = new Object[arguments.size()];
				for( int i=0; i< arguments.size(); i++ )
					args[i] = arguments.get(i).getValue();
			}	
			Object val = inv.invokeMethod(obj, "calc", args);
			
			// workaround since invokeMethod returns only double
			// we must convert it our self before the cast.
			if( val instanceof Double )
			{
				Double d = (Double)val;
				if( type  == Integer.class )
					return type.cast(d.intValue());
				
				if( type  == Float.class )
					return type.cast(d.floatValue());
			}
			return type.cast(val);
			
		} catch( ScriptException e) {
			ApplicationLogger.logError(e);
			throw new ScriptException(e);
		} catch( NoSuchMethodException e ) {
			//nothing to do since we generate the method
		}
		return null;
	}
	
	/**
	 * invoke
	 * 
	 * Generates and invokes the code snippet.
	 * 
	 * @param codeSnippet
	 * @param arguments can be null
	 * 
	 * @return
	 * @throws ScriptException 
	 */
	public T invoke( 
			String codeSnippet,
			ArrayList<JXScriptArgument<?>> arguments
		) throws ScriptException
	{
		this.scriptCode = getMethodHeader(arguments)
				+ codeSnippet
				+ getMethodFooter();
		
		return this.invoke(arguments);
	}

}
