package editor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

public class Editor extends JFrame {

	private JTextArea eingabeFeld = new JTextArea();
	
	public Editor(String titel) {
		super(titel);
		
		setLayout(new BorderLayout());
		//Ein ScrollPane hat ein Eingabefeld immer in sich
		
		menu();
		add(symbolleiste(), BorderLayout.NORTH);
		add(new JScrollPane(eingabeFeld), BorderLayout.CENTER);
		
		new MeinListener();
		
		//setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(600,300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true); 
		
	}
	
	private JToolBar symbolleiste() {
		MeinListener listener = new MeinListener();
		JToolBar tbLeiste = new JToolBar();
		JButton tbtnNeu = new JButton();
		JButton tbtnOeffnen = new JButton();
		JButton tbtnSpeichern = new JButton();
		JButton tbtnBeenden = new JButton();
	
		tbtnNeu.setIcon(new ImageIcon(getClass().getResource("/icons/New24.gif")));
		tbtnOeffnen.setIcon(new ImageIcon(getClass().getResource("/icons/openFile.gif")));
		tbtnSpeichern.setIcon(new ImageIcon(getClass().getResource("/icons/saveFile.gif")));
		
		tbtnNeu.addActionListener(listener);
		tbtnOeffnen.addActionListener(listener);
		tbtnSpeichern.addActionListener(listener);
		tbtnBeenden.addActionListener(listener);
		
		tbtnNeu.setActionCommand("neu");
		tbtnOeffnen.setActionCommand("oeffnen");
		tbtnSpeichern.setActionCommand("speichern");
		tbtnBeenden.setActionCommand("beenden");
		
		tbtnNeu.setToolTipText("Zum Erstellen einer neuen Datei");
		tbtnOeffnen.setToolTipText("Zum Öffnen einer neuen Textdatei");
		tbtnSpeichern.setToolTipText("Zum Speichern einer Textdatei");
		tbtnBeenden.setToolTipText("Schließen des Programmes");
		
//		tbtnNeu
//		tbtnOeffnen
//		tbtnSpeichern
//		tbtnBeenden
		
		add(tbLeiste, BorderLayout.NORTH);
		tbLeiste.add(tbtnNeu);
		tbLeiste.add(tbtnOeffnen);
		tbLeiste.add(tbtnSpeichern);
		tbLeiste.add(tbtnBeenden);
		
		return tbLeiste;
	}

	private void menu() {
		MeinListener listener = new MeinListener();
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menuDatei = new JMenu("Datei");
		
		//add(menuBar, BorderLayout.NORTH);
		
		JMenuItem itemDateiNeu = new JMenuItem("Neu");
		JMenuItem itemDateiOeffnen = new JMenuItem("Öffnen");
		JMenuItem itemDateiSpeichern = new JMenuItem("Speichern");
		
		////////////////		
		//Menü DATEI
		menuBar.add(menuDatei);
		//Menüeinträge
		menuDatei.add(itemDateiNeu);
		menuDatei.add(itemDateiOeffnen);
		menuDatei.add(itemDateiSpeichern);
		menuDatei.addSeparator();//Trennlinie
		//ActionListeners
		itemDateiNeu.addActionListener(listener);
		itemDateiOeffnen.addActionListener(listener);
		itemDateiSpeichern.addActionListener(listener);
		//Action Commands
		itemDateiNeu.setActionCommand("neu");
		itemDateiOeffnen.setActionCommand("oeffnen");
		itemDateiSpeichern.setActionCommand("speichern");
		//Tastenkombis 
		itemDateiNeu.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
		itemDateiOeffnen.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
		itemDateiSpeichern.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
		//setItcons
		itemDateiNeu.setIcon(new ImageIcon(getClass().getResource("/icons/New24.gif")));
		itemDateiOeffnen.setIcon(new ImageIcon(getClass().getResource("/icons/Open24.gif")));
		itemDateiSpeichern.setIcon(new ImageIcon(getClass().getResource("/icons/Save24.gif")));
		
		this.setJMenuBar(menuBar);
	}
	
	class MeinListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("neu")) {
				System.out.println("Neu");
				newFile();
				
			}
			if(e.getActionCommand().equals("oeffnen")) {
				System.out.println("Öffnen");
				openFile();
				
			}
			if(e.getActionCommand().equals("speichern")) {
				System.out.println("Speichern");
				saveFile();
				
			}
			if(e.getActionCommand().equals("beenden")) {
				closeEditor();
			}
		}
	}
	
	private void newFile() {
		if(JOptionPane.showConfirmDialog(this, "Wollen Sie wirklich ein neues Dokument anlegen?", "Neues Dokument?", 
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			eingabeFeld.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Es wurde kein neues Dokument angelegt.");
		}
	}
	
	private void openFile() {
		
			//Open file starts here
			MeinFilter meinfilter = new MeinFilter();
			File datei = meinfilter.openFileShow();
			
			if(datei != null) {
				try {
					eingabeFeld.read(new FileReader(datei), null);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this, "Beim Laden hat es ein Fehler gegeben", "Fehler", JOptionPane.ERROR_MESSAGE);
				}
			}
	}
	
	private void saveFile() {
		
		MeinFilter meinfilter = new MeinFilter();
		File datei = meinfilter.saveFileShow();
		
		if(datei != null) {
			try {
				eingabeFeld.write(new FileWriter(datei));
				JOptionPane.showMessageDialog(null, "Datei wurde gespeichert!");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Beim Speichern hat es ein Fehler gegeben", "Fehler", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	private void closeEditor() {
		
		if(JOptionPane.showConfirmDialog(this, "Wollen Sie wirklich beenden?", "Beenden?", 
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { //YES clicked
			System.exit(0);
		} else {
			
		}
	}
	
	class MeinFilter extends FileFilter {
	
		@Override
		public boolean accept(File f) {
	
			String name = f.getName().toLowerCase();
			if(f.isDirectory()) {
				return true;
			} 
			if(name.endsWith(".txt")) {
				return true;
			}
			return false;
		}
	
		@Override
		public String getDescription() {
			return "Textdateien .txt";
		}
		
		public File openFileShow() {
			JFileChooser oeffnenDialog = new JFileChooser();
			oeffnenDialog.setFileFilter(new MeinFilter());
			oeffnenDialog.setAcceptAllFileFilterUsed(false);
			int status = oeffnenDialog.showOpenDialog(null);
			
			if(status == JFileChooser.APPROVE_OPTION) {
				return oeffnenDialog.getSelectedFile();
			} else {
				return null;
			}
			
		}
		
		public File saveFileShow() {
			JFileChooser speichernDialog = new JFileChooser();
			speichernDialog.setFileFilter(new MeinFilter());
			speichernDialog.setAcceptAllFileFilterUsed(false);
			int status = speichernDialog.showSaveDialog(null);
			
			if(status == JFileChooser.APPROVE_OPTION) {
				return speichernDialog.getSelectedFile();
			} else {
				return null;
			}
			
		}
		
	}

	public static void main(String[] args) {
	
		new Editor("Der erste Texteditor");
		
	}


}
