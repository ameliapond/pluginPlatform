package displaying;

import interfaces.IAfficheur;
import interfaces.data.IComponent;
import interfaces.data.IMap;
import carte.Batiment;
import carte.Carte;
import carte.Position;

import java.awt.Color;
import java.awt.Dimension;

public class Displayer implements IAfficheur
{
	@Override
	public String toString() {
		return "Displayer [jc=" + jc + "]";
	}

	private JCanvas jc;
	
	public Displayer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void affiche(IMap carte)
	{
		jc = new JCanvas(carte);
		jc.setBackground(Color.WHITE);
		jc.setPreferredSize(new Dimension(400, 300));
	}

	public void displayConsole(Carte carte)
	{
		for (IComponent ic : carte.getComponentList())
		{
			System.out.println("nom:" + ic.getName() + "color:" + ic.getColor()
					+ "size:" + ic.getSize());
		}
	}

	public JCanvas getJCanvas() {
		return jc;
	}

	public void setJCanvas(JCanvas jc) {
		this.jc = jc;
	}
}