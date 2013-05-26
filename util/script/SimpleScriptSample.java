package jhv.util.script;

import javax.script.Invocable;
import javax.script.ScriptException;

/**
 * SimpleScriptSample
 * 
 * a sample java script executed with javax.script API
 * 
 * call it with:
 * 
 * new SimpleScriptSample(new JXScriptFactory());
 */
public class SimpleScriptSample 
		extends AbstractJXScript 
{
	
	public SimpleScriptSample(JXScriptFactory sf) 
	{
		super(sf);
		
		this.scriptCode = 
				"var obj = new Object();\n"
					+ "obj.calc = function (a,b){\n"
					+ "\tvar c = a + b;\n"
					+ "\tprintln('SimpleScriptSample -- insideScript a:'+a+' b:'+b+' c:'+c);\n"
					+ "\treturn c;\n"
					+ "}";
		
		System.out.println("SimpleScriptSample Sample:\n\n"+ this.toString() +"\n");
		
		try 
		{
			this.scriptEngine.eval(scriptCode);
			Invocable inv = (Invocable) scriptEngine;
			Object obj = scriptEngine.get("obj");
			int c = new Double((double) inv.invokeMethod(obj, "calc", 1,2)).intValue();
			System.out.println("SimpleScriptSample -- calculated by script c:"+ c );
		
		} catch( ScriptException | NoSuchMethodException e ) {
			e.printStackTrace();
		}
	}
	
}
