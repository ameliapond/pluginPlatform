package cle.modifier;

import java.util.ArrayList;
import java.util.List;

import cle.producer.data.IComponent;
import cle.producer.data.IMap;


public class ZoomOut implements IModidifier{
	List<IMap> lastMaps = new ArrayList<IMap>(); 
	@Override
	public void modify(IMap map) {
		if(map==null)
			return;
		// First : keep the curent state
		if(!lastMaps.contains(map))
				lastMaps.add(map);
		
		for(IComponent component : map.getItems()){
			double width = component.getComponentsize().getWidth();
			double height = component.getComponentsize().getHeight();
			
			// Decrease 10% of the size
			component.getComponentsize().setWidth(width-width*0.1);
			component.getComponentsize().setHeight(height-0.1*height);
		}
	}

	@Override
	public IMap reset() {
		IMap map = null;
		if(!lastMaps.isEmpty()){
			map = lastMaps.get(lastMaps.size()-1);
			lastMaps.remove(lastMaps.size()-1);
		}
		
		return map;
	}
}
