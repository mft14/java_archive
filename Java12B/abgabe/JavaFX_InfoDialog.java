package java12btaschenrechner;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class JavaFX_InfoDialog extends Stage{
	//für den Controller
	private FXMLControllerInfoDialog meinController; 
	
	//der Konstruktor
	//er erhält das übergeordnete Fenster zugewiesen
	public JavaFX_InfoDialog(Stage fenster) {
		//den Konstruktor der übergeordneten Klasse aufrufen
		super();
		//den Besitzer zuweisen
		initOwner(fenster);
		//eine Instanz von FXMLLoader erzeugen
		FXMLLoader meinLoader = new FXMLLoader(getClass().getResource("sb_infodialog.fxml"));
		
		try {
			//die Datei laden
			Parent root = meinLoader.load();
			//den Controller beschaffen
			meinController = meinLoader.getController();
			//die Szene erzeugen
			//an den Konstruktor werden der oberste Knoten und die Größe übergeben
			Scene meineScene = new Scene(root, 500, 150);
			//die Szene setzen
			setScene(meineScene);
			//der Dialog soll modal angezeigt werden
			initModality(Modality.WINDOW_MODAL);
			//und nur ein Schließensymbol haben
			initStyle(StageStyle.UTILITY);
			//die Bühne durchreichen
			meinController.setStage(this);
		}
		catch (IOException e) {
			System.out.println("Die Datei konnte nicht geladen werden.");
		}		
	}
	
	//die Texte setzen
	public void setzeInfo(String titel, String ueberschrift, String infotext) {
		//den Titel setzen
		setTitle(titel);
		//Überschrift und Text über den Controller zuweisen
		meinController.setUeberschrift(ueberschrift);
		meinController.setInfotext(infotext);
	}

	//den Dialog anzeigen
	public void dialogZeigen() {
		//anzeigen und abwarten
		showAndWait();
	}
	
}
