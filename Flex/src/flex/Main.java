package flex;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import utils.MessageConsole;

public class Main {

	private JFrame frmTeoilexico;

	private SymbolsTable st;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmTeoilexico.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		st = new SymbolsTable();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTeoilexico = new JFrame();
		frmTeoilexico.setTitle("TEOI-lexico");
		frmTeoilexico.setBounds(25, 25, 800, 600);
		frmTeoilexico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTeoilexico.getContentPane().setLayout(null);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Entrada", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		inputPanel.setBounds(10, 11, 766, 300);
		frmTeoilexico.getContentPane().add(inputPanel);
		inputPanel.setLayout(null);
		
		JTextArea programInput = new JTextArea();
		JScrollPane inputPane = new JScrollPane(programInput);
		inputPane.setBounds(10, 31, 746, 258);
		inputPanel.add(inputPane);
		
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(null);
		outputPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Salida", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		outputPanel.setBounds(10, 322, 766, 208);
		frmTeoilexico.getContentPane().add(outputPanel);
		
		JTextArea programOutput = new JTextArea();
		programOutput.setEditable(false);
		JScrollPane outputPane = new JScrollPane(programOutput);
		outputPane.setBounds(10, 31, 746, 166);
		outputPanel.add(outputPane);
		MessageConsole mc = new MessageConsole(programOutput);
		mc.redirectOut(null, System.out);
		mc.redirectErr(Color.RED, null);
		mc.setMessageLines(100);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		frmTeoilexico.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivo");
		mnNewMenu.setBackground(Color.WHITE);
		menuBar.add(mnNewMenu);
		
		JMenuItem openFileBtn = new JMenuItem("Abrir archivo...");
		openFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Abre selector de archivos.
				JFileChooser jfc = new JFileChooser();
				jfc.showOpenDialog(null);
				// Espera selección de archivo.
				// La siguiente llamada retorna 'null' si se cierra la ventana.
				File file = jfc.getSelectedFile();
				// Si se cerró la ventana, no hacemos nada.
				if (file == null) return;
				try {
					// Leemos el archivo.
					BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
					String data = "";
					String line;
					while ((line = br.readLine()) != null) {
						data += new String(line.getBytes(), "UTF-8") + System.lineSeparator();
					}
					// Volcamos en el JTextArea el contenido del archivo.
					programInput.setText(data);
				} catch (FileNotFoundException e) {
					System.out.println("No se encontró el archivo fuente.");
				} catch (UnsupportedEncodingException e) {
					System.out.println("No se pudo leer el archivo. Codificación no soportada.");
				} catch (IOException e) {
					System.out.println("No se pudo leer el archivo.");
				}
			}
		});
		openFileBtn.setBackground(Color.WHITE);
		mnNewMenu.add(openFileBtn);
		
		JMenuItem compileBtn = new JMenuItem("Compilar");
		compileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Si no se ingresó código, no hacemos nada.
				if (programInput.getText().isEmpty()) return;
				// Limpiamos la consola y la tabla de símbolos.
				programOutput.setText("");
				TS.getInstance().clearData();
				try {
					// Instanciamos un lector de string con el código ingresado.
					StringReader sr = new StringReader(programInput.getText());
					Lexico Lexer = new Lexico(sr);
					parser sintactico = new parser(Lexer);
					sintactico.parse();
				} catch (FileNotFoundException e) {
					System.out.println("No se encontró el archivo fuente.");
				} catch (Exception e) {
					System.out.println("Error al parsear el programa.");
				}
			}
		});
		compileBtn.setBackground(Color.WHITE);
		mnNewMenu.add(compileBtn);
		
		JMenuItem exitBtn = new JMenuItem("Salir");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Cierra el programa.
				System.exit(0);
			}
		});
		exitBtn.setBackground(Color.WHITE);
		mnNewMenu.add(exitBtn);
		
		JMenu mnVer = new JMenu("Ver");
		mnVer.setBackground(Color.WHITE);
		menuBar.add(mnVer);
		
		JMenuItem viewTSBtn = new JMenuItem("Tabla de símbolos");
		viewTSBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!st.isShowing()) {
					st = new SymbolsTable();
				}
				st.showTable();
			}
		});
		viewTSBtn.setBackground(Color.WHITE);
		mnVer.add(viewTSBtn);
	}
}
