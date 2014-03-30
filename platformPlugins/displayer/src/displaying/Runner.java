package displaying;

import interfaces.IAfficheur;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;

import carte.Batiment;
import carte.Carte;
import carte.Position;

public class Runner
{
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		Carte carte = new Carte(100);
		IAfficheur ia = new Displayer();
		carte.addComponent(new Batiment("Usine", new Position(50, 10),
				Color.RED, 50));
		carte.addComponent(new Batiment("Ecole", new Position(200, 200),
				Color.RED, 50));
		GUIHelper guiHelper=new GUIHelper(ia);
		guiHelper.affiche(carte);
	}
}