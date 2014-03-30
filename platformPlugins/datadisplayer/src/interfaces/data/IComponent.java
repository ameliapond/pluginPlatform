package interfaces.data;
import java.awt.Color;
import carte.Position;

public interface IComponent {

	public String showComponentInfo();

	public Position getPosition();
	
	public void setPosition(Position pos);
	
	public int getSize();

	public void setSize(int size);
	
	public Color getColor();

	public void setColor(Color col);
	
	public String getName();
	
	public void setName(String name);

}
