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
package jhv.swing.webView;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import static javafx.concurrent.Worker.State.FAILED;

import jhv.util.debug.logger.ApplicationLogger;


/**
 * WebView based on JavaFX.
 *
 * Includes the most basic browser handlers.
 */
public class JFXWebView 
		extends JFXPanel 
{
	// ============================================================================
	//  Variables
	// ============================================================================
	
	private static final long serialVersionUID = 1L;
	
	private WebView webView;
	
	private WebEngine webEngine;
	
	private Window frameOrDialog;
	
	private String titlePrefix;
	
	private JLabel statusLabel;
	
	// ============================================================================
	//  Constructors
	// ============================================================================
		
	/**
	 * Constructor
	 */
	public JFXWebView()
	{
		super();
		
		this.titlePrefix = "";
	}
	
	// ============================================================================
	//  Functions
	// ============================================================================
	
	/**
	 * initFX
	 * 
	 * must be called from JavaFx thread.
	 */
	private void fxInit()
	{
		ApplicationLogger.newLine();
		ApplicationLogger.logInfo(
				"JFXWebView initializes JavaFX..."
			);
		ApplicationLogger.separator();
		ApplicationLogger.logInfo(
				"JavaFX Version: " + System.getProperty("javafx.runtime.version")
			);
		ApplicationLogger.separator();
		
		webView = new WebView();
        webEngine = webView.getEngine();
        
        this.setScene(new Scene(webView));
        this.setVisible(true);
	}
	
	/**
	 * fxInitErrorHandler
	 * 
	 * must be called from JavaFx thread.
	 */
	private void fxInitErrorHandler()
	{
		webEngine.getLoadWorker().exceptionProperty().addListener(
				new ChangeListener<Throwable>() {

					public void changed(
							ObservableValue<? extends Throwable> o, 
							Throwable old, 
							final Throwable value
						)
					{
						if( webEngine.getLoadWorker().getState() == FAILED ) 
						{
							SwingUtilities.invokeLater(new Runnable() {
									@Override 
									public void run() 
									{
										String msg = webEngine.getLocation();
										
										if( value != null )
										{
											msg += "\n" + value.getMessage();
										} else {
											msg += "\nUnexpected error.";
										}
										
										ApplicationLogger.logError(
												"JFXWebView Loading Error.\n" + msg 
											);
										JOptionPane.showMessageDialog(
												JFXWebView.this,
												msg,
												"Lade Fehler ...",
												JOptionPane.ERROR_MESSAGE
											);
									}
								
								});
						}
					}
			});
	}
	
	/**
	 * inits all callbacks to swing.
	 */
	private void fxInitCallbacks()
	{
		webEngine.titleProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(
						ObservableValue<? extends String> observable, 
						String oldValue, 
						final String newValue
					)
				{
					SwingUtilities.invokeLater(new Runnable() {
							@Override 
							public void run() 
							{
								if( frameOrDialog == null )
									return;
								
								String suffix = newValue;
								
								if( newValue == null )
									suffix = "Verbinde ...";
									
								if( frameOrDialog instanceof Dialog )
								{
									((Dialog)frameOrDialog).setTitle(titlePrefix + suffix);
									
								} else if( frameOrDialog instanceof Frame ) {
									((Frame)frameOrDialog).setTitle(titlePrefix + suffix);
								}
							}
						});
				}
        	});
		
		webEngine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
				@Override 
				public void handle(final WebEvent<String> event) 
				{
					SwingUtilities.invokeLater(new Runnable() {
							@Override 
							public void run() 
							{
								if( statusLabel == null )
									return;
								
								statusLabel.setText(event.getData());
							}
						});
				}
        	});
        
		
	}
	
	/**
	 * createJavaFX.
	 * 
	 * must be called from JRE thread.
	 * 
	 * initializes all javaFX callbacks and handlers.
	 * call it after setup is done. 
	 */
	public void createJavaFX()
	{
		//init from the java fx thread
		Platform.runLater(new Runnable() {
				@Override
		        public void run() {
					fxInit();
		            fxInitErrorHandler();
		            fxInitCallbacks();
		        }
			});
	}
	
	/**
	 * setupWindowTitle
	 * 
	 * must be called from JRE thread. 
	 * 
	 * @param window either a frame or dialog
	 * @param titleprefix (optional)
	 */
	public void setupWindowTitle(
			Window window, 
			String titleprefix
		)
	{
		this.frameOrDialog = window;
		
		if( titleprefix != null ) 
			this.titlePrefix = titleprefix;
	}
	
	public void setupStatusLabel(
			JLabel lbl
		)
	{
		this.statusLabel = lbl;
	}
	
	
	/**
	 * openURL
	 * 
	 * must be called from JRE thread.
	 * 
	 * opens a url.
	 * don't forget to add the protocol. http:, file: or https:
	 * 
	 * @param url
	 */
	public void openURL(final String url) 
	{
		Platform.runLater(new Runnable() {
			@Override 
			public void run() 
			{
				try 
				{
		            webEngine.load(new URL(url).toExternalForm());
		        } catch( MalformedURLException e ) {
		             // TODO Dialog and Log
		        	System.out.println(e.toString());
		        }
			}
        });
    }
	
	/**
	 * reload
	 * 
	 * must be called from JRE thread.
	 */
	public void reload()
	{
		Platform.runLater(new Runnable() { 
            @Override
            public void run() {
                webEngine.reload();
            }
        });
	}
	
	/**
	 * browseBack
	 * 
	 * must be called from JRE thread.
	 */
	public void browseBack()
	{
		Platform.runLater(new Runnable() { 
            @Override
            public void run() {
                webEngine.executeScript("history.back()");
            }
        });
	}
	
	/**
	 * browseForward
	 * 
	 * must be called from JRE thread.
	 */
	public void browseForward()
	{
		Platform.runLater(new Runnable() { 
            @Override
            public void run() {
                webEngine.executeScript("history.forward()");
            }
        });
	}
	
	
}
