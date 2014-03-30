package cle.producer.core;

import interfaces.data.IMap;

import java.io.FileNotFoundException;
import java.util.Map;


public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
				
		DataProducer dp = new DataProducer();
		IMap map = dp.getMap();
		map.printMapComponents();
        
	}

}
