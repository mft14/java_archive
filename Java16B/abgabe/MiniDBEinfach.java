package java16beinsend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class MiniDBEinfach {

	public static void main(String[] args) {
		
		try {
			
			//Schritt 2: Treiber registrieren
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			
			//Schritt 3: Verbindung über Triebermanager herstellen
			Connection verbindung = DriverManager.getConnection("jdbc:derby:adressenDB");
			
			//Schritt 4: Ergebnismenge erzeugen
			Statement state = verbindung.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet ergebnisMenge = state.executeQuery("SELECT * FROM adressen");
			
			//Schritt 5: Daten verarbeiten
			while (ergebnisMenge.next()) {
				System.out.println(ergebnisMenge.getInt("iNummer"));
				System.out.println(ergebnisMenge.getString("vorname"));
				System.out.println(ergebnisMenge.getString("nachname"));
				System.out.println(ergebnisMenge.getString("plz"));
				System.out.println(ergebnisMenge.getString("ort"));
				System.out.println(ergebnisMenge.getString("telefon"));
			}
			
			//Schritt 6: Verbindung schließen
			state.close();
			ergebnisMenge.close();
			verbindung.close();
			System.out.println("ERfolgreich");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem: " + e.toString());
		}
		

	}

}
