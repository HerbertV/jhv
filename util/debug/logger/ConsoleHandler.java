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
	public ConsoleHandler()
	{
		super();
	}

	@Override
	public void close() throws SecurityException 
	{
	
	}

	@Override
	public void flush() 
	{
		
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
