package jhv.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import jhv.util.debug.logger.ApplicationLogger;

/**
 * Helper class for creating folder structures.
 * 
 * based on java.nio.file package
 */
public class PathCreator 
{

	/**
	 * createPathes.
	 * 
	 * @param arr
	 * @throws IOException
	 */
	public static void createPathes(String[] arr) 
			throws IOException
	{
		for( int i=0; i< arr.length; i++ )
		{
			Files.createDirectories(Paths.get(arr[i]));
			ApplicationLogger.logInfo("PathCreator: "+ arr[i]);
		}
	}

}
