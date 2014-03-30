package cle.displayer;

import javax.swing.JComponent;

import cle.producer.data.IMap;

public class Displayer implements IAfficheur{

	@Override
	public void affiche(IMap carte) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JComponent getView(IMap map) {
		MyViewer disp = new MyViewer(map);
		return disp.getView();
	}

}
