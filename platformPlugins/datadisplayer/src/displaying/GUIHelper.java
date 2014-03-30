package displaying;

import interfaces.IAfficheur;
import interfaces.data.IMap;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JComponent;
import javax.swing.JFrame;

import loader.Loader;

public class GUIHelper
{
	@Override
	public String toString() {
		return "GUIHelper []";
	}

	private IAfficheur ia;
	
	public GUIHelper() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GUIHelper(IAfficheur IA) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		ia = IA;
		IMap map = (IMap) Loader.getInstanceOf("IMap", "Map");
		this.affiche(map);
		
	}
	
	public void showOnFrame(JComponent component, String frameName)
	{
		JFrame frame = new JFrame(frameName);
		WindowAdapter wa = new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		};
		frame.addWindowListener(wa);
		frame.getContentPane().add(component);
		frame.pack();
		frame.setVisible(true);
	}

	public IAfficheur getIa() {
		return ia;
	}

	public void setIa(IAfficheur IA) 
	{
		ia = IA;
	}
	
	public void affiche(IMap carte)
	{
		ia.affiche(carte);
		showOnFrame(ia.getJCanvas(),"Fenêtre principale");
		
	}
}