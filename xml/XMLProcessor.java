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
package jhv.xml;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Basic Processor for loading and saving xml files.
 * 
 * uses javax.xml.* and org.w3c.dom.* packages.
 */
public class XMLProcessor 
{
	// ============================================================================
	//  Variables
	// ============================================================================
		
	public static String XML_DOC_VERSION = "0.0";
	
	public static String XML_ROOT_NODE = "unknown";
	
	/**
	 * filename with path
	 */
	protected String filename = "";
	
	/**
	 * File object for the xml doc.
	 */
	protected File xmlFile = null;
	
	/**
	 * Parsed Document.
	 */
	protected Document xmlDoc = null;
	
	/**
	 * Root element of the XML Doc
	 */
	protected Element rootElement = null;
	
	
	public XMLProcessor()
	{
		// TODO
	}
	
	
	public void loadXML()
	{
		// TODO
	}
	
	public void saveXML()
	{
		// TODO
	}
	
	
	public void dispose()
	{
		// TODO
	}
}
