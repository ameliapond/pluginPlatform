package cle.producer.data;

import java.awt.Color;

public class Building extends Component {
	
	String componentType;
	
	public Building() {
		super();
	}

	public Building(String ComponentName, Point ComponentPosition,Size ComponentSize) {
		
		super(ComponentName, ComponentPosition, ComponentSize);
		this.componentType = "Building";
		this.ComponentColor = Color.BLACK;
	}

   
	

}
