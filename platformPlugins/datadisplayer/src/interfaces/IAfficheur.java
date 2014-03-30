package interfaces;

import javax.swing.JPanel;

import interfaces.data.IMap;

public interface IAfficheur
{
	public void affiche(IMap carte);
	public JPanel getJCanvas();
}
