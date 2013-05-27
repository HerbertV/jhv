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


### jhv.swing.launcher.*

### jhv.swing.task.*

### jhv.swing.webView.JFXWebView

Simple Web View using JavaFX


### jhv.util.config.*

### jhv.util.debug.*

### jhv.util.script.*

A simple javax.script API Wrapper. Designed for JavaScript (Mozilla Rhino), but extendable.



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