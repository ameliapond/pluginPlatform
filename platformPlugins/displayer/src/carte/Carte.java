package carte;

import interfaces.data.*;

import java.util.ArrayList;
import java.util.List;

public class Carte implements IMap
{
	private int taille;

	private ArrayList<interfaces.data.IComponent> componentList;

	public Carte(int size)
	{
		taille = size;
	}

	/*
	 * Verifier que la position correspond bien à la taille de la carte.
	 * Verifier qu'il n'existe pas de batiment a l'endoit ou on veut ajouter le
	 * nouveau batiment.
	 */
	public void addComponent(interfaces.data.IComponent component)
	{
		if (componentList == null)
		{
			componentList = new ArrayList<interfaces.data.IComponent>();
			componentList.add(component);
		}else{
			componentList.add(component);
		}
	}

	public void removeComponent(Position position)
	{
		for (interfaces.data.IComponent compo : componentList)
		{
			if (compo.getPosition().equals(position))
			{
				componentList.remove(compo);
			}
		}
	}

	public int getTaille()
	{
		return taille;
	}

	public void setTaille(int taille)
	{
		this.taille = taille;
	}

	public ArrayList<interfaces.data.IComponent> getComponentList()
	{
		return componentList;
	}

	public void setCarte_(ArrayList<interfaces.data.IComponent> carte_)
	{
		this.componentList = carte_;
	}

	@Override
	public List<interfaces.data.IComponent> getItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printMapComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(interfaces.data.IComponent component) {
		// TODO Auto-generated method stub
		
	}

}