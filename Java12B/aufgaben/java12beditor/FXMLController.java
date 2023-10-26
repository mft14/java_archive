package java12beditor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FXMLController {
	
	@FXML private TextArea editor;
	private Stage meineStage;
	
	
	@FXML protected void ladenKlick(ActionEvent event) {
		FileChooser oeffnenDialog = new FileChooser();
		oeffnenDialog.setTitle("Datei öffnen");
		oeffnenDialog.setInitialDirectory(new File(System.getProperty("user.home")));
		oeffnenDialog.getExtensionFilters().add(new ExtensionFilter("Textdateien", "*.txt"));
		File datei = oeffnenDialog.showOpenDialog(meineStage);
		if (datei != null)
			editor.setText(datenLesen(datei));
	}


	private String datenLesen(File dateiLesen) {
		//gelesen wird als int!!
		int zeichen = 0;
		StringBuilder text = new StringBuilder();
		//eine Instanz der Klasse FileReader mit der Datei erzeugen
		try (FileReader tempDatei = new FileReader(dateiLesen)) {
			//solange das Ende der Datei nicht erreicht ist, werden die Zeichen einzeln gelesen und
			//an einem StringBuilder angehängt
			zeichen = tempDatei.read();
			while (zeichen != -1) {
				//zeichen wird in den Typ char umgewandelt
				text.append((char)zeichen);
				zeichen = tempDatei.read();
			}
		}
		catch (IOException e) {
			//bei Problemen einen Dialog erzeugen
			JavaFX_InfoDialog meinDialog = new JavaFX_InfoDialog(meineStage);
			meinDialog.setzeInfo("Hinweis", "Bitte beachten", "Beim Laden ist ein Problem aufgetreten");
			meinDialog.dialogZeigen();
		}
		return (text.toString());
	}
	

	
	
	
	
	
}
