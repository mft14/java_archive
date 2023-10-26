package memoryspiel;


import java.util.Arrays;
import java.util.Collections;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/*
 * Karim Kiel
 * Java13B Einsendaufgabe
 * Code: Java 13B-XX1-N01
 * �nderung von mir mit KK(1,2 oder 3) markiert
 */

public class MemoryFeld {
	
	//KK3: innere Klasse f�r den Button EventHandler
	class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			wirdGeschummelt = true;
			btnSchummeln.setDisable(true);//KK3: Wenn im Schummelvorgang, ist der Button erstmal aus 
			
			for (int i = 0; i < karten.length; i++) { //KK3: Alle Karten mit 'ner for Schleife
				if(karten[i].isNochImSpiel() == true) {//aber nur Karten zeigen, die noch im Spiel sind
					karten[i].vorderseiteZeigen();
				}
			}
			//KK3: Man hat zwei Sekunden Zeit, die offenen Karten anzuschauen.
			timer = new Timeline(new KeyFrame(Duration.millis(2000), new TimerHandler()));
			timer.play();
		}
	}
	
	//eine innere Klasse f�r den Eventhandler des Timer
	class TimerHandler implements EventHandler<ActionEvent> {
			@Override
			public void handle(ActionEvent arg0) {
				if(wirdGeschummelt == true) { //KK3: Wenn geschummelt wird, landet man hier
					
					for (int i = 0; i < karten.length; i++) { //KK3: Alle Karten mit 'ner for Schleife
						if(karten[i].isNochImSpiel() == true) {//nur Karten umdrehen, die noch im Spiel sind
							karten[i].rueckseiteZeigen(false);//Alle Karten wieder umdrehen
						}
					}
					wirdGeschummelt = false; //KK3: Schummeln vorbei
					btnSchummeln.setDisable(false);//KK3: Wenn Schummeln vorbei, wieder dr�ckbar
					
				} else { //Wenn nicht geschummelt wird, werden ganz normal Karten umgedreht
					karteSchliessen();
					//KK3: Hat Spieler ein Paar gefunden, muss der 
					//Button auf wieder klickbar sein, ansonsten
					//bleibt er aus
					if (spieler == 0) btnSchummeln.setDisable(false);
				}
			}
		}
	
	//KK3: Zustand, ob grad geschummelt wird
	private boolean wirdGeschummelt = false;

	//das Array f�r die Karten
	private MemoryKarte[] karten;
	
	//das Array f�r die Namen der Grafiken
	private String[] bilder = {"grafiken/apfel.jpg", "grafiken/birne.jpg", "grafiken/blume.jpg", "grafiken/blume2.jpg",
			"grafiken/ente.jpg", "grafiken/fisch.jpg", "grafiken/fuchs.jpg", "grafiken/igel.jpg",
			"grafiken/kaenguruh.jpg", "grafiken/katze.jpg", "grafiken/kuh.jpg", "grafiken/maus1.jpg",
			"grafiken/maus2.jpg", "grafiken/maus3.jpg", "grafiken/melone.jpg", "grafiken/pilz.jpg",
			"grafiken/ronny.jpg", "grafiken/schmetterling.jpg","grafiken/sonne.jpg",
			"grafiken/wolke.jpg", "grafiken/maus4.jpg"};
	
	//f�r die Punkte
	private int menschPunkte, computerPunkte;
	//zwei Labels f�r die Punkte
	//KK2: Einmal ein Label f�r die Anzeige & boolean, wer am Zuge ist
	private Label menschPunkteLabel, computerPunkteLabel, zugLabel;
	
	//KK3: Schummel Button
	private Button btnSchummeln;
	
	//wie viele Karten sind aktuell umgedreht?
	private int umgedrehteKarten;
	
	//f�r das aktuell umdrehte Paar
	private MemoryKarte[] paar;
	
	//f�r den aktuellen Spieler
	private int spieler;
	
	//das "Ged�chtnis" f�r den Computer
	//er speichert hier wo das Gegenst�ck liegt
	private int[][] gemerkteKarten;
	
	//f�r die Spielst�rke
	private int spielstaerke;
	
	//f�r den Timer
	private Timeline timer;
	
	//der Konstruktor
	public MemoryFeld() {
		//das Array f�r die Karten erstellen, insgesamt 42 St�ck
		karten = new MemoryKarte[42];

		//f�r das Paar
		paar = new MemoryKarte[2];

		//f�r das Ged�chtnis
		//es speichert f�r jede Karte paarweise die Position im Spielfeld
		gemerkteKarten = new int[2][21];
		
		//keiner hat zu Beginn einen Punkt
		menschPunkte = 0;
		computerPunkte = 0;
		
		//es ist keine Karte umgedreht
		umgedrehteKarten = 0;
		
		//der Mensch f�ngt an
		spieler = 0;
		
		//die Spielst�rke ist 10
		spielstaerke = 10;
		
		//es gibt keine gemerkten Karten
		for (int aussen = 0; aussen < 2; aussen++)
			for (int innen = 0; innen < 21; innen++)
				gemerkteKarten[aussen][innen] = -1;
	}

	//die Methode erstellt die Oberfl�che und zeichnet die Karten �ber eine eigene Methode
	//�bergeben wird ein FlowPane
	public FlowPane initGUI(FlowPane feld) {
		//f�r die Ausgaben
		kartenZeichnen(feld);
		menschPunkteLabel = new Label();
		computerPunkteLabel = new Label();
		zugLabel = new Label("Es zieht: Der Mensch"); //KK2: Zuweisung
		
		// KK3: Buttontext schreiben und Action zuweisen
		btnSchummeln = new Button("Schummeln"); 
		btnSchummeln.setOnAction(new ButtonHandler());
		
		menschPunkteLabel.setText(Integer.toString(menschPunkte));
		computerPunkteLabel.setText(Integer.toString(computerPunkte));
		
		//in zwei Spalten anzeigen
		GridPane tempGrid = new GridPane();
		//und einf�gen, dabei werden die Koordinaten angegeben
		tempGrid.add(new Label("Menschen: "), 0 , 0 );
		tempGrid.add(menschPunkteLabel, 1, 0);
		tempGrid.add(new Label("Computer: "), 0, 1);
		tempGrid.add(computerPunkteLabel, 1 ,1);
		
		//KK2: Wer zieht grade - Label einf�gen
		tempGrid.add(zugLabel, 0, 2);
		//KK3: Den Schummelknopf ins Grid einf�gen
		tempGrid.add(btnSchummeln, 0, 3);
		
		feld.getChildren().add(tempGrid);
		return feld;
	}
	
	//das eigentliche Spielfeld erstellen
	private void kartenZeichnen(FlowPane feld) {
		int count = 0;
		for (int i = 0; i <= 41; i++) {
			//eine neue Karte erzeugen
			karten[i] = new MemoryKarte(bilder[count], count, this);
			//bei jeder zweiten Karte kommt auch ein neues Bild
			if ((i + 1) % 2 == 0)
				count++;
		}
		//die Karten werden gemischt 
		Collections.shuffle(Arrays.asList(karten));
		

		//und ins Spielfeld gesetzt
		for (int i = 0; i <= 41; i++) {
			feld.getChildren().add(karten[i]);
			//die Position der Karte setzen
			karten[i].setBildPos(i);
		}
	}
	
	//die Methode �bernimmt die wesentliche Steuerung des Spiels
	//Sie wird beim Anklicken einer Karte ausgef�hrt
	public void karteOeffnen(MemoryKarte karte) {
		//zum Zwischenspeichern der ID und der Position
		int kartenID, kartenPos;

		//die Karten zwischenspeichern
		paar[umgedrehteKarten]=karte;
		
		//die ID und die Position beschaffen
		kartenID = karte.getBildID();
		kartenPos = karte.getBildPos();
		
		//die Karte in das Ged�chtnis des Computers eintragen
		//aber nur dann, wenn es noch keinen Eintrag an der entsprechenden Stelle gibt
		if ((gemerkteKarten[0][kartenID] == -1)) 
			gemerkteKarten[0][kartenID] = kartenPos;
		else
			//wenn es schon einen Eintrag gibt 
			//und der nicht mit der aktuellen Position �bereinstimmt, dann haben wir die
			//zweite Karte gefunden
			//die wird dann in die zweite Dimension eingetragen
			if (gemerkteKarten[0][kartenID] != kartenPos) 
				gemerkteKarten[1][kartenID] = kartenPos;
		//umgedrehte Karten erh�hen
		umgedrehteKarten++;
		
		//KK3: Wenn eine Karte schon umgedreht wurde, darf man auch nicht schummeln
		btnSchummeln.setDisable(true);
		
		//sind 2 Karten umgedreht worden?
		if (umgedrehteKarten == 2) {
			//dann pr�fen wir, ob es ein Paar ist
			paarPruefen(kartenID);
			//den Timer erzeugen
			if (computerPunkte + menschPunkte == 21) {
				//KK1: Siegesdialog aufrufen und Punkte �bermitteln
				siegesDialog(menschPunkte, computerPunkte);
			} else { //Sonst Karten mit Timer schlie�en
				timer = new Timeline(new KeyFrame(Duration.millis(1000), new TimerHandler()));
				timer.play();
			}
		}
	}
	
	//die Methode dreht die Karten wieder auf die R�ckseite
	//bzw. nimmt sie aus dem Spiel
	private void karteSchliessen() {
		boolean raus = false;
		//ist es ein Paar?
		if (paar[0].getBildID() == paar[1].getBildID()) 
			raus = true;
		//wenn es ein Paar war, nehmen wir die Karten aus dem Spiel
		//sonst drehen wir sie nur wieder um
		paar[0].rueckseiteZeigen(raus);
		paar[1].rueckseiteZeigen(raus);
		//es ist keine Karte mehr ge�ffnet
		umgedrehteKarten = 0;
		//hat der Spieler kein Paar gefunden?

			//Normal weiterziehen
			if (raus == false) 
				//dann wird der Spieler gewechselt
				spielerWechseln();
			else
				//hat der Computer ein Paar gefunden?
				//dann ist er noch einmal an der Reihe
				if (spieler == 1)
					computerZug();
		
	}
	
	//die Methode pr�ft, ob ein Paar gefunden wurde
	private void paarPruefen(int kartenID) {
		if (paar[0].getBildID() == paar[1].getBildID()) {
			//die Punkte setzen
			paarGefunden();
			//die Karten aus dem Ged�chtnis l�schen
			gemerkteKarten[0][kartenID]=-2;
			gemerkteKarten[1][kartenID]=-2;
		}
	}

	//die Methode setzt die Punkte, wenn ein Paar gefunden wurde
	private void paarGefunden() {
		//spielt gerade der Mensch?
		if (spieler == 0) {
			menschPunkte++;
			menschPunkteLabel.setText(Integer.toString(menschPunkte));
		}
		else {
			computerPunkte++;
			computerPunkteLabel.setText(Integer.toString(computerPunkte));
		}
	}
	
	//die Methode wechselt den Spieler
	private void spielerWechseln() {
		//wenn der Mensch an der Reihe war,
		//kommt jetzt der Computer
		if (spieler == 0) {
			spieler = 1;
			btnSchummeln.setDisable(true);//KK3: Wenn Computer dran ist, kein Schummeln m�glich
			zugLabel.setText("Es zieht: Der Computer");//KK2:
			computerZug();
		}
		else {
			spieler = 0;
			btnSchummeln.setDisable(false);//KK3: Spieler ist dran, Schummeln aktiviert
			zugLabel.setText("Es zieht: Der Mensch");
		}
	}
	
	//die Methode setzt die Computerz�ge um
	private void computerZug() {
		int kartenZaehler = 0;
		int zufall = 0;
		boolean treffer = false;
		//zur Steuerung �ber die Spielst�rke
		if ((int)(Math.random() * spielstaerke) == 0) {
			//erst einmal nach einem Paar suchen
			//dazu durchsuchen wir das Array gemerkteKarten, bis wir in beiden Dimensionen
			//einen Wert finden
			while ((kartenZaehler < 21) && (treffer == false)) {
				//gibt es in beiden Dimensionen einen Wert gr��er oder gleich 0?
				if ((gemerkteKarten[0][kartenZaehler] >=0) &&  (gemerkteKarten[1][kartenZaehler] >=0)) {
					//dann haben wir ein Paar
					treffer = true;
					//die Vorderseite der Karte zeigen
					karten[gemerkteKarten[0][kartenZaehler]].vorderseiteZeigen();
					//und dann die Karte �ffnen
					karteOeffnen(karten[gemerkteKarten[0][kartenZaehler]]);
					//die zweite Karte auch
					karten[gemerkteKarten[1][kartenZaehler]].vorderseiteZeigen();
					karteOeffnen(karten[gemerkteKarten[1][kartenZaehler]]);
				}
				kartenZaehler++;
			}
		}
		//wenn wir kein Paar gefunden haben, drehen wir zuf�llig zwei Karten um
		if (treffer == false) {
			//solange eine Zufallszahl suchen, bis eine Karte gefunden wird, die noch im Spiel ist
			do {
				zufall = (int)(Math.random() * karten.length);
			} while (karten[zufall].isNochImSpiel() == false);
			//die erste Karte umdrehen
			//die Vorderseite der Karte zeigen
			karten[zufall].vorderseiteZeigen();
			//und dann die Karte �ffnen
			karteOeffnen(karten[zufall]);

			//f�r die zweite Karte m�ssen wir au�erdem pr�fen, ob sie nicht gerade angezeigt wird
			do {
				zufall = (int)(Math.random() * karten.length);
			} while ((karten[zufall].isNochImSpiel() == false) || (karten[zufall].isUmgedreht() == true));
			//und die zweite Karte umdrehen
			karten[zufall].vorderseiteZeigen();
			karteOeffnen(karten[zufall]);
		}
	}
	
	//die Methode liefert, ob Z�ge des Menschen erlaubt sind
	//die R�ckgabe ist false, wenn gerade der Computer zieht
	//oder wenn schon zwei Karten umgedreht sind
	//sonst ist die R�ckgabe true
	public boolean zugErlaubt() {
		boolean erlaubt = true;
		//zieht der Computer?
		if (spieler == 1)
			erlaubt = false;
		//sind schon zwei Karten umdreht?
		if (umgedrehteKarten == 2)
			erlaubt = false;
		return erlaubt;
	}
	
		
	
	//KK1: Mit "Alert" macht man schnell und einfach ein Popupfenster.
	public void siegesDialog(int menschpunkteFinal, int computerpunkteFinal) {
		//die Parameter �bernehmen die Punkte, sodass man vergleichen kann
		//Wer die meisten Punkte hat, gewinnt
		
		//Benutzerdefinierten Button erstellen
		ButtonType buttonSpielBeenden = new ButtonType("Spiel beenden");	
		//Alert erstellen, Titel und �berschrift setzen
		Alert siegesAlert = new Alert(AlertType.INFORMATION);
		siegesAlert.setTitle("Spiel vorbei!");
		siegesAlert.setHeaderText("Das Spiel ist vorbei!");
		//Der Button wird hier mit eingef�gt
		siegesAlert.getButtonTypes().setAll(buttonSpielBeenden);
		
		//Wer mehr Punkte hat, gewinnt. Das wird hier gepr�ft
		if(menschpunkteFinal > computerpunkteFinal) {
			siegesAlert.setContentText("Sie haben gewonnen, gl�ckwunsch!");
			zugLabel.setText(siegesAlert.getContentText()); //KK2: Siegestext auch nochmal im Label
		} else if (menschpunkteFinal < computerpunkteFinal) {
			siegesAlert.setContentText("Schade, der Computer hat gewonnen!");
			zugLabel.setText(siegesAlert.getContentText()); //KK2: Siegestext auch nochmal im Label
		}
		//KK1: setOnHidden() Event wird aufgerufen, nachdem Dialog "versteckt" wird (also nach OK Klick) 
		//L�st den Konflikt zwischen TimerEvent und showAndWait() 
		siegesAlert.setOnHidden(event -> Platform.exit());//Lambda Expression �bernimmt den OK Klick Part
		siegesAlert.show(); 
	}
}
