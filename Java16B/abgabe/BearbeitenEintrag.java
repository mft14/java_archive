package java16beinsend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class BearbeitenEintrag extends JDialog {
	//automatisch �ber Eclipse erzeugt
	private static final long serialVersionUID = 2674865770208476234L;
	
	//f�r die Eingabefelder
	private JTextField name, nachname, strasse, plz, ort, telefon;
	//f�r die Anzeige
	private JLabel nummer, lblDatensatz; //KK1: Datensatz Label erstellen

	//f�r die Aktionen
	private MeineAktionen loeschenAct, vorAct, zurueckAct, startAct, endeAct, aktualisierenAct;
	
	//f�r die Verbindung
	private Connection verbindung;
	private ResultSet ergebnisMenge;
	
	//f�r die Abfrage
	private String sqlAbfrage;
	
	//eine innere Klasse f�r die Aktionen
	class MeineAktionen extends AbstractAction {
		//automatisch �ber Eclipse erg�nzt
		private static final long serialVersionUID = 8673560298548765044L;

		//der Konstruktor 
		public MeineAktionen(String text, ImageIcon icon, String beschreibung, KeyStroke shortcut, String actionText) {
			//den Konstruktor der �bergeordneten Klasse mit dem Text und dem Icon aufrufen
			super(text, icon);
			//die Beschreibung setzen f�r den Bildschirmtipp
			putValue(SHORT_DESCRIPTION, beschreibung);
			//den Shortcut
			putValue(ACCELERATOR_KEY, shortcut);
			//das ActionCommand
			putValue(ACTION_COMMAND_KEY, actionText);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("vor"))
				ganzVor();
			if (e.getActionCommand().equals("zurueck"))
				ganzZurueck();
			if (e.getActionCommand().equals("einenvor"))
				einenVor();
			if (e.getActionCommand().equals("einenzurueck"))
				einenZurueck();
			if (e.getActionCommand().equals("loeschen"))
				loeschen();
			if (e.getActionCommand().equals("aktualisieren"))
				aktualisieren();
		}
	}
	
	//die innere Klasse f�r die Fenster-Ereignisse
	class FensterListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			super.windowClosing(e);
			//die Datenbankverbindung trennen
			//ergebnisMenge und verbindung sind Variablen der �u�eren Klasse 
			try {
				BearbeitenEintrag.this.ergebnisMenge.close();
				BearbeitenEintrag.this.verbindung.close();
				MiniDBTools.schliessenDB("jdbc:derby:");
			}
			catch(Exception exc) {
				JOptionPane.showMessageDialog(null, "Problem: \n" + exc.toString());
			}
		}
	}
	
	//der Konstruktor der Klasse BearbeitenEintrag
	public BearbeitenEintrag(JFrame parent, boolean modal) {
		super(parent, modal);
		setTitle("Eintr�ge bearbeiten");
		
		//wir nehmen ein Borderlayout
		setLayout(new BorderLayout());
		//die Aktionen erstellen
		loeschenAct = new MeineAktionen("Datensatz l�schen", 
				new ImageIcon("icons/Delete24.gif"), 
				"L�scht den aktuellen Datensatz", 
				null,
				"loeschen");
		vorAct = new MeineAktionen("Einen Datensatz weiter", 
				new ImageIcon("icons/Forward24.gif"), 
				"Bl�ttert einen Datensatz weiter", 
				null, 
				"einenvor");
		zurueckAct = new MeineAktionen("Einen Datensatz zur�ck", 
				new ImageIcon("icons/Back24.gif"), 
				"Bl�ttert einen Datensatz zur�ck", 
				null, 
				"einenzurueck");
		startAct = new MeineAktionen("Zum ersten Datensatz",
				new ImageIcon("icons/Front24.gif"), 
				"Geht zum ersten Datensatz", 
				null, 
				"vor");
		endeAct = new MeineAktionen("Zum letzten Datensatz", 
				new ImageIcon("icons/End24.gif"), 
				"Geht zum letzten Datensatz", 
				null, 
				"zurueck");
		aktualisierenAct = new MeineAktionen("�nderungen speichern", 
				new ImageIcon("icons/Save24.gif"), 
				"Speichert �nderungen am aktuellen Datensatz", 
				null, 
				"aktualisieren");
		
		//die Symbolleiste oben einf�gen
		add(symbolleiste(), BorderLayout.NORTH);

		//die Oberfl�che erstellen und einf�gen
		add(initGui(), BorderLayout.CENTER);
		
		//zuerst nehmen wir alle Eintr�ge aus der Tabelle adressen
		sqlAbfrage = "SELECT * FROM adressen";
		//diese Abfrage w�hlt nur alle M�llers aus
		//sqlAbfrage = "SELECT * FROM adressen WHERE nachname = 'M�ller'";
		
		//die Datenbankverbindung herstellen
		initDB();
		
		//die Verbindung mit dem Listener des Fensters herstellen
		addWindowListener(new FensterListener());
		
		//packen und anzeigen
		pack();
		setVisible(true);
		//Standardoperation setzen
		//hier den Dialog ausblenden und l�schen
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	//f�gt die Felder in ein Panel ein und liefert das Panel zur�ck
	private JPanel initGui() {
		JPanel tempPanel = new JPanel();
		//im GridLayout mit zwei Spalten
		tempPanel.setLayout(new GridLayout(0,2));
		//f�r die Nummer (nur Anzeige)
		tempPanel.add(new JLabel("ID-Nummer:"));
		nummer = new JLabel();
		tempPanel.add(nummer);
		//f�r die anderen Felder
		tempPanel.add(new JLabel("Vorname:"));
		name = new JTextField();
		tempPanel.add(name);
		tempPanel.add(new JLabel("Nachname:"));
		nachname = new JTextField();
		tempPanel.add(nachname);
		tempPanel.add(new JLabel("Strasse:"));
		strasse = new JTextField();
		tempPanel.add(strasse);
		tempPanel.add(new JLabel("PLZ:"));
		plz = new JTextField();
		tempPanel.add(plz);
		tempPanel.add(new JLabel("Ort:"));
		ort = new JTextField();
		tempPanel.add(ort);
		tempPanel.add(new JLabel("Telefon:"));
		telefon = new JTextField();
		tempPanel.add(telefon);
		
		//KK1: Datensatz Label GUI einsetzen; 
		lblDatensatz = new JLabel();
		tempPanel.add(lblDatensatz);
		
		//zur�ckgeben
		return tempPanel;
	}
	
	//die Symbolleiste erzeugen und zur�ckgeben
	private JToolBar symbolleiste() {
		JToolBar leiste = new JToolBar();
		//die Symbole �ber die Aktionen einbauen
		leiste.add(loeschenAct);
		leiste.add(aktualisierenAct);
		//Abstand einbauen
		leiste.addSeparator();
		leiste.add(startAct);
		leiste.add(zurueckAct);
		leiste.add(vorAct);
		leiste.add(endeAct);
		
		//die komplette Leiste zur�ckgeben
		return (leiste);
	}
	
	//die Verbindung zur Datenbank herstellen
	private void initDB() {
		try{
			//Verbindung herstellen und Ergebnismenge beschaffen
			verbindung=MiniDBTools.oeffnenDB("org.apache.derby.jdbc.EmbeddedDriver", "jdbc:derby:adressenDB");
			ergebnisMenge = MiniDBTools.lieferErgebnis(verbindung, sqlAbfrage);
			if (ergebnisMenge.next()) 
				datenLesen();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Problem: \n" + e.toString());
		}
	}
	
private void datensatzAnzeige() {
	//KK1:  Datensatz Label aktualisieren
	
	try { //KK1:
		int position; //Position merken
		position = ergebnisMenge.getRow();
		
		//KK1: Speichert aktuellen Datensatz, nimmt von gemerkten Position
		String dsAktuell = Integer.toString(position);
		
		ergebnisMenge.last();//Geht zum letzten Datensatz
		//Speichert den letzten Datensatz auch in ein String
		String dsInsgesamt = Integer.toString(ergebnisMenge.getRow());
		
		//Datenbank neustarten, dann zur alten Position springen
		ergebnisMenge = MiniDBTools.lieferErgebnis(verbindung, sqlAbfrage);
		ergebnisMenge.absolute(position);
		
		//Datensatzanzahl ins Label schreiben
		lblDatensatz.setText("Datensatz  " + dsAktuell + " von " + dsInsgesamt);
	    
	} catch (SQLException e) { //SQLException wird immer f�r ResultSet ben�tigt
		JOptionPane.showMessageDialog(this, "Problem bei der Datensatzanzeige: \n\n" + e.toString());
	}
}

	//die Methode liest die Daten und schreibt sie in die Felder
	private void datenLesen() {
		try {
			
			nummer.setText(Integer.toString(ergebnisMenge.getInt(1)));
			name.setText(ergebnisMenge.getString(2));
			nachname.setText(ergebnisMenge.getString(3));
			strasse.setText(ergebnisMenge.getString(4));
			plz.setText(ergebnisMenge.getString(5));
			ort.setText(ergebnisMenge.getString(6));
			telefon.setText(ergebnisMenge.getString(7));
			
			datensatzAnzeige(); //KK1: Methode ausf�hren und Anzeige aktualisieren
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Problem: \n" + e.toString());
		}
	}

	//die Methode geht zum ersten Datensatz
	private void ganzVor() {
		try {
			//ganz nach vorne gehen
			ergebnisMenge.first();
			datenLesen();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Problem: \n" + e.toString());
		}
	}

	//die Methode geht zum letzten Datensatz
	private void ganzZurueck() {
		try {
			//ganz nach hinten gehen
			ergebnisMenge.last();
			datenLesen();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Problem: \n" + e.toString());
		}
}

	//die Methode geht einen Datensatz weiter
	private void einenVor() {
		try {
			//gibt es noch einen Datensatz?
			if (ergebnisMenge.next()) {
				datenLesen();
			} else if(ergebnisMenge.getRow() == 0) { 
				//KK2: Wenn der Datensatz "ein zu weit geht"
				//wird bei getRow() 0 zur�ckgegeben
				ergebnisMenge.last(); //Springe mit last() zum letzten "richtigen" Datensatz
			} 
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Problem: \n" + e.toString());
		}
	}
	
	//die Methode geht einen Datensatz zur�ck
	private void einenZurueck() {
		try{
			//gibt es noch einen Datensatz davor?
			if (ergebnisMenge.previous()) {
				datenLesen();
			} else if (ergebnisMenge.getRow() == 0) {
				//KK2: Dasselbe Spiel wie bei "einenVor()"
				//springe bei getRow() = 0 zum ersten richtigen Datensatz
				ergebnisMenge.first();
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Problem: \n" + e.toString());
		}
	}
	
	//die Methode l�scht einen Datensatz
	private void loeschen() {
		try {
			//wir m�ssen uns merken, wo wir sind
			int position;
			position = ergebnisMenge.getRow();
			//den Eintrag l�schen
			ergebnisMenge.deleteRow();
        	//Ergebnismenge schlie�en
	        ergebnisMenge.close();
	        // und neu �ffnen
			ergebnisMenge = MiniDBTools.lieferErgebnis(verbindung, sqlAbfrage);
			
			//und wieder zur "alten" Position gehen
			ergebnisMenge.absolute(position);
			//stehen wir jetzt hinter dem letzten?
			if (ergebnisMenge.isAfterLast())
				//dann zum letzten gehen
				ergebnisMenge.last();
			//die Daten neu lesen
			datenLesen();
			System.out.println("Datenbank gel�scht");
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Problem: \n" + e.toString());
		}
	}

	//die Methode aktualisiert einen Eintrag
	private void aktualisieren() {
		try {
			//wir m�ssen uns merken, wo wir sind
			int position;
			position = ergebnisMenge.getRow();
			
			//die Daten aktualisieren
        	ergebnisMenge.updateString(2, name.getText());
        	ergebnisMenge.updateString(3, nachname.getText());
        	ergebnisMenge.updateString(4, strasse.getText());
        	ergebnisMenge.updateString(5, plz.getText());
        	ergebnisMenge.updateString(6, ort.getText());
        	ergebnisMenge.updateString(7, telefon.getText());   	
        	//den Datensatz aktualisieren
        	ergebnisMenge.updateRow();
        	//Ergebnismenge schlie�en
	        ergebnisMenge.close();
	        // und neu �ffnen
			ergebnisMenge = MiniDBTools.lieferErgebnis(verbindung, sqlAbfrage);
			//und wieder zur "alten" Position gehen
			ergebnisMenge.absolute(position);
			//die Daten neu lesen
			datenLesen();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Problem: \n" + e.toString());
		}
	}
}
