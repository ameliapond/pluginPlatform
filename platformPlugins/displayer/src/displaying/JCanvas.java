package displaying;

import interfaces.IDrawable;
import interfaces.data.IComponent;
import interfaces.data.IMap;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import carte.Carte;

public class JCanvas extends JPanel
{
	private static final long serialVersionUID = 3511779887660439217L;

	private List<IDrawable> drawables = new LinkedList<IDrawable>();

	private IMap carte;

	public JCanvas(IMap carte)
	{
		super();
		this.carte = carte;
	}

	public void setCarte(Carte map)
	{
		this.carte = map;
	}

	public void addDrawable(IDrawable d)
	{
		drawables.add(d);
		repaint();
	}

	public void removeDrawable(IDrawable d)
	{
		drawables.remove(d);
		repaint();
	}

	public void clear()
	{
		drawables.clear();
		repaint();
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		if (carte != null)
		{
			for (IComponent ic : carte.getComponentList())
			{
				// creation du rectangle
				IDrawable d = new RectangleDrawable(ic.getColor(), new Point(ic
						.getPosition().getAbscisse(), ic.getPosition()
						.getOrdonnee()), new Dimension(20, 20));
				// ajout dans la liste des figures
				addDrawable(d);
			}
			for (Iterator<IDrawable> iter = drawables.iterator(); iter
					.hasNext();)
			{
				IDrawable d = (IDrawable) iter.next();
				d.draw(g);
			}
		}
	}
}