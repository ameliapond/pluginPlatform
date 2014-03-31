package cle.producer.core;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JComponent;

import cle.producer.data.*;

public class DataProducer implements IDataProducer {

	public String ressourcesPath;
	public IMap myMap = null;

	public DataProducer() {
		this.myMap = new Map();
		//this.getMap(); // Add default values to the Map
	}

	public DataProducer(String resPath, Properties prop) {
		this.ressourcesPath = resPath;
	}

	@Override
	public IMap getMap() {

		String str[];
		Component component;
		List<IComponent> list = new ArrayList<IComponent>();
		Map map = new Map(list, Color.WHITE);

		/*
		 * recuperation des valeurs pour la creation des composants et de la map
		 */

		//String filePath = System.getProperty("user.dir") + "/mapconfig";
		
		File currentDir = new File("");
		
		String filePath = currentDir.getAbsolutePath()+File.separator+"mapconfig";

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (scanner != null) {
			while (scanner.hasNextLine()) {

				String line = scanner.nextLine();
				if (!(line.startsWith("#"))) {
					IComponent compo;
					str = line.split(";");
					String className = str[0];
					if(str.length < 1)
						continue;
					String name = str[1];
					if(str.length < 2)
						continue;
					int posX = Integer.parseInt(str[2]);
					if(str.length < 3)
						continue;
					int posY = Integer.parseInt(str[3]);
					if(str.length < 4)
						continue;
					double width = Double.parseDouble(str[4]);
					if(str.length < 5)
						continue;
					double height = Double.parseDouble(str[5]);
					if(str.length < 6)
						continue;
					
					try {
						compo = (IComponent) Class.forName("cle.producer.data."+className).getConstructor(String.class, Point.class, Size.class).newInstance(name, new Point(posX, posY), new Size(width, height));
						//compo.setComponentColor(txtColor.getBackground());
						
						boolean b = this.myMap.componentCompare(compo);
						if(b == false)
						this.myMap.getItems().add(compo);
						else {
							System.out.println("component coincide ");
							continue;
						}	
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
			
			scanner.close();
		}
		return this.myMap;
	}

	public static void main(String args[]) throws FileNotFoundException {

		DataProducer dp = new DataProducer();
		IMap map = dp.getMap();
		map.printMapComponents();

	}

	public JComponent getView() {
		MyViewer view = new MyViewer(this.myMap);
		return view.getView();
	}

}
