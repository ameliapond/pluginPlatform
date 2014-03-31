package cle.producer.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.accessibility.Accessible;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import cle.producer.data.Component;
import cle.producer.data.IComponent;
import cle.producer.data.IMap;
import cle.producer.data.Map;
import cle.producer.data.Point;
import cle.producer.data.Size;
import cle.producer.utils.Grid;

public class MyViewer extends JPanel implements Accessible{

	private static final long serialVersionUID = 1L;
	public IMap myMap = new Map();
	JPanel pnlSouth = new JPanel(new BorderLayout());
	public JTextPane pnlError = new JTextPane();
	
	public MyViewer() {
		initialize();
	}
	public MyViewer(IMap myMap) {
		this.myMap = myMap;
		this.myMap.setBackground(Color.BLUE);
		initialize();
	}
	
	private void initialize(){
		this.setLayout(new BorderLayout());
		this.add(getNorthPanal(), BorderLayout.NORTH);
		//this.add(this.pnlSouth, BorderLayout.CENTER);
		this.add(this.pnlError, BorderLayout.SOUTH);
		this.getSouthPanal();
	}
	
	public JPanel getView(){
		return this;
	}
	
	private void getSouthPanal() {
		//JPanel pnlSouth = new JPanel();
		String[] entetes = {"Type", "Name", "Width", "Height", "Pos X", "Pos Y", "Color"};
		
		Object[][] data = new Object[0][0];
		if(myMap != null && !myMap.getItems().isEmpty()){
			data = new Object[myMap.getItems().size()][entetes.length];
			int i = 0;
			for(IComponent comp : myMap.getItems()){
				data[i][0] = comp.getClass().getName();
				data[i][1] = comp.getComponentName();
				data[i][2] = comp.getComponentsize().getWidth();
				data[i][3] = comp.getComponentsize().getHeight();
				data[i][4] = comp.getComponentPosition().getX();
				data[i][5] = comp.getComponentPosition().getY();
				
				JButton label = new JButton();
				label.setBackground(comp.getComponentColor());
				data[i][6] = label;
				i++;
			}
		}
		
		Grid tableau = new Grid(entetes, data, null);
		if(this.pnlSouth.getComponents().length == 0){
			JScrollPane scroll = new JScrollPane(/*tableau.getTable()*/);
			this.pnlSouth.add(scroll);
		}
		JScrollPane scroll = (JScrollPane) this.pnlSouth.getComponent(0);
		scroll.setViewportView(tableau.getTable());
		//return pnlSouth;
	}

	public JPanel getNorthPanal(){
		// Panel choix fichier
		JPanel pnlFromFile = new JPanel(new FlowLayout()); //new GridLayout(0, 2, 10, 10)
		//pnlDataProd.setLayout(new BoxLayout(pnlFromFile, BoxLayout.Y_AXIS));
		pnlFromFile.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Add from a file"));
		
		JButton chooseFileBtn = new JButton("Choose file to load");
		chooseFileBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadFromFile();				
			}
		});
		pnlFromFile.add(chooseFileBtn);
		
		// Panel add manually
		JPanel pnlManual = new JPanel();
		pnlManual.setLayout(new GridBagLayout());
		
		pnlManual.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Add Manually"));
		
		// list select classes
		List<String> allCompo = new ArrayList<String>();
		allCompo.add("Building");
		allCompo.add("Lawn");
		allCompo.add("Road");
		final JComboBox bxComponents = new JComboBox(allCompo.toArray());
		
		int nbCol = 5;
		Dimension commonDim = new Dimension(10, 15);
		
		KeyListener checkDouble = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				JTextField field = (JTextField)e.getSource();
				String texte = field.getText();
				if(field.getText().length() >= 1){
					try{
						Double.parseDouble(texte);
					}
					catch (Exception ex) {
						if(texte.charAt(texte.length()-1) != '.')
							field.setText(texte.substring(0, texte.length()-1));
					}
				}				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		};
		// Field Name
		final JTextField txtName = new JTextField("TestComp", nbCol);
		//txtName.setSize(commonDim);
		
		// Field width
		final JTextField txtWidth = new JTextField("150", nbCol);
		txtWidth.addKeyListener(checkDouble);
		txtWidth.setSize(commonDim);
		
		//Field Height
		final JTextField txtHeight = new JTextField("25", nbCol);
		txtHeight.addKeyListener(checkDouble);
		txtHeight.setSize(commonDim);
		
		//Field posX
		final JTextField txtPosX = new JTextField("3", nbCol);
		txtPosX.addKeyListener(checkDouble);
		txtPosX.setSize(commonDim);
		
		//Field Height
		final JTextField txtPosY = new JTextField("4", nbCol);
		txtPosY.addKeyListener(checkDouble);
		txtPosY.setSize(commonDim);
		
		//Field color
		final JTextField txtColor = new JTextField(nbCol);
		txtColor.setBackground(Color.GREEN);
		txtColor.setEditable(false);
		txtColor.setSize(commonDim);
		txtColor.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e){
				JFrame guiFrame = new JFrame();
				Color selectedColor = JColorChooser.showDialog(guiFrame, "Pick a Color", Color.GREEN);
				if(selectedColor != null){
					txtColor.setBackground(selectedColor);
					repaint();
					revalidate();
				}
				
            }
			
		});
		
		JButton btnAdd = new JButton("Add Component");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// code to add to the Map
				String className = (String) bxComponents.getSelectedItem();
				String name = txtName.getText();
				double width = Double.parseDouble(txtWidth.getText());
				double height = Double.parseDouble(txtHeight.getText());
				double posX = Double.parseDouble(txtPosX.getText());
				double posY = Double.parseDouble(txtPosY.getText());

				IComponent compo;
				try {
					//System.out.println(className+"-"+txtName+"-"+txtWidth+"-"+txtHeight);
					compo = (IComponent) Class.forName("cle.producer.data."+className).getConstructor(String.class, Point.class, Size.class).newInstance(name, new Point((int)posX, (int)posY), new Size(width, height));
					compo.setComponentColor(txtColor.getBackground());
					myMap.getItems().add(compo);
					MyViewer.this.getSouthPanal();
					
					//pnlError.setText("new add in map size"+myMap.getItems().size());
					//pnlError.setText("Color"+myMap.getBackground());
					//System.out.println("Prod: color "+myMap.getBackground());
				} catch (Exception e1) {
					//e1.printStackTrace();
					pnlError.setText("Exception"+e1.getMessage());
				} 
				
			}
		});
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(2,2,2,2);
		gc.anchor = GridBagConstraints.NORTHWEST;
		
		int i = 0;
		gc.gridx = 0; gc.gridy = i;
		pnlManual.add(new JLabel("Type :"), gc);
		gc.gridx = 1; gc.gridy = i;  
		gc.gridwidth = 3;
		gc.fill = GridBagConstraints.HORIZONTAL;
		pnlManual.add(bxComponents, gc);
		
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		
		i++;
		
		gc.gridx = 0; gc.gridy = i;
		pnlManual.add(new JLabel("Name :"), gc);
		gc.gridx = 1; gc.gridy = i;  
		gc.gridwidth = 3;
		gc.fill = GridBagConstraints.HORIZONTAL;
		pnlManual.add(txtName, gc);
		
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		
		i++;
		gc.gridx = 0; gc.gridy = i;
		pnlManual.add(new JLabel("Width :"), gc);
		gc.gridx = 1; gc.gridy = i;
		pnlManual.add(txtWidth, gc);
		gc.gridx = 2; gc.gridy = i;
		pnlManual.add(new JLabel("Height :"), gc);
		gc.gridx = 3; gc.gridy = i;
		pnlManual.add(txtHeight, gc);
		
		i++;
		gc.gridx = 0; gc.gridy = i;
		pnlManual.add(new JLabel("X position :"), gc);
		gc.gridx = 1; gc.gridy = i;
		pnlManual.add(txtPosX, gc);
		gc.gridx = 2; gc.gridy = i;
		pnlManual.add(new JLabel("Y position"), gc);
		gc.gridx = 3; gc.gridy = i;
		pnlManual.add(txtPosY, gc);
		
		i++;
		gc.gridx = 0; gc.gridy = i;
		pnlManual.add(new JLabel("Color :"), gc);
		gc.gridx = 1; gc.gridy = i;
		pnlManual.add(txtColor, gc);
		
		//button add
		i++;
		gc.gridx = 0; gc.gridy = i;  
		gc.gridwidth = 4;
		gc.fill = GridBagConstraints.HORIZONTAL;
		pnlManual.add(btnAdd, gc);
		
		JPanel pnlNorth= new JPanel(new GridLayout(0, 2, 10, 10));
		pnlNorth.add(pnlFromFile);
		pnlNorth.add(pnlManual);
		
		return pnlNorth;
	}
	
	private void loadFromFile() {
		
		JFileChooser fd = new JFileChooser();
		int ret = fd.showOpenDialog(this);
		if (ret == JFileChooser.CANCEL_OPTION)
			return;

		File file = fd.getSelectedFile();
		String name = file.getName();
		String abspath = file.getParent();
		
		// File choosen: CoreView.java - C:\Users\Net-Transact-1\Desktop
		System.out.println("File choosen: "+name+" - "+abspath);
		
		//******************************************************************//
		String str[];
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(abspath+File.separator+name));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (scanner != null) {
			this.myMap.getItems().removeAll(this.myMap.getItems());
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if ((!(line.isEmpty())&&(!(line.startsWith("#"))))) {
					IComponent compo;
					str = line.split(";");
					String className = str[0];
					if(str.length < 1)
						continue;
					@SuppressWarnings("unused")
					String namecomp = str[1];
					if(str.length < 2)
						continue;
					int posX = Integer.parseInt(str[2]);
					if(str.length < 3)
						continue;
					int posY = Integer.parseInt(str[3]);
					if(str.length < 4)
						continue;
					double width = Double.parseDouble(str[4]);
					if(str.length < 5)
						continue;
					double height = Double.parseDouble(str[5]);
					if(str.length < 6)
						continue;
					try {
						
						compo = (IComponent) Class.forName("cle.producer.data."+className).getConstructor(String.class, Point.class, Size.class).newInstance(name, new Point(posX, posY), new Size(width, height));
						//compo.setComponentColor(txtColor.getBackground());
						
						boolean b = this.myMap.componentCompare(compo);
						if(b == false)
							this.myMap.getItems().add(compo);
						else {
							//System.out.println("component coincide");
							continue;
						}
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

			}
			
			scanner.close();
			
		}
		
		
		
		
	}

	/*public static void main(String[] args) {
		MyViewer app = new MyViewer();
		
		JFrame fram = new JFrame();
		fram.setTitle("Test Producer");
		fram.setLayout(new BorderLayout());
		fram.setSize(660, 390);
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/favicon.png")));
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fram.setResizable(true); 
		fram.setContentPane(app.getView());
		fram.setVisible(true);
	}*/
}
