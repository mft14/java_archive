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
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class Editor extends JFrame{
	
	/*
	 * Java17B
	 * Karim Kiel
	 * Anpassungen f�r die JAR Datei
	 * Die Pfade der Icons m�ssen angepasst
	 */
	
	
	//automatisch �ber Eclipse eingef�gt
	private static final long serialVersionUID = 3215604333266961332L;
	
	//die innere Klasse f�r den ActionListener
	class MeinListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//welcher Eintrag ist angeklickt worden?
			if (e.getActionCommand().equals("neu"))
				dateiNeu();
			if (e.getActionCommand().equals("laden"))
				dateiLaden();
			if (e.getActionCommand().equals("speichern"))
				dateiSpeichern();
			if (e.getActionCommand().equals("beenden"))
				beenden();
		}
	}


	//f�r das Textfeld
	private JTextArea eingabeFeld;
	
	public Editor(String text) {
		super(text);
		
		//wir nehmen ein Border-Layout
		setLayout(new BorderLayout());
		
		//das Eingabefeld erstellen 
		eingabeFeld = new JTextArea();
		//gegebenfalls mit Scrollbars in der Mitte einf�gen
		add(new JScrollPane(eingabeFeld), BorderLayout.CENTER);
		
		//die Men�leiste erzeugen
		//sie kommt automatisch nach oben
		menu();
		//die Symbolleiste oben einf�gen
		add(symbolleiste(), BorderLayout.NORTH);

		//das Fenster ist maximiert
		setExtendedState(MAXIMIZED_BOTH);
		//die Mindestgr��e setzen
		setMinimumSize(new Dimension(600,200));
		//Standard-Verhalten festlegen und anzeigen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	//die Methode erzeugt das Men�
	private void menu() {
		//f�r die komplette Leiste
		JMenuBar menue = new JMenuBar();
		//das Men� Datei
		JMenu dateiMenue = new JMenu("Datei");
		
		//f�r den ActionListener
		MeinListener listener = new MeinListener();

		//der Eintrag Neu
		JMenuItem dateiNeu = new JMenuItem();
		//den Text setzen
		dateiNeu.setText("Neu");
		//das Bild setzen
		dateiNeu.setIcon(new ImageIcon(getClass().getResource("New24.gif")));
		//den Shortcut, hier CTRL und N
		dateiNeu.setAccelerator(KeyStroke.getKeyStroke('N',InputEvent.CTRL_DOWN_MASK));
		//das ActionCommand setzen
		dateiNeu.setActionCommand("neu");
		//den Listener verbinden
		dateiNeu.addActionListener(listener);
		//den Eintrag hinzuf�gen
		dateiMenue.add(dateiNeu);
		
		//der Eintrag �ffnen
		JMenuItem dateiOeffnen = new JMenuItem();
		//den Text setzen
		dateiOeffnen.setText("�ffnen...");
		//das Bild setzen
		dateiOeffnen.setIcon(new ImageIcon(getClass().getResource("Open24.gif")));
		//den Shortcut, hier CTRL und O
		dateiOeffnen.setAccelerator(KeyStroke.getKeyStroke('O',InputEvent.CTRL_DOWN_MASK));
		//das ActionCommand setzen
		dateiOeffnen.setActionCommand("laden");
		//den Listener verbinden
		dateiOeffnen.addActionListener(listener);
		//den Eintrag hinzuf�gen
		dateiMenue.add(dateiOeffnen);

		//eine Trennlinie einf�gen
		dateiMenue.addSeparator();
		
		//der Eintrag Speichern
		JMenuItem dateiSpeichern = new JMenuItem();
		//den Text setzen
		dateiSpeichern.setText("Speichern...");
		//das Bild setzen
		dateiSpeichern.setIcon(new ImageIcon(getClass().getResource("Save24.gif")));
		//den Shortcut, hier CTRL und S
		dateiSpeichern.setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_DOWN_MASK));
		//das ActionCommand setzen
		dateiSpeichern.setActionCommand("speichern");
		//den Listener verbinden
		dateiSpeichern.addActionListener(listener);
		//den Eintrag hinzuf�gen
		dateiMenue.add(dateiSpeichern);

		//eine Trennlinie einf�gen
		dateiMenue.addSeparator();
		
		//der Eintrag Beenden
		//er hat kein Bild
		JMenuItem dateiBeenden = new JMenuItem();
		//den Text setzen
		dateiBeenden.setText("Beenden");
		//das ActionCommand
		dateiBeenden.setActionCommand("beenden");
		//den Listener verbinden
		dateiBeenden.addActionListener(listener);
		//den Eintrag hinzuf�gen
		dateiMenue.add(dateiBeenden);

		//das gesamte Men� hinzuf�gen
		menue.add(dateiMenue);
		//und die Men�leiste f�r das Fenster setzen
		this.setJMenuBar(menue);
	}
	
	//die Methode erstellt die Symbolleiste und liefert sie zur�ck
	private JToolBar symbolleiste() {
		JToolBar leiste = new JToolBar();
		//f�r den ActionListener
		MeinListener listener = new MeinListener();

		//das Symbol Neu
		JButton dateiNeuButton = new JButton();
		//das ActionCommand setzen
		dateiNeuButton.setActionCommand("neu");
		//das Bild setzen
		dateiNeuButton.setIcon(new ImageIcon(getClass().getResource("New24.gif")));
		//den Text setzen
		dateiNeuButton.setToolTipText("Erstellt ein neues Dokument");
		//den Listener verbinden
		dateiNeuButton.addActionListener(listener);
		//das Symbol hinzuf�gen
		leiste.add(dateiNeuButton);
		
		//das Symbol �ffnen
		JButton dateiOeffnenButton = new JButton();
		//das ActionCommand setzen
		dateiOeffnenButton.setActionCommand("laden");
		//das Bild setzen
		dateiOeffnenButton.setIcon(new ImageIcon(getClass().getResource("Open24.gif")));
		//den Text setzen
		dateiOeffnenButton.setToolTipText("�ffnet ein vorhandenes Dokument");
		//den Listener verbinden
		dateiOeffnenButton.addActionListener(listener);
		//das Symbol hinzuf�gen
		leiste.add(dateiOeffnenButton);
		
		//das Symbol Speichern
		JButton dateiSpeichernButton = new JButton();
		//das ActionCommand setzen
		dateiSpeichernButton.setActionCommand("speichern");
		//das Bild setzen
		dateiSpeichernButton.setIcon(new ImageIcon(getClass().getResource("Save24.gif")));
		//den Text setzen
		dateiSpeichernButton.setToolTipText("Speichert das aktuelle Dokument");
		//den Listener verbinden
		dateiSpeichernButton.addActionListener(listener);
		//das Symbol hinzuf�gen
		leiste.add(dateiSpeichernButton);
		
		//die komplette Leiste zur�ckgeben
		return (leiste);
	}

	//die Methode f�r die Funktion Neu
	//hier wird einfach nur nach einer Abfrage der Text im Feld gel�scht 
	private void dateiNeu() {
		if(JOptionPane.showConfirmDialog(this, "Wollen Sie wirklich ein neues Dokument anlegen?","Neues Dokument", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			eingabeFeld.setText("");
	}

	//die Methode zum Laden einer Datei
	private void dateiLaden() {
		//f�r den Dialog
		EditorDialoge dialog = new EditorDialoge();
		
		//die Datei �ber den Dialog �ffnen beschaffen
		File datei = dialog.oeffnenDialogZeigen();
		
		//wenn eine Datei ausgew�hlt wurde, einlesen
		//die Methode read() erzwingt eine Ausnahmebehandlung
		if (datei != null) {
			try {
				eingabeFeld.read(new FileReader(datei), null);
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Beim Laden hat es ein Problem gegeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	//die Methode zum Speichern einer Datei
	private void dateiSpeichern() {
		//f�r den Dialog
		EditorDialoge dialog = new EditorDialoge();
		
		//die Datei �ber den Dialog Speichern beschaffen
		File datei = dialog.speichernDialogZeigen();
		
		//wenn etwas ausgew�hlt wurde, speichern
		//die Methode write() erzwingt eine Ausnahmebehandlung
		if (datei != null) {
			try {
				eingabeFeld.write(new FileWriter(datei));
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Beim Speichern hat es ein Problem gegeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//die Methode beendet die Anwendung nach einer Abfrage
	private void beenden() {
		if(JOptionPane.showConfirmDialog(this, "Sind Sie sicher?","Anwendung schlie�en", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	//Programm starten
	public static void main(String[] args) {
		new Editor("Text-Editor als JAR - Java17B Einsendaufgabe");
	}
	
	
}

