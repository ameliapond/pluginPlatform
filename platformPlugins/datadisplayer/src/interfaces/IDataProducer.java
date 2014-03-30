package interfaces;

import interfaces.data.IMap;

import java.io.FileNotFoundException;


public interface IDataProducer {
	
	public IMap getMap() throws FileNotFoundException; 
	
}
