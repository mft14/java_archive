package java15b;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLControllerEingabeDialog {
	//die beiden Labels
	//die Bezeichner stammen aus dem FXML-Beschreibung
	@FXML private Label messageLabel, detailsLabel;
	//für das Eingabefeld
	@FXML private TextField eingabeName;

	//für die Bühne
	private Stage meineStage;
	//für die Eingabe
	private String eingabe;
	
	//die Methode zum Schließen
	@FXML protected void okKlick(ActionEvent event) {
		//den Text kopieren
		eingabe = eingabeName.getText();
		//die Bühne und damit das Fenster schließen
		meineStage.close();
	}

    //die Methode setzt die Bühne auf den übergebenen Wert
	public void setStage(Stage meineStage) {
		this.meineStage = meineStage;
	}

	//die Überschrift setzen
	public void setUeberschrift(String ueberschrift) {
		messageLabel.setText(ueberschrift);	
	}

	//den Text setzen
	public void setInfotext(String infotext) {
		detailsLabel.setText(infotext);
	}	
	
	//die Eingabe beschaffen
	public String getEingabe() {
		return eingabe;
	}
}
