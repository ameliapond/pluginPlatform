package cle.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import loader.Loader;
import cle.displayer.IAfficheur;
import cle.modifier.IModidifier;
import cle.producer.core.IDataProducer;
import cle.producer.data.IMap;

public class MapApplication extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	IMap myMap;
	boolean isLoad = false;
	JMenu menuLoad = new JMenu("Load Plugins");
	JTabbedPane myTabs = new JTabbedPane();
	
	public MapApplication(){
		this.setLayout(new BorderLayout());
		
		this.initialize();
		this.getPanelNord();
		this.getPanelSud();
		
		this.setVisible(true);
	}

	private void initialize() {
		this.setTitle("Map Application");
		this.setLayout(new BorderLayout());
		//this.setContentPane(getJpPrincipal());
		this.setJMenuBar(getMenus());
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/favicon.png")));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true); 
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(dim.width, dim.height);
		this.setLocation(dim.width/2 - this.getWidth()/2, dim.height/2 - this.getHeight()/2);
		
		this.add(myTabs, BorderLayout.CENTER);
		//get plugins loads
		this.addPlugins();
	}
	
	public void addPlugins(){
		JMenuItem menuItem = new JMenuItem("DataProducer");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object o;
				try {
					o = Loader.getInstanceOf(IDataProducer.class, "DataProducer");
					myMap = (IMap)((IDataProducer)o).getMap();
					myTabs.addTab("Data Producer", ((IDataProducer) o).getView());
					myTabs.setMnemonicAt(myTabs.getComponentCount()-1, KeyEvent.VK_P);
					((JMenuItem)e.getSource()).setEnabled(false);
					MapApplication.this.validate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				System.out.println("first:"+myMap.getItems().size());	
			}
		});
		this.menuLoad.add(menuItem);
		
		
		// displayer
		menuItem = new JMenuItem("Data Displayer");
		menuItem.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(myMap==null){
						((JMenuItem)menuLoad.getItem(0)).doClick();
					}
						
					Object aff = Loader.getInstanceOf(IAfficheur.class, "Displayer");
					myTabs.addTab("Data Displayer", ((IAfficheur) aff).getView(myMap));
					myTabs.setMnemonicAt(myTabs.getComponentCount()-1, KeyEvent.VK_D);
					((JMenuItem)e.getSource()).setEnabled(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		this.menuLoad.add(menuItem);
		
		//Modifier
		menuItem = new JMenuItem("Data Modifier");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(myMap==null){
					((JMenuItem)menuLoad.getItem(0)).doClick();
				}
				try {
					Object mod = Loader.getInstanceOf(IModidifier.class, "MapModifier");
					myTabs.addTab("Data Modifier", ((IModidifier) mod).getView(myMap));
					myTabs.setMnemonicAt(myTabs.getComponentCount()-1, KeyEvent.VK_M);
					
					((JMenuItem)e.getSource()).setEnabled(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		this.menuLoad.add(menuItem);
	}

	private JMenuBar getMenus() {
		JMenuBar menuBar = new JMenuBar();
		// Menu File
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		
		// Sub menu quit
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setMnemonic(KeyEvent.VK_E);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menuItem.setActionCommand("exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuBar.add(menuLoad);
		return menuBar;
	}


	private void getPanelSud() {
		// TODO Auto-generated method stub
		
	}

	private void getPanelNord() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if ("exit".equals(e.getActionCommand())) {
			System.exit(0);
			return;
		}
	}


	public static void main(String[] args) {
		MapApplication app = new MapApplication();
		//app.pack();
		app.setVisible(true);
	}
}
