package java12beditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXMLControllerInfoDialog {
	//die beiden Labels
	//die Bezeichner stammen aus dem FXML-Beschreibung
	@FXML private Label messageLabel, detailsLabel;

	//f�r die B�hne
	private Stage meineStage; 
	
	//die Methode zum Schlie�en
	@FXML protected void okKlick(ActionEvent event) {
		//die B�hne und damit das Fenster schlie�en
		meineStage.close();
	}

    //die Methode setzt die B�hne auf den �bergebenen Wert
	public void setStage(Stage meineStage) {
		this.meineStage = meineStage;
	}

	//die �berschrift setzen
	public void setUeberschrift(String ueberschrift) {
		messageLabel.setText(ueberschrift);	
	}

	//den Text setzen
	public void setInfotext(String infotext) {
		detailsLabel.setText(infotext);
	}	
}
