package data;

import java.awt.Color;

import carte.Position;
import interfaces.data.IComponent;

public class Component implements IComponent{

	/*
	 * Attributs de classe
	 */
	
	@Override
	public String toString() {
		return "Component [componentName=" + name + ", componentsize="
				+ size + ", componentPosition=" + position
				+ "]";
	}

	private String name;
	private int size;
	private Position position;
	Color color;
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Component(){
		
	}
	
	public Component(String ComponentName, Position ComponentPosition, Color col, int ComponentSize) {
		
		this.name = ComponentName;
		this.position = ComponentPosition;
		this.size = ComponentSize;
		this.color = col;
	}



	@Override
	public String showComponentInfo() {
		return "Infos component \n Name : ["+this.name+"]"+"\n Position : ["+this.position+"]"+" \n Size : ["+this.size+"]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}




}
