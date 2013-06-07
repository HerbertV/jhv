#jhv.* Class Package

-----------------------------------

*A basic Java class lib for including as submodule into other repos.*

-----------------------------------

## About

Requires Java 1.7+ 

This is a collection of classes I use for my Java projects. 
Since I haven't reviewed all classes for Java 1.7 this only a part of it.


## Classes of Interest

### jhv.component.LabelResource

Loads a properties file (used for labels) from a locale path. 
Looks for it first in JAR and if not found in the same external folder.


### jhv.image.ImageResource

Loads a image/icon from a file.
Looks for it first in JAR and if not found in the same external folder.


### jhv.io.PathCreator

Creates folder structures from an array.


### jhv.swing.gridbag.*

Helpers for the GridBagLayout. Contains **GridBagPanel** and **GridBagContraintFactory**.

GridBagContraintFactory is for simplify the work with GridBags. 
It contains several helper functions for creating the most frequently components

Samples:

	Direct usage:
	// add a checkbox at grid 1,1 with grid width 1
 	addCheckbox( "label", true, 1, 1, 1 ); 

	Relative usage:
 	// go to beginning of next line
 	nextLine();
 	// add a checkbox at the beginnig of the next line with current grid width
 	addCheckbox( "label", true, CURRENT, CURRENT, CURRENT );
	// go to next x grid position
	nextX();


### jhv.swing.launcher.*


### jhv.swing.task.*

Helpers for SwingWorker. The **SerialTaskExecutor** executes all its Task in serial order (FIFO).
The **AbstractSerialTask** is a abstract wrapper that supplies you with status label and 
progress bar handling.


### jhv.swing.webView.JFXWebView

Simple Web View using JavaFX


### jhv.util.config.*

Abstract classes and interfaces for loading/saving application configurations from an properties file.


### jhv.util.debug.*

A very simple application logger.


### jhv.util.script.*

A simple javax.script API Wrapper. Designed for JavaScript (Mozilla Rhino), but extendable.

See (SimpleScriptSample) [https://github.com/HerbertV/jhv/blob/master/util/script/SimpleScriptSample.java].

Sample for using JXJavaScriptMethod:

	// prepare arguments
	ArrayList<JXScriptArgument<?>> arr = new ArrayList<JXScriptArgument<?>>();
	JXScriptArgument arg1 = JXScriptArgument<Integer>("a", 1);
	arr.add(arg1);
	JXScriptArgument arg2 = JXScriptArgument<Integer>("b", 2);
	arr.add(arg2);
	
	//prepare  method with return type integer
	JXJavaScriptMethod m = JXJavaScriptMethod(
			new JXScriptFactory(),
			Integer.class
		);
	
	// codesnippet
	String snippet = "var c = a + b; return c;";
	
	// invoke script
	int val = m.invoke(snippet,arr);
	
	// prints 3
	System.out.println(val);


### jhv.xml.*



## Brief How-To Include:


Git BASH:

	// adding submodule
	git submodule add git@github.com:HerbertV/jhv.git

	// init submodule
	git submodule init
	git submodule update

	// change dir to jhv
	cd jhv
	
	git checkout master
	git pull


Also check [Git Book on submodules](http://book.git-scm.com/5_submodules.html)

## License:

[jhv classes are Licensed under MIT License](http://opensource.org/licenses/MIT)