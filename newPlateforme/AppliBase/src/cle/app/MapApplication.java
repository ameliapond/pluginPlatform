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
	}

	private JMenuBar getMenus() {
		JMenuBar menuBar = new JMenuBar();
		
		// Menu File
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		
		// Sub menu load plugins
		JMenuItem menuItem = new JMenuItem("Load Plugins");
		menuItem.setMnemonic(KeyEvent.VK_L);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		menuItem.setActionCommand("load");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menuItem);
		
		// Sub menu test
		menuItem = new JMenuItem("Test");
		menuItem.setMnemonic(KeyEvent.VK_E);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.ALT_MASK));
		menuItem.setActionCommand("test");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		// Sub menu quit
		menuItem = new JMenuItem("Exit");
		menuItem.setMnemonic(KeyEvent.VK_E);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menuItem.setActionCommand("exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		/*/ Menu Load plugins
		menu = new JMenu("Load Plugins");
		menu.setMnemonic(KeyEvent.VK_L);
		menu.setActionCommand("load");
		menu.addActionListener(this);
		menuBar.add(menu);*/
		
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
			System.out.println("quit");
			return;
		}
		else if ("test".equals(e.getActionCommand())) {
			System.out.println("SZ"+this.myMap.getItems().size());
			return;
		}
		else if ("load".equals(e.getActionCommand())) {
			try {
				this.load();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
	}

	private void load() throws Exception {
		if(isLoad)
			return;
		
		isLoad = true;
		JTabbedPane tabbedPane = new JTabbedPane();
		
		// loading producer
			Object o = Loader.getInstanceOf("IDataProducer", "DataProducer");
			this.myMap = (IMap)((IDataProducer)o).getMap();
			System.out.println("first:"+this.myMap.getItems().size());
			//this.myMap = new Map();
			
			tabbedPane.addTab("Data Producer", ((IDataProducer) o).getView());
			tabbedPane.setMnemonicAt(0, KeyEvent.VK_P);
			
			//loading afficher
			Object aff = Loader.getInstanceOf("IAfficheur", "Displayer");
			
			tabbedPane.addTab("Data Displayer", ((IAfficheur) aff).getView(myMap));
			tabbedPane.setMnemonicAt(1, KeyEvent.VK_D);
			
			//loading modifier
			Object mod = Loader.getInstanceOf("IModidifier", "MapModifier");
			
			tabbedPane.addTab("Data Modifier", ((IModidifier) mod).getView(myMap));
			tabbedPane.setMnemonicAt(2, KeyEvent.VK_M);
		

		this.add(tabbedPane, BorderLayout.CENTER);
		
		this.validate();
	}
	
	public static void main(String[] args) {
		MapApplication app = new MapApplication();
		//app.pack();
		app.setVisible(true);
	}
}
