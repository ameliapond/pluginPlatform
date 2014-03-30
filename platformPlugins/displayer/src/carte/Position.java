package carte;

public class Position
{
	private int abscisse;
	private int ordonnee;

	public int getAbscisse()
	{
		return abscisse;
	}

	public Position(int x, int y)
	{
		abscisse = x;
		ordonnee = y;
	}

	public void setAbscisse(int abscisse)
	{
		this.abscisse = abscisse;
	}

	public int getOrdonnee()
	{
		return ordonnee;
	}

	public void setOrdonnee(int ordonnee)
	{
		this.ordonnee = ordonnee;
	}

	public boolean equals(Position pos)
	{
		return ((abscisse == pos.getAbscisse()) && (ordonnee == pos
				.getOrdonnee()));
	}

	@Override
	public String toString() {
		return "x=" + abscisse + ", y=" + ordonnee;
	}
	
	
}
