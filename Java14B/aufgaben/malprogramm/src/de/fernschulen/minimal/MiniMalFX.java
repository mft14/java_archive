package de.fernschulen.minimal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MiniMalFX extends Application{
	@Override
	public void start(Stage meineStage) throws Exception {
		//die Datei laden
		Parent root = FXMLLoader.load(getClass().getResource("sb_minimal.fxml"));		

		//die Szene erzeugen
		//an den Konstruktor werden der oberste Knoten und die Größe übergeben
		Scene meineScene = new Scene(root, 800, 600);
		
		//den Titel über stage setzen
		meineStage.setTitle("MiniMal");
		//die Szene setzen
		meineStage.setScene(meineScene);
		//Größenänderungen verhindern
		meineStage.setResizable(false);
		//und anzeigen
		meineStage.show();
	}
	
	public static void main(String[] args) {
		//der Start
		launch(args);
	}
}
