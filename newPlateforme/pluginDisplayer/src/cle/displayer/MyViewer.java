package cle.displayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import cle.producer.data.IComponent;
import cle.producer.data.IMap;
import cle.producer.data.Point;
import cle.producer.data.Size;


public class MyViewer extends JPanel {

	JPanel pnlSouth = new JPanel(new BorderLayout());
	JScrollPane scroll = new JScrollPane(pnlSouth);
	public JTextPane pnlError = new JTextPane();
	
	public IMap theMap;
	
	public MyViewer(IMap map){
		this.theMap = map;
		this.initialize();
	}
	
	public MyViewer() {
		this.initialize();
	}

	private void initialize(){
		this.setLayout(new BorderLayout());
		this.add(getNorthPanal(), BorderLayout.NORTH);
		this.add(this.pnlSouth, BorderLayout.CENTER);
		this.add(this.pnlError, BorderLayout.SOUTH);
		this.getSouthPanal();
	}
	
	public JPanel getView(){
		return this;
	}
	
	private void getSouthPanal() {
		
	}
	
	public JPanel getNorthPanal(){
		// Panel choix fichier
		JPanel pnlNorth = new JPanel(new GridLayout(0, 2, 10, 10));
		pnlNorth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Choose kind of view"));
		
		List<String> allType = new ArrayList<String>();
		allType.add("GUIView");
		allType.add("GridView");
		allType.add("TextView");
		final JComboBox cbxTypeView = new JComboBox(allType.toArray());
		pnlNorth.add(cbxTypeView);
		
		pnlError.setText("test error");
		
		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// view to dis[lay
				String className = (String) cbxTypeView.getSelectedItem();
				IAfficheur viewer;
				//pnlError.setText("className:"+className);
				try {
					pnlSouth.removeAll();
					viewer = (IAfficheur) Class.forName("cle.displayer."+className).getConstructor(JPanel.class).newInstance(pnlSouth);
					viewer.affiche(theMap);
					
					MyViewer.this.repaint();
					MyViewer.this.validate();
					//System.out.println("2-sz comp:"+pnlSouth.getComponents().length);
					pnlError.setText(className+" called size:"+theMap.getItems().size());
				} catch (Exception e1) {
					pnlError.setText("Exception e1:"+e1.getMessage());
				} 
			}
		});
		pnlNorth.add(btnDisplay);
		return pnlNorth;
	}
	
	public static void main(String[] args) {
		MyViewer disp = new MyViewer();
		
		JFrame fram = new JFrame();
		fram.setTitle("Test Producer");
		fram.setLayout(new BorderLayout());
		fram.setSize(660, 390);
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/favicon.png")));
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fram.setResizable(true); 
		fram.add(disp.getView());
		fram.setVisible(true);
	}

}
