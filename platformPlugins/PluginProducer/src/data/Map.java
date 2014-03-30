package data;

import interfaces.data.IComponent;
import interfaces.data.IMap;

import java.util.ArrayList;
import java.util.List;

public class Map implements IMap{

	List<IComponent> item = new ArrayList<IComponent>();
	
	public Map(){
		
	}
	
	public Map(List<IComponent> list){
		this.item = list;
	}
	

	@Override
	public void addComponent(IComponent component) {
		
		this.item.add(component);
	}

	@Override
	public void removeComponent(IComponent component) {
		
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
	public ArrayList<IComponent> getComponentList() {
		
		return (ArrayList<IComponent>) item;
	}
}
