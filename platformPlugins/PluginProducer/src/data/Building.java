package data;

import java.awt.Color;

import carte.Position;

public class Building extends Component {
	
	String componentType;
	
	public Building() {
		super();
	}

	public Building(String ComponentName, Position ComponentPosition, Color col,int ComponentSize) {
		
		super(ComponentName, ComponentPosition, col, ComponentSize);
		this.componentType = "Building";
	}

   
	

}
