package java12b;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class FXMLController {
	
	@FXML private Label ausgabe;
	@FXML private Button btnDrehen;
	@FXML private Button btnTextGross;
	@FXML private Button btnTextKlein;
	@FXML private TextField tfEingabeTextAenderer;
	
	private int fontsize = 12;
	

	@FXML protected void schaltflaecheKlick(ActionEvent event) {
		Platform.exit();
	}
	
	@FXML protected void textSetzen(ActionEvent event) {
		ausgabe.setText("Ich werde gef�llt");
		ausgabe.setRotate(45);
	}
	
	@FXML protected void tooltip() {
		ausgabe.setScaleY(2);
		ausgabe.setScaleX(2);
	}
	@FXML protected void tooltipAus() {
		ausgabe.setScaleY(1);
		ausgabe.setScaleX(1);
	}
	
	@FXML protected void drehAnimation() {
		RotateTransition autoRotation = new RotateTransition(Duration.millis(1000), ausgabe);
		autoRotation.setByAngle(360);
		autoRotation.setCycleCount(4);
		autoRotation.play();
	}
	
	/////////////////////////
	//JavaFX Texte
	
	@FXML protected void textGross() {
		System.out.println("text gr��er");
		if (fontsize < 20) {
			tfEingabeTextAenderer.setFont(new Font("System", fontsize++));
		} else {
			System.out.println("Schrift zu gro�");
		}
	}
	
	@FXML protected void textKlein() {
		System.out.println("text kleiner");
		tfEingabeTextAenderer.setFont(new Font("System", fontsize--));
	}
	
}
