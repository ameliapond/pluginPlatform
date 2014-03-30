package data;

import java.awt.Color;

import carte.Position;

public class Road extends Component {
		
	String componentType;

	public Road() {
		
	}

	public Road(String ComponentName, Position ComponentPosition,Color col, int ComponentSize) {
		super(ComponentName, ComponentPosition, col, ComponentSize);
		this.componentType ="road";
	}
	
	
	
	
}
