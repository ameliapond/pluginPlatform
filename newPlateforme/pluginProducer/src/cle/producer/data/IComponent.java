package cle.producer.data;

import java.awt.Color;

public interface IComponent {

	public String getComponentName();

	public void setComponentName(String componentName);

	public Point getComponentPosition(); 

	public void setComponentPosition(Point componentPosition);

	public Size getComponentsize();

	public void setComponentsize(Size componentsize);

	public String showComponentInfo();
	
	public Color getComponentColor();
	
	public void setComponentColor(Color color);
}
