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

import java.util.logging.ErrorManager;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * Custom Console output handler for debugging.
 * To get rid off the system.err while logging non errors.
 */
public class ConsoleHandler 
		extends Handler 
{
	/**
	 * Constructor.
	 */
	public ConsoleHandler()
	{
		super();
	}

	@Override
	public void close() throws SecurityException 
	{
		// not needed
	}

	@Override
	public void flush() 
	{
		// not needed
	}

	@Override
	public void publish(LogRecord record) 
	{
		if( getFormatter() == null )
            setFormatter(new SimpleFormatter());
        
        try 
        {
            String message = getFormatter().format(record);
            if( record.getLevel().intValue() >= Level.WARNING.intValue() )
            {
                System.err.write(message.getBytes());                       
            } else {
                System.out.write(message.getBytes());
            }
        } catch( Exception exception ) {
            reportError(null, exception, ErrorManager.FORMAT_FAILURE);
        }
	}

}
