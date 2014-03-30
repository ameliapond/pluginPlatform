package cle.producer.utils;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class ModeleTab extends AbstractTableModel {
	private final Object[][] donnees;
	private final String[] entetes;
	public static Object NtClass;

	public ModeleTab(String[] entetes, Object[][] donnees) {
		super();
		this.entetes = entetes;
		this.donnees = donnees;
	}
	
	public Object getObject(){
		return new Object();
	}

	public int getRowCount() {
		return donnees.length;
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return donnees[rowIndex][columnIndex];
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public Class getColumnClass(int column) {
		return getValueAt(0, column).getClass();
	}
}

class JTableButtonRenderer implements TableCellRenderer {
	private TableCellRenderer __defaultRenderer;

	public JTableButtonRenderer(TableCellRenderer renderer) {
		__defaultRenderer = renderer;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof Component)
			return (Component) value;
		return __defaultRenderer.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
	}
}

class CenterTableCellRenderer extends DefaultTableCellRenderer {
    public CenterTableCellRenderer() {
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }
}

class RightTableCellRenderer extends DefaultTableCellRenderer {
    public RightTableCellRenderer() {
        setHorizontalAlignment(RIGHT);
        setVerticalAlignment(CENTER);
    }
}
