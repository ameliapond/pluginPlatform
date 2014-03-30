package cle.producer.utils;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class Grid {
	private JTable table;

	/**
	 * le constructeur de la classe Grid qui cree un JTable et se sert de son
	 * model pour le remplir
	 */
	public Grid(String[] entetes, Object[][] donnees) {
		setTable(new JTable(new ModeleTab(entetes, donnees)));
		//NTObjet.
		table.setGridColor(Color.BLACK);
		table.setForeground(Color.decode("#4e1a05"));
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBackground(Color.decode("#faf49e"));
		table.setAutoscrolls(true);
	}
	
	public Grid(String[] entetes, Object[][] donnees, Object NTObjet) {
		this(entetes, donnees);
		TableCellRenderer defaultRenderer = table.getDefaultRenderer(JButton.class);
		table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(defaultRenderer));
		table.addMouseListener(new JTableButtonMouseListener(table, NTObjet));
		
		/*if(NTObjet.getClass()==MiniReleve.class){
			table.setBackground(Color.decode("#faf49e"));
			TableColumn colCode = table.getColumnModel().getColumn(2);
			TableColumn colMnt = table.getColumnModel().getColumn(3);  
			TableColumn colSolde = table.getColumnModel().getColumn(4);  

			table.getColumnModel().getColumn(0).setPreferredWidth(160);
			table.getColumnModel().getColumn(0).setMaxWidth(160);
			
			table.getColumnModel().getColumn(1).setPreferredWidth(140);
			table.getColumnModel().getColumn(1).setMaxWidth(140);
			
			colCode.setPreferredWidth(50);
			colCode.setMaxWidth(50);
			colCode.setCellRenderer(new CenterTableCellRenderer());
			
			colMnt.setPreferredWidth(100);
			colMnt.setMaxWidth(100);
			colMnt.setCellRenderer(new RightTableCellRenderer());
			
			colSolde.setPreferredWidth(100);
			colSolde.setMaxWidth(100);
			colSolde.setCellRenderer(new RightTableCellRenderer());
			
		}*/
	}

	public boolean isCellEditable(int row, int col) {
		return true;
	}


	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

}
