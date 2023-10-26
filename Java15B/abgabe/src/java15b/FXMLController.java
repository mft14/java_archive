package java15b;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Karim Kiel
 * 03/10/2020
 * Java15B Einsendaufgabe
 * 15B-XX1-K02
 * 
 * Die einzige Änderung für Aufgabe 2 befindet sich in neuesWort() 
 * und ist mit //############## KK2: markiert
 */

public class FXMLController {
    //das Kombinationsfeld
	@FXML private ComboBox<String> auswahl;
	//die Labels für die Ausgaben
    @FXML private Label ausgabeText;
    @FXML private Label anzVersuche;	
    @FXML private Label punktAusgabe;
    //für die Zeichenfläche
    @FXML private Canvas zeichenflaeche;    
	
    //ein Array mit Zeichenketten(!) für die Buchstaben
  	private String [] zeichen = new String[26];
  	//ein StringBuilder für die Darstellung des Suchwortes
  	private StringBuilder anzeige;
  	//ein String für das gesuchte Wort im Klartext
  	private String suchwort;
  	//für die verbleibenden Durchläufe
  	private int restDurchlauefe;
  	//für die Anzahl der Fehler
  	private int fehler;
  	//für den Grafikkontext
  	private GraphicsContext gc;
  	//für die Punkte
  	private Score spielpunkte;
  	//für die Bühne
  	private Stage meineStage;
  	

	//die Methode zur Auswahl aus dem Kombinationsfeld
	@FXML protected void auswahlNeu(ActionEvent event) {
		//der aktuell ausgewählte Eintrag wird übergeben und ausgewertet
		pruefen(auswahl.getSelectionModel().getSelectedItem().toString());
		//ist das Spiel zu Ende oder nicht?
		gewinnerOderNicht();
	}

	//die Methode zum Beenden
	@FXML protected void beendenKlick(ActionEvent event) {
		Platform.exit();
	}
	
	//die Methode setzt die Initialwerte
	//sie wird automatisch ausgeführt
    @FXML void initialize() {
		int tempIndex = 0;
		//es geht los mit 9 verbleibenden Durchläufe
		restDurchlauefe = 9;
		//die restlichen Durchläufe anzeigen
		anzVersuche.setText(Integer.toString(restDurchlauefe));
		
		//die Liste für das Kombinationsfeld füllen
		for (char temp = 'a'; temp <= 'z'; temp++) {
			zeichen[tempIndex] = Character.toString(temp);
			tempIndex++;
		}
		
		auswahl.getItems().addAll(zeichen);
		//ein Wort ermitteln
		neuesWort();    	
    	
		//den Grafikkontext beschaffen
		gc = zeichenflaeche.getGraphicsContext2D();
		spielpunkte = new Score(meineStage);
    }	
    
	//die Methode ermittelt zufällig ein Wort
	private void neuesWort() {
		
		//############## KK2: Hier beginnt Aufgabe 2
		String woerterDatei = ""; //Platzhalter für die neuen Wörter

		try { //Aus Datei lesen und in String schreiben mit RandomAccessFile
			RandomAccessFile leseDatei = new RandomAccessFile("hangmanwoerter.txt", "r");
			woerterDatei = leseDatei.readLine(); //Datei lesen und in String schreiben
		
			if(woerterDatei == null || woerterDatei.length() == 0) { //Wenn String leer, Dialog öffnen und hinweisen
		        Alert alert = new Alert(AlertType.WARNING);
		        alert.setTitle("Warnung");
		        alert.setHeaderText("Keine Wörter gefunden");
		        alert.setContentText("Es wurden keine Wörter in der \"hangmanwoerter.txt\" Datei gefunden. "
		        		+ "Bitte erstellen Sie eine neue Wörterliste mit dem HangmanEditor und starten sie das Spiel anschließend neu! \n\n"
		        		+ "Der Editor startet mit Klick auf OK!"); //Nutzer wird drauf hingewiesen, dass keine Wörter in der Datei sind
		        alert.showAndWait();
		        
		        woerterDatei = ","; //den String temporär füllen, sonst Fehler (Null)
		        Platform.exit(); //Hangmanspiel wieder schließen
		        new HangmanEditor("HangmanEditor"); //HangmanEditor automatisch öffnen
			}
			
			leseDatei.close(); //Ohne richtiges Schließen kann es zu Fehlern kommen
		} catch (FileNotFoundException e) { //Exception, wenn Datei nicht gefunden
			System.out.println("Datei \"hangmanwoerter.txt\" wurde nicht gefunden.");
		} catch (IOException e) { //Exception für I/O Fehler
			System.out.println("Es gab ein Fehler beim Lesen der Datei");
		}
		
		//Array erstellen und mit Anzahl der Wörter füllen
		String[] woerter = new String[Integer.parseInt(woerterDatei.replaceAll("[a-zA-Z-,]",""))]; 
		//StringBuilder Objekt für Stringverarbeitung erstellen
		StringBuilder wortBuilder = new StringBuilder(woerterDatei);
		
		for (int i = 0; i < woerter.length; i++) {//Wiederhole so oft wie lang das Array ist
			woerter[i] = (  wortBuilder.substring(0, wortBuilder.indexOf(",")) ); //Wort rauspicken und ins Array packen
			wortBuilder.delete(0, wortBuilder.indexOf(",") + 1);  //Wort samt Komma rauslöschen
		}
		
		//Zeigt in Konsole, welche Wörter möglich sind
		for (int i = 0; i < woerter.length; i++) { System.out.println("Mögliche Wörter: " + woerter[i]);}
		
		//############## KK2: Hier endet Aufgabe 2
		
		int zufall = 0;
		//ein zufälliges Wort ermitteln
		//dazu wird eine zufällige Zahl zwischen 0 und 1 ermitteln und mit der Länge von woerter multipliziert
		zufall = (int)(Math.random() * woerter.length);
		
		//das Suchwort und die Zeichen für die Anzeige setzen
		suchwort = new String(woerter[zufall]);
		anzeige = new StringBuilder(suchwort);
		
		//alle Zeichen in der Anzeige ersetzen durch *
		for (int zeichen = 0; zeichen < suchwort.length(); zeichen++)
			anzeige.setCharAt(zeichen, '*');

		//die Sternchen anzeigen
		ausgabeText.setText(anzeige.toString());
	}
    
	//die Methode zum Prüfen
	private void pruefen(Object auswahlZeichen) {
		char zeichen;
		int treffer = 0;
		//das ausgewählte Zeichen aus dem Kombinationsfeld wieder umbauen 
		zeichen = auswahlZeichen.toString().charAt(0);
		//gibt es das Zeichen auch im Suchwort?
		//dabei vergleichen wir nur die Kleinbuchstaben
		treffer = suchwort.toLowerCase().indexOf(zeichen);
		//wenn wir nichts gefunden haben
		if (treffer < 0) {
			//1 von den verbleibenden Durchläufen abziehen
			restDurchlauefe--;
			//die restlichen Durchläufe anzeigen
			anzVersuche.setText(Integer.toString(restDurchlauefe));
			//die Fehler für die Anzeige erhöhen und den Galgen zeichnen
			erhoeheFehler();
			//einen Punkt abziehen
			punktAusgabe.setText(Integer.toString(spielpunkte.veraenderePunkte(-1)));
		}
		else {
			//nach weiteren Vorkommen suchen
			while (treffer >= 0) {
				//das Zeichen aus der entsprechenden Position im Suchwort anzeigen
				anzeige.setCharAt(treffer, suchwort.charAt(treffer));
				//treffer erhöhen und dann weitersuchen
				treffer++;
				treffer = suchwort.toLowerCase().indexOf(zeichen,treffer);
				//Punkte erhöhen
				punktAusgabe.setText(Integer.toString(spielpunkte.veraenderePunkte(5)));
			}
			//das geänderte Wort anzeigen
			ausgabeText.setText(anzeige.toString());
		}
	}
	
	private void gewinnerOderNicht() {
		//ende steuert, ob das Spiel zu Ende ist
		//nur dann wird die Liste geprüft und die Anwendung
		//geschlossen
		boolean ende = false;
		//die Linienbreite auf 1 setzen
		gc.setLineWidth(1);
		//ist das Spiel zu Ende?
		if (restDurchlauefe == 0) {
			gc.strokeText("Das gesuchte Wort war: " + suchwort, 20, 100);
			ende = true;
		}
		//ist das Wort erraten worden?
		if (anzeige.toString().equals(suchwort)) {
			//pro verbleibendem Durchlauf gibt es noch zehn Punkte extra
			spielpunkte.veraenderePunkte(restDurchlauefe * 10);
			gc.strokeText("Hurra! Sie haben gewonnen!", 20, 100);
			ende = true;
		}
		if (ende == true) {
			//hat es für einen neuen Eintrag in der Bestenliste gereicht?
			if (spielpunkte.neuerEintrag() == true)
				spielpunkte.listeZeigen();
			Platform.exit();
		}
	}
	
	//Fehler hochzählen und den Galgen zeichnen
	private void erhoeheFehler() {
		fehler = fehler + 1;
		gc.setLineWidth(4);
		
		//je nach Wert von fehler zeichnen
		switch (fehler) {
		case 1:
			gc.strokeLine(10,10,10,200);
			break;
		case 2:
			gc.strokeLine(10,10,100,10);
			break;
		case 3:
			gc.strokeLine(40,10,10,40);
			break;
		case 4:
			gc.strokeLine(100,10,100,50);
			break;
		case 5:
			gc.strokeLine(70,50,130,50);
			break;
		case 6:
			gc.strokeLine(130,50,130,110);
			break;
		case 7:
			gc.strokeLine(130,110,70,110);
			break;
		case 8:
			gc.strokeLine(70,110,70,50);
			break;
		case 9:
			gc.strokeLine(0,200,20,200);
			break;
		}
	}
	
    //die Methode setzt die Bühne auf den übergebenen Wert
	public void setStage(Stage meineStage) {
		this.meineStage = meineStage;
		spielpunkte = new Score(meineStage);
	}
}
