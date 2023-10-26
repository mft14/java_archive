package java12beditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXMLControllerInfoDialog {
	//die beiden Labels
	//die Bezeichner stammen aus dem FXML-Beschreibung
	@FXML private Label messageLabel, detailsLabel;

	//für die Bühne
	private Stage meineStage; 
	
	//die Methode zum Schließen
	@FXML protected void okKlick(ActionEvent event) {
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
}
