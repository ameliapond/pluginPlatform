package interfaces.data;

import java.util.ArrayList;
import java.util.List;


public interface IMap {
	
	public List<IComponent> getItems();
	public void printMapComponents();
	public void addComponent(IComponent component);
	public void removeComponent(IComponent component);
	public ArrayList<IComponent> getComponentList();
}
