package de.fernschulen.minitext;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLEditorKit;


/*
 * Einsendeaufgabe Java11B
 * Karim Kiel
 * Code: Java 11B-XX1-N01
 * 
 * Um schnell zu meinen Codeschnipseln zu gelangen, einfach nach
 * 
 * KK Aufgabe1:
 * KK Aufgabe2:
 * KK Aufgabe3:
 * 
 * mit STRG+F suchen.
 */

public class MiniText extends JFrame {

	//automatisch �ber Eclipse eingef�gt
	private static final long serialVersionUID = -4622810145518216095L;
	
	//f�r das Eingabefeld
	private JEditorPane eingabeFeld;
	//f�r das Format
	//wir verwenden HTML
	private HTMLEditorKit htmlFormat;
	
	//f�r die Aktionen
	private MeineAktionen 	oeffnenAct, speichernAct, beendenAct, neuAct,
							druckenAct, speichernUnterAct, webOeffnenAct,
							//KK: die neuen Aktionen
							infoAct;
	
	//f�r die Datei
	private File datei;
	
	//f�r das Kontext-Men�
	private JPopupMenu kontext;
	
	//KK Aufgabe3: Programmname als Variable f�r schnelles �ndern
	private String programmName = "Minitext Editor";
	
	//eine innere Klasse f�r die Action-Objekte
	//Sie ist von der Klasse AbstractAction abgeleitet
	class MeineAktionen extends AbstractAction {
		//automatisch �ber Eclipse erg�nzt
		private static final long serialVersionUID = 5736947176597361976L;

		//der Konstruktor
		public MeineAktionen(String text, ImageIcon icon, String bildschirmtipp, KeyStroke shortcut, String actionText) {
			//den Konstruktor der �bergeordneten Klasse mit dem Text und dem Icon aufrufen
			super(text, icon);
			//die Beschreibung setzen f�r den Bildschirmtipp
			putValue(SHORT_DESCRIPTION, bildschirmtipp);
			//den Shortcut
			putValue(ACCELERATOR_KEY, shortcut);
			//das ActionCommand
			putValue(ACTION_COMMAND_KEY, actionText);
		}
		//die �berschriebene Methode actionPerformed()
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("neu"))
				dateiNeu();
			if (e.getActionCommand().equals("laden"))
				dateiLaden();
			if (e.getActionCommand().equals("speichern"))
				dateiSpeichern();
			if (e.getActionCommand().equals("beenden"))
				beenden();
			if (e.getActionCommand().equals("drucken")) {
				//wurde die Aktion �ber eine Schaltfl�che gestartet?
				//dann direkt drucken
				if (e.getSource() instanceof JButton) 
					drucken(false);
				//wurde die Aktion �ber einen Men�eintrag gestartet?
				//dann erst den Dialog zeigen
				if (e.getSource() instanceof JMenuItem) 
					drucken(true);
			}
			if (e.getActionCommand().equals("speichernUnter"))
				dateiSpeichernUnter();
			if (e.getActionCommand().equals("webladen"))
				webLaden();
			if (e.getActionCommand().equals("infos"))
				infos(); //KK Aufgabe1: Die Infos Methode aufrufen
			
		}

	}
	
	//eine innere Klasse mit dem Listener f�r das Kontext-Men�
	//sie ist von der Adapter-Klasse MouseAdapter abgeleitet
	class MeinKontextMenuListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			//die Methode der �bergeordneten Klasse aufrufen
			super.mouseReleased(e);
			//wurde die Maustaste benutzt, die f�r das Anzeigen des Kontext-Men�s festgelegt ist?
			if (e.isPopupTrigger()) 
				//dann das Kontext-Men� anzeigen
				kontext.show(e.getComponent(), e.getX(), e.getY());
		}
	}
	
	//der Konstruktor der Klasse MiniText
	public MiniText(String text) {
		super(text);
		//wir nehmen ein Border-Layout
		setLayout(new BorderLayout());
		
		//KK Aufgabe3: Standardtitel setzen
		setzeFenstertitel("ohneName");
		
		//die Aktionen erstellen
		neuAct = new MeineAktionen("Neu...", new ImageIcon(getClass().getResource("/icons/New24.gif")), "Erstellt ein neues Dokument", KeyStroke.getKeyStroke('N',InputEvent.CTRL_DOWN_MASK), "neu");
		oeffnenAct = new MeineAktionen("�ffnen...", new ImageIcon(getClass().getResource("/icons/Open24.gif")), "�ffnet ein vorhandenes Dokument", KeyStroke.getKeyStroke('O',InputEvent.CTRL_DOWN_MASK),"laden");
		speichernAct = new MeineAktionen("Speichern", new ImageIcon(getClass().getResource("/icons/Save24.gif")), "Speichert das aktuelle Dokument", KeyStroke.getKeyStroke('S',InputEvent.CTRL_DOWN_MASK), "speichern");
		beendenAct = new MeineAktionen("Beenden", null, "", null, "beenden");
		druckenAct = new MeineAktionen("Drucken...", new ImageIcon(getClass().getResource("/icons/Print24.gif")), "Druckt das aktuelle Dokument", KeyStroke.getKeyStroke('P',InputEvent.CTRL_DOWN_MASK), "drucken");
		speichernUnterAct = new MeineAktionen("Speichern unter...", null, "", null, "speichernUnter");
		webOeffnenAct = new MeineAktionen("Webseite...", new ImageIcon(getClass().getResource("/icons/WebComponent24.gif")), "�ffnet eine Webseite", null, "webladen");
		//KK Aufgabe1: Meine Aktion f�r "Info"
		infoAct = new MeineAktionen("Info", new ImageIcon(getClass().getResource("/icons/Information24.gif")), "Informationen �ber das Programm", null, "infos"); 
		
		
		//das Men� erzeugen
		menu();
		//die Symbolleiste oben einf�gen
		add(symbolleiste(), BorderLayout.NORTH);
		//das Kontext-Men� erzeugen
		kontextMenu();

		//das Eingabefeld erzeugen und mit dem EditorKit verbinden 
		eingabeFeld = new JEditorPane();
		htmlFormat = new HTMLEditorKit();
		
		eingabeFeld.setEditorKit(htmlFormat);
		//das Eingabefeld mit dem Listener f�r das Kontext-Men� verbinden
		eingabeFeld.addMouseListener(new MeinKontextMenuListener());

		//gegebenenfalls mit Scrollbars in der Mitte einf�gen
		add(new JScrollPane(eingabeFeld), BorderLayout.CENTER);
		
		//anzeigen und Standard-Verhalten festlegen
		//das Fenster ist maximiert
		setExtendedState(MAXIMIZED_BOTH);
		//die Mindestgr��e setzen
		setMinimumSize(new Dimension(600,200));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//das Eingabefeld bekommt den Fokus
		eingabeFeld.requestFocus();
	}
	
	//die Methode erstellt die Men�leiste
	private void menu() {
		JMenuBar menue = new JMenuBar();
		//das Men� Datei
		JMenu dateiMenue = new JMenu("Datei");
		
		//das Untermen� �ffnen
		//es ist vom Typ Menu, da es weitere Untereintr�ge haben soll
		JMenu dateiOeffnen = new JMenu("�ffnen");
		//die Eintr�ge werden jetzt zum Untermen� hinzugef�gt
		//das "normale" �ffnen
		dateiOeffnen.add(oeffnenAct);
		//das �ffnen aus dem Internet
		dateiOeffnen.add(webOeffnenAct);
		
		//die Eintr�ge werden direkt �ber Action-Objekte erzeugt
		//der Eintrag Neu
		dateiMenue.add(neuAct);
		//das komplette Untermen� einf�gen
		dateiMenue.add(dateiOeffnen);
		
		//der Eintrag Speichern
		dateiMenue.add(speichernAct);
		//der Eintrag Speichern unter
		dateiMenue.add(speichernUnterAct);
		
		//eine Trennlinie
		dateiMenue.addSeparator();
		//der Eintrag Drucken
		dateiMenue.add(druckenAct);
		
		//eine Trennlinie
		dateiMenue.addSeparator();
		//der Eintrag Beenden
		dateiMenue.add(beendenAct);
		
		////////
		//KK Aufgabe1: Men�punkt "Hilfe" erstellen
		JMenu hilfeMenue = new JMenu("Hilfe");
		hilfeMenue.add(infoAct);
		
		//das gesamte Men� hinzuf�gen
		menue.add(dateiMenue);
		menue.add(hilfeMenue); //KK Aufgabe1: "Hilfe" dann in  Men� einbinden
		this.setJMenuBar(menue);
	}
	
	//die Methode erstellt die Symbolleiste
	private JToolBar symbolleiste() {
		JToolBar leiste = new JToolBar();

		//die Symbole werden ebenfalls direkt �ber Action-Objekte erzeugt
		//das Symbol Neu
		leiste.add(neuAct);
		//das Symbol �ffnen
		leiste.add(oeffnenAct);
		//das Symbol f�r das Laden von Webseiten
		leiste.add(webOeffnenAct);
		//das Symbol Speichern
		leiste.add(speichernAct);
		//das Symbol Drucken
		leiste.add(druckenAct);
					
		//etwas Platz einf�gen
		leiste.addSeparator();
		//ein Symbol f�r die fette Zeichenformatierung
		//�ber eine Standard-Aktion aus dem StyledEditorKit
		//die neue Aktion erzeugen
		//Vorsicht! Es gibt mehrere Klassen Action
		//richtig ist die aus javax.swing
		Action fettFormat = new StyledEditorKit.BoldAction();
		//den Bildschirmtipp und das Symbol setzen
		fettFormat.putValue(Action.SHORT_DESCRIPTION, "Fett formatieren");
		fettFormat.putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getResource("/icons/Bold24.gif")));
		//und zur Leiste hinzuf�gen
		leiste.add(fettFormat);
		
		//ein Symbol f�r die kursive Zeichenformatierung
		Action kursivFormat = new StyledEditorKit.ItalicAction();
		kursivFormat.putValue(Action.SHORT_DESCRIPTION, "Kursiv formatieren");
		kursivFormat.putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getResource("/icons/Italic24.gif")));
		leiste.add(kursivFormat);
		
		//ein Symbol f�r die unterstrichene Zeichenformatierung
		Action unterstrichenFormat = new StyledEditorKit.UnderlineAction();
		unterstrichenFormat.putValue(Action.SHORT_DESCRIPTION, "Unterstrichen formatieren");
		unterstrichenFormat.putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getResource("/icons/Underline24.gif")));
		leiste.add(unterstrichenFormat);
		
		//Platz einf�gen
		leiste.addSeparator();
		
		/////////KK Aufgabe2: Einf�gen unserer Textbearbeitungsaktionen
		
		//KK Aufgabe2: Kopieren Aktion
		Action kopierenAct = new DefaultEditorKit.CopyAction();
		kopierenAct.putValue(Action.NAME, "Kopieren");
		kopierenAct.putValue(Action.LARGE_ICON_KEY,  new ImageIcon(getClass().getResource("/icons/Copy24.gif")));
		kopierenAct.putValue(Action.SHORT_DESCRIPTION, "Einen markierten Text kopieren");
		leiste.add(kopierenAct);
		
		//KK Aufgabe2: Ausschneiden Aktion
		Action ausschneidenAct = new DefaultEditorKit.CutAction();
		ausschneidenAct.putValue(Action.NAME, "Ausschneiden");
		ausschneidenAct.putValue(Action.LARGE_ICON_KEY,  new ImageIcon(getClass().getResource("/icons/Cut24.gif")));
		ausschneidenAct.putValue(Action.SHORT_DESCRIPTION, "Einen markierten Text ausschneiden");
		leiste.add(ausschneidenAct);
		
		//KK Aufgabe2: Einf�gen Aktion
		Action einfuegenAct = new DefaultEditorKit.PasteAction();
		einfuegenAct.putValue(Action.NAME, "Einf�gen");
		einfuegenAct.putValue(Action.LARGE_ICON_KEY,  new ImageIcon(getClass().getResource("/icons/Paste24.gif")));
		einfuegenAct.putValue(Action.SHORT_DESCRIPTION, "Einen kopierten Text einf�gen");
		leiste.add(einfuegenAct);
		
		leiste.addSeparator();
		
		//das Symbol f�r die linksb�ndige Ausrichtung
		//an den Konstruktor von StyledEditorKit.AlignmentAction() wird eine Beschreibung und die
		//gew�nschte Ausrichtung �bergeben
		Action linksAbsatz = new StyledEditorKit.AlignmentAction("Linksb�ndig", StyleConstants.ALIGN_LEFT);
		linksAbsatz.putValue(Action.SHORT_DESCRIPTION, "Linksb�ndig ausrichten");
		linksAbsatz.putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getResource("/icons/AlignLeft24.gif")));
		leiste.add(linksAbsatz);

		//das Symbol f�r die zentrierte Ausrichtung
		Action zentriertAbsatz = new StyledEditorKit.AlignmentAction("Zentriert", StyleConstants.ALIGN_CENTER);
		zentriertAbsatz.putValue(Action.SHORT_DESCRIPTION, "Zentriert ausrichten");
		zentriertAbsatz.putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getResource("/icons/AlignCenter24.gif")));
		leiste.add(zentriertAbsatz);

		//das Symbol f�r die rechtsb�ndige Ausrichtung
		Action rechtsAbsatz = new StyledEditorKit.AlignmentAction("Rechts", StyleConstants.ALIGN_RIGHT);
		rechtsAbsatz.putValue(Action.SHORT_DESCRIPTION, "Rechtsb�ndig ausrichten");
		rechtsAbsatz.putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getResource("/icons/AlignRight24.gif")));
		leiste.add(rechtsAbsatz);
		
		//das Symbol f�r den Blocksatz
		Action blockAbsatz = new StyledEditorKit.AlignmentAction("Blocksatz", StyleConstants.ALIGN_JUSTIFIED);
		blockAbsatz.putValue(Action.SHORT_DESCRIPTION, "Im Blocksatz ausrichten");
		blockAbsatz.putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getResource("/icons/AlignJustify24.gif")));
		leiste.add(blockAbsatz);
		
		leiste.addSeparator(); //KK Aufgabe1: Mit dem Seperator noch etwas optisch abtrennen
		leiste.add(infoAct); //KK Aufgabe1: F�gt Info in die Toolbar ein
		
		//die komplette Leiste zur�ckgeben
		return (leiste);
	}
	
	//die Methode erstellt das Kontext-Men�
	private void kontextMenu() {
		//kontext ist eine Instanzvariable vom Typ JPopupMenu
		kontext = new JPopupMenu();
		//den Eintrag Neu hinzuf�gen
		kontext.add(neuAct);
		//die Eintr�ge zum �ffnen
		kontext.add(oeffnenAct);
		kontext.add(webOeffnenAct);
	}

	//die Methode f�r die Funktion Neu
	private void dateiNeu() {
		//eine Abfrage
		if(JOptionPane.showConfirmDialog(this, "Wollen Sie wirklich ein neues Dokument anlegen?","Neues Dokument", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			//den Text l�schen
			eingabeFeld.setText("");
			//datei wieder auf null setzen, damit klar ist, dass die Datei noch nicht gespeichert wurde
			datei = null;
			//KK Aufgabe3: ohneName im Titel setzen bei neuer Datei
			setzeFenstertitel("ohneName");
		}
	}

	//die Methode zum Laden einer Datei
	private void dateiLaden() {
		//f�r den Dialog
		MiniTextDialoge dialog = new MiniTextDialoge();
		
		//die Datei �ber den Dialog �ffnen beschaffen
		//es wird erst einmal in einer lokalen Variablen zwischengespeichert
		File dateiLokal = dialog.oeffnenDialogZeigen();
		
		//wenn eine Datei ausgew�hlt wurde, einlesen
		//die Methode read() erzwingt eine Ausnahmebehandlung
		if (dateiLokal != null) {
			try {
				eingabeFeld.read(new FileReader(dateiLokal), null);
				//datei neu setzen
				datei = dateiLokal;
				//KK Aufgabe3: datei in String umwandeln und als Titel einf�gen
				setzeFenstertitel(datei.toString());
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Beim Laden hat es ein Problem gegeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	//die Methode zum Speichern einer Datei
	private void dateiSpeichern() {
		//WICHTIG!!!!
		//datei muss als Instanzvariable vereinbart werden
		//wenn die Datei noch nicht gespeichert wurde, ist datei noch null
		if (datei == null) {
			//f�r den Dialog
			MiniTextDialoge dialog = new MiniTextDialoge();
			//die Datei �ber den Dialog Speichern beschaffen
			datei = dialog.speichernDialogZeigen();
		}
		//ist datei ungleich null, dann wurde vorher schon einmal gespeichert
		//bzw. im Dialog eine Datei ausgew�hlt
		if (datei != null) {
			//die Ausnahmebehandlung ist zwingend erforderlich
			try {
				//den Output-Stream f�r die Datei beschaffen
				OutputStream output = new FileOutputStream(datei);
				//das Dokument komplett speichern
				htmlFormat.write(output, eingabeFeld.getDocument(), 0, eingabeFeld.getDocument().getLength());
				
				//KK Aufgabe3: datei in String umwandeln und als Titel einf�gen
				setzeFenstertitel(datei.toString());
			}
			catch (IOException | BadLocationException e) {
				JOptionPane.showMessageDialog(this, "Beim Speichern hat es ein Problem gegeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//die Methode �ffnet den Speichern-Dialog und ruft die Methode dateiSpeichern() auf,
	//wenn ein neuer Name ausgew�hlt wurde
	private void dateiSpeichernUnter() {
		//f�r den Dialog
		MiniTextDialoge dialog = new MiniTextDialoge();
		//die Datei �ber den Dialog Speichern beschaffen
		File dateiLokal = dialog.speichernDialogZeigen();
		//ist dateiLokal ungleich null, dann wurde ein neuer Name ausgew�hlt
		if (dateiLokal != null) {
			datei = dateiLokal;
			dateiSpeichern();
			
			//KK Aufgabe3: datei in String umwandeln und als Titel einf�gen
			setzeFenstertitel(datei.toString());
		}
	}
	
	
	//die Methode beendet die Anwendung nach einer Abfrage
	private void beenden() {
		if(JOptionPane.showConfirmDialog(this, "Sind Sie sicher?","Anwendung schlie�en", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	//die Methode druckt das Dokument
	//wenn dialogZeigen true ist, wird erst der Drucken-Dialog eingeblendet
	private void drucken(boolean dialogZeigen) {
		try {
			if (dialogZeigen == true)
				//mit Dialog drucken
				eingabeFeld.print();
			else
				//ohne Dialog drucken
				//dazu muss das dritte Argument false sein
				eingabeFeld.print(null, null, false, null, null, true);
			}
		catch (PrinterException e) {
			JOptionPane.showMessageDialog(this, "Beim Drucken hat es ein Problem gegeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//die Methode �ffnet ein Dokument aus dem Internet
	private void webLaden() {
		//f�r die URL
		String adresse;
		//einen Dialog zur Eingabe erzeugen
		adresse = JOptionPane.showInputDialog(this, "Bitte geben Sie die URL der Seite ein:");
		//wurde etwas eingegeben?
		if (adresse != null) {
			//den Text im Feld l�schen
			eingabeFeld.setText("");
			try {
				//den Inhalt anzeigen
				eingabeFeld.setPage(adresse);
				
				//KK Aufgabe3: Webadresse in den Titel �bergeben
				setzeFenstertitel(adresse);
			} 
			catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Beim Laden ist ein Problem aufgetreten.");
			}
		}
	}
	
	//KK Aufgabe3: Methode f�r den dynamischen Fenstertitel
	private void setzeFenstertitel(String pfad) {
		this.setTitle(programmName + " - " + pfad);
	}
	
	//KK Aufgabe1: F�r das JOptionPane ist es sinnvoll, als viertes Argument
	//INFORMATION_MESSAGE f�r ein vorgefertiges Icon zu nehmen!
	private void infos() {
		JOptionPane.showMessageDialog(this, "MiniText Editor f�r Java11B Einsendung von Karim Kiel\n"
				+ "Auf Basis von Christoph Siebeck's Version\n"
				+ "Erstellt am 04.06.2020", "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void main(String[] args) {
		new MiniText("MiniText - Einsendeaufgabe Java 11 B von Karim Kiel");
	}
	
}

