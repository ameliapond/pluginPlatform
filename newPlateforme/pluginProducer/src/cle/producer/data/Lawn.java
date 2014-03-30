package cle.producer.data;

import java.awt.Color;

public class Lawn extends Component{
    
	String componentType;
	
	public Lawn() {
		super();
	}

	public Lawn(String ComponentName, Point ComponentPosition,Size ComponentSize) {
		
		super(ComponentName, ComponentPosition, ComponentSize);
		this.componentType = "Lawn";
		this.ComponentColor = Color.green;
	}

	
	
}
