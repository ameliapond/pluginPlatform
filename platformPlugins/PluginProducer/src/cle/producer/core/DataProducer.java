package cle.producer.core;

import data.*;
import interfaces.data.IComponent;
import interfaces.data.IMap;
import interfaces.IDataProducer;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import carte.Position;

public class DataProducer implements IDataProducer{

	@Override
	public String toString() {
		return "DataProducer [ressourcesPath=" + ressourcesPath + "]";
	}

	public String ressourcesPath;
	
	public DataProducer(){
		System.out.println("PLUGINDATAPRODUCER");
	}
	
	public DataProducer(String resPath, Properties prop ) {
		this.ressourcesPath = resPath;
	}
	
		
	@Override
	public IMap getMap() throws FileNotFoundException{
		
		
		String str[];
		Component component;
		List<IComponent> list = new ArrayList<IComponent>();
		Map map= new Map(list);
		Scanner scanner;
		String filePath ="mapconfig";
		
		/*
	     * recuperation des valeurs pour la creation des composants et de la map
	     */
		
		 
		try {
			scanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			filePath =System.getProperty("user.home")+"/"+"platformPlugins"+"/"+"PluginProducer"+"/"+"mapconfig";
			scanner=new Scanner(new File(filePath));//e.printStackTrace();
		}
		 
		 while (scanner.hasNextLine()) {
		    
			 String line = scanner.next();
			 if(!(line.startsWith("#"))) {
			     
				 str = line.split(";");
				 
				 switch(str[0]){

					case "Building" : component = new Building(str[1],(new Position(Integer.parseInt(str[2]),Integer.parseInt(str[3]))),Color.RED, Integer.parseInt(str[4]));
									  list.add(component);
									  break;
					
					case "Lawn"     : component = new Lawn(str[1],(new Position(Integer.parseInt(str[2]),Integer.parseInt(str[3]))),Color.GREEN, Integer.parseInt(str[4]));
									  list.add(component);
									  break;
					
					case "Road" 	: component = new Road(str[1],(new Position(Integer.parseInt(str[2]),Integer.parseInt(str[3]))),Color.GRAY, Integer.parseInt(str[4]));
									  list.add(component);
					                  break;
				 
				 }
		     }
		  
		  }
		 return map;
		 }
	
	
}
	
