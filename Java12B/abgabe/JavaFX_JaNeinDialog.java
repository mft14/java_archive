package java12btaschenrechner;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class JavaFX_JaNeinDialog extends Stage {

	private FXMLControllerJaNeinDialog meinController;
	
	public JavaFX_JaNeinDialog(Stage fenster) {
		super();
		initOwner(fenster);
		
		FXMLLoader meinLoader = new FXMLLoader(getClass().getResource("sb_janeindialog.fxml"));
		
		try {
			Parent root = meinLoader.load();
			meinController = meinLoader.getController();
			Scene meineScene = new Scene(root, 500, 150);
			setScene(meineScene);
			initModality(Modality.WINDOW_MODAL);
			initStyle(StageStyle.UTILITY);
			meinController.setStage(this);
			
		} catch (IOException e) {
			System.out.println("Die Datei konnte nicht geladen werden");
		}
		
	}
	
	public void setzeInfo(String titel, String ueberschrift, String infotext) {
		setTitle(titel);
		meinController.setUeberschrift(ueberschrift);
		meinController.setInfoText(infotext);
	}
	
	public void dialogZeigen() {
		showAndWait();
	}
	
	//KK Aufgabe 2 : Wurde Ja geklickt, zeigt diese Methode mit Rückgabewert true an
	public boolean wurdeJaGeklickt() {
		return meinController.isJaGeklickt();
	}
}
