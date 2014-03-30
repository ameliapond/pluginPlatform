package cle.displayer;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import cle.producer.data.IComponent;

public class RealComponent {
	public static JComponent getView(IComponent comp){
		JPanel label = new  JPanel();
		//label.setPreferredSize(new Dimension((int)comp.getComponentsize().getWidth(), (int)comp.getComponentsize().getHeight()));
		label.setBounds(comp.getComponentPosition().getX(), comp.getComponentPosition().getY(), (int)comp.getComponentsize().getWidth(), (int)comp.getComponentsize().getHeight());
		label.setBackground(comp.getComponentColor());
		label.add(new JLabel(comp.getComponentName()));
		//label.setForeground(comp.getComponentColor());
		label.setVisible(true);
		//label.setPreferredSize(new DimensionUIResource((int)comp.getComponentsize().getWidth(), (int)comp.getComponentsize().getHeight()));
		
		System.out.println("TST"+label.getHeight()+"-"+label.getWidth()+"-"+label.getX()+"-"+label.getY());
		return label;
	}
}
