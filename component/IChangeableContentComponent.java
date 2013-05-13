package jhv.component;

public interface IChangeableContentComponent
{
	/**
	 * hasContentChanged
	 * 
	 * becomes true if it contains unsaved content.
	 * 
	 * @return
	 */
	public abstract boolean hasContentChanged();
	
	
	/**
	 * contentSaved
	 * 
	 * resets the hasContentChanged state 
	 */
	public abstract void contentSaved();
	
}
