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

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 * AbstractSerialTask
 * 
 * SwingWorker Task designed to run on its own or inside SerialTaskExecutor
 * 
 * needs a status label for showing progress info.
 * ProgressBar is optional
 */
public abstract class AbstractSerialTask 
		extends SwingWorker<String, String>
{
	
	// ============================================================================
	//  Variables
	// ============================================================================

	/**
	 * label where the current worker status is posted.
	 */
	protected JLabel statusLabel;
	
	/**
	 * progressbar (optional) 
	 */
	protected JProgressBar progressBar;
	
	/**
	 * currentStep
	 */
	protected int currentStep = 0;
	
	/**
	 * maximum number of steps.
	 */
	protected int maxSteps = 1;
	
	
	protected Exception exception;
	
	
	// ============================================================================
	//  Constructors
	// ============================================================================

	/**
	 * Constructor with label.
	 * 
	 * @param lbl
	 */
	public AbstractSerialTask(
			JLabel lbl
		)
	{
		this.statusLabel = lbl;
		this.progressBar = null;
	}
	
	/**
	 * Constructor with label and progress bar.
	 * 
	 * @param lbl
	 * @param progress
	 */
	public AbstractSerialTask(
			JLabel lbl, 
			JProgressBar progress
		)
	{
		this.statusLabel = lbl;
		this.progressBar = progress;
	}

	
	// ============================================================================
	//  Functions
	// ============================================================================
	
	/**
	 * doInBackground
	 * 
	 * returns null since we don't need the whole list.
	 */
	@Override
	protected String doInBackground() 
			throws Exception 
	{
		while( currentStep < maxSteps )
		{
			String msg = prepareNextStep();
			setProgress( (int)(((float)currentStep)/maxSteps * 100) );
			publish(msg);
			doNextStep();
			
			currentStep++;
		}
		return null;
	}

	/**
	 * doNextStep
	 * 
	 * generates a message for our statusLabel.
	 * 
	 * @return
	 */
	protected abstract String prepareNextStep();
	
	/**
	 * doNextStep
	 * 
	 * executes the next step
	 * 
	 * @throws Exception
	 */
	protected abstract void doNextStep() throws Exception;
	
	/**
	 * called by main thread
	 */
	@Override
	protected void process(List<String> chunks) 
	{
		if( chunks.size() > 0 )
			statusLabel.setText(chunks.get(chunks.size()-1));
		
		if( progressBar != null )
			progressBar.setValue(getProgress());
	}
	
	/**
	 * getMaximumSteps
	 * 
	 * @return
	 */
	public int getMaximumSteps()
	{
		return this.maxSteps;
	}
	
	/**
	 * getCurrentStep
	 * 
	 * @return
	 */
	public int getCurrentStep()
	{
		return this.currentStep;
	}
	
}
