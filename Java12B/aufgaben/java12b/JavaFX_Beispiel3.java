package java12b;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFX_Beispiel3 extends Application {

	@Override
	public void start(Stage meineStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("sb_beispiel01.fxml"));
		
		Scene meineScene = new Scene(root, 200, 250);
		meineStage.setTitle("JavaFX mit FXML");
		meineStage.setScene(meineScene);
		meineStage.show();
		
	}
	
	public static void main(String[] args) {
		//der Start
		launch(args);
	}

}
