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

/**
 * JXScriptArgument 
 * 
 * @see JXJavaScriptMethod
 *
 * @param <T>
 */
public class JXScriptArgument<T>
{
	// ============================================================================
	//  Variables
	// ============================================================================
	
	/**
	 * the value of the argument
	 */
	private T value;
	
	/**
	 * the name used inside the script
	 */
	private String name;
	
	
	// ============================================================================
	//  Consturctors
	// ============================================================================
	
	/**
	 * Constructor.
	 * 
	 * @param name
	 * @param val
	 */
	public JXScriptArgument(String name, T val) 
	{
		this.name = name;
		this.value = val;
	}

	
	// ============================================================================
	//  Functions
	// ============================================================================
	
	/**
	 * getValue
	 * 
	 * @return
	 */
	public T getValue()
	{
		return value;
	}
	
	/**
	 * getName
	 * 
	 * @return
	 */
	public String getName()
	{
		return name;
	}
}
