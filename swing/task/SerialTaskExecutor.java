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
package jhv.swing.task;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingWorker;

import jhv.util.debug.logger.ApplicationLogger;

/**
 * SerialTaskExecutor
 * 
 * Serial background executor.
 * Designed to run with SwingWorker's in a serial queue.
 * 
 * @see AbstractSerialTask
 * 
 * uses Runnable as callback 
 * for exception handling and if the queue are finished.
 */
public class SerialTaskExecutor 
		implements Executor 
{
	// ============================================================================
	//  Variables
	// ============================================================================

	/**
	 * the task queue
	 */
	private final Queue<Runnable> queue = new ArrayDeque<Runnable>();
	
	/**
	 * executor worker.
	 */
	private final ExecutorService executor;
	
	/**
	 * the currently executed runnable
	 */
	private Runnable currentRunnable;
	
	/**
	 * executed if the queue are finished without exceptions or
	 * if continueOnException is true.
	 */
	private Runnable finishedRunnable;
	
	/**
	 * called if continueOnException is false
	 * and an exception occurred.
	 */
	private Runnable errorRunnable;
	
	/**
	 * if true the task continue even if an exception occurred.
	 */
	private boolean continueOnException;
	
	/**
	 * 
	 */
	private boolean onShutdown = false;
	
	/**
	 * true as long the executor does something
	 */
	private boolean runs = false;
	
	// ============================================================================
	//  Constructors
	// ============================================================================
	
	/**
	 * Constructor.
	 * 
	 * @param continueOnExecption
	 */
	public SerialTaskExecutor(boolean continueOnException)
	{
		this.executor = Executors.newSingleThreadExecutor();
		this.continueOnException = continueOnException;
	}

	
	// ============================================================================
	//  Functions
	// ============================================================================
	
	public boolean isRunning()
	{
		return this.runs;
	}
	
	/**
	 * setFinishedRunnable
	 * 
	 * @param r
	 */
	public void setFinishedRunnable(Runnable r)
	{
		this.finishedRunnable = r;
	}
	
	/**
	 * setErrorRunnable
	 * 
	 * @param r
	 */
	public void setErrorRunnable(Runnable r)
	{
		this.errorRunnable = r;
	}
	
	/**
	 * execute
	 * 
	 * @param r
	 */
	@Override
	public synchronized void execute(final Runnable r) 
	{
		queue.offer(new Runnable() {
				public void run() 
				{
					runs = true;
					try 
					{
						r.run();
						
						// to fire up the exception if there was one.
						if( r instanceof SwingWorker )
							((SwingWorker<?,?>)r).get();
						
						
					} catch( Exception e ) {
						ApplicationLogger.logError(e);
						
						if( !continueOnException )
						{
							// clear remaining queue and add callback
							queue.clear();
							if( errorRunnable != null )
								executor.execute(errorRunnable);
							
							SerialTaskExecutor.this.onShutdown = true;
							executor.shutdown();
						}
					}
					scheduleNext();
				}
			});
		
		if( currentRunnable == null ) 
	       scheduleNext();
	}

	/**
	 * scheduleNext
	 */
	protected synchronized void scheduleNext() 
	{
		if( this.onShutdown )
		{
			// reset for the next use
			this.onShutdown = false;
			this.runs = false;
			return;
		}
		
		currentRunnable = queue.poll();
		
		if( currentRunnable != null ) 
		{
			executor.execute(currentRunnable);
		} else {
			// we are done
			if( finishedRunnable != null )
				executor.execute(finishedRunnable);
			
			this.runs = false;
		}
	}
}
