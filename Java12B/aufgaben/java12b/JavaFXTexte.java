package java12b;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXTexte extends Application {

	@Override
	public void start(Stage meineStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("sb_javafxtexte.fxml"));
		
		Scene meineScene = new Scene(root, 200, 250);
		meineStage.setTitle("JavaFX mit Texte");
		meineStage.setScene(meineScene);
		meineStage.show();
		
	}
	
	public static void main(String[] args) {
		//der Start
		launch(args);
	}

}
