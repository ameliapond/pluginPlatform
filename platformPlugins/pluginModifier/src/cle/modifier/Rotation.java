package cle.modifier;

import cle.producer.data.IMap;


public class Rotation implements IModidifier {
	IMap map;
	int corner = 10;
	public Rotation(){
		
	}

	@Override
	public void modify(IMap map) {
		this.map = map;
		// Ask more infos ??
		
		//looping data to modify position
		
		
	}

	@Override
	public IMap reset() {
		// TODO Auto-generated method stub
		return null;
	}


}
