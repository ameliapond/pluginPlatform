package cle.producer.data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Map implements IMap{
    
	Color Background;
	List<IComponent> item = new ArrayList<IComponent>();
	
	
	public Map(){
		
	}
	
	public Map(List<IComponent> list, Color background){
		this.item = list;
		this.Background = background;
	}
	

	@Override
	public void addComponents(IComponent component) {
		
		this.item.add(component);
	}

	@Override
	public void removeComponents(IComponent component) {
		
		this.item.remove(component);
		
	}
	
	
	public List<IComponent> getItem() {
		return item;
	}

	public void setItem(List<IComponent> item) {
		this.item = item;
	}

	@Override
	public void printMapComponents() {
		
		for(IComponent comp : this.item){
			System.out.println(comp);
		}
	}

	@Override
	public List<IComponent> getItems() {
		
		return getItem();
	}

	@Override
	public void setBackground(Color color) {
		
		this.Background = color;
		
	}

	@Override
	public Color getBackground() {
				
		return this.Background;
	}

	@Override
	public void setItems(List<IComponent> list) {
		
		this.item = list;
		
	}
}
