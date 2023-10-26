package java12btaschenrechner;

import java.text.DecimalFormat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLController {
	
	private Stage meineStage;
	
	@FXML private TextField tfZahl1;
	@FXML private TextField tfZahl2;

	@FXML private RadioButton rbAddieren;
	@FXML private RadioButton rbSubtrahieren;
	@FXML private RadioButton rbMultiplizieren;
	@FXML private RadioButton rbDividieren;
	
	@FXML private Label lblErgebnis;
	
	@FXML protected void berechnenKlick(ActionEvent event) { //Beim Klick auf berechnen führe diese Methode aus

		try { //Mit NumberFormatException falsche Eingaben verhindern
			//DecimalFormat sorgt für zwei Nullstellen hinter dem Komma
			DecimalFormat formatFolge = new DecimalFormat("0.##");
			//das Format anwenden, das Ergebnis ist eine Zeichenkette
			
			double zahl1, zahl2, ergebnis; 
			//Zahlen in Variablen abspeichern
			zahl1 = Double.parseDouble(tfZahl1.getText());
			zahl2 = Double.parseDouble(tfZahl2.getText());
			
			
			//Je nach Auswahl der Rechenoperation, springe in die If Abzweigung
			if (rbAddieren.isSelected()) { //Addieren ausgewählt
				ergebnis = zahl1 + zahl2;
				lblErgebnis.setText(formatFolge.format(ergebnis));
			}
			if (rbSubtrahieren.isSelected()) {//Subraktion ausgewählt
				ergebnis = zahl1 - zahl2;
				lblErgebnis.setText(formatFolge.format(ergebnis));
			}
			if (rbMultiplizieren.isSelected()) { //Multiplikation ausgewählt
				ergebnis = zahl1 * zahl2;
				lblErgebnis.setText(formatFolge.format(ergebnis));
			}
			if (rbDividieren.isSelected()) { //Division ausgewählt
				if(zahl2 != 0) {
					ergebnis = zahl1 / zahl2;
					lblErgebnis.setText(formatFolge.format(ergebnis));
				} else { //Durch 0 geteilt gibt ebenfalls eine Fehlermeldung
					throw new NumberFormatException("Durch 0 geteilt");
				}
			}

		} catch (NumberFormatException e) {
			//Werden ungültige Zahlen eingegeben, so erscheint eine simple Fehlermeldung
			//InfoDialog aus der Lektion Java12B kopiert
			JavaFX_InfoDialog infodialog = new JavaFX_InfoDialog(meineStage);
			infodialog.setzeInfo("Fehler", "Fehler bei der Eingabe", "Überprüfen Sie, ob sie richtige Zahlen eingegeben haben!");
			infodialog.dialogZeigen();
		}

	}
	
	//KK Aufgabe 2
	@FXML protected void zuruecksetzenKlick(ActionEvent event) {
		
		//KK Aufgabe 2: Es wird ein normaler Dialog erstellt bloß mit einem Ja / Nein
		JavaFX_JaNeinDialog janeindialog = new JavaFX_JaNeinDialog(meineStage);
		janeindialog.setzeInfo("Zurücksetzen", "Zurücksetzen der Eingabefelder", "Sind sie sicher?");
		janeindialog.dialogZeigen();
		
		//Jetzt steht dem Programmierer aber die Methode "JaGeklickt" zur Verfügung,
		//sodass man einen boolschen true oder false Wert erhält, je nachdem, was geklickt wurde
		if(janeindialog.wurdeJaGeklickt() == true) { //Wenn ja, resette die Felder, sonst tue nichts
			lblErgebnis.setText("...");
			rbAddieren.setSelected(true);
			tfZahl1.setText("");
			tfZahl2.setText("");
		}
	}
	
	@FXML protected void beendenKlick(ActionEvent event) {
		
		//KK Aufgabe 2: Aus Spaß nochmal den Bestätigungsdialog beim Beenden einfügen. Selbes Spiel wie oben
		JavaFX_JaNeinDialog janeindialog = new JavaFX_JaNeinDialog(meineStage);
		janeindialog.setzeInfo("Beenden", "Programm beenden", "Wollen Sie das Programm beeenden?");
		janeindialog.dialogZeigen();
		if(janeindialog.wurdeJaGeklickt() == true) {
			Platform.exit(); //Beendet das Programm
		}
	}
	
    //die Methode setzt die Bühne auf den übergebenen Wert
	public void setStage(Stage meineStage) {
		this.meineStage = meineStage;
	}	
	
}
