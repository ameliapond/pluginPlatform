package cle.displayer;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import cle.producer.data.IComponent;
import cle.producer.data.IMap;

public class TextView implements IAfficheur{

	public JPanel view;

	public TextView(JPanel view) {
		this.view = view;
	}
	
	@Override
	public void affiche(IMap carte) {
		// TODO Auto-generated method stub
		JTextPane txtPane = new JTextPane();
		for(IComponent comp : carte.getItems()){
			txtPane.setText(txtPane.getText()+"\n"+comp);
		}
		this.view.add(txtPane);
	}

	@Override
	public JComponent getView(IMap carte) {
		// TODO Auto-generated method stub
		return null;
	}

}
