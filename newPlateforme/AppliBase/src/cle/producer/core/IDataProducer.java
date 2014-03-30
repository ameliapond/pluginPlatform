package cle.producer.core;

import java.io.FileNotFoundException;

import javax.swing.JComponent;
import cle.producer.data.IMap;

public interface IDataProducer {
	
	public IMap getMap() throws FileNotFoundException;
	public JComponent getView();
}
