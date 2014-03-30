package data;

import java.awt.Color;

import carte.Position;

public class Lawn extends Component{
    
	String componentType;
	
	public Lawn() {
		super();
	}

	public Lawn(String ComponentName, Position ComponentPosition,Color col,int ComponentSize) {
		
		super(ComponentName, ComponentPosition, col, ComponentSize);
		
	}

	
	
}
