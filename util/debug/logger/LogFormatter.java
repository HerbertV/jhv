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

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Very simple formatter used by ApplicationLogger
 */
public class LogFormatter 
		extends Formatter 
{

	/**
	 * formats our message 
	 * 
	 * @param record
	 */
	@Override
	public String format(LogRecord r)
	{
		return r.getMessage() +"\n";
	}

}
