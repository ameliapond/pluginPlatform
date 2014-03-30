package cle.modifier;

import java.util.ArrayList;
import java.util.List;

import cle.producer.data.IComponent;
import cle.producer.data.IMap;

public class MoveMap implements IModidifier{
	List<IMap> lastMaps = new ArrayList<IMap>(); 
	static int step = 5;
	@Override
	public void modify(IMap map) {
		if(map==null)
			return;
		// First : keep the curent state
		if(!lastMaps.contains(map))
			lastMaps.add(map);
		
		moveRight(map);
		moveDown(map);
	}
	
	public void moveRight(IMap map){
		for(IComponent component : map.getItems()){
			System.out.println("comp moved: "+component.getComponentName());
			double posX = component.getComponentPosition().getX();
			//double posY = component.getComponentPosition().getY();
			
			// move 10% of postion to left
			component.getComponentPosition().setX((int)(posX + step));
			//component.getComponentPosition().setY((int)(posY + step));
		}
	}
	
	public void moveLeft(IMap map){
		for(IComponent component : map.getItems()){
			double posX = component.getComponentPosition().getX();
			//double posY = component.getComponentPosition().getY();
			
			// move 10% of postion to left
			component.getComponentPosition().setX((int)(posX - step));;
			//component.getComponentPosition().setY((int)(posY + step));
		}
	}

	public void moveUp(IMap map){
		for(IComponent component : map.getItems()){
			//double posX = component.getComponentPosition().getX();
			double posY = component.getComponentPosition().getY();
			
			// move 10% of postion to left
			//component.getComponentPosition().setX((int)(posX + step));;
			component.getComponentPosition().setY((int)(posY - step));
		}
	}
	
	public void moveDown(IMap map){
		for(IComponent component : map.getItems()){
			//double posX = component.getComponentPosition().getX();
			double posY = component.getComponentPosition().getY();
			
			// move 10% of postion to left
			//component.getComponentPosition().setX((int)(posX + step));;
			component.getComponentPosition().setY((int)(posY + step));
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
