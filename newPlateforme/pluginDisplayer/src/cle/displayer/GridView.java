package cle.displayer;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cle.displayer.utils.Grid;
import cle.producer.data.IComponent;
import cle.producer.data.IMap;
import cle.producer.data.Map;

public class GridView implements IAfficheur{
	public IMap myMap = new Map();
	JPanel pnlSouth;// = new JPanel(new BorderLayout());
	
	public GridView(JPanel view) {
		this.pnlSouth = view;
	}
	@Override
	public void affiche(IMap carte) {
		// TODO Auto-generated method stub
		this.myMap = carte;
		this.pnlSouth.setLayout(new BorderLayout());
		getSouthPanal();
	}

	@Override
	public JComponent getView(IMap carte) {
		// TODO Auto-generated method stub
		getSouthPanal();
		return this.pnlSouth;
	}

	private void getSouthPanal() {
		//JPanel pnlSouth = new JPanel();
		String[] entetes = {"Type", "Name", "Width", "Height", "Pos X", "Pos Y", "Color"};
		
		Object[][] data = new Object[0][0];
		if(myMap != null && !myMap.getItems().isEmpty()){
			data = new Object[myMap.getItems().size()][entetes.length];
			int i = 0;
			for(IComponent comp : myMap.getItems()){
				data[i][0] = comp.getClass().getName();
				data[i][1] = comp.getComponentName();
				data[i][2] = comp.getComponentsize().getWidth();
				data[i][3] = comp.getComponentsize().getHeight();
				data[i][4] = comp.getComponentPosition().getX();
				data[i][5] = comp.getComponentPosition().getY();
				
				JButton label = new JButton();
				label.setBackground(comp.getComponentColor());
				data[i][6] = label;
				i++;
			}
		}
		
		Grid tableau = new Grid(entetes, data, null);
		if(this.pnlSouth.getComponents().length == 0){
			JScrollPane scroll = new JScrollPane(/*tableau.getTable()*/);
			this.pnlSouth.add(scroll);
		}
		JScrollPane scroll = (JScrollPane) this.pnlSouth.getComponent(0);
		scroll.setViewportView(tableau.getTable());
		//return pnlSouth;
	}
}
