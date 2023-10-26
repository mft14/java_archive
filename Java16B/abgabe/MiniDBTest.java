package java16beinsend;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//ACHTUNG!! Das Programm l�sst sich nur ausf�hren, wenn derby.jar eingebunden ist

public class MiniDBTest extends JFrame {
	//automatisch �ber Eclipse erzeugt
	private static final long serialVersionUID = 7908452154759854933L;
	
	//f�r den Treiber und das Protokoll
	private String treiber = "org.apache.derby.jdbc.EmbeddedDriver";
	private String protokoll = "jdbc:derby:";
	 
	//f�r die Verbindung
	//ACHTUNG! Es gibt mehrere Connection-Klassen!
	//ben�tigt wird die Klasse aus dem Paket java.sql.
 	private Connection verbindung;

	
	//die innere Klasse f�r den ActionListener
	class MeinListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//wurde auf Anlegen geklickt?
			if (e.getActionCommand().equals("anlegen"))
				//dann die Datenbank anlegen
				anlegenDB();
			//wurde auf neue Eintr�ge geklickt?
			if (e.getActionCommand().equals("eintraege"))
				//dann die Testeintr�ge anlegen
				anlegenEintraege();
			//wurde auf Beenden geklickt?
			if (e.getActionCommand().equals("ende"))
				//dann beenden
				System.exit(0);
		}
	}


	public MiniDBTest(String titel) {
		super(titel);
		
		//ein FlowLayout
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//die Schaltfl�chen
		JButton anlegen = new JButton("Datenbank anlegen");
		anlegen.setActionCommand("anlegen");
		JButton test = new JButton("Testeintr�ge erzeugen");
		test.setActionCommand("eintraege");
		JButton beenden = new JButton("Beenden");
		beenden.setActionCommand("ende");
		
		MeinListener listener = new MeinListener();
		anlegen.addActionListener(listener);
		test.addActionListener(listener);
		beenden.addActionListener(listener);
	
		add(anlegen);
		add(test);
		add(beenden);
		
		//Gr��e setzen, Standard-Verhalten festlegen und anzeigen
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	private void oeffnenDB(String arg) {
		try {
			//den Treiber laden
			Class.forName(treiber).newInstance();
			//die Datenbank ist adressen, sie wird neu angelegt
			verbindung = DriverManager.getConnection(arg);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem: \n" + e.toString());
		}
	}
	
	private void schliessenDB() {
		//das Schlie�en l�st bei Erfolg eine Exception aus!!!
		boolean erfolg = false;
		try {
			DriverManager.getConnection(protokoll + ";shutdown=true");
		}
		catch (SQLException e) {
			erfolg = true;
		}
		if (erfolg !=true) 
			JOptionPane.showMessageDialog(this, "Das DBMS konnte nicht heruntergefahren werden.");
	}
	
	public static void main(String[] args) {
		new MiniDBTest("Datenbank-Werkzeuge");
	}

	
	private void anlegenDB(){
		//die Datenbank ist adressenDB, sie wird neu angelegt
		oeffnenDB(protokoll + "adressenDB;create=true");
		JOptionPane.showMessageDialog(this, "Die Datenbank wurde angelegt bzw. ge�ffnet.");
		//ein Statement zum Erstellen der Tabelle
		//bitte auch hier auf die richtige Klasse aus dem Paket sql achten!
		try {
			Statement state = verbindung.createStatement();
			state.execute("CREATE TABLE adressen(iNummer int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), " +
					"vorname varchar(50) NOT NULL," +
					"nachname varchar(50) NOT NULL," +
					"strasse varchar(50) NOT NULL," +
					"plz varchar(5) NOT NULL," +
					"ort varchar(50) NOT NULL," +
					"telefon varchar(20)," +
					"PRIMARY KEY(iNummer))");
			JOptionPane.showMessageDialog(this, "Die Tabelle wurde angelegt.");
			//Verbindung schlie�en
			state.close();
			verbindung.close();
			//und die Datenbank schlie�en
			schliessenDB();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem: \n" + e.toString());
		}
	}
	
	private void anlegenEintraege() {
		//die Datenbank �ffnen
		oeffnenDB(protokoll + "adressenDB");
		try {
			//�nderungen von au�en werden sichtbar und der Cursor kann beliebig bewegt werden
			Statement state = verbindung.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//die Ergebnismenge erzeugen
			//dazu beschaffen wir alle Eintr�ge in der Tabelle
	        ResultSet ergebnisMenge = state.executeQuery("SELECT * FROM adressen");
	        //die Testdaten einf�gen
	        for (int i = 0; i < 10; i++) {
	        	//zur "Einf�gezeile" bewegen
	        	ergebnisMenge.moveToInsertRow();
	        	//die Nummer wird automatisch gesetzt
	        	//angegeben wird die Spalte und der neue Wert
	        	ergebnisMenge.updateString(2, "Hans" + i);
	        	ergebnisMenge.updateString(3, "Meier" + i);
	        	ergebnisMenge.updateString(4, i + "stra�e");
	        	ergebnisMenge.updateString(5, Integer.toString(i));
	        	ergebnisMenge.updateString(6, i + "hausen");
	        	ergebnisMenge.updateString(7, "00" + i);
	        	//und einf�gen
	        	ergebnisMenge.insertRow();
	        }
	        //ganz nach vorne gehen
	        ergebnisMenge.beforeFirst();
	        
	        //zum Test alle Werte in der Konsole ausgeben
	        while (ergebnisMenge.next()) {
	        	//hier wird direkt das Feld angegeben
	       	 	System.out.println(ergebnisMenge.getInt("iNummer"));
	       	 	System.out.println(ergebnisMenge.getString("vorname"));
	       	 	System.out.println(ergebnisMenge.getString("nachname"));
	       	 	System.out.println(ergebnisMenge.getString("strasse"));
	       	 	System.out.println(ergebnisMenge.getString("plz"));
	       	 	System.out.println(ergebnisMenge.getString("ort"));
	       	 	System.out.println(ergebnisMenge.getString("telefon"));
	        }
	        //zum letzten Eintrag gehen
	        ergebnisMenge.last();
	        System.out.println("In der Tabelle stehen jetzt " + ergebnisMenge.getRow() + " Eintr�ge");
			//Verbindung schlie�en
			state.close();
			ergebnisMenge.close();
			verbindung.close();
			//und die Datenbank schlie�en
			schliessenDB();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem: \n" + e.toString());
		}
	}
}
