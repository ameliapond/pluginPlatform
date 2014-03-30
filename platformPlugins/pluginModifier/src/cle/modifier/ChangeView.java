package cle.modifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import cle.producer.data.IComponent;
import cle.producer.data.IMap;


public class ChangeView implements IModidifier{
	List<IMap> lastMaps = new ArrayList<IMap>(); 
	static float nuancy = (float) 10.5;
	@Override
	public void modify(IMap map) {
		if(map==null)
			return;
		// First : keep the curent state
		if(!lastMaps.contains(map))
			lastMaps.add(map);
				
		// here we change color of components
		for(IComponent component : map.getItems()){
			Color color = component.getComponentColor();
			
			float r = (color.getRed()+nuancy)%255;
			float g = (color.getGreen()+nuancy)%255; 
			float b = (color.getBlue()+nuancy)%255;
			
			// Set new color values to the component
			component.setComponentColor(new Color(r, g, b));
		}
		
	}

	@Override
	public IMap reset() {
		// TODO Auto-generated method stub
		return null;
	}

}
