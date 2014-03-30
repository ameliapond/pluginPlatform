package cle.producer.data;

import java.awt.Color;


public class Component implements IComponent{

	/*
	 * Attributs de classe
	 */
	
	@Override
	public String toString() {
		return "Component [componentName=" + componentName + ", componentsize="
				+ componentsize + ", componentPosition=" + componentPosition
				+ "]";
	}

	private String componentName;
	private Size componentsize;
	private Point componentPosition;
	Color ComponentColor;
	
	public Component(){
		
	}
	
	public Component(String ComponentName, Point ComponentPosition, Size ComponentSize) {
		
		this.componentName = ComponentName;
		this.componentPosition = ComponentPosition;
		this.componentsize = ComponentSize;
		this.ComponentColor = Color.blue;
	}



	@Override
	public String showComponentInfo() {
		return "Infos component \n Name : ["+this.componentName+"]"+"\n Position : ["+this.componentPosition+"]"+" \n Size : ["+this.componentsize+"]";
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	

	public Point getComponentPosition() {
		return componentPosition;
	}

	public void setComponentPosition(Point componentPosition) {
		this.componentPosition = componentPosition;
	}

	public Size getComponentsize() {
		return componentsize;
	}

	public void setComponentsize(Size componentsize) {
		this.componentsize = componentsize;
	}

	@Override
	public Color getComponentColor() {
	
		return this.ComponentColor;
	}

	@Override
	public void setComponentColor(Color color) {
		
		this.ComponentColor = color;
	}




}
