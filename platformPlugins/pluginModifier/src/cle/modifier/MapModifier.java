package cle.modifier;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;

import cle.producer.data.IMap;

public class MapModifier {
	private IMap map;
	public MapModifier(IMap tMap){
		this.map = tMap;
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
	
	public JComponent getView(){
		MyViewer mdf = new MyViewer(this.map);
		return mdf.getView();
	}
	
	public static void main(String args[]){
		MapModifier mod = new MapModifier(null);
		
		JFrame fram = new JFrame();
		fram.setTitle("Test Producer");
		fram.setLayout(new BorderLayout());
		fram.setSize(660, 390);
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/favicon.png")));
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fram.setResizable(true); 
		fram.add(mod.getView());
		fram.setVisible(true);
	}
}
