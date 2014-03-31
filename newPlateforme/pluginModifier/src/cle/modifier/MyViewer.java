package cle.modifier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import cle.producer.data.IMap;

public class MyViewer extends JPanel{
	JPanel pnlSouth = new JPanel(new BorderLayout());
	JScrollPane scroll = new JScrollPane(pnlSouth);
	JTextPane txtPane = new JTextPane();
	
	private IMap theMap;
	
	public MyViewer(IMap map){
		this.theMap = map;
		this.initialize();
	}
	
	private void initialize(){
		this.setLayout(new BorderLayout());
		this.add(getNorthPanal(), BorderLayout.NORTH);
		this.add(this.pnlSouth, BorderLayout.CENTER);
		this.getSouthPanal();
	}
	
	public JPanel getView(){
		return this;
	}
	
	private void getSouthPanal() {
		this.pnlSouth.add(new JScrollPane(this.txtPane));
	}
	
	public JPanel getNorthPanal(){
		// Panel choix fichier
		JPanel pnlNorth = new JPanel(new GridLayout(0, 2, 10, 10));
		pnlNorth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Modifiers Type"));
		
		List<String> allModifiers = new ArrayList<String>();
		allModifiers.add("ChangeView");
		allModifiers.add("MoveMap");
		allModifiers.add("Rotation");
		allModifiers.add("ZoomIn");
		allModifiers.add("ZoomOut");
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton src = (JButton) e.getSource();
				String className = (String) src.getText();
				System.out.println("cls:"+className);
				IModidifier modifier;
				try {
					modifier = (IModidifier) Class.forName("cle.modifier."+className).getConstructor().newInstance();
					modifier.modify(theMap);
					txtPane.setText(className+" applied successfully"+" NBElem:"+theMap.getItems().size()+"\n"+txtPane.getText());
					
					//modify color
					System.out.println("Modif: old color"+theMap.getBackground());
					theMap.setBackground(Color.YELLOW);
					System.out.println("Modif: new color"+theMap.getBackground());
					
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			}
		};
		for(int i=0; i<allModifiers.size(); i++){
			JButton btnModifier = new JButton(allModifiers.get(i));
			btnModifier.addActionListener(listener);
			pnlNorth.add(btnModifier);
		}
		return pnlNorth;
	}
	
	public static void main(String[] args) {
		MyViewer mdf = new MyViewer(null);
		
		JFrame fram = new JFrame();
		fram.setTitle("Test Producer");
		fram.setLayout(new BorderLayout());
		fram.setSize(660, 390);
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/favicon.png")));
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fram.setResizable(true); 
		fram.add(mdf.getView());
		fram.setVisible(true);
	}
}
