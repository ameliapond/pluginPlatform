/**
 * 
 */
package cle.modifier;

import javax.swing.JComponent;

import cle.producer.data.IMap;


/**
 * @author Malaba
 *
 *	This is the class Interface for all modifiers
 */
public interface IModidifier {
	/*
	 * 
	 */
	public void modify(IMap map);
	
	/*
	 * 
	 */
	public IMap reset();
	
	public JComponent getView(IMap carte);
	
}
