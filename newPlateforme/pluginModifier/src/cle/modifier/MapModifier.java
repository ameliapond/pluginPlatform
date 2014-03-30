package cle.modifier;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;

import cle.producer.data.IMap;

public class MapModifier implements IModidifier{
	public MapModifier(){
	}
	
	public IModidifier zoomIn(){
		return new ZoomIn();
	}
	
	public IModidifier zoomOut(){
		return new ZoomOut();
	}
	
	public IModidifier moveMap(){
		return new MoveMap();
	}
	
	public static void main(String args[]){
		MapModifier mod = new MapModifier();
		
		JFrame fram = new JFrame();
		fram.setTitle("Test Producer");
		fram.setLayout(new BorderLayout());
		fram.setSize(660, 390);
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/favicon.png")));
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fram.setResizable(true); 
		fram.add(mod.getView(null));
		fram.setVisible(true);
	}

	@Override
	public void modify(IMap map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IMap reset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JComponent getView(IMap carte) {
		MyViewer mdf = new MyViewer(carte);
		return mdf.getView();
	}
}
