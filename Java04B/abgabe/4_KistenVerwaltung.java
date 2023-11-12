/* ######################################################
 * Einsendeaufgabe 4
 * ###################################################### */
//Name: Karim Kiel
//Datum 28/11/2019
// JAVA 4B-XX1-N01

//Lagerverwaltung

/*
 * Zusätzliche Erklärungen:
 * 
 * kisten[][] habe ich ein zweidimensionales Array gegeben. In der 
 * ersten Dimension bekommen die Kisten ihre zugehörige ID.
 * In der zweiten Dimension stecken 4 Index Werte drin:
 * 0 = ist die Kiste voll, dann 1, sonst 0
 * 1 = Länge
 * 2 = Breite
 * 3 = Höhe
 * ich habe für die Maße auch int genommen, weil ein double innerhalb
 * eines Arrays nicht möglich ist (glaube ich)
 * 
 * Alle Methoden erhalten im Parameter das Array kisten vom Typ int[][], sodass diese lokal bearbeitet und 
 * wieder übergeben werden. So spart man sich die Klassenvariable. Rückgabewert ist überall void
 * 
 * Löschen/Ändern/Bearbeiten/Erstellen wird am Anfang 
 * direkt mit dem Maximum von 75 abgefangen. 0 oder 76 wird 
 * erst gar nicht abgefragt
 * 
 * 
 * kistenErstellen() fragt nach einer ID, nur im gültigen Bereich oder ob schon vorhanden.
 * Dann wird erstellt. Die 2 While Schleifen mit Try/Catch sorgen dafür, dass
 * nicht eine Kiste erstellt wird mit einer Maße von 0x0x0
 * 
 * kistenLoeschen() prüft, ob ID vorhanden, dann setzt es alle Werte von Array "kisten"
 * auf 0
 * 
 * kistenBearbeiten() prüft, ob ID vorhanden, wenn ja dann wird jeder Wert abgefragt, dem 
 * man ändern möchte. Zur besseren Übersicht wird der alte Wert immer oben angezeigt
 * 
 * kistenAnzeigen() fragt nach einer ID, wenn diese vorhanden, wird sie angezeigt
 * 
 * kistenAuflisten() zeigt alle Kisten an. Mit einer for schleife werden alle
 * Kisten mit ihren Maßangaben durchgegeben aber nur, wenn diese vorhanden ist
 * kisten[ID][0] == 1  || Wenn der zweite Index bei 0 gleich 1 ist, ist die Kiste vorhanden
 * 
 * */
import javax.swing.JOptionPane;

public class Java04b_einsend01 {

	//Legt die Konstante der maximalen Kisten fest
	static final int ANZAHLKISTEN = 75;
	
	//////KISTE ERSTELLEN
	public static void kistenErstellen(int[][] eingabe) {
		
		
		boolean istAmLaufen = true;
		
		while (istAmLaufen) { //wiederholt solange, bis man richtige Werte eingibt, sonst erstellt man Kisten ohne Maßangaben
			
			try { //Try und catch fängt falsche Werte ab
				
				int kistenID = 0; //Speichert eingegebene Kisten ID vom Benutzer
				kistenID = Integer.parseInt(JOptionPane.showInputDialog(""
						+ "Geben Sie ihrer Kiste eine ID: \n"
						+ "(Erlaubt ist von 1 - " + ANZAHLKISTEN));
				
				if (kistenID >= 1 && kistenID <= ANZAHLKISTEN ) { //Prüfe, ob Kisten ID im gültigen Bereich
					if (eingabe[kistenID-1][0] == 1) { //Prüfen, ob Kiste bereits vorhanden
						System.out.println("Eine Kiste mit der ID : " + kistenID + " gibt es schon\n\n");
					}
					 else { //Erstellen der Kiste, wenn alles richtig ist
						eingabe[kistenID-1][0] = 1;

						while (istAmLaufen) {
							try { // fängt falsche Werte ab
								eingabe[kistenID-1][1] = Integer.parseInt(JOptionPane.showInputDialog("Kistenlänge:"));
								eingabe[kistenID-1][2] = Integer.parseInt(JOptionPane.showInputDialog("Kistenbreite:"));
								eingabe[kistenID-1][3] = Integer.parseInt(JOptionPane.showInputDialog("Kistenhöhe:"));
								System.out.print("Kiste ID" + kistenID + " erfolgreich erstellt. --->");
								System.out.print(" L:" + eingabe[kistenID-1][1]);
								System.out.print(" B:" + eingabe[kistenID-1][2]);
								System.out.print(" H:" + eingabe[kistenID-1][3] +"\n\n");
								istAmLaufen = false;
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "Bitte nur Ganzzahlen bei der Maße eingeben!");	
							}
						} //zweite while End
						//eine weitere While Schleife beugt vor, dass eine Kiste erstellt mit der Maße 0x0x0 wird

					}
				} else { // Kiste nicht im gültigen Bereich
					System.out.println("Ungültig. Erlaubt sind nur ID's von 1 - " + ANZAHLKISTEN+"\n\n");
				}
				
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Bitte nur Ganzzahlen eingeben!");
			}
			
		} // while End
				
	}
	
	////// KISTEN LÖSCHEN
	public static void kistenLoeschen(int[][] eingabe) {
		
		try {
			
			int sucheID;
			sucheID = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie die Kisten ID an, um diese zu löschen: "));

			if (sucheID >= 1 && sucheID <= ANZAHLKISTEN ) { //Prüfe, ob Kisten ID im gültigen Bereich
				
				if(eingabe[sucheID-1][0] == 1) { //setze alles auf Null, auch die "istVoll" Variable 
					eingabe[sucheID-1][0] = 0; // <----- istVoll Platzhalter
					eingabe[sucheID-1][1] = 0;
					eingabe[sucheID-1][2] = 0;
					eingabe[sucheID-1][3] = 0;
					System.out.println("Die Kiste ID" + sucheID + " wurde gelöscht.\n\n");
					
				} else if (eingabe[sucheID-1][0] == 0) { // Kiste nicht verfügbar
					System.out.println("Diese Kiste gibt es leider nicht.\n\n");
				}
				
			} else { // Kiste nicht im gültigen Bereich
				System.out.println("Ungültig. Erlaubt sind nur ID's von 1 - " + ANZAHLKISTEN+"\n\n");
			}
			
		} catch (NumberFormatException e) { //falsche Eingabe
			JOptionPane.showMessageDialog(null, "Bitte nur Ganzzahlen eingeben!");
		}
		
	}
	////// KISTEN BEARBEITEN
	public static void kistenBearbeiten(int[][] eingabe) {
		
		boolean istAmLaufen = true; //für die while Schleife, um leere Eingaben auszuschließen
		
		while (istAmLaufen) {
			try {
				
				int sucheID;
				sucheID = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie die Kisten ID an, um diese zu bearbeiten: \n\n(0 = Abbruch)"));
				
				if (sucheID == 0) { //Falls der Benutzer rausmöchte
					istAmLaufen = false;
					System.out.println("Bearbeitung abgebrochen\n");
				} else {
					if (sucheID >= 1 && sucheID <= ANZAHLKISTEN ) { //Prüfe, ob Kisten ID im gültigen Bereich 
						
						if (eingabe[sucheID-1][0] == 1) {
							eingabe[sucheID-1][1] = Integer.parseInt(JOptionPane.showInputDialog("Alte Länge: " + eingabe[sucheID-1][1] 
																								+ "\n\nNeue Kistenlänge:"));
							eingabe[sucheID-1][2] = Integer.parseInt(JOptionPane.showInputDialog("Alte Breite: " + eingabe[sucheID-1][2] 
									+ "\n\nNeue Kistenbreite:"));
							eingabe[sucheID-1][3] = Integer.parseInt(JOptionPane.showInputDialog("Alte Höhe: " + eingabe[sucheID-1][3] 
									+ "\n\nNeue Kistenhöhe:"));
							istAmLaufen = false; //raus aus der While
							System.out.println("Die Kiste ID" + sucheID + " wurde erfolgreich geändert!\n\n");
						} else if (eingabe[sucheID-1][0] == 0) { // Kiste nicht verfügbar
							System.out.println("Diese Kiste gibt es leider nicht.\n\n");
						}
						
					} else { // Kiste nicht im gültigen Bereich
						System.out.println("Ungültig. Erlaubt sind nur ID's von 1 - " + ANZAHLKISTEN+"\n\n");
					}
				}			
				
			} catch (NumberFormatException e) { // falls falsche Angaben
				JOptionPane.showMessageDialog(null, "Bitte nur Ganzzahlen eingeben!");
			}
		}
			
	}

	////// KISTE ANZEIGEN
	public static void kistenAnzeigen(int[][] eingabe) {
		
		try { 

			int sucheID;
			sucheID = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie die Kisten ID an, um diese anzuzeigen: "));
			
			if (sucheID >= 1 && sucheID <= ANZAHLKISTEN ) { //Prüfe, ob Kisten ID im gültigen Bereich 

				if(eingabe[sucheID-1][0] == 1) { //Zeige Liste an
					
					System.out.println("Kiste ID" + sucheID + " anzeigen: ");
					System.out.print(" Länge:" + eingabe[sucheID-1][1]);
					System.out.print(" Breite:" + eingabe[sucheID-1][2]);
					System.out.print(" Höhe:" + eingabe[sucheID-1][3] + "\n\n");
					
				} else if (eingabe[sucheID-1][0] == 0) { // Kiste nicht verfügbar
					System.out.println("Diese Kiste gibt es leider nicht.\n\n");
				}
				
			} else { // Kiste nicht im gültigen Bereich
				System.out.println("Ungültig. Erlaubt sind nur ID's von 1 - " + ANZAHLKISTEN+"\n\n");
			}
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Bitte nur Ganzzahlen eingeben!");
		}

	}

	////// KISTE AUFLISTEN
	public static void kistenAuflisten(int[][] eingabe) {
		
		int counter = 0;
		System.out.println("Alle verfügbaren Kisten werden nun angezeigt:");
		System.out.println("-----------------------------");
		
		for (int i = 0; i < ANZAHLKISTEN; i++) { //zeige alle Kisten ab, die vorhanden sind
			
			if (eingabe[i][0] == 1) { //wenn [][0] == 1, dann ist die Kiste vorhanden
				System.out.println("Kiste ID" + (i+1) + " anzeigen: ");
				System.out.print(" Länge:" + eingabe[i][1]);
				System.out.print(" Breite:" + eingabe[i][2]);
				System.out.print(" Höhe:" + eingabe[i][3] + "\n\n");
				counter++;
			}
		}
		if (counter == 0) {
			System.out.println("Keine Kisten gefunden...\n\n");
		} else {
			System.out.println("Anzahl der Kisten: " + counter + "\n");			
		}

	}

	//Programmablauf
	public static void main(String[] args) {

		boolean istAmLaufen = true; //für die while Schleife zum 
		String eingabe = ""; //Benutzereingabe als String
		char auswahl; //Eingabe wird zu char gewandelt für die Switch Abfrage
		int[][] kisten = new int[ANZAHLKISTEN][4]; //die Konstante legt max. Kisten fest, die 4 steht für Maßangaben und "istVoll" als 0 oder 1

		while (istAmLaufen) { //solang in der Schleife, läuft das Programm

			// Eingabe fragt ab, was man tun möchte
			eingabe = JOptionPane
					.showInputDialog("Lagerverwaltung! Was möchten Sie tun? Tippen sie ein: \n\n"
							+ "E = Erstellen einer neuen Kiste\n"
							+ "B = Bearbeiten einer Kiste\n"
							+ "A = Anzeigen einer Kiste\n"
							+ "L = Liste aller erstellten Kisten\n"
							+ "D = Löschen einer Kiste\n\n"
							+ "X = Beenden des Programmes");

			if (eingabe == null) { //wenn man Abbrechen klickt, beendet das Programm
				istAmLaufen = false;
			} else {
			
			eingabe = eingabe.toLowerCase(); //Buchstaben immer verkleinern
			auswahl = eingabe.charAt(0); //das char aus Eingabe rausfiltern
			
			switch (auswahl) {
			case 'e': // Erstellen
				kistenErstellen(kisten);
				break;
			case 'd': // Löschen
				kistenLoeschen(kisten);
				break;
			case 'b': // Bearbeiten
				kistenBearbeiten(kisten);
				break;
			case 'a':// Anzeigen
				kistenAnzeigen(kisten);
				break;
			case 'l': // Auflisten
				kistenAuflisten(kisten);
				break;
			case 'x': // Beenden
				istAmLaufen = false;
				break;
			default:
				System.out.println("Ungültige Eingabe.");
				break;
			}
			
			}

		} // while End, Programm wird beendet

		System.out.println("Programm beendet!");
		System.exit(0);

	}

}
