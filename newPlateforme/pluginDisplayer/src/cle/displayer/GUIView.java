package cle.displayer;



import javax.swing.JComponent;
import javax.swing.JPanel;

import cle.producer.data.IComponent;
import cle.producer.data.IMap;

public class GUIView implements IAfficheur{

	public JPanel view;
	/**
	 * @param theMap
	 * @param view
	 */
	public GUIView(JPanel view) {
		this.view = view;
	}

	@Override
	public void affiche(IMap carte) {
		// TODO Auto-generated method stub
		this.view.setLayout(null);
		
		//System.out.println("GUIView NB:"+carte.getItems().size());
		for(IComponent comp : carte.getItems()){
			//System.out.println("aff:"+comp.getComponentName());
			this.view.add(RealComponent.getView(comp));
		}
		//this.view.add(new JLabel("MALABA"));
	}


	@Override
	public JComponent getView(IMap carte) {
		return this.view;
	}
	

}
