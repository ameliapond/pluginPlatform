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

	@Override
	public boolean equal(IComponent c1){
		
		if((this.componentName.equalsIgnoreCase(c1.getComponentName()))&&(this.getClass().equals(c1.getClass())))
			
			return true;

		return false;
	}
	
	@Override
	public boolean positionCoincide(IComponent c1){
		
		int posX1 = this.getComponentPosition().getX(), 
			posX2 = (int) (posX1+this.getComponentsize().width),
			posY1 = this.getComponentPosition().getY(),
			posY2 = (int) (posY1+this.componentsize.height);
		if((c1.getComponentPosition().getX() >= posX1) &&(c1.getComponentPosition().getX() <= posX2))
			if((c1.getComponentPosition().getY() >= posY1)&&(c1.getComponentPosition().getY() <= posY2))
				return true;
		return false;
	}


}
