package java14;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class FXMLController {
    //das Kombinationsfeld
	@FXML private ComboBox<String> auswahl;
	//die Labels f�r die Ausgaben
    @FXML private Label ausgabeText;
    @FXML private Label anzVersuche;	
    //f�r die Zeichenfl�che
    @FXML private Canvas zeichenflaeche;    
	
    //ein Array mit Zeichenketten(!) f�r die Buchstaben
  	private String [] zeichen = new String[26];
  	//ein StringBuilder f�r die Darstellung des Suchwortes
  	private StringBuilder anzeige;
  	//ein String f�r das gesuchte Wort im Klartext
  	private String suchwort;
  	//f�r die verbleibenden Durchl�ufe
  	private int restDurchlauefe;
  	//f�r die Anzahl der Fehler
  	private int fehler;
  	//f�r den Grafikkontext
  	private GraphicsContext gc;

	//die Methode zur Auswahl aus dem Kombinationsfeld
	@FXML protected void auswahlNeu(ActionEvent event) {
		//der aktuell ausgew�hlte Eintrag wird �bergeben und ausgewertet
		pruefen(auswahl.getSelectionModel().getSelectedItem());
		//ist das Spiel zu Ende oder nicht?
		gewinnerOderNicht();
	}

	//die Methode zum Beenden
	@FXML protected void beendenKlick(ActionEvent event) {
		Platform.exit();
	}
	
	//die Methode setzt die Initialwerte
	//sie wird automatisch ausgef�hrt
    @FXML void initialize() {
		int tempIndex = 0;
		//es geht los mit 9 verbleibenden Durchl�ufe
		restDurchlauefe = 9;
		//die restlichen Durchl�ufe anzeigen
		anzVersuche.setText(Integer.toString(restDurchlauefe));
		
		//die Liste f�r das Kombinationsfeld f�llen
		for (char temp = 'a'; temp <= 'z'; temp++) {
			zeichen[tempIndex] = Character.toString(temp);
			tempIndex++;
		}
		
		auswahl.getItems().addAll(zeichen);
		//ein Wort ermitteln
		neuesWort();    	
    	
		//den Grafikkontext beschaffen
		gc = zeichenflaeche.getGraphicsContext2D();
    }	
    
	//die Methode ermittelt zuf�llig ein Wort
	private void neuesWort() {
		int zufall = 0;
		//die Wortliste
		String[] woerter ={"Test", "Automobil", "Versuch", "Hund", "Katze",
				"Ziege", "Maus", "Elefant", "Isopropylalkohol", "Schwimmbad"};
		//ein zuf�lliges Wort ermitteln
		//dazu wird eine zuf�llige Zahl zwischen 0 und 1 ermitteln und mit der L�nge von woerter multipliziert
		zufall = (int)(Math.random() * woerter.length);
		
		//das Suchwort und die Zeichen f�r die Anzeige setzen
		suchwort = new String(woerter[zufall]);
		anzeige = new StringBuilder(suchwort);
		
		//alle Zeichen in der Anzeige ersetzen durch *
		for (int zeichen = 0; zeichen < suchwort.length(); zeichen++)
			anzeige.setCharAt(zeichen, '*');

		//die Sternchen anzeigen
		ausgabeText.setText(anzeige.toString());
	}
    
	//die Methode zum Pr�fen
	private void pruefen(String auswahlZeichen) {
		char zeichen;
		int treffer = 0;
		//das ausgew�hlte Zeichen aus dem Kombinationsfeld umbauen 
		zeichen = auswahlZeichen.charAt(0);
		//gibt es das Zeichen auch im Suchwort?
		//dabei vergleichen wir nur die Kleinbuchstaben
		treffer = suchwort.toLowerCase().indexOf(zeichen);
		//wenn wir nichts gefunden haben
		if (treffer < 0) {
			//1 von den verbleibenden Durchl�ufen abziehen
			restDurchlauefe--;
			//die restlichen Durchl�ufe anzeigen
			anzVersuche.setText(Integer.toString(restDurchlauefe));
			//die Fehler f�r die Anzeige erh�hen und den Galgen zeichnen
			erhoeheFehler();
		}
		else {
			//nach weiteren Vorkommen suchen
			while (treffer >= 0) {
				//das Zeichen aus der entsprechenden Position im Suchwort anzeigen
				anzeige.setCharAt(treffer, suchwort.charAt(treffer));
				//treffer erh�hen und dann weitersuchen
				treffer++;
				treffer = suchwort.toLowerCase().indexOf(zeichen,treffer);
			}
			//das ge�nderte Wort anzeigen
			ausgabeText.setText(anzeige.toString());
		}
	}
	
	private void gewinnerOderNicht() {
		//die Linienbreite auf 1 setzen
		gc.setLineWidth(1);
		//ist das Spiel zu Ende?
		if (restDurchlauefe == 0) {
			gc.strokeText("Das gesuchte Wort war: " + suchwort, 20, 100);
			Platform.exit();
		}
		//ist das Wort erraten worden?
		if (anzeige.toString().equals(suchwort)) {
			gc.strokeText("Hurra! Sie haben gewonnen!", 20, 100);
			Platform.exit();
		}
	}
	
	//Fehler hochz�hlen und den Galgen zeichnen
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
}
