/*************************************************************
 * Eine Klasse f�r Eingaben �ber einen grafischen Dialog
 * ***********************************************************
 * Das Einlesen erfolgt �ber eine �berladene statische
 * Methode einlesen()
 * Die Klassenvariable abgebrochen speichert, ob die 
 * Eingabe �ber die Schaltfl�che Abbrechen abgebrochen
 * ***********************************************************
 * Beschreibung der Methode einlesen()
 * 
 * Die Methode liest �ber die Methode lesen() einen Wert
 * ein und versucht, diesen Wert in den passenden Typ
 * umzuwandeln. Die Eingabe wird so lange wiederholt,
 * bis ein g�ltiger Typ eingegeben wurde bzw. auf die
 * Schaltfl�che Abbrechen geklickt wurde.
 * 
 * Parameter: 	wert f�r den Typ, der verarbeitet werden soll
 * R�ckgabe: 	Der Wert, der umgewandelt wurde
 * 				Der Typ h�ngt vom �bergebenen Parameter ab
 * 
 * Die Methode ist drei Mal vorhanden (f�r int, float und 
 * double)
 * **************************************************************
 * Beschreibung der Methode lesen()
 * 
 * Die Methode wird zum Einlesen der Werte �ber den Dialog 
 * benutzt.
 * Sie setzt au�erdem die Klassenvariable abgebrochen.
 * 
 * Parameter:	text als Zeichenkette f�r die Ausgabe im 
 * 				Dialog
 * R�ckgabe:	Der eingelesene Wert als Zeichenkette
 * ***********************************************************
 * */


//f�r den Eingabedialog
import javax.swing.JOptionPane;

public class EingabeDialog {
	//die Klassenvariable abgebrochen
	
	//die Hilfsmethode zum Einlesen der Daten �ber den 
	//Dialog
	//Beschreibung siehe oben
	private static String lesen(String text) throws AbbruchException {
		String eingabeTemp;
		//bitte in einer Zeile eingeben
		eingabeTemp = JOptionPane.showInputDialog("Bitte geben Sie einen " + text + " Wert ein:");
		
		//Wichtig! abgebrochen muss wieder auf false 
		//gesetzt werden, wenn eine Eingabe erfolgt ist

		if (eingabeTemp == null) {
			throw new AbbruchException("Die Eingabe f�r " + text + " wurde abgebrochen");
		}
		
		return (eingabeTemp);
	}
	
	//die �berladene Methode einlesen() f�r den Typ int
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
					//eine Ausgabe �ber einen
		 			//grafischen Dialog
					JOptionPane.showMessageDialog(null,"Ihre Eingabe war nicht g�ltig. Bitte wiederholen...");
				}
			
			//wenn die Eingabe abgebrochen wurde, wird die 
			//Schleife direkt wieder beendet

		}
		//den Wert zur�ckliefern
		//wenn die Eingabe abgebrochen wurde, wird 0 
		//geliefert
		return wertTemp;
	}
	
	//die �berladene Methode einlesen() f�r den Typ double
	//Beschreibung siehe oben bzw. bei der Methode f�r den 
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
					//eine Ausgabe �ber einen
		 			//grafischen Dialog
					JOptionPane.showMessageDialog(null,"Ihre Eingabe war nicht g�ltig. Bitte wiederholen...");
				}
			}

		
		return wertTemp;
	}
	
	//die �berladene Methode einlesen() f�r den Typ float
	//Beschreibung siehe oben bzw. bei der Methode f�r den 
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
					//eine Ausgabe �ber einen
		 			//grafischen Dialog
					JOptionPane.showMessageDialog(null,"Ihre Eingabe war nicht g�ltig. Bitte wiederholen...");
				}
			}

		return wertTemp;
	}
}
