package cle.modifier;

import javax.swing.JComponent;

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
}
