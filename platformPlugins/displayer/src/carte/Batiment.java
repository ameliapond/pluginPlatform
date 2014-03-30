package carte;

import interfaces.data.IComponent;

import java.awt.Color;

public class Batiment implements IComponent
{
	private Position position_;
	private int size;
	private Color color;
	private String name;

	public Batiment(String nom, Position pos, Color col, int taille)
	{
		position_ = pos;
		name = nom;
		color = col;
		size = taille;
	}

	public Position getPosition()
	{
		return position_;
	}

	public void setPosition_(Position position_)
	{
		this.position_ = position_;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String showComponentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(Position pos) {
		// TODO Auto-generated method stub
		
	}
}