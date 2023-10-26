package java12btaschenrechner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXMLControllerJaNeinDialog {
	
	@FXML private Label messageLabel, detailsLabel;
	
	//KK Aufgabe 2
	//Ob Ja geklickt wurde, löse ich mit einem boolean "JaGeklickt"
	private boolean JaGeklickt = false;
	private Stage meineStage;
	//Klick auf JA
	@FXML protected void jaKlick(ActionEvent event) {
		setJaGeklickt(true);
		meineStage.close();
	}
	//Klick auf NEIN
	@FXML protected void neinKlick(ActionEvent event) {
		meineStage.close();
	}
	
	//KK: Getter für JaGeklickt Boolean
	public boolean isJaGeklickt() {
		return JaGeklickt;
	}
	
	//KK: Setter für JaGeklickt Boolean
	public void setJaGeklickt(boolean jaGeklickt) {
		JaGeklickt = jaGeklickt;
	}
	
	public void setStage(Stage meineStage) {
		this.meineStage = meineStage;
	}

	public void setUeberschrift(String ueberschrift) {
		messageLabel.setText(ueberschrift);
	}
	

	public void setInfoText(String infotext) {
		detailsLabel.setText(infotext);
	}
}
