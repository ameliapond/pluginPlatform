package cle.displayer;

import javax.swing.JComponent;

import cle.producer.data.IMap;


public interface IAfficheur{
	public void affiche(IMap carte);
	public JComponent getView(IMap carte);
}
