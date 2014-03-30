package cle.producer.data;

import java.awt.Color;

public class Road extends Component {
		
	String componentType;

	public Road() {
		
	}

	public Road(String ComponentName, Point ComponentPosition, Size ComponentSize) {
		super(ComponentName, ComponentPosition, ComponentSize);
		this.componentType ="road";
		this.ComponentColor = Color.gray;
	}
	
	
	
	
}
