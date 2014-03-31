package cle.producer.core;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JComponent;

import cle.producer.data.*;

public class DataProducer implements IDataProducer{

	public String ressourcesPath;
	public IMap myMap = null;	
	
	public DataProducer() {
		this.myMap = new Map();
	}
	
	public void setMap(IMap map){
		map = this.myMap;
	}
	
	public DataProducer(String resPath, Properties prop ) {
		this.ressourcesPath = resPath;
	}
	
	
	
	
	@Override
	public IMap getMap(){
		
		
		String str[] ;
		Component component;
		List<IComponent> list = new ArrayList<IComponent>();
		Map map= new Map(list, Color.WHITE);
		
		/*
	     * recuperation des valeurs pour la creation des composants et de la map
	     */
		
		 
		 /*String filePath = System.getProperty("user.dir")+"/mapconfig";
		 
	
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(new File(filePath));
		 
		 while (scanner.hasNextLine()) {
		    
			 String line = scanner.next();
			 if(!(line.startsWith("#"))) {
			     
				 str = line.split(";");
				 
				 /*switch((String)str[0]){

					case "Building" : component = new Building(str[1],(new Point(Integer.parseInt(str[2]),Integer.parseInt(str[3]))),new Size(Double.parseDouble(str[4]),Double.parseDouble(str[5])));
									  list.add(component);
									  break;
					
					case "Lawn"     : component = new Lawn(str[1],(new Point(Integer.parseInt(str[2]),Integer.parseInt(str[3]))),new Size(Double.parseDouble(str[4]),Double.parseDouble(str[5])));
									  list.add(component);
									  break;
					
					case "Road" 	: component = new Road(str[1],(new Point(Integer.parseInt(str[2]),Integer.parseInt(str[3]))),new Size(Double.parseDouble(str[4]),Double.parseDouble(str[5])));
									  list.add(component);
					                  break;
			
				 }/
		     }
		  
		  }*/
		 	return this.myMap;
		 }

	 public static void main(String args[]) throws FileNotFoundException{
		 
			DataProducer dp = new DataProducer();
			IMap map = dp.getMap();
			map.printMapComponents();
			
	 }

	public JComponent getView() {
		MyViewer view = new MyViewer(this.myMap);
		return view.getView();
	}
	
}
	
