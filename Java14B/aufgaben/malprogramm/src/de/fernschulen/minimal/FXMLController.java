package de.fernschulen.minimal;

import com.sun.prism.Graphics;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class FXMLController {
    //f�r die Zeichenfl�che
    @FXML private Canvas zeichenflaeche;
    //f�r die Farbauswahl
    @FXML private ColorPicker farbauswahl;    

  	//f�r den Grafikkontext
  	private GraphicsContext gc;
    //f�r die Position
	private int xPos, yPos;
	//f�r das Werkzeug
	private int werkzeug;
	//f�r die Farbe
	private Color farbe;

	//die Methode zum Beenden
	@FXML protected void beendenKlick(ActionEvent event) {
		Platform.exit();
	}
	
	//die Methode setzt die Initialwerte
	//sie wird automatisch ausgef�hrt
    @FXML void initialize() {
		//die Standard-Farbe ist Schwarz
		farbe = Color.BLACK;
		farbauswahl.setValue(farbe);
		//das Standardwerkzeug ist die Linie
		werkzeug = 1;
		//den Grafikkontext beschaffen
		gc = zeichenflaeche.getGraphicsContext2D();
    }

    //f�r die Symbole mit dem Zeichenwerkzeug
    //gesetzt wird ein Wert f�r das jeweilige Werkzeug
    @FXML protected void linieKlick(ActionEvent event) {
    	werkzeug = 1;
    }

    @FXML protected void kreisKlick(ActionEvent event) {
    	werkzeug = 2;
    }
    
    @FXML protected void rechteckKlick(ActionEvent event) {
    	werkzeug = 3;
    }
    
    @FXML protected void kreisGefuelltKlick(ActionEvent event) {
    	werkzeug = 4;
    } //KK3: Gef�llter Kreis bekommt als werkzeug int die Nummer 4    
    
   	//die Methode f�r das Dr�cken der Maustaste
	@FXML protected void mausGedrueckt(MouseEvent e) {
		//die Koordinate als Startpunkt speichern
		//aber nur, wenn die linke Maustaste gedr�ckt wurde
		if (e.getButton() == MouseButton.PRIMARY) {
			xPos = (int)e.getX();
			yPos = (int)e.getY();
		}
	}

	//die Methode f�r das Loslassen der Maustaste
	@FXML protected void mausLos(MouseEvent e) {
		//wenn die linke Taste losgelassen wird, zeichnen wir von der alten 
		//zur aktuellen Position 
		//je nach Werkzeug wird etwas anders gezeichnet
		if (e.getButton() == MouseButton.PRIMARY) {
			//die Linie
			if (werkzeug == 1) 
				linie((int)e.getX(), (int)e.getY());
			//ein Kreis
			if (werkzeug == 2)
				kreis((int)(e.getX()-xPos), (int)(e.getY() - yPos));
			//ein Rechteck
			if (werkzeug == 3)
				rechteck((int)(e.getX()-xPos), (int)(e.getY() - yPos));
			//KK3: ein gef�llter Kreis
			if (werkzeug == 4)
				gefuellterKreis((int)(e.getX()-xPos), (int)(e.getY() - yPos));
		}
	}
	
	//die Methode setzt die neue Farbe
	@FXML protected void farbAuswahlZeigen() {
		farbe = farbauswahl.getValue();
		gc.setStroke(farbe);
	}

	//die Methode zeichnet ein Rechteck
	//die Breite und H�he werden als Parameter �bergeben
	protected void rechteck(int breite, int hoehe) {
		//die Figur zeichnen
		gc.strokeRect(xPos, yPos, breite, hoehe);
	}

	//die Methode zeichnet einen Kreis
	//die Breite und H�he des umgebenden Rechtecks werden als Parameter �bergeben
	protected void kreis(int breite, int hoehe) {
		gc.strokeOval(xPos, yPos, breite, hoehe);
	}
	
	//die Methode zeichnet eine Linie
	//Der Endpunkt wird als Parameter �bergeben
	public void linie(int x2, int y2) {
		gc.strokeLine(xPos, yPos, x2, y2);
	}
	
	//KK3: Ein gef�llter Kreis wird erstellt.
	//inklusive Farbe. Daf�r wird nur setFill() ben�tigt
	protected void gefuellterKreis(int breite, int hoehe) {
		gc.setFill(farbe);
		gc.fillOval(xPos, yPos, breite, hoehe);
	}
}
