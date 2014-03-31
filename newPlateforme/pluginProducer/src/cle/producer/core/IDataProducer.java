package cle.producer.core;

import javax.swing.JComponent;
import cle.producer.data.IMap;

public interface IDataProducer {
	
	public IMap getMap();
	public JComponent getView();
}
