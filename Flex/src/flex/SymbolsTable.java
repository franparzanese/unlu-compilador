package flex;

import javax.swing.JFrame;
import javax.swing.JTable;

public class SymbolsTable {

	String[] columnNames = {
		"NOMBRE",
		"TOKEN",
		"TIPO",
		"VALOR",
		"LONGITUD"
	};

	private JFrame frame;

	public SymbolsTable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Tabla de símbolos");
		frame.setBounds(75, 50, 600, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	public boolean isShowing() {
		return frame.isShowing();
	}

	public void showTable() {
		JTable table = new JTable(TS.getInstance().getData(), columnNames);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		table.setBounds(12, 12, 566, 446);
		frame.getContentPane().add(table);
		frame.setVisible(true);
	}
}
