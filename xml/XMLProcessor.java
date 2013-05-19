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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import jhv.util.debug.logger.ApplicationLogger;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Basic XMLProcessor for loading and saving xml files.
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
	
	// ============================================================================
	//  Variables
	// ============================================================================
	
	/**
	 * singleton instance
	 */
	private static XMLProcessor instance;

	protected DocumentBuilderFactory documentBuilderFactory;
	
	// ============================================================================
	//  Constructors
	// ============================================================================
	
	/**
	 * Constructor.
	 * 
	 * Can be overridden to adjust configuration.
	 */
	protected XMLProcessor() 
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		// Configure it to coalesce CDATA nodes
		documentBuilderFactory.setCoalescing(true);
		documentBuilderFactory.setExpandEntityReferences(true);
		documentBuilderFactory.setIgnoringElementContentWhitespace(false);
	}
	
	
	// ============================================================================
	//  Functions
	// ============================================================================
	
	/**
	 * getInstance
	 * 
	 * @return
	 */
	public static XMLProcessor getInstance()
	{
		if( instance == null )
			instance = new XMLProcessor();
		
		return instance;
	}
	
	/**
	 * loadXML
	 * 
	 * @param filename
	 * @return
	 */
	public Document loadXML(String filename)
	{
		try 
		{
			DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
			Document doc =  db.parse(filename);
			
			return doc;
		
		} catch (ParserConfigurationException | SAXException | IOException e) {
			ApplicationLogger.logError(e);
		} 
		
		return null;
	}
	
	/**
	 * saveXML
	 * 
	 * @param doc
	 * @param filename
	 */
	public void saveXML(
			Document doc, 
			String filename
		)
	{
		try 
		{
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer;
			transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileOutputStream(filename));
			transformer.transform(source, result); 
			
		} catch (FileNotFoundException | TransformerException  e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * checkXML
	 * 
	 * just a simple check without schema validation.
	 * 
	 * @param doc
	 * @return
	 */
	public boolean checkXML(Document doc)
	{
		if( !doc.getDocumentElement().getAttribute("XMLVersion").equals(XML_DOC_VERSION) )
    	{
    		ApplicationLogger.logError("XML Document does not match version "+XML_DOC_VERSION);
			return false;
    	}
		
		if ( !doc.getDocumentElement().getNodeName().equals(XML_ROOT_NODE) )
		{
			ApplicationLogger.logError("XML Document does not have Root Node "+XML_ROOT_NODE);
			return false;
	    }
    	return true;
	}
	
	/**
	 * vaildateSchema
	 * 
	 * a full schema validation
	 * 
	 * @param doc
	 * @param schemafile
	 * 
	 * @return
	 */
	public boolean vaildateSchema(Document doc, String schemafile)
	{
		try
		{
			SchemaFactory sfactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sfactory.newSchema(new StreamSource(new FileInputStream(schemafile)));
			Validator validator = schema.newValidator();
			validator.validate(new DOMSource(doc));
			return true;
			
		} catch(Exception e) {
			ApplicationLogger.logError(e);
		}
		return false;
	}
}
