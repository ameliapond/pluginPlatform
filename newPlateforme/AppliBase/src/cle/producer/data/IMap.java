package cle.producer.data;

import java.awt.Color;
import java.util.List;



public interface IMap {
	
	public void setBackground(Color color);
	public Color getBackground();
	public List<IComponent> getItems();
	public void setItems(List<IComponent> list);
	public void printMapComponents();
	public void addComponents(IComponent component);
	public void removeComponents(IComponent component);
}
