/*************************************************************
 * Eine Klasse für Eingaben über einen grafischen Dialog
 * ***********************************************************
 * Das Einlesen erfolgt über eine überladene statische
 * Methode einlesen()
 * Die Klassenvariable abgebrochen speichert, ob die 
 * Eingabe über die Schaltfläche Abbrechen abgebrochen
 * ***********************************************************
 * Beschreibung der Methode einlesen()
 * 
 * Die Methode liest über die Methode lesen() einen Wert
 * ein und versucht, diesen Wert in den passenden Typ
 * umzuwandeln. Die Eingabe wird so lange wiederholt,
 * bis ein gültiger Typ eingegeben wurde bzw. auf die
 * Schaltfläche Abbrechen geklickt wurde.
 * 
 * Parameter: 	wert für den Typ, der verarbeitet werden soll
 * Rückgabe: 	Der Wert, der umgewandelt wurde
 * 				Der Typ hängt vom übergebenen Parameter ab
 * 
 * Die Methode ist drei Mal vorhanden (für int, float und 
 * double)
 * **************************************************************
 * Beschreibung der Methode lesen()
 * 
 * Die Methode wird zum Einlesen der Werte über den Dialog 
 * benutzt.
 * Sie setzt außerdem die Klassenvariable abgebrochen.
 * 
 * Parameter:	text als Zeichenkette für die Ausgabe im 
 * 				Dialog
 * Rückgabe:	Der eingelesene Wert als Zeichenkette
 * ***********************************************************
 * */


//für den Eingabedialog
import javax.swing.JOptionPane;

public class EingabeDialog {
	//die Klassenvariable abgebrochen
	
	//die Hilfsmethode zum Einlesen der Daten über den 
	//Dialog
	//Beschreibung siehe oben
	private static String lesen(String text) throws AbbruchException {
		String eingabeTemp;
		//bitte in einer Zeile eingeben
		eingabeTemp = JOptionPane.showInputDialog("Bitte geben Sie einen " + text + " Wert ein:");
		
		//Wichtig! abgebrochen muss wieder auf false 
		//gesetzt werden, wenn eine Eingabe erfolgt ist

		if (eingabeTemp == null) {
			throw new AbbruchException("Die Eingabe für " + text + " wurde abgebrochen");
		}
		
		return (eingabeTemp);
	}
	
	//die überladene Methode einlesen() für den Typ int
	//Beschreibung siehe oben
	
	public static int einlesen(int wert) throws AbbruchException {
		int wertTemp = 0;
		boolean gelungen = false;
		String eingabeTemp;
		while (gelungen == false) {
			//die Eingabe lesen
			eingabeTemp = lesen("int");
			//wenn nicht auf Abbrechen geklickt wurde, 
			//wird versucht die Eingabe in den Typ int 
			//umzuwandeln

				try {
					//bitte in einer Zeile eingeben
					wertTemp = Integer.parseInt(eingabeTemp);
					gelungen = true;
				}
				catch (NumberFormatException e) {
					//bitte in einer Zeile eingeben
					//eine Ausgabe über einen
		 			//grafischen Dialog
					JOptionPane.showMessageDialog(null,"Ihre Eingabe war nicht gültig. Bitte wiederholen...");
				}
			
			//wenn die Eingabe abgebrochen wurde, wird die 
			//Schleife direkt wieder beendet

		}
		//den Wert zurückliefern
		//wenn die Eingabe abgebrochen wurde, wird 0 
		//geliefert
		return wertTemp;
	}
	
	//die überladene Methode einlesen() für den Typ double
	//Beschreibung siehe oben bzw. bei der Methode für den 
	//int-Typ
	public static double einlesen(double wert) throws AbbruchException {
		double wertTemp = 0;
		boolean gelungen = false;
		String eingabeTemp;
		while (gelungen == false) {
			eingabeTemp = lesen("double");
				try {
					//bitte in einer Zeile eingeben
					wertTemp = Double.parseDouble(eingabeTemp);
					gelungen = true;
				}
				catch (NumberFormatException e) {
					//bitte in einer Zeile eingeben
					//eine Ausgabe über einen
		 			//grafischen Dialog
					JOptionPane.showMessageDialog(null,"Ihre Eingabe war nicht gültig. Bitte wiederholen...");
				}
			}

		
		return wertTemp;
	}
	
	//die überladene Methode einlesen() für den Typ float
	//Beschreibung siehe oben bzw. bei der Methode für den 
	//int-Typ
	public static float einlesen(float wert) throws AbbruchException {
		float wertTemp = 0F;
		boolean gelungen = false;
		String eingabeTemp;
		while (gelungen == false) {
			eingabeTemp = lesen("float");

				try {
					//bitte in einer Zeile eingeben
					wertTemp = Float.parseFloat(eingabeTemp);
					gelungen = true;
				}
				catch (NumberFormatException e) {
					//bitte in einer Zeile eingeben
					//eine Ausgabe über einen
		 			//grafischen Dialog
					JOptionPane.showMessageDialog(null,"Ihre Eingabe war nicht gültig. Bitte wiederholen...");
				}
			}

		return wertTemp;
	}
}
