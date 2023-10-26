package java16beinsend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class MiniDBTools {

	public static Connection oeffnenDB(String treiber, String arg) {
		
		Connection verbindung = null;
		try {
			Class.forName(treiber).newInstance();
			verbindung = DriverManager.getConnection(arg);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Problem: \n" + e.toString());
		}
		return verbindung;
	}
	
	public static ResultSet lieferErgebnis(Connection verbindung, String sqlAnweisung) {
		ResultSet ergebnisMenge = null;
		try {
			Statement state = verbindung.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ergebnisMenge = state.executeQuery(sqlAnweisung);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem: \n\n" + e.toString());
		}
		return ergebnisMenge;
	}
	
	public static void schliessenDB(String protokoll) {
		boolean erfolg = false;
		try {
			DriverManager.getConnection(protokoll + ";shutdown=true");
		} catch (SQLException e) {
			erfolg = true;
		}
		
		if(erfolg != true) {
			JOptionPane.showMessageDialog(null, "Das DBMS konnte nicht heruntergefahren werden.");
		}
	}
	
}
